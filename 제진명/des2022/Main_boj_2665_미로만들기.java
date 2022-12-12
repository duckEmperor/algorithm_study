package 제진명.des2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_boj_2665_미로만들기 {

    static int N;
    static int [][] visited;
    static int [][] map;
    static int [][] D = {{0, 1},{0, -1},{1, 0},{-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new int[N][N];

        for (int i = 0 ; i < N ; i++) {
            String line = br.readLine();
            for (int j = 0 ; j < N ; j++) {
                if (line.charAt(j) - '0' == 0) map[i][j] = 1;
                else map[i][j] = 0;
                visited[i][j] = Integer.MAX_VALUE;
            }
        }

        solution();

        System.out.println(visited[N-1][N-1]);
    }

    static void solution () {
        Queue<int []> queue = new LinkedList<>();
        queue.offer(new int [] {0, 0});
        visited[0][0] = 0;

        while (!queue.isEmpty()) {
            int [] now = queue.poll();

            for (int d = 0 ; d < 4 ; d++) {
                int nx = now[0] + D[d][0];
                int ny = now[1] + D[d][1];

                if (checkOverflow(nx, ny) || visited[nx][ny] <= visited[now[0]][now[1]] + map[nx][ny]) continue;

                visited[nx][ny] = visited[now[0]][now[1]] + map[nx][ny];
                queue.offer(new int [] {nx, ny});
            }
        }
    }

    static boolean checkOverflow (int nx, int ny) {
        if (nx < 0 || nx >= N || ny < 0 || ny >= N) return true;
        return false;
    }
}
