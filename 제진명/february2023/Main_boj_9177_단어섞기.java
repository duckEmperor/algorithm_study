package 제진명.february2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_boj_9177_단어섞기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 1 ; t <= T ; t++) {
            st = new StringTokenizer(br.readLine());

            char [] word1 = st.nextToken().toCharArray();
            char [] word2 = st.nextToken().toCharArray();
            char [] word3 = st.nextToken().toCharArray();

            sb.append("Data set").append(" ").append(t).append(": ");

            sb.append(bfs (word1, word2, word3) ? "yes" : "no").append("\n");
        }

        System.out.print(sb);
    }

    static boolean bfs (char [] word1, char [] word2, char [] word3) {
        Queue<int []> queue = new LinkedList<>();
        boolean visited [][] = new boolean[word1.length + 1][word2.length + 1];
        queue.offer(new int[] {0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int [] now = queue.poll();

            if (now[0] + now[1] == word3.length) {
                return true;
            }

            if (now[0] < word1.length && word3[now[0] + now[1]] == word1[now[0]] && !visited[now[0] + 1][now[1]]) {
                visited[now[0] + 1][now[1]] = true;
                queue.offer(new int [] {now[0] + 1, now[1]});
            }

            if (now[1] < word2.length && word3[now[0] + now[1]] == word2[now[1]] && !visited[now[0]][now[1] + 1]) {
                visited[now[0]][now[1] + 1] = true;
                queue.offer(new int [] {now[0], now[1] + 1});
            }
        }

        return false;

    }
}
