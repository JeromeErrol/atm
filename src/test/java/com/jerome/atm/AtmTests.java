package com.jerome.atm;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AtmTests {

    private Atm atm;
    private final int initialTwenties = 5;
    private final int initialFifties = 4;

    @Before
    public void initAtm() {
        atm = new Atm(initialTwenties, initialFifties);
    }

    @Test
    public void initTest() {
        assertEquals(atm.getFifties(), initialFifties);
        assertEquals(atm.getTwenties(), initialTwenties);
    }

    @Test
    public void withdrawSingleTwenty() {
        atm.withdraw(20);
        assertEquals(initialFifties, atm.getFifties());
        assertEquals(initialTwenties - 1, atm.getTwenties());
    }

    @Test
    public void withdrawTwoTwenties() {
        atm.withdraw(40);
        assertEquals(initialFifties, atm.getFifties());
        assertEquals(initialTwenties - 2, atm.getTwenties());
    }

    @Test
    public void withdrawThreeTwenties() {
        atm.withdraw(60);
        assertEquals(initialFifties, atm.getFifties());
        assertEquals(initialTwenties - 3, atm.getTwenties());
    }

    @Test
    public void withDrawSingleFifty() {
        atm.withdraw(50);
        assertEquals(initialFifties - 1, atm.getFifties());
        assertEquals(initialTwenties, atm.getTwenties());
    }

    @Test
    public void withdrawTwoFifties() {
        atm.withdraw(100);
        assertEquals(initialFifties - 2, atm.getFifties());
        assertEquals(initialTwenties, atm.getTwenties());
    }

    @Test
    public void withdrawThreeFifties() {
        atm.withdraw(150);
        assertEquals(initialFifties - 3, atm.getFifties());
        assertEquals(initialTwenties, atm.getTwenties());
    }

    @Test
    public void withdrawTwentyAndFifty() {
        atm.withdraw(70);
        assertEquals(initialFifties - 1, atm.getFifties());
        assertEquals(initialTwenties - 1, atm.getTwenties());
    }

    @Test
    public void withdrawTwoTwentiesAndFifty() {
        atm.withdraw(90);
        assertEquals(initialFifties - 1, atm.getFifties());
        assertEquals(initialTwenties - 2, atm.getTwenties() );
    }

    @Test
    public void withdrawTwoTwentiesAndTwoFifties() {
        atm.withdraw(140);
        assertEquals(initialFifties - 2, atm.getFifties());
        assertEquals(initialTwenties - 2, atm.getTwenties());
    }

    @Test
    public void withdrawAllMoneyInAtm() {
        atm.withdraw(300);
        assertEquals(0, atm.getFifties());
        assertEquals(0, atm.getTwenties());
    }

    @Test
    public void withInvalidAmount() {
        try {
            atm.withdraw(30);
        } catch (IllegalWithdrawalException e) {
            assertEquals(initialFifties, atm.getFifties());
            assertEquals(initialTwenties, atm.getTwenties());
        }
    }

    @Test
    public void withdrawIllegalTen() {
        try {
            atm.withdraw(10);
        } catch (IllegalWithdrawalException e) {
            assertEquals(initialFifties, atm.getFifties());
            assertEquals(initialTwenties, atm.getTwenties());
        }
    }

    @Test
    public void withdrawIllegalZero() {
        try {
            atm.withdraw(0);
        } catch (IllegalWithdrawalException e) {
            assertEquals(initialFifties, atm.getFifties());
            assertEquals(initialTwenties, atm.getTwenties());
        }
    }

    @Test
    public void withdrawIllegalNegativeAmount() {
        try {
            atm.withdraw(-50);
        } catch (IllegalWithdrawalException e) {
            assertEquals(initialFifties, atm.getFifties());
            assertEquals(initialTwenties, atm.getTwenties());
        }
    }

    @Test
    public void insufficientMoneyInAtm() {
        try {
            atm.withdraw(1000);
        } catch (IllegalWithdrawalException e) {
            assertEquals(initialFifties, atm.getFifties());
            assertEquals(initialTwenties, atm.getTwenties());
        }
    }

    @Test
    public void withdrawSeventyWithNoFiftiesLeft() {
        atm = new Atm(6, 0);
        try {
            atm.withdraw(70);
        } catch (IllegalWithdrawalException e) {
            assertEquals(0, atm.getFifties());
            assertEquals(6, atm.getTwenties());
        }
    }

    @Test
    public void withdrawHundredWithNoFiftiesLeft() {
        atm = new Atm(6, 0);
        try {
            atm.withdraw(100);
        } catch (IllegalWithdrawalException e) {
            assertEquals(1, atm.getTwenties());
        }
    }

    @Test
    public void withdrawFiftyWithOnlyTwentiesLeft() {
        atm = new Atm(5, 0);
        try {
            // now try to withdraw 50 which shouldn't be possible as there are only 20's left
            atm.withdraw(50);
        } catch (IllegalWithdrawalException e) {
            assertEquals(0, atm.getFifties());
            // all of the 20's should still be remaining
            assertEquals(5, atm.getTwenties());
        }
    }
}
