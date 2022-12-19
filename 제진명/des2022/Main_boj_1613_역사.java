package 제진명.des2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_boj_1613_역사 {

    static int n, k;
    static boolean [][] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        check = new boolean[n][n];

        for (int i = 0 ; i < k ; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;

            check[s][e] = true;
        }

        floydWarshall();

        int l = Integer.parseInt(br.readLine());

        for (int i = 0 ; i < l ; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;

            if (check[s][e]) sb.append(-1 + "\n");
            else if (check[e][s]) sb.append(1 + "\n");
            else sb.append(0 + "\n");
        }

        System.out.println(sb);

    }

    static void floydWarshall () {
        for (int m = 0 ; m < n ; m++) {
            for (int s = 0 ; s < n ; s++) {
                for (int e = 0 ; e < n ; e++) {
                    if (check[s][m] && check[m][e]) check[s][e] = true;
                }
            }
        }
    }

}
