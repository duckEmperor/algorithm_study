package 제진명.june2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_boj_1477_휴게소세우기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int [] rests = new int [N + 2];

        rests[0] = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 1 ; i <= N ; i++) {
            rests[i] = Integer.parseInt(st.nextToken());
        }
        rests[N + 1] = L;

        Arrays.sort(rests);

        int min = 0;
        int max = L;

        while (min <= max) {
            int mid = (min + max) / 2;
            int temp = 0;
            for (int i = 0 ; i < N + 1 ; i++) {
                temp += (rests[i + 1] - rests[i] - 1)/mid;
            }
            if (temp > M) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }

        System.out.println(min);
    }

}
