package leetcode.list.T3500_3549;

import java.util.LinkedHashSet;
import java.util.Objects;

public class T3508 {
    static class Router {

        LinkedHashSet<Packet> packets;
        int limit;

        public Router(int memoryLimit) {
            this.packets = new LinkedHashSet<>(memoryLimit);
            this.limit = memoryLimit;
        }

        public boolean addPacket(int source, int destination, int timestamp) {
            Packet packet = new Packet(source, destination, timestamp);
            packets.remove(packet);
            return packets.add(packet);
        }

        // public int[] forwardPacket() {
        //
        // }
        //
        // public int getCount(int destination, int startTime, int endTime) {
        //
        // }

        static class Packet {
            int src;
            int dest;
            int time;

            public Packet(int s, int d, int t) {
                this.src = s;
                this.dest = d;
                this.time = t;
            }

            @Override
            public boolean equals(Object o) {
                Packet packet = (Packet) o;
                return this.src == packet.src && this.dest == packet.dest && this.time == packet.time;
            }

            @Override
            public int hashCode() {
                int result = 1;
                result = 31 * result + src;
                result = 31 * result + dest;
                result = 31 * result + time;
                return result;
            }
        }
    }
}
