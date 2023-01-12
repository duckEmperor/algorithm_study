package 제진명.jenuary2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_boj_9376_탈옥 {
    static class Pos implements Comparable<Pos>{
        int x, y, c;
        Pos (int x, int y) {
            this.x = x;
            this.y = y;
        }
        Pos (int x, int y, int c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }

        @Override
        public int compareTo (Pos o) {
            return this.c - o.c;
        }
    }
    static int [][] D = {{1, 0},{0, 1},{-1, 0},{0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0 ; t < T ; t++) {
            st = new StringTokenizer(br.readLine());

            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            char [][] map = new char[R + 2][C + 2];
            ArrayList<Pos> prisoner = new ArrayList<>();

            for (int i = 1 ; i <= R ; i++) {
                String s = br.readLine();
                for (int j = 1 ; j <= C ; j++) {
                    map[i][j] = s.charAt(j - 1);
                    if (map[i][j] == '$') prisoner.add(new Pos(i, j));
                }
            }

            int [][] outsider = bfs(R, C, new Pos(0, 0, 0), map);
            int [][] prisoner1 = bfs(R, C, new Pos(prisoner.get(0).x, prisoner.get(0).y, 0), map);
            int [][] prisoner2 = bfs(R, C, new Pos(prisoner.get(1).x, prisoner.get(1).y, 0), map);


            int answer = 1_000_000_000;

            for (int i = 0 ; i < R + 2 ; i++) {
                for (int j = 0 ; j < C + 2 ; j++) {
                    if (map[i][j] == '*') continue;
                    int sum = outsider[i][j] + prisoner1[i][j] + prisoner2[i][j];
                    if (map[i][j] == '#') sum -= 2;
                    answer = Math.min(answer, sum);
                }
            }
            sb.append(answer).append("\n");
        }

        System.out.print(sb);

    }

    static int [][] bfs (int R, int C, Pos p, char [][] map) {
        boolean [][] visited = new boolean[R + 2][C + 2];
        int [][] res = new int[R + 2][C + 2];

        PriorityQueue<Pos> queue = new PriorityQueue<>();
        queue.offer(p);

        visited[p.x][p.y] = true;

        while (!queue.isEmpty()) {
            Pos now = queue.poll();
            res[now.x][now.y] = now.c;

            for (int d = 0 ; d < 4 ; d++) {
                int nx = now.x + D[d][0];
                int ny = now.y + D[d][1];

                if (isOverflow(nx, ny, R, C) || map[nx][ny] == '*' || visited[nx][ny]) continue;

                visited[nx][ny] = true;
                queue.offer(new Pos(nx, ny, map[nx][ny] == '#' ? now.c + 1 : now.c));
            }
        }

        for (int i = 0 ; i < R + 2 ; i++) {
            for (int j = 0 ; j < C + 2 ;j++) {
                if (!visited[i][j]) map[i][j] = '*';
            }
        }

        return res;
    }

    static boolean isOverflow (int nx, int ny, int R, int C) {
        if (nx < 0 || ny < 0 || nx > R + 1 || ny > C + 1) return true;
        return false;
    }
}