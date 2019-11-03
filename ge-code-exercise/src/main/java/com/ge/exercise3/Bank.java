package com.ge.exercise3;

import static com.ge.ExcerciseConstants.BANK_IN_LOSS;
import static com.ge.ExcerciseConstants.BANK_IN_PROFIT;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ge.ExerciseException;

public class Bank {

	private static final Logger logger = LogManager.getLogger(Bank.class);
	private Map<String, Account> accountMap;

	public Bank() {
		accountMap = new HashMap<>();
	}

	public Account getAccount(String accountNumber) {
		return accountMap.get(accountNumber);
	}

	public void addAccount(Account account) {
		accountMap.put(account.getAccountNumber(), account);
	}

	public void depositToAccount(String accountNumber, float amount) {
		getAccount(accountNumber).deposit(amount);
	}

	public void withdrawFromAccount(String accountNumber, float amount) {
		Account account = getAccount(accountNumber);
		// Added new functionality - Prevent checking accounts from being overdrawn by
		// more that $100
		if (account.getAccountType().equals("Checking") && amount > 100) {
			throw new ExerciseException("More that 100$ cannot be withdrawn from checking account.");
		}
		account.withdraw(amount);
		if (account.getAccountType().equals("Savings") && account.getBalance() < 0) {
			throw new ExerciseException("Cannot be withdrawn amount as balance is going to negative.");
		}
	}

	public int numAccounts() {
		return accountMap.size();
	}

	/**
	 * Get a sum of current holdings
	 */
	public float getSumOfCurrentHoldings() {
		float sum = 0f;
		for (Map.Entry<String, Account> map : accountMap.entrySet()) {
			if (map.getValue().getAccountType().equals("Checking")) {
				sum = sum + map.getValue().getBalance();
			}
		}
		return sum;
	}

	/**
	 * Project if the bank will produce a profit or loss in the next month based on
	 * fees collected on each account vs interest paid out
	 */
	public String checkIfBankProducesProfitOrLoss() {
		float totalInterestPaidOut = 0;
		float totalMontlyFee = 0;
		for (Map.Entry<String, Account> map : accountMap.entrySet()) {
			totalInterestPaidOut = totalInterestPaidOut
					+ (map.getValue().getBalance() * map.getValue().getMonthlyInterestRate());
			totalMontlyFee = totalMontlyFee + map.getValue().getMonthlyFee();
		}
		if (totalMontlyFee > totalInterestPaidOut) {
			return BANK_IN_PROFIT;
		} else if (totalMontlyFee < totalInterestPaidOut) {
			return BANK_IN_LOSS;
		} else {
			return "";
		}
	}
}
