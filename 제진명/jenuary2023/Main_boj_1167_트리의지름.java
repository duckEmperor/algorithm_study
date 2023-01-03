package 제진명.jenuary2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_boj_1167_트리의지름 {

    static class Node {
        int e, w;
        public Node (int e, int w) {
            this.e = e;
            this.w = w;
        }
    }

    static int N, answer, max;
    static ArrayList<Node> [] nodes;
    static boolean [] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        nodes = new ArrayList[N+1];

        for (int i = 1 ; i <= N ; i++) nodes[i] = new ArrayList<>();

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());

            while(true) {
                int e = Integer.parseInt(st.nextToken());

                if (e == -1) break;

                int w = Integer.parseInt(st.nextToken());

                nodes[s].add(new Node(e, w));
            }
        }
        answer = -1;

        visited = new boolean[N+1];
        dfs(1, 0);

        visited = new boolean[N+1];
        dfs(max, 0);

        System.out.println(answer);
    }

    static void dfs (int s, int w) {
        if (w > answer) {
            answer = w;
            max = s;
        }

        visited[s] = true;
        for (int i = 0 ; i < nodes[s].size() ; i++) {
            if (visited[nodes[s].get(i).e]) continue;
            dfs(nodes[s].get(i).e, w + nodes[s].get(i).w);
        }
        visited[s] = false;
    }
}
