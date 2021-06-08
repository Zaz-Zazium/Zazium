package com.zazium.Misc;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Fileread {

    public static ArrayList<String> wordslist() {
        ArrayList<String> result = new ArrayList<>();

//        File directory = new File("Assets");
//        System.out.println(directory.getAbsolutePath());

        String filename = "Assets/words_list.txt";

        try (FileReader f = new FileReader(filename)) {
            StringBuffer sb = new StringBuffer();
            while (f.ready()) {
                char c = (char) f.read();
                if (c == '\n') {
                    result.add(sb.toString());
                    sb = new StringBuffer();
                } else {
                    sb.append(c);
                }
            }
            if (sb.length() > 0) {
                result.add(sb.toString());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    public static ArrayList<String> base64String() {
        ArrayList<String> result = new ArrayList<>();

        String filename = "Assets/base64.txt";
        try (FileReader f = new FileReader(filename)) {
            StringBuffer sb = new StringBuffer();
            while (f.ready()) {
                char c = (char) f.read();
                if (c == '\n') {
                    result.add(sb.toString());
                    sb = new StringBuffer();
                } else {
                    sb.append(c);
                }
            }
            if (sb.length() > 0) {
                result.add(sb.toString());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
}
