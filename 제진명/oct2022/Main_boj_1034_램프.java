package 제진명.oct2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_boj_1034_램프 {
    static int N, M, K, ans;
    static int [][] map;
    static boolean [] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0 ; i < N ; i++) {
            String s = br.readLine();
            for (int  j = 0 ; j < M ; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        K = Integer.parseInt(br.readLine());

        visited = new boolean[N];

        for (int i = 0 ; i < N ; i++) {
            if (visited[i]) continue;
            visited[i] = true;

            int cnt = 0;
            for (int m : map[i]) {
                if (m == 0) cnt++;
            }

            int rowCnt = getRowCnt(i);

            if (cnt % 2 == K % 2 && cnt <= K) {
                ans = Math.max(rowCnt, ans);
            }
        }

        System.out.println(ans);
    }

    static int getRowCnt(int r) {
        int cnt = 0;
        for (int i = 0 ; i < N ; i++) {
            if(Arrays.equals(map[i], map[r])) {
                cnt++;
                visited[i] = true;
            }
        }

        return cnt;
    }
}