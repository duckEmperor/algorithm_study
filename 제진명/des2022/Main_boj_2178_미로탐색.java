package 제진명.des2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_boj_2178_미로탐색 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean [][] map = new boolean[N][M];

        for (int i = 0 ; i < N ; i++) {
            String input = br.readLine();
            for (int j = 0 ; j < M ; j++) {
                if (input.charAt(j) == '1') map[i][j] = true;
                else map[i][j] = false;
            }
        }

        System.out.println(bfs(N, M, map));
    }

    static int bfs (int N, int M, boolean [][] map) {
        int [][] D = {{0, -1},{0, 1},{-1, 0},{1, 0}};
        boolean [][] visited = new boolean[N][M];

        Queue<int []> queue = new LinkedList<>();
        queue.offer(new int [] {0, 0, 1});
        visited[0][0] = true;
        int answer = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            int [] now = queue.poll();

            if (now[0] == N-1 && now[1] == M-1) {
                answer = Math.min(answer, now[2]);
                break;
            }

            for (int d = 0 ; d < 4 ; d++) {
                int nx = now[0] + D[d][0];
                int ny = now[1] + D[d][1];

                if (checkOverflow(nx, ny, N, M) || visited[nx][ny] || !map[nx][ny]) continue;

                visited[nx][ny] = true;
                queue.offer(new int [] {nx, ny, now[2]+1});
            }

        }

        return answer;
    }

    static boolean checkOverflow (int nx, int ny, int N, int M) {
        if (nx < 0 || ny < 0 || nx >= N || ny >= M) return true;

        return false;
    }
}
