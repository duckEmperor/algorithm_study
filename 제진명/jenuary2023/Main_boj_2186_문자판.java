package 제진명.jenuary2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_boj_2186_문자판 {
    static int N, M, K;
    static int [][] D = {{-1, 0},{1, 0},{0, -1},{0, 1}};
    static char [][] map;
    static String word;
    static int [][][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        for (int i = 0 ; i < N ; i++) {
            String s = br.readLine();
            for (int j = 0 ; j < M ; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        word = br.readLine();

        dp = new int[N][M][word.length()];

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        int ans = 0;

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                if (map[i][j] != word.charAt(0)) continue;
                ans += dfs(0, i, j);
            }
        }

        System.out.println(ans);
    }

    static int dfs (int idx, int x, int y) {
        if (idx == word.length() - 1) {
            return 1;
        }

        if (dp[x][y][idx] != -1) return dp[x][y][idx];

        dp[x][y][idx] = 0;

        for (int d = 0 ; d < 4 ; d++) {
            for (int k = 1 ; k <= K ; k++) {
                int nx = x + k * D[d][0];
                int ny = y + k * D[d][1];

                if (isOverflow(nx, ny) || map[nx][ny] != word.charAt(idx+1)) continue;

                dp[x][y][idx] += dfs(idx+1, nx, ny);
            }
        }

        return dp[x][y][idx];
    }

    static boolean isOverflow (int nx, int ny) {
        return nx < 0 || ny < 0 || nx >= N || ny >= M;
    }
}
