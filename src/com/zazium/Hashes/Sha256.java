package com.zazium.Hashes;


public class Sha256 {
    // take msg
    // breakdown msg for each char in int
    // convert for each char to 8 bit binary

    public static void main(String[] args) {
        char character = 'a';
        int ascii = (int) character; //char to int value
        String bii = Integer.toBinaryString(ascii); //int value to binary value
        String bii8 = String.format("%08d", Integer.parseInt(bii)); //binary to 8 bit full


        //String qqqq = String.format("%08d", Integer.parseInt(Integer.toBinaryString((int) 'a')));;
        //System.out.println(qqqq);


        System.out.println(ascii);
        System.out.println(bii);
        System.out.println(bii8);
        System.out.println();

        String msg = "abc";

        for (int i = 0; i < msg.length(); i++){
            String qqqq = String.format("%08d", Integer.parseInt(Integer.toBinaryString((int) msg.charAt(i))));
            System.out.println(qqqq);
        }

    }

    public static void hashes(String msg){
        // string to binary

    }

}
