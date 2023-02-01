package 제진명.jenuary2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_boj_12886_돌그룹 {
    static int [] stones;
    static int sum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        stones = new int[3];
        sum = 0;

        for (int i = 0 ; i < 3 ; i++) {
            stones[i] = Integer.parseInt(st.nextToken());
            sum += stones[i];
        }
        // 일단 모든 돌의 무게의 합이 3으로 나눠지지 않으면 0 출력
        if (sum %3 != 0) {
            System.out.println(0);
            return;
        }

        Arrays.sort(stones);

        System.out.println(bfs() ? 1 : 0);

    }


    static boolean bfs () {
        Queue<int []> queue = new LinkedList<>();
        boolean [][] visited = new boolean[1501][1501];

        queue.offer(new int [] {stones[0], stones[1], stones[2]});
        visited[stones[0]][stones[1]] = true;

        while (!queue.isEmpty()) {
            int [] now = queue.poll();

            if (now[0] == now[1] && now[1] == now[2]) return true;

            for (int i = 0 ; i < 2 ; i++) {
                for (int j = i + 1 ; j < 3 ; j++) {
                    if (stones[i] == stones[j]) continue;
                    int [] next = new int[3];
                    next[0] = now[i] * 2;
                    next[1] = now[j] - now[i];
                    next[2] = sum - next[0] - next[1];

                    Arrays.sort(next);

                    if (visited[next[0]][next[1]]) continue;

                    visited[next[0]][next[1]] = true;
                    queue.offer(next);
                }
            }

        }

        return false;

    }

}
