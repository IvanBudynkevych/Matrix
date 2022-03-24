package com.company;

import java.util.*;

public class Main {
    static int maxHist(int R, int C, int row[]) {
        Stack<Integer> result = new Stack<Integer>();
        int top_val;
        int max_area = 0;
        int area = 0;
        int i = 0;
        while (i < C) {
            if (result.empty() || row[result.peek()] <= row[i])
                result.push(i++);
            else {
                top_val = row[result.peek()];
                result.pop();
                area = top_val * i;
                if (!result.empty()) area = top_val * (i - result.peek() - 1);
                max_area = Math.max(area, max_area);
            }
        }

        while (!result.empty()) {
            top_val = row[result.peek()];
            result.pop();
            area = top_val * i;
            if (!result.empty())
                area = top_val * (i - result.peek() - 1);
            max_area = Math.max(area, max_area);
        }
        return max_area;
    }

    static int MatrixChallenge(int R, int C, int A[][]) {

        int result = maxHist(R, C, A[0]);
        for (int i = 1; i < R; i++) {
            for (int j = 0; j < C; j++)
                if (A[i][j] == 1)
                    A[i][j] += A[i - 1][j];
            result = Math.max(result, maxHist(R, C, A[i]));
        }
        return result;
    }


    public static void main(String[] args) {
        Scanner myInput = new Scanner(System.in);
        System.out.print("Enter size of row: ");
        int size = myInput.nextInt();
        Scanner in = new Scanner(System.in);
        String[] data = in.nextLine().split("");

        int A[][] = new int[size][data.length];
        for (int column = 0; column < data.length; column++) {
            int[] numbers = new int[data.length];
            numbers[column] = Integer.parseInt(data[column]);
            A[0][column] =  numbers[column];
        }
        int C = data.length;
        int R = size;
        for (int row = 1; row < size; row++) {
            data = in.nextLine().split("");
            int[] numbers = new int[data.length];
            for (int column = 0; column < data.length; column++) {
                numbers[column] = Integer.parseInt(data[column]);
                A[row][column] =  numbers[column];
            }
        }
        System.out.println("Area of maximum rectangle is ");
        for (int[] row : A) System.out.println(Arrays.toString(row));
      System.out.print("Area of maximum rectangle is " + MatrixChallenge(R, C, A));
    }
}
