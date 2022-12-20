package 제진명.des2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_boj_10159_저울 {

    static int N, M;
    static boolean [][] connect;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        connect = new boolean[N][N];

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                if (i == j) connect[i][j] = true;
            }
        }

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;

            connect[s][e] = true;
        }

        solution();

        int [] cnt = new int[N];
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                if (connect[i][j] || connect[j][i]) {
                    cnt[i]++;
                }
            }
        }

        for (int ans : cnt) {
            System.out.println(N - ans);
        }
    }

    static void solution () {
        for (int k = 0 ; k < N ; k++) {
            for (int i = 0 ; i < N ; i++) {
                for (int j = 0 ; j < N ; j++) {
                    if (connect[i][k] && connect[k][j]) connect[i][j] = true;
                }
            }
        }
    }

}
