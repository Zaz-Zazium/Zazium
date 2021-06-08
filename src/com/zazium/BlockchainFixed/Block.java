package com.zazium.BlockchainFixed;

public class Block {
    int size;
    Header header;
    int tCounter;
    Transaction transaction;

    public Block(int size, Header header, int tCounter, Transaction transaction) {
        this.size = size;
        this.header = header;
        this.tCounter = tCounter;
        this.transaction = transaction;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public int gettCounter() {
        return tCounter;
    }

    public void settCounter(int tCounter) {
        this.tCounter = tCounter;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
