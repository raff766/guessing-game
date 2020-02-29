package sample;

import java.util.Scanner;
import static sample.Util.*;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static final int MIN_ALLOWED_RANGE = 10;
    private static final int MAX_ALLOWED_GUESSES = 10;
    private static final double EFFICIENCY_RANGE_MULTIPLIER = 0.1; //The lower it is, the harder it is to score efficiency points and vice versa
    private static int[] guessArr = new int[MAX_ALLOWED_GUESSES];
    private static int goal;
    private static int attempts;
    private static int min;
    private static int max;
    private static String username;
    private static boolean won;

    //calculates efficiency based off of what the "correct guess" is, aka the most efficient way to guess.
    private static void calcEfficiency() {
        int points = 0;
        int min = Main.min;
        int max = Main.max;

        for (int i = 0; i < attempts; i++) {
            int guess = guessArr[i];
            int range = max - min;
            int correctGuess = (max + min) / 2;

            if (correctGuess - range * EFFICIENCY_RANGE_MULTIPLIER <= guess && guess <= correctGuess + range * EFFICIENCY_RANGE_MULTIPLIER) {
                points++;
            }
            if (guess > max || guess < min) {
                points--;
            } else if (guess > goal) {
                max = guess - 1;
            } else if (guess < goal) {
                min = guess + 1;
            }
        }
        int guessEfficiency = (int)(((double)points/attempts) * 100.0);
        if ((double)attempts/MAX_ALLOWED_GUESSES <= 0.2 && won) {
            System.out.println("You got lucky that time!");
        } else if (guessEfficiency >= 75) {
            System.out.println("Based off of how you answered, you had %" + guessEfficiency + " guessing efficiency this game! Great job!");
        } else if (guessEfficiency >= 50){
            System.out.println("Based off of how you answered, your strategy was average this time, you had %" + guessEfficiency + " guessing efficiency this game.");
        } else if (guessEfficiency < 0){
            System.out.println("Were you trying to lose?? You had -%" + Math.abs(guessEfficiency) + " guessing efficiency this game!");
        } else {
            System.out.println("Based off of how you answered, your strategy was pretty poor this time, you had %" + guessEfficiency + " guessing efficiency this game...");
        }
    }
    private static void askForRange() {
        while (max - min < MIN_ALLOWED_RANGE) {
            System.out.println("Please enter the minimum number");
            min = getIntInput();
            System.out.println("Please enter the maximum number");
            max = getIntInput();
            if (max - min < MIN_ALLOWED_RANGE) {
                System.out.println("Your range is to small! Enter a wider range of numbers!");
            }
        }
    }

    private static void resetGame() {
        guessArr = new int[MAX_ALLOWED_GUESSES];
        attempts = 0;
        min = 0;
        max = 0;
    }

    private static boolean askToExit() {
        while (true) {
            System.out.println("Would you like to play again? Enter (Y) - yes or (N) - no.");
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no")) {
                return true;
            } else if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
                return false;
            } else {
                System.out.println("Please enter a valid input.");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Please enter your name.");
        username = sc.nextLine();
        System.out.println("Welcome to the Guessing Game " + username);
        loop: while (true) {
            resetGame();
            System.out.println("Enter 0 to play the Guessing Game, enter 1 to play the Reverse Guessing Game or enter 2 to exit.");
            switch (sc.nextLine()) {
                case "0":
                    guessingGame();
                    if (askToExit()) break loop;
                    break;
                case "1":
                    reverseGuessingGame();
                    if (askToExit()) break loop;
                    break;
                case "2":
                    break loop;
            }
        }
    }

    private static void guessingGame() {
        askForRange();
        goal = randomInt(min, max);
        System.out.println("Guess a number!");
        while (true) {
            int guess = getIntInput();
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
                printIntArr(guessArr, attempts);
                won = true;
                break;
            }
            if (attempts >= MAX_ALLOWED_GUESSES) {
                System.out.println("Sorry, " + username + ", you did not guess my number - its " + goal + "! Here's a list of your prior guesses: ");
                printIntArr(guessArr, attempts);
                break;
            }
        }
        calcEfficiency();
    }

    private static void reverseGuessingGame() {
        int guess;
        askForRange();
        System.out.println("Think of a number within that range of numbers.");
        sc.nextLine();
        guess = randomInt(min, max); //Make the first guess completely random so its not boring
        while (true) {
            System.out.println("Is your number " + guess + "? Enter 1 if my guess is too low, 2 if its too high, and 3 if its right on!");
            int input = getIntInput();
            if (input == 2) {
                max = guess - 1;
                guessArr[attempts] = guess;
                attempts++;
            } else if (input == 1) {
                min = guess + 1;
                guessArr[attempts] = guess;
                attempts++;
            } else if (input == 3) {
                System.out.println("Yay I win! It took " + attempts + " attempts - for me to win" + "! Here's a list of my prior guesses: ");
                printIntArr(guessArr, attempts);
                break;
            }
            if (attempts >= MAX_ALLOWED_GUESSES) {
                System.out.println("Aww I lost! Here's a list of my prior guesses: ");
                printIntArr(guessArr, attempts);
                break;
            }
            if (max - min == 1) {
                System.out.println("Are you trying to cheat a computer? The number is obviously " + (max - 1) + ". Here's a list of my prior guesses: ");
                printIntArr(guessArr, attempts);
                break;
            }
            if (max < min) {
                System.out.println("Stop cheating!");
                break;
            }
            do {
                guess = ((max + min) / 2) + randomInt(-2, 2);
            } while (IsNumInArr(guess, guessArr));
        }
    }
}
