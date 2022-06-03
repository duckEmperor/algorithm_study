package 제진명;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_boj_1012_유기농배추 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;
        final int [][] D = {{ -1, 0 },{ 1, 0 },{ 0, -1 },{ 0, 1 }};

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1 ; tc <= T ; tc++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            boolean [][] map = new boolean[M][N];

            for (int i = 0 ; i < K ; i++) {
                st = new StringTokenizer(br.readLine());
                map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
            }

            int res = bfs(M, N, map, D);
            sb.append(res + "\n");
        }
        System.out.println(sb);
    }

    public static int bfs(int M, int N, boolean [][] map, int [][] D) {
        boolean [][] visited = new boolean[M][N];
        Queue<int []> queue = new LinkedList<>();
        int ans = 0;

        for (int i = 0 ; i < M ; i++) {
            for (int j = 0 ; j < N ; j++) {
                if (!map[i][j] || visited[i][j]) continue;
                queue.add(new int [] {i, j});
                visited[i][j] = true;
                ans++;

                while(!queue.isEmpty()) {
                    int [] now = queue.poll();

                    for (int d = 0 ; d < 4 ; d++) {
                        int nx = now[0] + D[d][0];
                        int ny = now[1] + D[d][1];

                        if (nx < 0 || nx >= M || ny < 0 || ny >= N || !map[nx][ny] || visited[nx][ny]) continue;

                        queue.add(new int [] {nx, ny});
                        visited[nx][ny] = true;
                    }

                }
            }
        }

        return ans;
    }
}