package 제진명.jenuary2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_boj_1039_교환 {

    static class Node {
        int num, cnt;
        Node (int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
    static int N, K, ans;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        ans = -1;
        bfs();

        System.out.println(ans);
    }

    static void bfs () {
        Queue<Node> queue = new LinkedList();
        queue.offer(new Node(N, 0));

        int len = String.valueOf(N).length();

        boolean [][] visited = new boolean[1_000_000 + 1][K + 1];
        visited[N][0] = true;

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (now.cnt == K) {
                ans = Math.max(ans, now.num);
                continue;
            }

            for (int i = 0 ; i < len - 1 ; i++) {
                for (int j = i + 1 ; j < len ; j++) {
                    int next = swap(now.num, i, j);

                    if (next == -1 || visited[next][now.cnt + 1]) continue;

                    visited[next][now.cnt + 1] = true;
                    queue.offer(new Node(next, now.cnt + 1));
                }
            }
        }


    }

    static int swap (int num, int i, int j) {
        char [] seq = String.valueOf(num).toCharArray();

        // 교환하려는 위치가 첫 번째이고 교환하려는 값이 0이면 -1 리턴
        if (i == 0 && seq[j] == '0') return -1;

        char temp = seq[i];
        seq[i] = seq[j];
        seq[j] = temp;

        return Integer.parseInt(new String(seq));
    }

}
