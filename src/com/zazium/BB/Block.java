package com.zazium.BB;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.zazium.Misc.Conv.bytesToHex;

public class Block {
    int index;
    String currentHash;
    String previousHash;
    int timestamp;
    String datas;
    int difficulty;
    int nonce;

    public Block(int index, String currentHash, String previousHash,
                 int timestamp, String datas, int difficulty, int nonce) throws NoSuchAlgorithmException {
        this.index = index;
        this.currentHash = currentHash;
        this.previousHash = previousHash;
        this.timestamp = timestamp;
        this.datas = datas;
        this.difficulty = difficulty;
        this.nonce = nonce;
    }

    public Block(int index, String previousHash, int timestamp, String datas,
                 int difficulty, int nonce) throws NoSuchAlgorithmException {
        this.index = index;
        this.previousHash = previousHash;
        this.timestamp = timestamp;
        this.datas = datas;
        this.difficulty = difficulty;
        this.nonce = nonce;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getCurrentHash() {
        return currentHash;
    }

    public void setCurrentHash(String currentHash) {
        this.currentHash = currentHash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getDatas() {
        return datas;
    }

    public void setDatas(String datas) {
        this.datas = datas;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

    public static byte[] calcHash(Block block) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(toString(block).getBytes(StandardCharsets.UTF_8));
        return hashBytes;
    }

    public static String toString(Block block) {
        return Integer.toString(block.getIndex()) + block.getCurrentHash() + block.getPreviousHash()
                + block.getTimestamp() + block.getDatas() + Integer.toString(block.getDifficulty()) + Integer.toString(block.getNonce());
    }

    public static String toStringHash(Block block) throws NoSuchAlgorithmException {
        byte[] hasher = Block.calcHash(block);
        String a = bytesToHex(hasher);

        return a;
    }

}
