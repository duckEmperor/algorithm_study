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
    static int [][] visited;
    static Pos human;
    static Queue<Pos> fire = new LinkedList<>();
    static int [][] D = {{0, 1},{0, -1},{1, 0},{-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        visited = new int[R][C];

        for (int i = 0 ; i < R ; i++) {
            String s = br.readLine();
            for (int j = 0 ; j < C ; j++) {
                visited[i][j] = Integer.MAX_VALUE;
                char c = s.charAt(j);
                if (c == 'J') {
                    human = new Pos(i, j, 0);
                } else if (c == 'F') {
                    fire.offer(new Pos(i, j, 0));
                } else if (c == '#') {
                    map[i][j] = -2;
                    continue;
                }
                map[i][j] = -1;
            }
        }

        spreadFire();
        findRoute(human);

        System.out.println(ans==0 ? "IMPOSSIBLE" : ans);
    }
    static void spreadFire() {
        while(!fire.isEmpty()) {
            Pos now = fire.poll();

            map[now.x][now.y] = now.t;

            for (int d = 0 ; d < 4 ; d++) {
                int nx = now.x + D[d][0];
                int ny = now.y + D[d][1];

                if (checkOverflow(nx, ny) || map[nx][ny] != -1) continue;

                fire.offer(new Pos(nx, ny, now.t+1));
            }
        }
    }

    static void findRoute(Pos human) {
        if (visited[human.x][human.y] <= human.t) {
            return;
        } else {
            visited[human.x][human.y] = human.t;
        }

        if (human.x == 0 || human.x == R-1 || human.y == 0 || human.y == C-1) {
            ans = Math.max(ans, human.t + 1);
            return;
        }

        for (int d = 0 ; d < 4 ; d++) {
            int nx = human.x + D[d][0];
            int ny = human.y + D[d][1];

            if (checkOverflow(nx, ny) || map[nx][ny] <= human.t || map[nx][ny] == -2) continue;

            findRoute(new Pos(nx, ny, human.t + 1));
        }
    }

    static boolean checkOverflow(int nx, int ny) {
        if (nx < 0 || ny < 0 || nx >= R || ny >= C) return true;
        return false;
    }
}
