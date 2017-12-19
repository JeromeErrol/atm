package com.jerome.atm;

public class Atm {

    private int twenties;
    private int fifties;

    public Atm(int twenties, int fifties) {
        this.twenties = twenties;
        this.fifties = fifties;
    }

    public void withdraw(int amountRequested) {
        if (amountRequested < 20) {
            throw new IllegalWithdrawalException("The minimum withdrawal amount is 20");
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
        if (remainder % 20 > 0 && numberOfFiftiesToGive > 0) {
            numberOfFiftiesToGive--;
            remainder += 50;
        }
        int numberOfTwentiesToGive = remainder / 20;
        if (remainder % 20 > 0 || numberOfTwentiesToGive > twenties) {
            throw new IllegalWithdrawalException("The atm does not contain the notes required to complete the request");
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

    public int getTotal() {
        return (twenties * 20) + (fifties * 50);
    }
}
