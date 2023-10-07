package com.scripter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClientMain {
	private static final String sqlLiteurl = "jdbc:sqlite:/home/syndicate/Documents/Tools/SQLite3DBs/Car_Database.db";
	private static final int MAX_PASSWORD = 9999;

	public static void main(String[] args) throws InterruptedException {
		Random random = new Random();
		Vault vault = new Vault(random.nextInt(MAX_PASSWORD));

		List<Thread> threads = new ArrayList<>();
		threads.add(new AscendingHackerThread(vault));
		threads.add(new DecendingHackerThread(vault));
		threads.add(new PoliceThread());
		
		for (Thread thread : threads) {
			thread.start();
		}
	}

	private static class Vault {
		private int password;

		public Vault(int password) {
			this.password = password;
		}

		public boolean isCorrectPassword(int guess) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {

			}
			return this.password == guess;
		}
	}

	private static abstract class HackerThread extends Thread {

		protected Vault vault;

		public HackerThread(Vault vault) {
			this.vault = vault;
			this.setName(this.getClass().getSimpleName());
			this.setPriority(MAX_PRIORITY);
		}

		@Override
		public synchronized void start() {
			System.out.println("Starting thread : " + this.getName());
			super.start();
		}
	}

	private static class AscendingHackerThread extends HackerThread {

		public AscendingHackerThread(Vault vault) {
			super(vault);
		}

		@Override
		public void run() {
			for (int guess = 0; guess < MAX_PASSWORD; guess++) {
//				System.out.println(this.getName() + " guess is :: " + guess);
				if (vault.isCorrectPassword(guess)) {
					System.out.println(this.getName() + " has guessed the password :" + guess);
					System.exit(0);
				}
			}
		}

	}

	private static class DecendingHackerThread extends HackerThread {

		public DecendingHackerThread(Vault vault) {
			super(vault);
		}

		@Override
		public void run() {
			for (int guess = MAX_PASSWORD; guess >= 0; guess--) {
//				System.out.println(this.getName() + " guess is :: " + guess);
				if (vault.isCorrectPassword(guess)) {
					System.out.println(this.getName() + " has guessed the password :" + guess);
					System.exit(0);
				}
			}
		}

	}

	private static class PoliceThread extends Thread {

		@Override
		public void run() {
			for (int i = 10; i >= 0; i--) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(i);
			}
			System.out.println("Game over for you ");
			System.exit(0);
		}

	}
}

/*
Output Console::

Starting thread : AscendingHackerThread
Starting thread : DecendingHackerThread
10
9
8
7
6
5
4
3
2
AscendingHackerThread has guessed the password :1895

*/
