package com.zazium.Hashes;


import java.math.BigInteger;
import java.util.ArrayList;

public class Sha256 {
    // take msg
    // breakdown msg for each char in int
    // convert for each char to 8 bit binary
    static int[] rt2 = {2,3,5,7,11,13,17,19};
    static int[] rt3 = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,
                        101,103,107,109,113,127,131,137,139,149,151,157,163,167,173,179,181,191,
                        193,197,199,211,223,227,229,233,239,241,251,257,263,269,271,277,281,283,
                        293,307,311};

    public static ArrayList<String> rt2s(){
        //2^1/2 - 2^1/2 * 2^32
        ArrayList<String> rt = new ArrayList<>();
        for (int i = 0; i < rt2.length; i++){
            rt.add(addZeros(String.format(Long.toBinaryString((long)((Math.sqrt(rt2[i]) - (int)Math.sqrt(rt2[i]))*Math.pow(2, 32)))),32));
        }
        return rt;
    }

    public static ArrayList<String> rt3s(){
        //2^1/3 - 2^1/3 * 2^32
        ArrayList<String> rt = new ArrayList<>();
        for (int i = 0; i < rt3.length; i++){
            rt.add(addZeros(String.format(Long.toBinaryString((long)((Math.sqrt(rt3[i]) - (int)Math.sqrt(rt3[i]))*Math.pow(2, 32)))),32));
        }
        return rt;
    }


    public static void main(String[] args) {
        hashes("123");

    }

    public static void hashes(String msg){
        ArrayList<String> rt22 = rt2s();
        ArrayList<String> rt33 = rt3s();

        String message = "";
        for (int i = 0; i < msg.length(); i++){
            message += String.format("%08d", Integer.parseInt(Integer.toBinaryString((int) msg.charAt(i)))); //msg to binary
        }
        String msgLen = String.format("%064d", Integer.parseInt(Integer.toBinaryString((int) message.length()))); //msg length in binary
        int chunkno = chunkNo(msg); //chuncks required

        message += "1";
        int padding = chunkno*512-(message.length()+64);

        for (int i = 0; i < padding; i++){
            message += "0";
        }
        message += msgLen;

        int min = 0;
        int max = 512;
        ArrayList<String> chunks = new ArrayList<>();
        for (int i = 0; i < chunkno; i++){
            chunks.add(message.substring(min,max)); //split into chunks of 512bit
            min += 512;
            max += 512;
        }

        //process the message in successive 512bit chunks:
        for (int i = 0; i < chunkno; i++){
            String newMsg = chunks.get(i);
            min = 0;
            max = 32;

            ArrayList<String> msgChunks = new ArrayList<>();
            for (int j = 0; j < 16; j++){
                msgChunks.add(newMsg.substring(min,max));
                min = 0;
                max = 32;
            }

            //
            //
            //
            for (int j = 0; j < 16; j++){

            }
            //
            //
            //

            for (int j = 16; j < 64; j++){
                //int mlen = msgChunks.size();
                String s0 = sig0(msgChunks.get(msgChunks.size()-15));
                String s1 = sig1(msgChunks.get(msgChunks.size()-2));
                String addup = adder(msgChunks.get(msgChunks.size()-16), s0, msgChunks.get(msgChunks.size()-7), s1);
                msgChunks.add(addup);

            }
            String a = rt22.get(0);
            String b = rt22.get(1);
            String c = rt22.get(2);
            String d = rt22.get(3);
            String e = rt22.get(4);
            String f = rt22.get(5);
            String g = rt22.get(6);
            String h = rt22.get(7);

            for (int j = 0; j < 64; j++){
                String S1 = sigma1(e);
                String ch = cho(e,f,g);
                String temp1 = adders(Long.parseLong(h, 2) + Long.parseLong(S1, 2) +
                        Long.parseLong(ch, 2) + Long.parseLong(rt33.get(j), 2) +
                        Long.parseLong(msgChunks.get(j), 2));

                String S0 = sigma0(a);
                String maj = mj(a,b,c);
                String temp2 = adders(Long.parseLong(S0, 2) + Long.parseLong(maj, 2));

                h = g;
                g = f;
                f = e;
                e = adders(Long.parseLong(d, 2) + Long.parseLong(temp1, 2));
                d = c;
                c = b;
                b = a;
                a = adders(Long.parseLong(temp1, 2) + Long.parseLong(temp2, 2));

            }
            rt22.set(0, adders(Long.parseLong(rt22.get(0), 2) + Long.parseLong(a, 2)));
            rt22.set(1, adders(Long.parseLong(rt22.get(1), 2) + Long.parseLong(b, 2)));
            rt22.set(2, adders(Long.parseLong(rt22.get(2), 2) + Long.parseLong(c, 2)));
            rt22.set(3, adders(Long.parseLong(rt22.get(3), 2) + Long.parseLong(d, 2)));
            rt22.set(4, adders(Long.parseLong(rt22.get(4), 2) + Long.parseLong(e, 2)));
            rt22.set(5, adders(Long.parseLong(rt22.get(5), 2) + Long.parseLong(f, 2)));
            rt22.set(6, adders(Long.parseLong(rt22.get(6), 2) + Long.parseLong(g, 2)));
            rt22.set(7, adders(Long.parseLong(rt22.get(7), 2) + Long.parseLong(h, 2)));


        }
        String digest = "";
        String hexStr = "";
        for (int j = 0; j < 8; j++){
            digest += rt22.get(j);
            long decimal = Long.parseLong(rt22.get(j),2);
            hexStr += Long.toString(decimal,16);
        }
        //long decimal = Long.parseLong(digest,2);
        //String hexStr = Long.toString(decimal,16);
        String hexString = new BigInteger(digest, 2).toString(16);

        System.out.println(digest);
        System.out.println(hexString);
//        digestRes = hex(int(digest, 2))
//        digestOb = digestRes[2:]
//
//        if len(digestOb) < 64:
//        digestOb = "0" + digestOb
//
//    # print(digest)
//    # print(len(digest))
//    # print(hex(int(digest, 2)))
//    #return hex(int(digest, 2))
//        return digestOb

    }

    public static int chunkNo(String msg){
        int chunks = 1;
        int bsize = 447;

        if(msg.length() <= bsize){
            chunks = 1;
        }else if(msg.length() >= bsize+1){
            while(true){
                bsize += 512;
                chunks += 1;
                if(msg.length() <= bsize){
                    break;
                }
            }
        }
        return chunks;
    }

    public static String sig0(String bits){
        String a = rotr(bits,7);
        String b = rotr(bits,18);
        String c = shr(bits,3);

        String res = xor(a,b,c);
        return res;
    }

    public static String sig1(String bits){
        String a = rotr(bits,17);
        String b = rotr(bits,19);
        String c = shr(bits,10);

        String res = xor(a,b,c);
        return res;
    }

    public static String sigma0(String bits){
        String a = rotr(bits,2);
        String b = rotr(bits,13);
        String c = rotr(bits,22);

        String res = xor(a,b,c);
        return res;
    }

    public static String sigma1(String bits){
        String a = rotr(bits,6);
        String b = rotr(bits,11);
        String c = rotr(bits,25);

        String res = xor(a,b,c);
        return res;
    }

    public static String xor(String a, String b, String c){
        String res = "";
        for(int i = 0; i < a.length(); i++){
            if(a.substring(i).equals("1") && b.substring(i).equals("1")){
                res += "1";
            }else if(b.substring(i).equals("1") && c.substring(i).equals("1")){
                res += "1";
            }else if(a.substring(i).equals("1") && c.substring(i).equals("1")){
                res += "1";
            }else{
                res += "0";
            }
        }
        return res;
    }

    public static String adder(String a, String b, String c, String d){
        String res = "";
        long aint = Long.parseLong(a, 2);
        long bint = Long.parseLong(b, 2);
        long cint = Long.parseLong(c, 2);
        long dint = Long.parseLong(d, 2);
        long r = aint + bint + cint + dint;

        String binr = Long.toBinaryString((long) r);
        if(binr.length() == 32){
            res = binr;
        }else if(binr.length() >= 32){
            res = Long.toBinaryString((long) (r % Math.pow(2,32)));
        }

        if(res.length() < 32){
            res = addZeros(res,32);
        }
        return res;
    }

    public static String adders(long a){
        String res = "";
        long r = a;

        //String binr = String.format("%032d", Long.parseLong(Long.toBinaryString((long) r)));
        String binr = Long.toBinaryString((long) r);
        if(binr.length() == 32){
            res = binr;
        }else if(binr.length() >= 32){
            res = Long.toBinaryString((long) (r % Math.pow(2,32)));
        }

        if(res.length() < 32){
            res = addZeros(res,32);
        }
        return res;
    }

    public static String rotr(String a, int rotnumber){
        for(int i = 0; i < rotnumber; i++){
            String last_char = a.substring(a.length()-1);
            a = a.substring(0,a.length()-1);
            a = last_char + a;
        }
        return a;
    }

    public static String shr(String a, int rotnumber){
        for(int i = 0; i < rotnumber; i++){
            a = a.substring(0,a.length()-1);
            a = "0" + a;
        }
        return a;
    }

    public static String cho(String a, String b, String c){
//    #use 'a' input to determine whether to take 'b' or 'c'
        String res = "";
        for(int i = 0; i < a.length(); i++){
            if(a.substring(i).equals("1")){
                res += b.substring(i);
            }else if(a.substring(i).equals("0")){
                res += c.substring(i);
            }
        }
        return res;
    }

    public static String mj(String a, String b, String c){
//    #take majority input value
        String res = "";
        for(int i = 0; i < a.length(); i++){
            if((a.substring(i).equals("1") && b.substring(i).equals("1")) ^
                    (a.substring(i).equals("1") && c.substring(i).equals("1")) ^
                    (b.substring(i).equals("1") && c.substring(i).equals("1"))){
                res += "1";
            }else{
                res += "0";
            }
        }
        return res;
    }

    public static String addZeros(String msg, int newLen){
        for(int i = msg.length(); i < newLen; i++){
            msg = "0" + msg;
        }
        return msg;
    }

}
