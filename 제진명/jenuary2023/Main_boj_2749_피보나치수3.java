package 제진명.jenuary2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_boj_2749_피보나치수3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long n = Long.parseLong(br.readLine());

        // 피보나치의 속성
        // 1. 피사노 주기: 피보나치 수를 K로 나눈 나머지는 항상 주기를 가진다.
        // 2. 주기의 길이가 P이면, N번째 피보나치 수를 M으로 나눈 나머지는 N%P번째 피보나치 수를 M으로 나눈 나머지와 같다.
        // 3. M=10^K 일 때, K > 2라면, 주기는 항상 15 * 10^(K-1)이다.

        int period = 15 * (int) Math.pow(10, 5);
        int index = (int) (n % period);

        int [] fibonacci = new int [index+1];

        fibonacci[0] = 0;
        fibonacci[1] = 1;

        for (int i = 2 ; i <= index ; i++) {
            fibonacci[i] = (fibonacci[i-1] + fibonacci[i-2])%1_000_000;
        }

        System.out.println(fibonacci[index]);
    }
}
