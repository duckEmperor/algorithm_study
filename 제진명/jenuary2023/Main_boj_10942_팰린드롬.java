package 제진명.jenuary2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_boj_10942_팰린드롬 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int [] arr = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1 ; i <= N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            boolean isOdd = (s + e)%2 == 0 ? true : false;

            int mid = (s + e)/2;

            int answer = 1;

            for (int j = 0 ; j <= e - mid - (isOdd ? 0 : 1) ; j++) {
                if (arr[mid - j] != arr[mid + j + (isOdd ? 0 : 1)]) {
                    answer = 0;
                    break;
                }
            }

            sb.append(answer).append("\n");

        }

        System.out.print(sb);
    }
}
