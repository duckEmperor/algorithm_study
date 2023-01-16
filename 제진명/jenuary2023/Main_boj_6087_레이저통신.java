package 제진명.jenuary2023;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_boj_6087_레이저통신 {

    static class Pos implements Comparable<Pos> {
        Point p;
        int d, c;
        Pos (Point p, int d, int c) {
            this.p = p;
            this.d = d;
            this.c = c;
        }

        @Override
        public int compareTo (Pos o) {
            return this.c - o.c;
        }
    }

    static int W, H;
    static int [][] D = {{-1, 0},{1, 0},{0, -1},{0, 1}}; // 상, 하, 좌, 우
    static char [][] map;
    static ArrayList<Point> lasers;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new char[H][W];
        lasers = new ArrayList<>();

        for (int i = 0 ; i < H ; i++) {
            String s = br.readLine();
            for (int j = 0 ; j < W ; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'C') lasers.add(new Point(i, j));
            }
        }

        System.out.println(bfs());

    }

    static int bfs () {
        PriorityQueue<Pos> queue = new PriorityQueue<>();
        int [][][] visited = new int[H][W][4];

        for (int i = 0 ; i < H ; i++) {
            for (int j = 0 ; j < W ; j++) {
                for (int k = 0 ; k < 4 ; k++) {
                    visited[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        for (int d = 0 ; d < 4 ; d++) {
            int x = lasers.get(0).x;
            int y = lasers.get(0).y;
            queue.offer(new Pos(new Point(x, y), d, 0));
            visited[x][y][d] = 0;
        }

        while (!queue.isEmpty()) {
            Pos now = queue.poll();

            if (now.p.x == lasers.get(1).x && now.p.y == lasers.get(1).y) {
                return now.c;
            }
            // 일단 앞으로 전진. 전진을 해서 거울을 설치하든 말든 해야하니까
            int nx = now.p.x + D[now.d][0];
            int ny = now.p.y + D[now.d][1];

            for (int d = 0 ; d < 4 ; d++) {
                int c = now.c;

                // 방향을 180도 바꾸면 continue
                if (D[now.d][0] * D[d][0] + D[now.d][1] * D[d][1] == -1) continue;
                else if (D[now.d][0] * D[d][0] + D[now.d][1] * D[d][1] == 0) c += 1;

                if (isOverflow(nx, ny) || visited[nx][ny][d] <= c || map[nx][ny] == '*') continue;

                visited[nx][ny][d] = c;
                queue.offer(new Pos(new Point(nx, ny), d, c));
            }

        }

        return -1;
    }

    static boolean isOverflow (int nx, int ny) {
        if (nx < 0 || ny < 0 || nx >= H || ny >= W) return true;
        return false;
    }
}
