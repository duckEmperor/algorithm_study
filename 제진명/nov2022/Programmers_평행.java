package 제진명.nov2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Programmers_평행 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String input = br.readLine();

        input = input.substring(2, input.length() - 2);
        st = new StringTokenizer(input, "], [");

        int [][] dots = new int[st.countTokens()/2][2];

        for (int i = 0 ; i < dots.length ; i++) {
            dots[i][0] = Integer.parseInt(st.nextToken());
            dots[i][1] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(dots));
    }

    static int solution (int[][] dots) {

        // 1, 2번 / 3, 4번
        if (getGradient(dots[0], dots[1]) ==  getGradient(dots[2], dots[3])) return 1;

        // 1, 3번 / 2, 4번
        if (getGradient(dots[0], dots[2]) ==  getGradient(dots[1], dots[3])) return 1;

        // 1, 4번 / 2, 3번
        if (getGradient(dots[0], dots[3]) ==  getGradient(dots[1], dots[2])) return 1;

        return 0;
    }

    static float getGradient(int [] dot1, int [] dot2) {
        float gradient = Math.abs((float)dot1[1] - (float) dot2[1])/Math.abs((float)dot1[0] - (float) dot2[0]);

        return gradient;
    }
}
