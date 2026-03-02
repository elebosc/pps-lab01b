package it.unibo.pps.e1.api;

public interface BankAccount {

    int getBalance();

    void deposit(int amount);

    void withdraw(int amount);

}