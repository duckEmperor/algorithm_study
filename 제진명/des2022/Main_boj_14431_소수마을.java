package 제진명.des2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_boj_14431_소수마을 {

    static class Pos {
        int x, y;

        Pos (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Node implements Comparable<Node> {
        int e, w;

        public Node (int e, int w) {
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo (Node o) {
            return this.w - o.w;
        }
    }

    static Pos [] positions;
    static int N;
    static ArrayList<Node> [] nodes;
    static boolean [] primeNumber;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Double MaxIndex = Math.floor(Math.sqrt(Math.pow(6000, 2) + Math.pow(6000, 2)));
        primeNumber = new boolean[MaxIndex.intValue()];
        Arrays.fill(primeNumber, true);

        primeNumber[0] = false;
        primeNumber[1] = false;

        makePrimeNumber();

        N = Integer.parseInt(br.readLine());

        positions = new Pos[N+2];

        positions[0] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        positions[1] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        for (int i = 2 ; i < N+2 ; i++) {
            st = new StringTokenizer(br.readLine());

            positions[i] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        nodes = new ArrayList[N+2];

        for (int i = 0 ; i < N+2 ; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0 ; i < N+2 ; i++) {
            for (int j = i + 1 ; j < N+2 ; j++) {
                int x1 = positions[i].x;
                int x2 = positions[j].x;
                int y1 = positions[i].y;
                int y2 = positions[j].y;

                Double dist = Math.floor(Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)));

                if (!primeNumber[dist.intValue()]) continue;

                nodes[i].add(new Node(j, dist.intValue()));
                nodes[j].add(new Node(i, dist.intValue()));
            }
        }

        System.out.println(dijkstra());

    }

    static int dijkstra () {
        int [] dist = new int [N+2];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Node> queue = new PriorityQueue<>();
        dist[0] = 0;

        queue.offer(new Node(0, 0));

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (dist[now.e] < now.w) continue;

            for (int i = 0 ; i < nodes[now.e].size() ; i++) {
                Node next = nodes[now.e].get(i);

                if (dist[next.e] <= dist[now.e] + next.w) continue;

                dist[next.e] = dist[now.e] + next.w;
                queue.offer(new Node(next.e, dist[next.e]));
            }
        }

        return dist[1] == Integer.MAX_VALUE ? -1 : dist[1];
    }

    static void makePrimeNumber () {
        for (int i = 2 ; i < primeNumber.length; i++) {
            if (!primeNumber[i]) continue;
            for (int j = i + i ; j < primeNumber.length ; j += i) {
                primeNumber[j] = false;
            }
        }
    }
}
