package Uses;

import Misc.Checksums;
import Misc.Conv;
import Misc.Fileread;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import org.apache.commons.codec.digest.DigestUtils;

public class Usermain {
    static Wallet userWallet = new Wallet();

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);



//        while(true){
//            System.out.println("1) Use existing Keypair");
//            System.out.println("2) Create new Keypair");
//            int a = scan.nextInt();
//            if(a == 1){
//                System.out.println("Enter private key");
//                String pkey = scan.nextLine();
//            }else if(a==2){
//                ArrayList<Integer> rSeed = new ArrayList<>();
//                SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
//                byte[] aa = random.generateSeed(16);
//                for (int i = 0; i < aa.length; i++) {
//                    rSeed.add(Byte.toUnsignedInt(aa[i]));
//                }
//                toBinary(rSeed);
//                System.out.println("Here is your new private seed phrase key:");
//                System.out.println("**************output**************");
//                System.out.println(userWallet.getMnemonic());
//                System.out.println(userWallet.getPrivateAddr());
//                System.out.println();
//                System.out.println("Here is your new public key:");
//                System.out.println("**************output**************");
//                System.out.println(userWallet.getWalletId());
//                System.out.println(userWallet.getPublicAddr());
//                walletAccess();
//            }
//        }
    }

    public static void walletAccess() {
        System.out.println("******************************");
        System.out.println("**      Wallet Access       **");
        System.out.println("******************************");
        //view balance
        // outputs [ balance]
//
        //send to a user
        // inputs [address, amount]
//
        //receive from a user
        // outputs [address]
//
        //transcation history
    }

    public static ArrayList<String> seed_phrase(String entropy) {
        ArrayList<String> arr_entropy = new ArrayList<>();
        ArrayList<Integer> decimalValue = new ArrayList<>();
        ArrayList<String> wordslist = Fileread.wordslist();
        ArrayList<String> seed_words = new ArrayList<>();

        int min = 0;
        int max = 11;
        for (int i = 0; i < 12; i++) {
            arr_entropy.add(entropy.substring(min, max));
            min += 11;
            max += 11;
            decimalValue.add(Integer.parseInt(arr_entropy.get(i), 2));
            seed_words.add(wordslist.get(decimalValue.get(i)));
        }
        return seed_words;
    }

    public static String toBinary(ArrayList<Integer> toBin) {
        String converted = "";
        ArrayList<Integer> w1 = new ArrayList<>();

        String a = "";
        for (int i = 0; i < toBin.size(); i++) {
            if (toBin.get(i) >= 128) {
                a += 1;
                toBin.set(i, toBin.get(i) - 128);
                w1.add(1);
            } else {
                a += 0;
                w1.add(0);
            }
            if (toBin.get(i) >= 64) {
                a += 1;
                toBin.set(i, toBin.get(i) - 64);
                w1.add(1);
            } else {
                a += 0;
                w1.add(0);
            }
            if (toBin.get(i) >= 32) {
                a += 1;
                toBin.set(i, toBin.get(i) - 32);
                w1.add(1);
            } else {
                a += 0;
                w1.add(0);
            }
            if (toBin.get(i) >= 16) {
                a += 1;
                toBin.set(i, toBin.get(i) - 16);
                w1.add(1);
            } else {
                a += 0;
                w1.add(0);
            }
            if (toBin.get(i) >= 8) {
                a += 1;
                toBin.set(i, toBin.get(i) - 8);
                w1.add(1);
            } else {
                a += 0;
                w1.add(0);
            }
            if (toBin.get(i) >= 4) {
                a += 1;
                toBin.set(i, toBin.get(i) - 4);
                w1.add(1);
            } else {
                a += 0;
                w1.add(0);
            }
            if (toBin.get(i) >= 2) {
                a += 1;
                toBin.set(i, toBin.get(i) - 2);
                w1.add(1);
            } else {
                a += 0;
                w1.add(0);
            }
            if (toBin.get(i) >= 1) {
                a += 1;
                toBin.set(i, toBin.get(i) - 1);
                w1.add(1);
            } else {
                a += 0;
                w1.add(0);
            }
        }
        a += Checksums.checksumers(w1).get(0) + "" + Checksums.checksumers(w1).get(1) + "" + Checksums.checksumers(w1).get(2) + "" + Checksums.checksumers(w1).get(3);

        ArrayList<String> qwerty = seed_phrase(a);
        userWallet.setMnemonic(qwerty);

        bin2Str(a);
        return converted;
    }

    public static String bin2Str(String bin) {
        String res = "";
        int it = bin.length() / 4;
        int min = 0;
        int max = 4;

        for (int i = 0; i < it; i++) {
            String sbin = bin.substring(min,max);
            int here = Integer.parseInt(sbin, 2);
            String hexStr = Integer.toString(here, 16);
            res += hexStr;
            min += 4;
            max += 4;
        }
        userWallet.setPrivateAddr(res);
        publicK(userWallet.getPrivateAddr());
        walletid(publicK(userWallet.getPublicAddr()));
        return "";
    }

    public static String publicK(String privateK){
        String publick = DigestUtils.sha256Hex(privateK);
        userWallet.setPublicAddr(publick);
        return publick;
    }

    public static String walletid(String publick){
        String wallid = DigestUtils.sha256Hex(publick);
        userWallet.setWalletId(wallid);
        return publick;
    }


}