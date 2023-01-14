package 제진명.jenuary2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_boj_11582_치킨TopN {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(br.readLine());

        int [] arr = new int [N];

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0 ; i < k ; i++) {
            printResult(i * (N / k), (i + 1) * (N / k), k, arr, sb);
        }

        System.out.println(sb);
    }

    static void printResult (int s, int e, int k, int [] arr, StringBuilder sb) {
        int length = arr.length / k;
        int [] temp = new int [length];
        for (int i = s ; i < e ; i++) {
            temp[i - s] = arr[i];
        }
        Arrays.sort(temp);

        for (int i = 0 ; i < length ; i++) {
            sb.append(temp[i]).append(" ");
        }
    }
}
