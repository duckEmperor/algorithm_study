package 제진명.july2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_boj_7569_토마토 {
    static class Pos implements Comparable<Pos> {
        int x,y,z,day;
        public Pos(int x, int y, int z, int day) {
            // TODO Auto-generated constructor stub
            this.x = x;
            this.y = y;
            this.z = z;
            this.day = day;
        }

        @Override
        public int compareTo(Pos o) {
            return this.day - o.day;
        }
    }

    static int N, M, H;
    static boolean [][][] visited;
    static int [][][] tomatos;
    static int [][] D = {{0,0, 1},{0,0,-1},{0,1,0},{0,-1,0},{1,0,0},{-1,0,0}};
    static PriorityQueue<Pos> queue;
    static int amount, count, ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        visited = new boolean[H][M][N];
        tomatos = new int[H][M][N];

        queue = new PriorityQueue<Pos>();
        amount = 0;
        count = 0;

        for (int i = 0 ; i < H ; i++) {
            for (int j = 0 ; j < M ; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0 ; k < N ; k++) {
                    tomatos[i][j][k] = Integer.parseInt(st.nextToken());

                    if (tomatos[i][j][k] == 1) {
                        queue.add(new Pos(i, j, k, 0));
                        visited[i][j][k] = true;
                    }
                    if (tomatos[i][j][k] == 0) {
                        amount += 1;
                    }
                }
            }
        }
        if (amount == 0) {
            System.out.println(0);
            return;
        }

        bfs();
        if (amount != count) System.out.println(-1);
        else System.out.println(ans);
    }

    static void bfs() {
        while(!queue.isEmpty()) {
            Pos now = queue.poll();
            ans = Math.max(ans, now.day);

            for (int d = 0 ; d < 6 ; d++) {
                int nx = now.x + D[d][0];
                int ny = now.y + D[d][1];
                int nz = now.z + D[d][2];

                if (nx < 0 || ny < 0 || nz < 0 || nx >= H || ny >= M || nz >= N || visited[nx][ny][nz] || tomatos[nx][ny][nz] != 0) continue;

                queue.add(new Pos(nx, ny, nz, now.day+1));
                visited[nx][ny][nz] = true;
                count++;
            }
        }
    }
}