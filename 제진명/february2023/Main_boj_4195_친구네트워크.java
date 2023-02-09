package 제진명.february2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_boj_4195_친구네트워크 {
    static int [] parents;
    static int [] cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 1 ; t <= T ; t++) {
            int F = Integer.parseInt(br.readLine());
            HashMap<String, Integer> map = new HashMap<>();
            parents = new int[200000];
            cnt = new int[200000];

            for (int f = 0 ; f < F ; f++) {
                st = new StringTokenizer(br.readLine());

                String p1 = st.nextToken();
                String p2 = st.nextToken();

                if (!map.containsKey(p1)) {
                    map.put(p1, map.size());
                    parents[map.get(p1)] = map.get(p1);
                    cnt[map.get(p1)]= 1;
                }

                if (!map.containsKey(p2)) {
                    map.put(p2, map.size());
                    parents[map.get(p2)] = map.get(p2);
                    cnt[map.get(p2)] = 1;
                }

                union(map.get(p1), map.get(p2));

                sb.append(cnt[parents[map.get(p1)]]).append("\n");
            }
        }

        System.out.print(sb);
    }

    static void union (int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parents[b] = a;
            cnt[a] += cnt[b];
        };
    }

    static int find (int a) {
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }
}
