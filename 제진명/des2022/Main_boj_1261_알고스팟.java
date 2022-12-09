package 제진명.des2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_boj_1261_알고스팟 {
    static int N, M;
    static int [][] map;
    static int [][] visited;
    static int [][] D = {{0, -1},{0, 1},{1, 0},{-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new int[N][M];

        for (int i = 0 ; i < N ; i++) {
            String input = br.readLine();
            for (int j = 0 ; j < M ; j++) {
                map[i][j] = input.charAt(j) - '0';
                visited[i][j] = Integer.MAX_VALUE;
            }
        }

        solution();

        System.out.println(visited[N-1][M-1]);

    }

    static void solution() {
        Queue<int []> queue = new LinkedList<>();

        queue.offer(new int [] {0, 0});
        visited[0][0] = 0;

        while(!queue.isEmpty()) {
            int [] now = queue.poll();

            for (int d = 0 ; d < 4 ; d++) {
                int nx = now[0] + D[d][0];
                int ny = now[1] + D[d][1];

                if (checkOverflow(nx, ny) || visited[nx][ny] <= visited[now[0]][now[1]] + map[nx][ny]) continue;

                queue.offer(new int [] {nx, ny});
                visited[nx][ny] = visited[now[0]][now[1]] + map[nx][ny];
            }
        }
    }

    static boolean checkOverflow(int nx, int ny) {
        if (nx < 0 || nx >= N || ny < 0 || ny >= M) return true;
        return false;
    }
}
