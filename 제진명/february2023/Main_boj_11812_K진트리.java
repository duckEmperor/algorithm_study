package 제진명.february2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_boj_11812_K진트리 {

    static class Node {
        int d;
        long pos;
        Node (int d, long pos) {
            this.d = d;
            this.pos = pos;
        }

        @Override
        public String toString() {
            return "차수 : " + this.d + " 위치 : " + this.pos;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 자신이 속한 차수 이전의 노드 개수
        // root + K + K^2 + K^3 ...
        // 자신의 부모가 속한 차수의 위치
        // 자신의 위치 / 3

        long N = Long.parseLong(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        ArrayList<Long> depth = new ArrayList<>();
        depth.add(0L);

        if (K != 1) {
            int d = 0;
            long temp = 0;
            while (temp < N) {
                long next = (long) Math.pow(K, d);
                depth.add(temp + next);
                temp += next;
                d++;
            }
        }

        for (int i = 0 ; i < Q ; i++) {
            st = new StringTokenizer(br.readLine());

            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());

            if (K == 1) {
                sb.append(Math.max(x, y) - Math.min(x, y)).append("\n");
                continue;
            }

            Node s = null;
            Node e = null;

            for (int d = 1 ; d < depth.size() ; d++) {
                if (s == null && x - depth.get(d) <= 0) {
                    s = new Node(d, x - depth.get(d - 1) - 1);
                }
                if (e == null && y - depth.get(d) <= 0) {
                    e = new Node(d, y - depth.get(d - 1) - 1);
                }

                if (s != null && e != null) break;
            }

            int cnt = 0;

            if (s.d > e.d) {
                while (s.d != e.d) {
                    s = new Node(s.d - 1, s.pos/K);
                    cnt++;
                }
            } else if (s.d < e.d) {
                while (s.d != e.d) {
                    e = new Node(e.d - 1, e.pos/K);
                    cnt++;
                }
            }

            while (s.pos != e.pos) {
                s = new Node(s.d - 1, s.pos / K);
                e = new Node(e.d - 1, e.pos / K);
                cnt += 2;
            }

            sb.append(cnt).append("\n");

        }

        System.out.print(sb);
    }
}
