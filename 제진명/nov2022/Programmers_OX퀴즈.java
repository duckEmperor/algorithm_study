package 제진명.nov2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Programmers_OX퀴즈 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        input = input.substring(2, input.length() - 2);

        System.out.println(input);

        String [] quiz = input.split("\", \"");

        System.out.println(solution(quiz));
    }

    static String [] solution(String [] quiz) {
        String [] answer = new String[quiz.length];

        for (int i = 0 ; i < quiz.length ; i++) {
            StringTokenizer st = new StringTokenizer(quiz[i]);

            int num1 = Integer.parseInt(st.nextToken());
            String oper = st.nextToken();
            int num2 = Integer.parseInt(st.nextToken());
            st.nextToken();
            int num3 = Integer.parseInt(st.nextToken());

            int result = 0;
            if ("+".equals(oper)) result = num1 + num2;
            else result = num1 - num2;

            if (result == num3) answer[i] = "O";
            else answer[i] = "X";
        }

        return answer;
    }
}
