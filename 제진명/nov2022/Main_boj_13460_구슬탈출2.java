package 제진명.nov2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_boj_13460_구슬탈출2 {
    static class Pos {
        int rx, ry, bx, by, c;
        Pos (int rx, int ry, int bx, int by, int c) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.c = c;
        }
    }
    static int N, M, ans;
    static char [][] map;
    static boolean [][][][] visited;
    static int [][] D = {{1, 0},{-1, 0},{0, 1},{0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new boolean[N][M][N][M];

        int rx = -1, ry = -1, bx = -1, by = -1;

        for (int i = 0 ; i < N ; i++) {
            String input = br.readLine();
            for (int j = 0 ; j < M ; j++){
                char c = input.charAt(j);
                map[i][j] = c;
                if (map[i][j] == 'R') {
                    rx = i; ry = j;
                } else if (map[i][j] == 'B') {
                    bx = i; by = j;
                }
            }
        }

        ans = 11;
        bfs(new Pos(rx, ry, bx, by, 0));

        System.out.println(ans > 10 ? -1 : ans);

    }

    static void bfs(Pos ball) {
        Queue<Pos> queue = new LinkedList<>();
        queue.offer(ball);

        visited[ball.rx][ball.ry][ball.bx][ball.by] = true;

        while (!queue.isEmpty()) {
            Pos now = queue.poll();

            if (now.c > 10) {
                return;
            }

            if (map[now.bx][now.by] == 'O') continue;
            if (map[now.rx][now.ry] == 'O') {
                ans = Math.min(ans, now.c);
                return;
            }

            for (int d = 0 ; d < 4 ; d++) {
                int bx = now.bx;
                int by = now.by;

                while (true) {
                    bx += D[d][0];
                    by += D[d][1];

                    if (map[bx][by] == 'O') break;
                    else if (map[bx][by] == '#') {
                        bx -= D[d][0];
                        by -= D[d][1];
                        break;
                    }
                }

                int rx = now.rx;
                int ry = now.ry;

                while (true) {
                    rx += D[d][0];
                    ry += D[d][1];

                    if (map[rx][ry] == 'O') break;
                    else if (map[rx][ry] == '#') {
                        rx -= D[d][0];
                        ry -= D[d][1];
                        break;
                    }
                }

                if (rx == bx && ry == by && map[rx][ry] != 'O'){
                    if ((Math.abs(now.rx - rx) + Math.abs(now.ry - ry)) > (Math.abs(now.bx - bx) + Math.abs(now.by - by))) {
                        rx -= D[d][0];
                        ry -= D[d][1];
                    } else {
                        bx -= D[d][0];
                        by -= D[d][1];
                    }
                }

                if (visited[rx][ry][bx][by]) continue;

                visited[rx][ry][bx][by] = true;
                queue.offer(new Pos(rx, ry, bx, by, now.c + 1));
            }
        }
    }
}