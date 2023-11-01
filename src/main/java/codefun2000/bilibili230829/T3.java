package codefun2000.bilibili230829;

import java.util.Arrays;

public class T3 {
    public static void main(String[] args) {
        int[] a = {24,32,17,58,3,76};
        selectSort(a);
    }

    public static void selectSort(int[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            int max = arr[i];
            int maxIdx = i;
            for (int j = i + 1 ; j < arr.length; j++){
                if (max < arr[j]){
                    max = arr[j];
                    maxIdx = j;
                }
            }
            if (maxIdx != i) {
                arr[maxIdx] = arr[i];
                arr[i] = max;
            }
            System.out.println(Arrays.toString(arr));
        }
    }
}
