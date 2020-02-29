package com.company;

import java.util.Scanner;

public class Main {

    public static void guessingGame (int minAllowedRange, int maxAllowedGuess, String username) {
        boolean gameOver = false;
        Scanner sc = new Scanner(System.in);
        int[] guessArr = new int[maxAllowedGuess];
        int attempts = 0;
        int min = 0; int max = 0;
        while (max - min < minAllowedRange) {
            System.out.println("Please enter the minimum number");
            min = sc.nextInt();
            System.out.println("Please enter the maximum number");
            max = sc.nextInt();
            if (max - min < minAllowedRange) {
                System.out.println("Your range is to small! Enter a wider range of numbers!");
            }
        }
        int goal = Util.randomInt(min, max);
        System.out.println("Guess a number!");
        while (!gameOver) {
            if (attempts >= maxAllowedGuess) {
                System.out.println("Sorry, " + username + ", you did not guess my number - its " + goal + "! Here's a list of your prior guesses: ");
                Util.printIntArr(guessArr, attempts);
                gameOver = true;
            }
            int guess = sc.nextInt();
            if (guess > goal) {
                System.out.println("Guess is too high!");
                guessArr[attempts] = guess;
                attempts++;
            } else if (guess < goal) {
                System.out.println("Guess is too low!");
                guessArr[attempts] = guess;
                attempts++;
            } else {
                System.out.println("Congrats, " + username + ", you guessed my number in " + attempts + " attempts - its " + goal + "! Here's a list of your prior guesses: ");
                Util.printIntArr(guessArr, attempts);
                gameOver = true;
            }
        }
    }

    public static void reverseGuessingGame (int minAllowedRange, int maxAllowedGuess) {
        boolean gameOver = false;
        Scanner sc = new Scanner(System.in);
        int[] guessArr = new int[maxAllowedGuess];
        int attempts = 0;
        int min = 0; int max = 0;
        while (max - min < minAllowedRange) {
            System.out.println("Please enter the minimum number");
            min = sc.nextInt();
            System.out.println("Please enter the maximum number");
            max = sc.nextInt();
            if (max - min < minAllowedRange) {
                System.out.println("Your range is to small! Enter a wider range of numbers!");
            }
        }
        System.out.println("Think of a number within that range of numbers.");
        sc.nextLine();
        while (!gameOver) {
            if (attempts > maxAllowedGuess) {
                System.out.println("Aww I lost! Here's a list of my prior guesses: ");
                Util.printIntArr(guessArr, attempts);
                gameOver = true;
            }
            int guess;
            do {
                guess = ((max + min) / 2) + Util.randomInt(-2, 2);
            } while (Util.IsNumInArr(guess, guessArr));
            System.out.println("Is your number " + guess + "? Enter 1 if my guess is too low, 2 if its too high, and 3 if its right on!");
            int input = sc.nextInt();
            if (input == 2) {
                max = guess;
                guessArr[attempts] = guess;
                attempts++;
            } else if (input == 1) {
                min = guess;
                guessArr[attempts] = guess;
                attempts++;
            } else if (input == 3) {
                System.out.println("Yay I win! It took " + attempts + " attempts - for me to win" + "! Here's a list of my prior guesses: ");
                Util.printIntArr(guessArr, attempts);
                gameOver = true;
            }
            if (max - min == 1) {
                System.out.println("Are you trying to cheat a computer? The number is obviously " + (max - 1) + ". Here's a list of my prior guesses: ");
                Util.printIntArr(guessArr, attempts);
                gameOver = true;
            }
            if (max < min) {
                System.out.println("Stop cheating!");
                gameOver = true;
            }
        }
    }

    public static void main(String[] args) {
        final int MIN_ALLOWED_RANGE = 10;
        final int MAX_ALLOWED_GUESSES = 8;
        boolean exit = false;
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter your name.");
        String username = sc.nextLine();
        System.out.println("Welcome to the Guessing Game " + username);
        while (!exit) {
            System.out.println("Enter 0 to play the Guessing Game, enter 1 to play the Reverse Guessing Game or enter 2 to exit.");
            switch (sc.nextLine()) {
                case "0":
                    guessingGame(MIN_ALLOWED_RANGE, MAX_ALLOWED_GUESSES, username);
                    System.out.println("Would you like to play again? Enter (Y) - yes or (N) - no.");
                    String input = sc.nextLine();
                    if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no")) {
                        exit = true;
                    }
                    break;
                case "1":
                    reverseGuessingGame(MIN_ALLOWED_RANGE, MAX_ALLOWED_GUESSES);
                    System.out.println("Would you like to play again? Enter (Y) - yes or (N) - no.");
                    String input1 = sc.nextLine();
                    if (input1.equalsIgnoreCase("n") || input1.equalsIgnoreCase("no")) {
                        exit = true;
                    }
                    break;
                case "2":
                    exit = true;
                    break;
            }
        }
    }
}
