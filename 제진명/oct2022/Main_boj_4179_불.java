package 제진명.oct2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_boj_4179_불 {

    static class Pos {
        int x, y, t;

        public Pos(int x, int y, int t) {
            this.x = x;
            this.y = y;
            this.t = t;
        }
    }
    static int R, C, ans;
    static int [][] map;
    static boolean [][] visited;
    static Queue<Pos> human = new LinkedList<>();
    static Queue<Pos> fire = new LinkedList<>();
    static int [][] D = {{0, 1},{0, -1},{1, 0},{-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        visited = new boolean[R][C];

        for (int i = 0 ; i < R ; i++) {
            String s = br.readLine();
            for (int j = 0 ; j < C ; j++) {
                char c = s.charAt(j);
                if (c == 'J') {
                    human.offer(new Pos(i, j, 0));
                    visited[i][j] = true;
                } else if (c == 'F') {
                    fire.offer(new Pos(i, j, 0));
                    map[i][j] = -1;
                } else if (c == '#') {
                    map[i][j] = -1;
                    visited[i][j] = true;
                }
            }
        }

        ans = findRoute();

        System.out.println(ans==Integer.MAX_VALUE ? "IMPOSSIBLE" : ans + 1);
    }

    static int findRoute() {
        while(!human.isEmpty()) {
            int preHumanQueueSize = human.size();
            int preFireQueueSize = fire.size();

            for (int i = 0 ; i < preFireQueueSize ; i++) {
                Pos now = fire.poll();

                for (int d = 0 ; d < 4 ; d++) {
                    int nx = now.x + D[d][0];
                    int ny = now.y + D[d][1];

                    if (checkOverflow(nx, ny) || map[nx][ny] == -1) continue;

                    map[nx][ny] = -1;
                    fire.offer(new Pos(nx, ny, now.t + 1));
                }
            }


            for (int i = 0 ; i < preHumanQueueSize ; i++) {
                Pos now = human.poll();

                if (now.x == 0 || now.x == R - 1 || now.y == 0 || now.y == C - 1) {
                    return now.t;
                }

                for (int d = 0 ; d < 4 ; d++) {
                    int nx = now.x + D[d][0];
                    int ny = now.y + D[d][1];

                    if (checkOverflow(nx, ny) || visited[nx][ny] || map[nx][ny] == -1) continue;

                    visited[nx][ny] = true;
                    human.offer(new Pos(nx, ny, now.t + 1));
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    static boolean checkOverflow(int nx, int ny) {
        if (nx < 0 || ny < 0 || nx >= R || ny >= C) return true;
        return false;
    }

}