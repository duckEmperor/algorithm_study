package 제진명.nov2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_boj_21609_상어중학교 {
    static int N, M, ans;
    static int [][] map;
    static int [][] D = {{0, -1},{1, 0},{0, 1},{-1, 0}};
    static int amount;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        amount = N*N;
        map = new int [N][N];

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int  j = 0 ; j < N ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) amount--;
            }
        }

        ans = 0;
        while (amount != 0) {
            if(!findLargestBlock()) break;
            gravity();
            map = rotateMap();
            gravity();
        }

//        printMap();

        System.out.println(ans);

    }

    static int [][] rotateMap() {
        int [][] newMap =  new int [N][N];
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
               newMap[N-i-1][j] = map[j][i];
            }
        }
        return newMap;
    }

    static void gravity() {
        for (int i = 0 ; i < N ; i++) {
            for (int j = N-2 ; j >= 0 ; j--) {
                if (map[j][i] > -1) {
                    int y = j;
                    for (int k = j+1 ; k < N ; k++) {
                        if (map[k][i] == -2) {
                            y = k;
                        } else break;
                    }
                    if (j != y) {
                        map[y][i] = map[j][i];
                        map[j][i] = -2;
                    }
                }
            }
        }

    }

    static boolean findLargestBlock() {
        boolean [][] visited = new boolean[N][N];
        Queue<int []> queue = new LinkedList<>();
        int cnt = 0;
        int x = -1;
        int y = -1;
        int rainbowBlockCnt = 0;

        for (int i = 0 ; i < N ; i++) {
            for (int j= 0 ; j < N ; j++) {
                if (map[i][j] > 0 && !visited[i][j]) {
                    int temp = 1;
                    int rainbowTemp = 0;
                    visited[i][j] = true;
                    queue.offer(new int [] {i, j});
                    while (!queue.isEmpty()) {
                        int [] now = queue.poll();

                        for (int d = 0 ; d < 4 ; d++) {
                            int nx = now[0] + D[d][0];
                            int ny = now[1] + D[d][1];

                            if (isOverflow(nx, ny) || (map[nx][ny] != 0 && map[nx][ny] != map[i][j]) || visited[nx][ny]) continue;

                            visited[nx][ny] = true;
                            temp++;
                            if(map[nx][ny] == 0) rainbowTemp++;
                            queue.offer(new int [] {nx, ny});
                        }
                    }

//                    System.out.println(map[i][j] + " cnt:" + temp);

                    if (temp > cnt) {
                        cnt = temp;
                        x = i; y = j;
                        rainbowBlockCnt = rainbowTemp;
                    } else if (temp == cnt && rainbowBlockCnt < rainbowTemp) {
                        rainbowBlockCnt = rainbowTemp;
                        x = i; y = j;
                    } else if (temp == cnt && rainbowBlockCnt == rainbowTemp) {
                        x = i; y = j;
                    }
                }
            }
        }

        if (cnt < 2) return false;

        removeBlock(x, y);
        calculate(cnt);
        amount -= cnt;

        return true;

    }


    static void calculate(int cnt) {
        ans += cnt * cnt;
    }

    static void removeBlock(int x, int y) {
        Queue<int []> queue = new LinkedList<>();
        boolean [][] visited = new boolean[N][N];
        visited[x][y] = true;
        queue.offer(new int [] {x, y});
        int selectedNum = map[x][y];

        while(!queue.isEmpty()) {
            int [] now = queue.poll();
            map[now[0]][now[1]] = -2;

            for (int d = 0 ; d < 4 ; d++) {
                int nx = now[0] + D[d][0];
                int ny = now[1] + D[d][1];

                if (isOverflow(nx, ny) || (map[nx][ny] != 0 && map[nx][ny] != selectedNum) || visited[nx][ny]) continue;

                visited[nx][ny] = true;
                queue.offer(new int [] {nx, ny});
            }
        }
    }
    static boolean isOverflow(int nx, int ny) {
        if (nx < 0 || ny < 0 || nx >= N || ny >= N) return true;
        return false;
    }

    static void printMap() {
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

