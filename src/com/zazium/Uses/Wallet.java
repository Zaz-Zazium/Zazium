package com.zazium.Uses;

import com.zazium.BB.Transaction;

import java.util.ArrayList;

public class Wallet {
    String privateAddr;
    String publicAddr;
    String walletId;
    double currentBalance;
    ArrayList<String> mnemonic;
    ArrayList<Transaction> transactions;

    public Wallet(String privateAddr, String publicAddr, String WalletId, double currentBalance, ArrayList<String> mnemonic, ArrayList<Transaction> transactions) {
        this.privateAddr = privateAddr;
        this.publicAddr = publicAddr;
        this.walletId = WalletId;
        this.currentBalance = currentBalance;
        this.mnemonic = mnemonic;
        this.transactions = transactions;
    }

    public Wallet(String publicAddr, String WalletId, double currentBalance, ArrayList<Transaction> transactions) {
        this.publicAddr = publicAddr;
        this.walletId = WalletId;
        this.currentBalance = currentBalance;
        this.transactions = transactions;
    }
    public Wallet() {
    }

    public String getPrivateAddr() {
        return privateAddr;
    }

    public void setPrivateAddr(String privateAddr) {
        this.privateAddr = privateAddr;
    }

    public String getPublicAddr() {
        return publicAddr;
    }

    public void setPublicAddr(String publicAddr) {
        this.publicAddr = publicAddr;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public ArrayList<String> getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(ArrayList<String> mnemonic) {
        this.mnemonic = mnemonic;
    }
}
