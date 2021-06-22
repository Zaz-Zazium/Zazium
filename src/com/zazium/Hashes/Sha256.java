package com.zazium.Hashes;


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

    public static void main(String[] args) {
        //2^1/2 - 2^1/2 * 2^32

        //int e = (int)((Math.sqrt(2) - (int)Math.sqrt(2))*Math.pow(2, 32));
        //String df = String.format(Integer.toBinaryString(e));
        //String qq = addZeros(String.format(Integer.toBinaryString((int)((Math.sqrt(2) - (int)Math.sqrt(2))*Math.pow(2, 32)))),32);


        //System.out.println(e);
        //System.out.println(df);
        //System.out.println(qq);
        //System.out.println(qq.length());

        ArrayList<String> art = rt2s();
        for(int i = 0; i < art.size(); i++){
            System.out.println(art.get(i));
        }
        //hashes("abc");

    }

    public static void hashes(String msg){
        String message = "";
        for (int i = 0; i < msg.length(); i++){
            message += String.format("%08d", Integer.parseInt(Integer.toBinaryString((int) msg.charAt(i))));
        }
        String msgLen = String.format("%064d", Integer.parseInt(Integer.toBinaryString((int) message.length())));
        int chunkno = chunkNo(msg);

        message += "1";
        int padding = chunkno*512-(message.length()+64);

        for (int i = 0; i < padding; i++){
            message += "0";
        }
        System.out.println(message);
        System.out.println(message.length());

        int min = 0;
        int max = 512;
        ArrayList<String> chunks = new ArrayList<>();
        for (int i = 0; i < chunkno; i++){
            chunks.add(message.substring(min,max));
            min += 512;
            max += 512;
        }

        //Process the message in successive 512-bit chunks:
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

            for (int j = 16; j < 64; j++){
                //int mlen = msgChunks.size();
                String s0 = sig0(msgChunks.get(msgChunks.size()-15));
                String s1 = sig1(msgChunks.get(msgChunks.size()-2));
                String addup = adder(msgChunks.get(msgChunks.size()-16), s0, msgChunks.get(msgChunks.size()-7), s1);

            }
//            String a = h0;
//            String b = h1;
//            String c = h2;
//            String d = h3;
//            String e = h4;
//            String f = h5;
//            String g = h6;
//            String h = h7;

        }


//
//        a = h0
//        b = h1
//        c = h2
//        d = h3
//        e = h4
//        f = h5
//        g = h6
//        h = h7
//
//        k = rt3s()
//        for j in range(64):
//        S1 = sigma1(e)
//        ch = cho(e,f,g)
//        temp1 = adders5(h,S1,ch,k[j],messageChunk[j])
//
//        S0 = sigma0(a)
//        maj = mj(a,b,c)
//        temp2 = adders2(S0, maj)
//
//        h = g
//        g = f
//        f = e
//        e = adders2(d, temp1)
//        d = c
//        c = b
//        b = a
//        a = adders2(temp1, temp2)
//
//        h0 = adders2(h0, a)
//        h1 = adders2(h1, b)
//        h2 = adders2(h2, c)
//        h3 = adders2(h3, d)
//        h4 = adders2(h4, e)
//        h5 = adders2(h5, f)
//        h6 = adders2(h6, g)
//        h7 = adders2(h7, h)

    }

    public static int chunkNo(String msg){
        System.out.println();
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

        return null;
    }

    public static String sig1(String bits){

        return null;
    }

    public static String sigma0(String bits){

        return null;
    }

    public static String sigma1(String bits){

        return null;
    }

    public static String xor(String a, String b, String c){

        return null;
    }

    public static String adder(String a, String b, String c, String d){

        return null;
    }

    public static String rotr(String a, int rotnumber){

        return null;
    }

    public static String shr(String a, int rotnumber){

        return null;
    }

    public static String cho(String a, String b, String c){

        return null;
    }

    public static String mj(String a, String b, String c){

        return null;
    }

    public static String addZeros(String msg, int newLen){
        for(int i = msg.length(); i < newLen; i++){
            msg = "0" + msg;
        }
        return msg;
    }

}
