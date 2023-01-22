package 제진명.jenuary2023;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_boj_2931_가스관 {
    static int R, C;
    static int [][] D = {{0, -1},{1, 0},{0, 1},{-1, 0}}; // 좌 하 우 상
    static char [][] map;
    static Point start, end;
    static int [] ONE = {1, 2};
    static int [] TWO = {2, 3};
    static int [] THREE = {0, 3};
    static int [] FOUR = {0, 1};
    static int [] PLUS = {0, 1, 2, 3};
    static int [] MINERS = {0, 2};
    static int [] VERTICAL_BAR = {1, 3};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R+1][C+1];

        for (int i = 1 ; i <= R ; i++) {
            String s = br.readLine();
            for (int j = 1 ; j <= C ; j++) {
                char c = s.charAt(j - 1);

                if (c == 'M') start = new Point(i, j);
                else if (c == 'Z') end = new Point(i, j);
                map[i][j] = c;
            }
        }

        Point removed = bfs();

        sb.append(removed.x).append(" ").append(removed.y).append(" ");

        // 연결해야하는 관 확인
        ArrayList<Integer> res = new ArrayList<>();
        for (int d = 0 ; d < 4 ; d++) {
            int nx = removed.x + D[d][0];
            int ny = removed.y + D[d][1];

            if (isOverflow(nx, ny) || map[nx][ny] == '.' || map[nx][ny] == 'M' || map[nx][ny] == 'Z') continue;
            if (d == 0 && (map[nx][ny] == '|' || map[nx][ny] == '3' || map[nx][ny] == '4')) continue;
            else if (d == 1 && (map[nx][ny] == '-' || map[nx][ny] == '1' || map[nx][ny] == '4')) continue;
            else if (d == 2 && (map[nx][ny] == '|' || map[nx][ny] == '1' || map[nx][ny] == '2')) continue;
            else if (d == 3 && (map[nx][ny] == '-' || map[nx][ny] == '2' || map[nx][ny] == '3')) continue;

            res.add(d);
        }

        if (res.size() == 4) sb.append("+");
        else if (res.get(0) == 1 && res.get(1) == 2) sb.append("1");
        else if (res.get(0) == 2 && res.get(1) == 3) sb.append("2");
        else if (res.get(0) == 0 && res.get(1) == 3) sb.append("3");
        else if (res.get(0) == 0 && res.get(1) == 1) sb.append("4");
        else if (res.get(0) == 0 && res.get(1) == 2) sb.append("-");
        else if (res.get(0) == 1 && res.get(1) == 3) sb.append("|");

        System.out.print(sb);
    }

    static Point bfs () {
        boolean[][] visited = new boolean[R + 1][C + 1];
        Queue<Point> queue = new LinkedList<>();

        visited[start.x][start.y] = true;

        // 첫 번째 송유관을 Queue에 추가함
        for (int d = 0; d < 4; d++) {
            int nx = start.x + D[d][0];
            int ny = start.y + D[d][1];

            if (isOverflow(nx, ny) || map[nx][ny] == '.' || map[nx][ny] == 'Z') continue;

            visited[nx][ny] = true;
            queue.offer(new Point(nx, ny));
        }

        while (!queue.isEmpty()) {
            Point now = queue.poll();

            int [] loop = null;
            if (map[now.x][now.y] == '1') {
                loop = ONE;
            } else if (map[now.x][now.y] == '2') {
                loop = TWO;
            } else if (map[now.x][now.y] == '3') {
                loop = THREE;
            } else if (map[now.x][now.y] == '4') {
                loop = FOUR;
            } else if (map[now.x][now.y] == '+') {
                loop = PLUS;
            } else if (map[now.x][now.y] == '-') {
                loop = MINERS;
            } else if (map[now.x][now.y] == '|') {
                loop = VERTICAL_BAR;
            }

            for (int d = 0 ; d < loop.length ; d++) {
                int nx = now.x + D[loop[d]][0];
                int ny = now.y + D[loop[d]][1];

                if (isOverflow(nx, ny) || visited[nx][ny] || map[nx][ny] == 'Z') continue;

                if (map[nx][ny] == '.') return new Point(nx, ny);

                visited[nx][ny] = true;
                queue.offer(new Point(nx, ny));
            }
        }

        return new Point(-1, -1);
    }

    static boolean isOverflow (int nx, int ny) {
        return nx < 1 || ny < 1 || nx > R || ny > C;
    }
}

