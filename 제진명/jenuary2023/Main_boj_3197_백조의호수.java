package 제진명.jenuary2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_boj_3197_백조의호수 {

    static class Pos implements Comparable<Pos>{
        int x, y, d;

        Pos (int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        @Override
        public int compareTo (Pos o) {
            return this.d - o.d;
        }
    }

    static int R, C;
    static int [][] count;
    static ArrayList<int []> birds;
    static int [][] D = {{0, 1},{0, -1},{1, 0},{-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        count = new int[R][C];
        birds = new ArrayList<>();

        for (int i = 0 ; i < R ; i++) {
            String s = br.readLine();
            for (int j = 0 ; j < C ; j++) {
                char c = s.charAt(j);
                if (c == '.') {
                    count[i][j] = 0;
                } else if (c == 'X') {
                    count[i][j] = Integer.MAX_VALUE;
                } else if (c == 'L') {
                    birds.add(new int [] {i, j});
                }
            }
        }

        bfs();

        System.out.println(findOtherBird());

    }

    static void bfs () {
        PriorityQueue<Pos> queue = new PriorityQueue<>();

        for (int i = 0 ; i < R ; i++) {
            for (int j = 0 ; j < C ; j++) {
                if (count[i][j] == 0) queue.offer(new Pos(i, j, 0));
            }
        }

        while(!queue.isEmpty()) {
            Pos now = queue.poll();

            for (int d = 0 ; d < 4 ; d++) {
                int nx = now.x + D[d][0];
                int ny = now.y + D[d][1];

                if (isOverflow(nx, ny) || count[nx][ny] <= now.d + 1) continue;

                count[nx][ny] = now.d + 1;
                queue.offer(new Pos (nx, ny, now.d + 1));
            }
        }

    }

    static boolean isOverflow (int nx, int ny) {
        if (nx < 0 || ny < 0 || nx >= R || ny >= C) return true;
        return false;
    }

    static int findOtherBird () {
        PriorityQueue<Pos> queue = new PriorityQueue<>();

        boolean [][] visited = new boolean[R][C];

        queue.offer(new Pos(birds.get(0)[0], birds.get(0)[1], 0));
        visited[birds.get(0)[0]][birds.get(0)[1]] = true;

        while(!queue.isEmpty()) {
            Pos now = queue.poll();

            if (now.x == birds.get(1)[0] && now.y == birds.get(1)[1]) return now.d;

            for (int d = 0 ; d < 4 ; d++) {
                int nx = now.x + D[d][0];
                int ny = now.y + D[d][1];

                if (isOverflow(nx, ny) || visited[nx][ny]) continue;

                visited[nx][ny] = true;
                queue.offer(new Pos(nx, ny, Math.max(now.d, count[nx][ny])));
            }
        }

        return -1;
    }
}
