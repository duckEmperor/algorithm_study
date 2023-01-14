package 제진명.jenuary2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_boj_6580_쿼드트리 {
    static char [][] bits;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 첫 줄에서 N 추출
        // 필요 없는 정보 버리기
        st.nextToken();st.nextToken();
        int N = Integer.parseInt(st.nextToken());
        // 둘째, 셋째 줄 버리기
        br.readLine();
        br.readLine();

        bits = new char[N][N];

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine(), ",");
            for (int j = 0 ; j < N / 8 ; j++) {
                String hex = st.nextToken();
                int res = hexToInteger(hex);

                String binary = "";
                for (int k = 7 ; k >= 0 ; k--) {
                    if (res >= Math.pow(2, k)) {
                        res -= Math.pow(2, k);
                        binary += "1";
                    } else binary += "0";
                }

                for (int k = 7 ; k >= 0 ; k--) {
                    if (binary.charAt(k) == '1') bits[i][(7 - k) + (8 * j)] = 'B';
                    else bits[i][(7 - k) + (8 * j)] = 'W';
                }
            }
        }
        br.readLine();

        System.out.println(N);
        System.out.println(quadTree(N, 0, 0));
    }

    static String quadTree (int k, int x, int y) {
        if (k == 1) {
            return String.valueOf(bits[x][y]);
        }

        String res = "";

        for (int i = 0 ; i < 4 ; i++) {
            res += quadTree(k / 2, x + (i/2)*(k/2), y + (i%2)*(k/2));
        }

        int temp = 0;

        for (int i = 0 ; i < res.length() ; i++) {
            temp += res.charAt(i);
        }
        if (res.charAt(0) == temp / 4) return String.valueOf(res.charAt(0));
        else return "Q" + res;
    }

    static int hexToInteger (String hex) {
        StringTokenizer st = new StringTokenizer(hex, "x");
        st.nextToken();
        hex = st.nextToken();
        return Integer.parseInt(hex, 16);
    }
}
