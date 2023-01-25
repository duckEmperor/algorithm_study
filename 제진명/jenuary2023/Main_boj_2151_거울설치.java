package 제진명.jenuary2023;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main_boj_2151_거울설치 {
    static int N;
    static char [][] map;
    static ArrayList<Point> doors;
    static int [][] D = {{1, 0},{0, 1},{-1, 0},{0, -1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new char[N][N];
        doors = new ArrayList<>();

        for (int i = 0 ; i < N ; i++) {
            String s = br.readLine();
            for (int j = 0 ; j < N ; j++) {
                char c = s.charAt(j);

                if (c == '#') doors.add(new Point(i, j));

                map[i][j] = c;
            }
        }

        System.out.println(bfs());
    }

    static class Ray implements Comparable<Ray> {
        Point p;
        // d는 현재 방향, c는 거울에 몇 번 반사된 빛인지
        int d, c;
        Ray (Point p, int d, int c) {
            this.p = p;
            this.d = d;
            this.c = c;
        }

        @Override
        public int compareTo (Ray o) {
            return this.c - o.c;
        }

        @Override
        public String toString() {
            return this.p.x + " " + this.p.y + " " + this.d + " " + this.c;
        }
    }

    static int bfs () {
        PriorityQueue<Ray> queue = new PriorityQueue<>();
        boolean [][][] visited = new boolean[N][N][4];

        for (int d = 0 ; d < 4 ; d++) {
            queue.offer(new Ray(doors.get(0), d, 0));
        }

        while (!queue.isEmpty()) {
            Ray now = queue.poll();

            visited[now.p.x][now.p.y][now.d] = true;

            // 최종 문에 도달 하는 경우
            if (now.p.x == doors.get(1).x && now.p.y == doors.get(1).y) {
                return now.c;
            }

            // 거울에 반사 시키는 경우
            if (map[now.p.x][now.p.y] == '!') {
                for (int d = 0 ; d < 4 ; d++) {
                    if (now.d % 2 == d % 2) continue;

                    int nx = now.p.x + D[d][0];
                    int ny = now.p.y + D[d][1];

                    if (isOverflow(nx, ny) || map[nx][ny] == '*' || visited[nx][ny][d]) continue;

                    queue.offer(new Ray(new Point(nx, ny), d, now.c+1));
                }
            }

            // 기존 방향 그대로 이동하는 경우
            int nx = now.p.x + D[now.d][0];
            int ny = now.p.y + D[now.d][1];

            if (isOverflow(nx, ny) || map[nx][ny] == '*' || visited[nx][ny][now.d]) continue;

            queue.offer(new Ray(new Point(nx, ny), now.d, now.c));

        }

        return -1;
    }

    static boolean isOverflow (int nx, int ny) {
        return nx < 0 || ny < 0 || nx >= N || ny >= N;
    }
}

