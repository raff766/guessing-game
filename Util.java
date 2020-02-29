package sample;

import java.util.Random;
import java.util.Scanner;

public class Util {
    public static int randomInt(int min, int max) {
        Random rnd = new Random();
        return rnd.nextInt(max - min + 1) + min;
    }
    public static void printIntArr(int[] arr, int max) {
        for (int i = 0; i < max; i++) {
            System.out.print(arr[i] + ", ");
        }
    }
    public static boolean IsNumInArr(int number, int[] arr) {
        for (int num : arr) {
            if (number == num) {
                return true;
            }
        }
        return false;
    }
    public static int getIntInput() {
        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                return sc.nextInt();
            } catch (Exception e) {
                System.out.println("Please enter a valid input.");
            }
        }
    }
}
