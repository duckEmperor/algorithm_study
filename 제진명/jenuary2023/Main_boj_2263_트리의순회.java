package 제진명.jenuary2023;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_boj_2263_트리의순회 {
    static int N;
    static int [] inOrder, postOrder;
    static Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        inOrder = new int [N];
        for (int i = 0 ; i < N ; i++) {
            inOrder[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        postOrder = new int [N];

        for (int i = 0 ; i < N ; i++) {
            postOrder[i] = Integer.parseInt(st.nextToken());
        }

        makePreOrder(0, N, 0, N);

        while (!queue.isEmpty()) {
            sb.append(queue.poll() + " ");
        }

        System.out.println(sb);
    }

    static void makePreOrder (int s1, int e1, int s2, int e2) {
        if (s1 >= e1 || s2 >= e2) return;
        // inOrder에서의 root index
        int root = findRootNode(s1, e1 , postOrder[e2 - 1]);
        // 왼쪽
        makePreOrder(s1, root, s2, s2 + root - s1);
        // 오른쪽
        makePreOrder(root + 1, e1, s2 + root - s1 , e2 - 1);
    }

    static int findRootNode (int s, int e, int r) {
        for (int i = s ; i < e ; i++) {
            if (inOrder[i] == r) {
                queue.offer(r);
                return i;
            };
        }
        return -1;
    }
}