package data_structure.ratelimiter;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import org.redisson.api.RScript;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * <a href="https://mp.weixin.qq.com/s/fsTBXAKacoeLx2NUBYaFsQ">四种限流</a>
 */
public abstract class BaseRateLimiter implements RateLimiter {
    private final String name;
    private final int rate;
    private final Duration duration;
    private final RedissonClient redissonClient;

    public BaseRateLimiter(String name, int rate, Duration duration) {
        this.name = name;
        this.rate = rate;
        this.duration = duration;
        this.redissonClient = RedissonConfig.getInstance();
    }

    @Override
    public void acquire() {
        RScript script = redissonClient.getScript(StringCodec.INSTANCE);

        long waitTime;

        Object[] args = getArgs();
        List<Object> params = new ArrayList<>(args.length);
        Collections.addAll(params, args);

        do {
            waitTime = script.eval(
                    RScript.Mode.READ_WRITE,
                    getLuaScript(),
                    RScript.ReturnType.INTEGER,
                    Collections.singletonList(name),
                    params.toArray()
            );

            if (waitTime > 0) LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(waitTime));
        } while (waitTime != 0);
    }

    protected abstract String getLuaScript();

    protected Object[] getArgs() {
        return new Object[]{rate, duration.toMillis(), 1};
    }

    public static void main(String[] args) {
        // String file = "/home/gqh/下载/filter2.log";
        // var lines = FileUtil.readUtf8Lines(file);
        // for (var i = 0; i < lines.size(); i++) {
        //     if (i >= 63) {
        //         System.out.println("\"" + lines.get(i) + "\",");
        //     }
        // }

        var lazyBeans = CollUtil.newHashSet(
                "tomcatServletWebServerFactory",
                "dataSourceScriptDatabaseInitializer",
                "metaEntityManagerFactory",
                "runtimeEntityManagerFactory",
                "jpaMappingContext",
                "configurationRepository",
                "queryOpFactory",
                "serviceFactory",
                "endpointObjectMapper",
                "jackson2ObjectMapperBuilderCustomizer",
                "commonJackson2ObjectMapperBuilderCustomizer",
                "mybatisConfigurationCustomizer",
                "ossAdapter",
                "orgStructMdRepo",
                "purPrItemTrRepo",
                "purPoHeadTrRepo",
                "purPoItemTrRepo",
                "slsSoHeaderTrRepo",
                "slsSoItemTrRepo",
                "extWmsTransferHeadTrRepo",
                "extWmsTransferItemTrRepo",
                "extWmsTransferBoxTrRepo",
                "sgTrantorContextHelper",
                "delDnITrRepo",
                "warehouseMasterMdRepo",
                "settItemTrRepo",
                "settDocTrRepo",
                "mgSettItemConvertHelper",
                "offlineOrderTrRepo",
                "sgAdvancedShippingNoticeTrRepo",
                "omsReturnOrderHeadTrRepo",
                "genAssetsAccountBalanceAuditTempRepo",
                "genComTypeCfRepo",
                "s3FormUploadOssAdapter",
                "operationLogClientProcessor",
                "branchRepository",
                "systemModuleRepo",
                "consumerCustomizer",
                "sortCustomizer",
                "pageableCustomizer",
                "requestMappingHandlerAdapter",
                "commonsDbcp2PoolDataSourceMetadataProvider",
                "hikariPoolDataSourceMetadataProvider",
                "TSchedulerConsumerCustomizer",
                "managementServletContext",
                "eagerTaskExecutorMetrics",
                "freeMarkerConfigurer",
                "restTemplateCustomizer",
                "loadBalancedRestTemplateInitializerDeprecated",
                "purPartnerLinkTrRepo",
                "extWmsTransportHeadTrRepo",
                "extWmsPalletHeadTrRepo",
                "genMatCharaLinkMdRepo",
                "orgInvOrgCfRepo",
                "orgSlsOrgCfRepo",
                "orgPurOrgCfRepo",
                "genContactPersonMdRepo",
                "genPartnerProcedureHeadCfRepo",
                "genAttachmentProcedureHeadCfRepo",
                "billingTemplateLogisticsSpendLineRepo",
                "operationLogClientProcessor",
                "operationLogClient",
                "branchRepository",
                "mgFixedRewardTrRepo",
                "serviceItemCfRepo",
                "scwInboundUnloadTemplateItemCfRepo",
                "scwInboundArrangeTemplateItemCfRepo",
                "scwInboundCheckTemplateItemCfRepo",
                "scwInboundOnShelfTemplateItemCfRepo",
                "scwInboundIncrementTemplateItemCfRepo",
                "scwInboundReturnTemplateItemCfRepo",
                "scwInboundUnpackingTemplateItemCfRepo",
                "scwInboundReshelfTemplateItemCfRepo",
                "scwStorageTemplateItemCfRepo",
                "dataSourceScriptDatabaseInitializer",
                "metaEntityManagerFactory",
                "runtimeEntityManagerFactory",
                "jpaMappingContext",
                "configurationRepository",
                "serviceFactory",
                "ossAdapter",
                "mgSettItemConvertHelper",
                "sgTrantorContextHelper",
                "operationLogClientProcessor",
                "operationLogClient",
                "branchRepository",
                "freeMarkerConfigurer",
                "ossTemplate"
        );

        for (var lazyBean : lazyBeans) {
            System.out.println("\"" + lazyBean + "\",");
        }
    }
}
