package 제진명.jenuary2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_boj_1967_트리의지름 {

    static class Node {
        int e, w;

        public Node (int e, int w) {
            this.e = e;
            this.w = w;
        }
    }

    static int N, ans, node;
    static boolean [] visited;
    static ArrayList<Node> [] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        if (N == 1) {
            System.out.println(0);
            return;
        }

        nodes = new ArrayList[N+1];

        for (int i = 1 ; i <= N ; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 1 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            nodes[u].add(new Node(v, w));
            nodes[v].add(new Node(u, w));

        }

        visited = new boolean[N+1];

        dfs(1, 0);
        dfs(node, 0);

        System.out.println(ans);
    }

    static void dfs(int s, int cost) {
        if (ans < cost) {
            ans = cost;
            node = s;
        }

        visited[s] = true;
        for (int i = 0 ; i < nodes[s].size() ; i++) {
            if (visited[nodes[s].get(i).e]) continue;

            dfs(nodes[s].get(i).e , cost + nodes[s].get(i).w);
        }
        visited[s] = false;
    }
}
