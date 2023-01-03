package 제진명.jenuary2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_boj_11725_트리의부모찾기 {
    static int N;
    static ArrayList<Integer> [] nodes;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        nodes = new ArrayList[N + 1];

        for (int i = 0 ; i <= N ; i++) {
            nodes[i] = new ArrayList<>();
        }
        for (int i = 1 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            nodes[u].add(v);
            nodes[v].add(u);
        }

        Queue<Integer> queue = new LinkedList<>();

        queue.offer(1);

        int [] parent = new int [N + 1];

        while(!queue.isEmpty()) {
            int now = queue.poll();

            for (int i = 0 ; i < nodes[now].size() ; i++) {
                int next = nodes[now].get(i);

                if (parent[next] != 0 || next == 1) continue;

                parent[next] = now;

                queue.offer(next);
            }
        }

        for (int i = 2 ; i <= N ; i++) {
            sb.append(parent[i]+"\n");
        }

        System.out.print(sb);

    }
}
