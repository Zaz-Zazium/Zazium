package com.zazium.BB;

public class Transaction {
    double amount;
    String fromAddr;
    String toAddr;
    int timestamp;
    String transactionId;

    public Transaction(double amount, String fromAddr, String toAddr, int timestamp, String transactionId) {
        this.amount = amount;
        this.fromAddr = fromAddr;
        this.toAddr = toAddr;
        this.timestamp = timestamp;
        this.transactionId = transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getFromAddr() {
        return fromAddr;
    }

    public void setFromAddr(String fromAddr) {
        this.fromAddr = fromAddr;
    }

    public String getToAddr() {
        return toAddr;
    }

    public void setToAddr(String toAddr) {
        this.toAddr = toAddr;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
