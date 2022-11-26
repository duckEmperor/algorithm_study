package 제진명.nov2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Programmers_안전지대 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        input = input.substring(2, input.length() - 2);


        StringTokenizer st = new StringTokenizer(input, "], [");

        int N = (int) Math.round(Math.sqrt(st.countTokens()));
        int [][] board = new int[N][N];

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution(board));
    }

    static int solution(int[][] board) {
        Queue<int []> queue = new LinkedList<>();
        int [][] D = {{0, 1},{0, -1},{1, 0},{-1, 0},{1, 1},{1, -1},{-1, 1},{-1, -1}};
        boolean [][] visited = new boolean[board.length][board.length];

        for (int i = 0 ; i < board.length ; i++) {
            for (int j = 0 ; j < board.length ; j++) {
                if (board[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }


        int cnt = queue.size();
        while(!queue.isEmpty()) {
            int [] now = queue.poll();

            for (int d = 0 ; d < 8 ; d++) {
                int nx = now[0] + D[d][0];
                int ny = now[1] + D[d][1];

                if (checkOverflow(nx, ny, board.length) || visited[nx][ny]) continue;

                visited[nx][ny] = true;
                cnt++;
            }
        }

        return board.length*board.length - cnt;
    }

    static boolean checkOverflow (int nx, int ny, int N) {
        if (nx < 0 || ny < 0 || nx >= N || ny >= N) return true;
        return false;
    }
}
