package 제진명.jenuary2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_boj_1991_트리순회 {
    static class Node {
        int l, r;
        public Node (int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    static Node [] nodes;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        nodes = new Node[N];

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());

            int n = st.nextToken().charAt(0) - 'A';
            int l = st.nextToken().charAt(0) - 'A';
            int r = st.nextToken().charAt(0) - 'A';

            nodes[n] = new Node(l, r);
        }

        preOrder(0);
        sb.append("\n");
        inOrder(0);
        sb.append("\n");
        postOrder(0);

        System.out.println(sb);
    }

    static void preOrder (int p) {
        if (nodes[p].r < 0 && nodes[p].l < 0) {
            sb.append((char) (p + 'A'));
            return;
        }

        sb.append((char) (p + 'A'));
        if (nodes[p].l > -1) preOrder (nodes[p].l);
        if (nodes[p].r > -1) preOrder (nodes[p].r);

    }

    static void inOrder (int p) {
        if (nodes[p].r < 0 && nodes[p].l < 0) {
            sb.append((char) (p + 'A'));
            return;
        }

        if (nodes[p].l > -1) inOrder (nodes[p].l);
        sb.append((char) (p + 'A'));
        if (nodes[p].r > -1) inOrder (nodes[p].r);

    }

    static void postOrder (int p) {
        if (nodes[p].r < 0 && nodes[p].l < 0) {
            sb.append((char) (p + 'A'));
            return;
        }

        if (nodes[p].l > -1) postOrder (nodes[p].l);
        if (nodes[p].r > -1) postOrder (nodes[p].r);
        sb.append((char) (p + 'A'));
    }
}
