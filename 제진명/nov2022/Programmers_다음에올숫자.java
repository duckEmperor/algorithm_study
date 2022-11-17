package 제진명.nov2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Programmers_다음에올숫자 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String s = br.readLine();
        String input = "";
        for (int i = 1 ; i < s.length() - 1 ; i++) {
            input += s.charAt(i);
        }

        st = new StringTokenizer(input, ", ");

        int [] common = new int [st.countTokens()];

        for (int i = 0 ; i < common.length ; i++) {
            common[i] = Integer.parseInt(st.nextToken());
        }

        Solution(common);
    }

    static int Solution(int [] common) {
        // 등차 수열인지 등비 수열인지 확인
        boolean isArithmeticProgression = true;
        int answer = 0;

        if (common[common.length - 2] - common[common.length - 3] != common[common.length - 1] - common[common.length - 2]) isArithmeticProgression = false;

        if (isArithmeticProgression) {
            answer = common[common.length - 1] + common[common.length - 1] - common[common.length - 2];
        } else {
            answer = common[common.length - 1] * (common[common.length - 1] / common[common.length - 2]);
        }

        return answer;
    }
}
