package 제진명.jenuary2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_boj_3108_로고 {
    static class Box {
        int x1, y1, x2, y2;
        Box (int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
        }
    }

    static int N;
    static Box [] boxes;
    static int [][] D = {{-1, 0 },{1, 0},{0, -1},{0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        boxes = new Box[N + 1];

        boxes[0] = new Box(0,0,0,0);

        for (int i = 1 ; i <= N ; i++) {
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            boxes[i] = new Box(x1, y1, x2, y2);
        }

        System.out.println(bfs());
    }

    static int bfs () {
        Queue<Integer> queue = new LinkedList<>();
        boolean [] visited = new boolean[N+1];

        int cnt = -1;

        for (int i = 0 ; i <= N ; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            queue.offer(i);
            cnt++;

            while (!queue.isEmpty()) {
                int now = queue.poll();

                for (int j = 1; j <= N; j++) {
                    if (now == j || !isOverlap(now, j) || visited[j]) continue;
                    visited[j] = true;
                    queue.offer(j);
                }
            }

        }



        return cnt;
    }

    static boolean isOverlap (int now, int next) {
        Box b1 = boxes[now];
        Box b2 = boxes[next];

        if ((b1.x1 < b2.x1 && b2.x2 < b1.x2 && b1.y1 < b2.y1 && b2.y2 < b1.y2) ||
            (b2.x1 < b1.x1 && b1.x2 < b2.x2 && b2.y1 < b1.y1 && b1.y2 < b2.y2) ||
            b1.x1 > b2.x2 || b2.x1 > b1.x2 || b1.y1 > b2.y2 || b2.y1 > b1.y2) return false;
        return true;
    }
}
