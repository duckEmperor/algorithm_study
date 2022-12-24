package 제진명.des2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_boj_3063_게시판 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0 ; t < T ; t++) {
            st = new StringTokenizer(br.readLine());

            int [] x = new int [4];
            int [] y = new int [4];

            for (int i = 0 ; i < 8 ; i++) {
                if (i % 2 == 0) x[i/2] = Integer.parseInt(st.nextToken());
                else y[i/2] = Integer.parseInt(st.nextToken());
            }

            int s = (x[1] - x[0])*(y[1] - y[0]);
            // 겹치는 부분의 넓이
            // x[3]가 x[0]보다 작거나 x[2]가 x[1]보다 크면 겹치는 부분이 없음
            // y[3]가 y[0]보다 작거나 y[2]가 y[1]보다 크면 겹치는 부분이 없음
            if (x[3] <= x[0] || x[2] >= x[1] || y[3] <= y[0] || y[2] >= y[1]);
            else s -= (Math.min(x[3], x[1]) - Math.max(x[2], x[0])) * (Math.min(y[3], y[1]) - Math.max(y[2], y[0]));

            sb.append(s + "\n");
        }

        System.out.println(sb);
    }
}
