package 제진명.jenuary2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_boj_2933_미네랄 {

    static int R, C;
    static char [][] map;
    static int [][] minerals;

    static int [][] D = {{0, 1},{0, -1},{1, 0},{-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for (int i = 0 ; i < R ; i++) {
            String s = br.readLine();
            for (int j = 0 ; j < C ; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for (int i = 0 ; i < N ; i++) {
            int L = Integer.parseInt(st.nextToken());

            for (int j = 0 ; j < C ; j++) {
                int row = i % 2 == 0 ? j : C - 1 - j;

                if (map[R - L][row] == 'x') {
                    map[R - L][row] = '.';
                    break;
                }
            }
            bfs();

        }

        for (int i = 0 ; i < R ; i++) {
            for (int j = 0 ; j < C ; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void bfs () {
        Queue<int []> queue = new LinkedList<>();

        minerals = new int[R][C];
        boolean [][] visited = new boolean[R][C];
        int clusterCnt = 0;

        for (int i = R - 1 ; i >= 0 ; i--) {
            for (int j = 0 ; j < C ; j++) {
                if (map[i][j] == '.' || visited[i][j]) continue;
                clusterCnt++;
                if (i == R - 1) clusterCnt = 1;

                visited[i][j] = true;
                minerals[i][j] = clusterCnt;
                queue.offer(new int [] {i, j});
                break;
            }

            while (!queue.isEmpty()) {
                int [] now = queue.poll();

                for (int d = 0 ; d < 4 ; d++) {
                    int nx = now[0] + D[d][0];
                    int ny = now[1] + D[d][1];

                    if (isOverflow(nx, ny) || visited[nx][ny] || map[nx][ny] == '.') continue;

                    visited[nx][ny] = true;
                    minerals[nx][ny] = clusterCnt;
                    queue.offer(new int [] {nx, ny});
                }
            }

        }

        if (clusterCnt == 1) return;



        int [] res = getMinimumGap();

        while (res[1] != Integer.MAX_VALUE) {
            gravity(res[0], res[1]);
            res = getMinimumGap();
        }

    }

    static int [] getMinimumGap () {
        int [] res = new int [2];
        res[1] = Integer.MAX_VALUE;

        for (int j = 0 ; j < C ; j++) {
            int temp = 0;
            for (int i = R - 1 ; i >= 0 ; i--) {
                if (minerals[i][j] == 0) temp++;
                else if (minerals[i][j] == 1) temp = 0;
                else if (minerals[i][j] > 1 && temp < res[1]) {
                    res[0] = minerals[i][j];
                    res[1] = temp;
                }
            }
        }

        return res;
    }

    static void gravity (int n, int gap) {

        Queue<int []> queue = new LinkedList<>();

        for (int i = R - 1 ; i >= 0  ; i--) {
            for (int j = 0 ; j < C ; j++) {
                if (minerals[i][j] == n) queue.offer(new int [] {i, j});
            }
        }

        while (!queue.isEmpty()) {
            int [] now = queue.poll();

            int x = now[0], y = now[1];
            minerals[x][y] = 0;
            minerals[x+gap][y] = 1;
            map[x][y] = '.';
            map[x+gap][y] = 'x';
        }
    }

    static boolean isOverflow (int nx, int ny) {
        if (nx < 0 || ny < 0 || nx >= R || ny >= C) return true;
        return false;
    }
}
