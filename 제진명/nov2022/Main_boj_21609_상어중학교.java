package 제진명.nov2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_boj_21609_상어중학교 {

    static class Pos {
        int x, y;
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N, M, ans;
    static int [][] map;
    static int [][] D = {{0, 1},{0, -1},{1, 0},{-1, 0}};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int [N][N];

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < N ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(findLargestBlock());

        System.out.println(ans);
    }

    static boolean findLargestBlock() {
        boolean [][] visited = new boolean[N][N];
        Queue<Pos> queue = new LinkedList<>();

        int largestBlockCnt = 0;
        int includeRainbowBlockCnt = 0;
        Pos pos = null;

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                // 행 우선, 열 나중으로 반복문 순회하며 검정 블록과 무지개 불록이 아닌 블록 탐색
                // 검정 블록 => -1 , 무지개 블록 => 0 이므로 0보다 블록이 숫자 블록, 이미 확인한 숫자 블록은 제외
                if (map[i][j] <= 0 || visited[i][j]) continue;

                int currentBlocksCnt = 1;
                int currentRainbowBlocksCnt = 0;
                visited[i][j] = true;
                queue.offer(new Pos(i, j));

                while (!queue.isEmpty()) {
                    Pos now = queue.poll();

                    for (int d = 0 ; d < 4 ; d++) {
                        int nx = now.x + D[d][0];
                        int ny = now.y + D[d][1];

                        if (checkOverflow(nx, ny) || visited[nx][ny] || (map[nx][ny] != 0 && map[nx][ny] != map[i][j])) continue;

                        visited[nx][ny] = true;
                        currentBlocksCnt++;
                        if (map[nx][ny] == 0) currentRainbowBlocksCnt++;

                        queue.offer(new Pos(nx, ny));
                    }
                }

                // 다 돌고 나면 무지개 블록의 방문을 해제
                for (int k = 0 ; k < N ; k++) {
                    for (int l = 0 ; l < N ; l++) {
                        if (map[k][l] == 0) visited[k][l] = false;
                    }
                }

                if (currentBlocksCnt >= 2) {
                    // 현재 연속된 블록의 개수가 기존 블록의 개수보다 많은 경우
                    // 블록의 최대 개수, 무지개 블록 개수, 좌표를 현재 값으로 초기화
                    if (currentBlocksCnt > largestBlockCnt) {
                        pos = new Pos(i, j);
                        largestBlockCnt = currentBlocksCnt;
                        includeRainbowBlockCnt = currentRainbowBlocksCnt;
                    }
                    // 현재 연속된 블록의 개수가 기존 블로그이 개수와 동일한 경우
                    else if (currentBlocksCnt == largestBlockCnt) {
                        // 현재 블록들에 포함된 무지개 블록 개수가 기존 무지개 블록 개수보다 많을 경우
                        // 무지개 블록 개수를 초기화 하고 좌표를 현재 값으로 초기화
                        if (currentRainbowBlocksCnt > includeRainbowBlockCnt) {
                            includeRainbowBlockCnt = currentRainbowBlocksCnt;
                            pos = new Pos(i, j);
                        }
                        // 현재 블록들에 포함된 무지개 블록 개수가 기존 무지개 블록 개수와 동일한 경우
                        // 좌표만 초기화
                        else if (currentRainbowBlocksCnt == includeRainbowBlockCnt) {
                            pos = new Pos(i, j);
                        }
                    }
                }
            }
        }

        if (pos != null) {
            ans += largestBlockCnt * largestBlockCnt;
            removeBlocks(pos);
            gravity();
            map = rotateMap();
            gravity();
            return true;
        }
        else return false;

    }

    static void gravity() {
        for (int i = 0 ; i < N ; i++) {
            for (int j = N-2 ; j >= 0 ; j--) {
                if (map[j][i] < 0) continue;

                int nx = j;

                for (int k = j + 1 ; k < N ; k++) {
                    if (map[k][i] != -2) break;
                    nx = k;
                }

                if (nx == j) continue;

                map[nx][i] = map[j][i];
                map[j][i] = -2;
            }
        }
    }

    static void removeBlocks(Pos pos) {
        Queue<Pos> queue = new LinkedList<>();
        boolean [][] visited = new boolean[N][N];
        visited[pos.x][pos.y] = true;
        int blockNumber = map[pos.x][pos.y];
        map[pos.x][pos.y] = -2;
        queue.offer(pos);

        while(!queue.isEmpty()) {
            Pos now = queue.poll();

            for (int d = 0 ; d < 4 ; d++) {
                int nx = now.x + D[d][0];
                int ny = now.y + D[d][1];

                if (checkOverflow(nx, ny) || visited[nx][ny] || (map[nx][ny] != 0 && map[nx][ny] != blockNumber)) continue;

                visited[nx][ny] = true;
                map[nx][ny] = -2;
                queue.offer(new Pos(nx, ny));
            }
        }
    }

    static int[][] rotateMap (){
        int [][] newMap = new int[N][N];

        for (int j = 0 ; j < N ; j++) {
            for (int i = 0 ; i < N ; i++) {
                newMap[N-1-j][i] = map[i][j];
            }
        }

        return newMap;
    }

    static boolean checkOverflow(int nx, int ny) {
        if (nx < 0 || ny < 0 || nx >= N || ny >= N) return true;
        return false;
    }

}
