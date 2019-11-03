package com.ge.exercise3;

import org.junit.Before;
import org.junit.Test;
import static com.ge.ExcerciseConstants.BANK_IN_LOSS;

import static org.junit.Assert.assertEquals;

public class BankTest {

	Bank bank;

	@Before
	public void setUp() {
		bank = new Bank();
	}

	@Test
	public void addAccountTest() {
		Account account = new Account("001");
		bank.addAccount(account);
		assertEquals(1, bank.numAccounts());
	}

	@Test
	public void getAccountTest() {
		Account account = new Account("002", "Checking", 100.0f);
		bank.addAccount(account);
		assertEquals(account, bank.getAccount("002"));
	}

	@Test
	public void depositToAccountTest() {
		Account account = new Account("003", "Checking", 100.0f);
		bank.addAccount(account);
		bank.depositToAccount("003", 100.0f);
		assertEquals(200.0f, account.getBalance(), 0.01);
	}

	@Test
	public void withdrawFromAccountTest() {
		Account account = new Account("004", "Checking", 100.0f);
		bank.addAccount(account);
		bank.withdrawFromAccount("004", 100.0f);
		assertEquals(0.0f, account.getBalance(), 0.01);
	}

	/**
	 * Added new test case for - Get a sum of current holdings method
	 */
	@Test
	public void getSumOfCurrentHoldingsTest() {
		Account savingsAcount1 = new Account("005", "Checking", 100.0f);
		Account savingsAcount2 = new Account("006", "Checking", 100.0f);
		bank.addAccount(savingsAcount1);
		bank.addAccount(savingsAcount2);
		assertEquals(200.0f, bank.getSumOfCurrentHoldings(), 0.01);
	}

	/**
	 * Added new test case for - o Project if the bank will produce a profit or loss
	 * in the next month based on fees collected on each account vs interest paid
	 * out
	 */
	@Test
	public void getcheckIfBankProducesProfitOrLoss() {
		Account savingsAcount1 = new Account("007", "Checking", 100.0f);
		savingsAcount1.setMonthlyInterestRate(10);
		savingsAcount1.setMonthlyInterestRate(1);
		Account savingsAcount2 = new Account("008", "Checking", 100.0f);
		savingsAcount1.setMonthlyInterestRate(10);
		savingsAcount1.setMonthlyInterestRate(1);
		bank.addAccount(savingsAcount1);
		bank.addAccount(savingsAcount2);
		assertEquals(BANK_IN_LOSS, bank.checkIfBankProducesProfitOrLoss(), BANK_IN_LOSS);
	}

}