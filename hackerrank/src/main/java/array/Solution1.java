package array;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

// 현재 가진 돈으로 살 수 있는 최대의 장난감
public class Solution1 {

    private static final Scanner scanner = new Scanner(System.in);

    // Complete the maximumToys function below.
    static int maximumToys(int[] prices, int k) {
        List<Integer> toys = Arrays.stream(prices).boxed().collect(Collectors.toList());
        Collections.sort(toys, (o1, o2) -> Integer.compare(o1, o2) * (-1));

        int countOfToys = 0;
        int totalMoney = k;

        for (Integer singlePrice : toys) {
            if (totalMoney >= singlePrice) {
                totalMoney -= singlePrice;
                countOfToys++;
            }
            return countOfToys;
        }

        return countOfToys;

    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] prices = new int[n];

        String[] pricesItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int pricesItem = Integer.parseInt(pricesItems[i]);
            prices[i] = pricesItem;
        }

        int result = maximumToys(prices, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
