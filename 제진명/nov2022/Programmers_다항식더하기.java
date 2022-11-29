package 제진명.nov2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Programmers_다항식더하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(solution(br.readLine()));

    }

    static String solution(String polymonial) {
        String answer = "";
        int num = 0;
        int x = 0;

        StringTokenizer st = new StringTokenizer(polymonial, " +");
        while(st.hasMoreTokens()) {
            String s = st.nextToken();
            if (s.contains("x")) {
                s = s.replace("x", "");
                x += "".equals(s) ? 1 : Integer.parseInt(s);
            } else {
                num += "".equals(s)? 1 : Integer.parseInt(s);
            }
        }

        if (x != 0) {
            if (x == 1) answer += "x";
            else answer += x + "x";

            if (num != 0) {
                answer += " + " + num;
            }
        } else {
            answer += num;
        }

        return answer;
    }
}
