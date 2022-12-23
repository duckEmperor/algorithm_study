package 제진명.des2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_boj_14495_피보나치비스무리한수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        if (N < 4) {
            System.out.println(1);
            return;
        }

        long [] seq = new long [N + 1];

        seq[1] = 1;
        seq[2] = 1;
        seq[3] = 1;

        for (int i = 4 ; i <= N ; i++) {
            seq[i] = seq[i-1] + seq[i-3];
        }

        System.out.println(seq[N]);
    }
}
