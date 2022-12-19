package 제진명.des2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_boj_2458_키순서 {

    static int N, M;
    static boolean [][] connect;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

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

        int answer = 0;
        for(int num : cnt) {
            if(num == N) answer++;
        }
        System.out.println(answer);
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
