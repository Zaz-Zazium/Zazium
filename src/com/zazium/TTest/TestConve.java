package com.zazium.TTest;

import com.zazium.Misc.Checksums;
import com.zazium.Uses.Usermain;

import java.util.ArrayList;
import java.util.Random;

public class TestConve {
    public static ArrayList generateEntropy(){
        ArrayList<Integer> entropy1 = new ArrayList();
        ArrayList<Integer> entropy2 = new ArrayList();
        ArrayList<Integer> entropy3 = new ArrayList();
        ArrayList<Integer> entropy4 = new ArrayList();

        int min = 0;
        int max = 1;
        String entropyString = "";
        String checksumVal = "";

        for (int i = 0; i < 32; i++) {
            Random rand = new Random();
            int randomNum = rand.nextInt((max - min) + 1) + min;
            entropy1.add(randomNum);
            entropyString += Integer.toString(randomNum);
        }
        checksumVal += Integer.toString(Checksums.checksumz(entropy1));

        for (int i = 0; i < 32; i++) {
            Random rand = new Random();
            int randomNum = rand.nextInt((max - min) + 1) + min;
            entropy2.add(randomNum);
            entropyString += Integer.toString(randomNum);
        }
        checksumVal += Integer.toString(Checksums.checksumz(entropy2));

        for (int i = 0; i < 32; i++) {
            Random rand = new Random();
            int randomNum = rand.nextInt((max - min) + 1) + min;
            entropy3.add(randomNum);
            entropyString += Integer.toString(randomNum);
        }
        checksumVal += Integer.toString(Checksums.checksumz(entropy3));

        for (int i = 0; i < 32; i++) {
            Random rand = new Random();
            int randomNum = rand.nextInt((max - min) + 1) + min;
            entropy4.add(randomNum);
            entropyString += Integer.toString(randomNum);
        }
        checksumVal += Integer.toString(Checksums.checksumz(entropy4));

        String entropy = entropyString+checksumVal;
        //b64Encode(entropy);
        //String aa = Base64.encodeBase64String(entropy.getBytes());
        //System.out.println("bas54: " + aa);

        return Usermain.seed_phrase(entropy);
    }


    public static ArrayList<String> generateKeypair() throws Exception {
        //System.out.println(generateEntropy());
        return generateEntropy();
    }

}
