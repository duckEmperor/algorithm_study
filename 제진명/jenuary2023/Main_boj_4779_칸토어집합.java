package 제진명.jenuary2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_boj_4779_칸토어집합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input;
        while ((input = br.readLine()) != null && !input.isEmpty()) {
            int N = Integer.parseInt(input);

            split((int) Math.pow(3, N), sb);
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void split (int N, StringBuilder sb) {
        if (N == 1) {
            sb.append("-");
            return;
        }
        for (int i = 0 ; i < 3 ; i++) {
            if (i%2 == 0) {
                split(N / 3, sb);
            } else {
                for (int j = 0 ; j < N / 3 ; j++) {
                    sb.append(" ");
                }
            }
        }
    }
}
