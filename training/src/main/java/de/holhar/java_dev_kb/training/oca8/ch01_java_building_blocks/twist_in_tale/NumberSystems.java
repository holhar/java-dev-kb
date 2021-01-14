package de.holhar.java_dev_kb.training.oca8.ch01_java_building_blocks.twist_in_tale;

public class NumberSystems {

    public static void main(String args[]) {
        int baseDecimal = 267;
        int octVal = 0413; // 267
        int hexVal = 0x10B; // 267
        int binVal = 0b100001011; // 267

        System.out.println(baseDecimal + octVal); // should print 534
        System.out.println(hexVal + binVal); // should print 534
    }

    private void correctUsageOfUnderscores() {
        long var1 = 0_100_267_760;
        long var2 = 0x4_13; // 0_x_4_13;
        long var3 = 0b1_001_100; // 0b_x10_BA_75;
        long var4 = 0b1_0000_10_11; // 0b_10000_10_11;
        long var5 = 0xa10_AF_75; // 0xa10_AG_75;
        long var6 = 0x1_0000_10;
        long var7 = 100__12_12;
    }
}
