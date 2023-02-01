package 제진명.february2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_boj_14003_가장긴증가하는부분수열5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        int [] dp = new int[N];
        int [] seq = new int [N];
        int maxIdx = 0;
        List<Integer> list = new ArrayList<>();
        list.add(0);

        for (int i = 0 ; i < N ; i++) {
            int now = Integer.parseInt(st.nextToken()) + 1_000_000_001;

            seq[i] = now;

            if (now > list.get(list.size() - 1)) {
                list.add(now);
                dp[i] = list.size() - 1;
                maxIdx = i;
            } else {
                int s = 0;
                int e = list.size() - 1;

                while (s < e) {
                    int mid = (s + e) / 2;

                    if (list.get(mid) >= now) {
                        e = mid;
                    } else {
                        s = mid + 1;
                    }
                }
                list.set(e, now);
                dp[i] = e;
            }
        }

        sb.append(list.size() - 1).append("\n");

        int idx = list.size() - 1;

        Stack<Integer> stack = new Stack<>();

        for (int i = maxIdx ; i >= 0 ; i--) {
            if (dp[i] == idx) {
                stack.push(seq[i] - 1_000_000_001);
                idx--;
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }

        System.out.print(sb);

    }
}
