package 제진명.june2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_boj_14500_테트로미노 {

    static int N, M;
    static int [][] map;
    static boolean [][] visited;
    static int [][] dp;
    static int ans;
    static int [][] D = {{ -1, 0 },{ 1, 0 },{ 0, -1 },{ 0, 1 }};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        dp = new int[N][M];

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                visited[i][j] = true;
                DFS(i, j, 1, map[i][j]);
                visited[i][j] = false;
            }
        }

        System.out.println(ans);
    }
    static void DFS(int x, int y, int cnt, int tmp) {
        if (cnt == 4) {
            ans = Math.max(ans, tmp);
            return;
        }

        for (int i = 0 ; i < 4 ; i++) {
            // 뻐큐 모양인 경우 따로 처리
            if (cnt == 1) {
                int sum = 0;
                int c = 0;
                for (int j = 0 ; j < 4 ; j++) {
                    if (i == j) continue;
                    int nx = x + D[j][0];
                    int ny = y + D[j][1];

                    if (nx < 0 || nx >= N || ny < 0 || ny >= M) break;
                    sum += map[nx][ny];
                    c += 1;
                }
                if (c == 3) {
                    DFS(x, y, cnt + c, tmp+sum);
                }
            }
            int nx = x + D[i][0];
            int ny = y + D[i][1];

            if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) continue;

            visited[nx][ny] = true;
            DFS(nx, ny, cnt+1, tmp + map[nx][ny]);
            visited[nx][ny] = false;
        }
    }

}
