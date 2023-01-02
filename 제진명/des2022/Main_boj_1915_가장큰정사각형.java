package 제진명.des2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_boj_1915_가장큰정사각형 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n,m;

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int [][] matrix = new int[n+1][m+1];

        int ans = 0;

        for (int i = 1 ; i <= n ; i++) {
            String s = br.readLine();
            for (int j = 1 ; j <= m ; j++) {
                int num = s.charAt(j-1) - '0';
                if (i == 1 && j == 1) {
                    matrix[i][j] = num;
                    ans = num;
                } else {
                    if (num == 1) {
                        matrix[i][j] = Math.min(matrix[i - 1][j - 1], Math.min(matrix[i - 1][j], matrix[i][j - 1])) + 1;
                        ans = Math.max(ans, matrix[i][j]);
                    }
                }

            }
        }

        System.out.println(ans*ans);
    }
}
