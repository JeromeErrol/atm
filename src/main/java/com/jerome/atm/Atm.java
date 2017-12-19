package com.jerome.atm;

public class Atm {

    private int twenties;
    private int fifties;

    public Atm(int twenties, int fifties) {
        this.twenties = twenties;
        this.fifties = fifties;
    }

    public void withdraw(int amountRequested) {
        if (amountRequested <= 0) {
            throw new IllegalWithdrawalException("Amount must be greater than 0");
        }
        if (amountRequested > getTotal()) {
            throw new IllegalWithdrawalException("Insufficient funds remaining in atm to complete request");
        }
        int remainder = amountRequested;
        int numberOfFiftiesToGive = amountRequested / 50;
        if (numberOfFiftiesToGive > fifties) {
            numberOfFiftiesToGive = fifties;
        }
        remainder = amountRequested - (numberOfFiftiesToGive * 50);
        if (remainder % 20 > 0) {
            if (numberOfFiftiesToGive > 0) {
                numberOfFiftiesToGive--;
                remainder += 50;
                if (remainder % 20 > 0) {
                    throw new IllegalWithdrawalException("Request must be a combination of 20's and 50's");
                }
            } else {
                throw new IllegalWithdrawalException("Not enough correct notes left in machine to complete transaction");
            }
        }
        int numberOfTwentiesToGive = remainder / 20;
        if(numberOfTwentiesToGive > twenties){
            throw new IllegalWithdrawalException("Not enough correct notes left in machine to complete transaction");
        }
        twenties -= numberOfTwentiesToGive;
        fifties -= numberOfFiftiesToGive;
    }

    public int getTwenties() {
        return twenties;
    }

    public int getFifties() {
        return fifties;
    }

    public int getTotal(){
        return (twenties * 20) + (fifties * 50);
    }
}
