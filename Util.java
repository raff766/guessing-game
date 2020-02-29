package com.company;

public class Util {
    public static int randomInt(int min, int max) {
        return (int)(Math.random() * (max - min + 1) + min);
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
}
