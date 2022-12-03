package 제진명.des2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_boj_13549_숨바꼭질3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        if (M <= N) {
            System.out.println(N - M);
            return;
        }
        System.out.println(bfs(N, M));

        return;
    }

    static int bfs (int N, int M) {
        boolean [] visited = new boolean [M+2];
        Queue<int []> queue = new LinkedList<>();

        queue.offer(new int [] {N, 0});
        visited[N] = true;

        while(!queue.isEmpty()) {
            int [] now = queue.poll();

            if (now[0] == M) {
                return now[1];
            }

            // 순간이동
            if (now[0]*2 <= M+1 && !visited[now[0]*2]) {
                visited[now[0]*2] = true;
                queue.offer(new int[]{now[0] * 2, now[1]});
            }


            // 뒤로 한 칸
            if (now[0] - 1 >= 0 && !visited[now[0]-1]) {
                visited[now[0] - 1] = true;
                queue.offer(new int[]{now[0] - 1, now[1] + 1});
            }

            // 앞으로 한 칸
            if (now[0] + 1 <= M + 1 && !visited[now[0]+1]) {
                visited[now[0] + 1] = true;
                queue.offer(new int[]{now[0] + 1, now[1] + 1});
            }
        }

        return -1;
    }
}
