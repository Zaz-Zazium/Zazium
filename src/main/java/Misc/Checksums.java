package Misc;

import java.util.ArrayList;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

public class Checksums {
    public static int checksumz(ArrayList<Integer> a) {
        int current = a.get(0) + a.get(1);
        if (current == 2) {
            current = 0;
        }
        for (int i = 2; i < 32; i++) {
            current = current + a.get(i);
            if (current == 2) {
                current = 0;
            }
        }
        System.out.println("current: " + current);
        return current;
    }

    public static ArrayList<Integer> checksumers(ArrayList<Integer> a) {
        ArrayList<Integer> returnCk = new ArrayList<>();
        int min = 0;
        int max = 1;
        int it = 32;
        int ini = 2;
        for(int k = 0; k < 4; k++){
            int current = a.get(min) + a.get(max);
            if (current == 2) {
                current = 0;
            }
            for (int i = ini; i < it; i++) {
                current = current + a.get(i);
                if (current == 2) {
                    current = 0;
                }
            }
            returnCk.add(current);
            min += 32;
            max += 32;
            it += 32;
            ini += 32;
        }
        return returnCk;
    }
}
