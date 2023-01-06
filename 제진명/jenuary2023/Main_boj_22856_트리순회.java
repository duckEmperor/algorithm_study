package 제진명.jenuary2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_boj_22856_트리순회 {

    static class Node {
        int l, r;
        public Node (int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    static int N, answer, cnt;
    static Node [] nodes;
    static boolean passByRoot;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        nodes = new Node[N];

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken()) - 1;
            int l = Integer.parseInt(st.nextToken()) - 1;
            int r = Integer.parseInt(st.nextToken()) - 1;

            nodes[n] = new Node(l, r);
        }
        answer = -1;
        inOrder(0);

        System.out.println(answer);

    }

    static void inOrder (int n) {
        // 현재 노드로 들어오면 이동 횟수, 방문 노드 개수 +1
        answer++;
        cnt++;
        // 더 이상 이동할 노드가 없으면 return
        if (nodes[n].l < 0 && nodes[n].r < 0) {
            return;
        }

        // 왼쪽에 이동할 노드가 있으면 이동
        if (nodes[n].l >= 0) {
            inOrder(nodes[n].l);
            // 왼쪽 노드 방문 후 원래 노드로 돌아오는 이동 횟수 +1
            answer++;
        };
        if (n == 0) passByRoot = true;
        // 오른쪽에 이동할 노드가 있으면 이동
        if (nodes[n].r >= 0) {
            inOrder(nodes[n].r);
            // 오른쪽 노드 방문 후 원래 노드로 돌아오는 이동 횟수 +1
            // 단, 트리의 루트를 이미 방문했고, 모든 노드를 방문 했을 경우에는 중위 순회가 끝났으므로 이동 횟수 추가하지 않음.
            if (!passByRoot || cnt < N) {
                answer++;
            }

        };
    }
}
