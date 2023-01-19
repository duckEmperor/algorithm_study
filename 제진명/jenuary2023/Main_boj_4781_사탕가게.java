package 제진명.jenuary2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_boj_4781_사탕가게 {

    static class Candy {
        int kcal, price;
        Candy (int kcal, int price) {
            this.kcal = kcal;
            this.price = price;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = (int) (Double.parseDouble(st.nextToken()) * 100 + 0.5);

            if (N == 0 && M == 0) break;

            Candy [] candies = new Candy[N];

            for (int i = 0 ; i < N ; i++) {
                st = new StringTokenizer(br.readLine());

                int kcal = Integer.parseInt(st.nextToken());
                int price = (int) (Double.parseDouble(st.nextToken()) * 100 + 0.5);

                candies[i] = new Candy(kcal, price);
            }

            int [] dp = new int[M + 1];

            for (int i = 1 ; i <= M ; i++) {
                dp[i] = dp[i - 1];
                for (int j = 0 ; j < N ; j++) {
                    if (candies[j].price <= i) {
                        dp[i] = Math.max(dp[i], dp[i - candies[j].price] + candies[j].kcal);
                    }
                }
            }

            sb.append(dp[M]).append("\n");
        }

        System.out.print(sb);
    }
}
