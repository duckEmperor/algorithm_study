package 제진명.jenuary2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_boj_10217_KCMTravel {
    static class Ticket implements Comparable<Ticket> {
        int v, c, d;
        Ticket (int v, int c, int d) {
            this.v = v;
            this.c = c;
            this.d = d;
        }

        @Override
        public int compareTo (Ticket o) {
            return this.d - o.d;
        }
    }

    static ArrayList<Ticket> [] tickets;
    static int N, M;
    static final int INF = 100_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0 ; t < T ; t++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            tickets = new ArrayList[N+1];

            for (int i = 1 ; i <= N ; i++) {
                tickets[i] = new ArrayList<>();
            }

            for (int i = 0 ; i < K ; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                boolean isAdd = true;

                for (int j = 0 ; j < tickets[u].size() ; j++) {
                    Ticket ticket = tickets[u].get(j);
                    if (ticket.v == v && (ticket.c <= c && ticket.d <= d)) {
                        isAdd = false;
                        break;
                    }
                }

                if(isAdd) tickets[u].add(new Ticket(v, c, d));
            }

            int res = bfs();

            sb.append(res == INF ? "Poor KCM" : res).append("\n");
        }
        System.out.print(sb);
    }

    static int bfs () {
        Queue<Integer> queue = new LinkedList<>();
        int [][] dp = new int[N+1][M+1];

        for (int i = 2 ; i <= N ; i++) {
            for (int j = 0 ; j <= M ; j++) {
                dp[i][j] = INF;
            }
        }

        queue.offer(1);

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int i = 0 ; i < tickets[now].size() ; i++) {
                int v = tickets[now].get(i).v;
                int c = tickets[now].get(i).c;
                int d = tickets[now].get(i).d;
                boolean flag = false;

                for (int j = c ; j <= M ; j++) {
                    // 출발지의 비용 - 이번 티켓에 소요되는 비용의 값과 원래의 비용을 비교
                    if (dp[now][j - c] + d < dp[v][j]) {
                        dp[v][j] = dp[now][j - c] + d;
                        flag = true;
                    }
                }

                if (flag) queue.offer(v);
            }
        }

        return dp[N][M];
    }
}
