package 제진명.jenuary2023;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main_boj_16954_움직이는미로탈출 {
    static char [][] map;
    static int [][] D = {{0, 0},{0, 1},{0, -1},{1, 0},{-1, 0},{1, 1},{1, -1},{-1, 1},{-1, -1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new char[8][8];

        for (int i = 0 ; i < 8 ; i++) {
            String s = br.readLine();
            for (int j = 0 ; j < 8 ; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        if (bfs()) System.out.println(1);
        else System.out.println(0);
    }

    static class Pos {
        Point p;
        int c;
        Pos (Point p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    static boolean bfs () {
        // 모든 map의 결과
        char [][][] _map = new char[9][8][8];
        boolean [][][] visited = new boolean[8][8][9];
        Queue<Pos> queue = new LinkedList<>();

        _map[0] = map;

        for (int i = 1 ; i < 9 ; i++) {
            _map[i] = moveMap(_map[i-1]);
        }

        queue.offer(new Pos(new Point(7, 0), 0));
        visited[7][0][0] = true;

        while (!queue.isEmpty()) {
            Pos now = queue.poll();

            // 캐릭터의 위치가 오른쪽 위 이거나 모든 벽이 사라진 경우
            if (now.c > 7 || (now.p.x == 0 && now.p.y ==7)) {
                return true;
            }

            for (int d = 0 ; d < 9 ; d++) {
                int nx = now.p.x + D[d][0];
                int ny = now.p.y + D[d][1];

                // 현재 이동하는 위치에 벽이 있거나 캐릭터 이동 후 맵이 움직였을 때 해당 위치에 벽이 있는 경우
                if (isOverflow(nx, ny) || visited[nx][ny][now.c + 1] || _map[now.c][nx][ny] == '#' || _map[now.c + 1][nx][ny] == '#') continue;

                visited[nx][ny][now.c + 1] = true;
                queue.offer(new Pos(new Point(nx, ny), now.c + 1));
            }
        }

        return false;
    }

    static char [][] moveMap (char [][] map) {
        char [][] _map = new char[8][8];

        for (int i = 6 ; i >= 0 ; i--) {
            for (int j = 0 ; j < 8 ; j++) {
                _map[i + 1][j] = map[i][j];
            }
        }

        Arrays.fill(_map[0], '.');

        return _map;
    }

    static boolean isOverflow (int nx, int ny) {
        return nx < 0 || ny < 0 || nx >= 8 || ny >= 8;
    }
}
