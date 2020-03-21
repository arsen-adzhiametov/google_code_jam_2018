import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            final int n = in.nextInt();
            int[] arr = new int[n];
            for (int j = 0; j < n; j++) {
                arr[j] = in.nextInt();
            }

            troubleSort(arr);

            System.out.println("input:" + Arrays.toString(arr));

            final int indexOfError = findIndexOfError(arr);
            String response = indexOfError < 0 ? "OK" : String.valueOf(indexOfError);
            System.out.println("Case #" + i + ": " + response);
        }
    }

    private static int findIndexOfError(final int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return i;
            }
        }


        return -1;
    }

    private static void troubleSort(int[] arr) {
        boolean done = false;
        while (!done) {
            done = true;
            for (int i = 0; i < arr.length - 2; i++) {
                if (arr[i] > arr[i+2]) {
                    done = false;
                    int temp = arr[i];
                    arr[i] = arr[i+2];
                    arr[i+2] = temp;
                }

            }
        }
    }
}
