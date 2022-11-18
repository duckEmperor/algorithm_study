package 제진명.nov2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Programmers_문자열밀기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String A = br.readLine();
        String B = br.readLine();

        System.out.println(solution(A, B));
    }

    static int solution(String A, String B) {
        int answer = 0;
        String temp = A;

        if (A.equals(B)) return 0;

        for (int i = 0 ; i < A.length() ; i++) {
            answer++;
            temp = temp.charAt(temp.length() - 1) + temp.substring(0, temp.length() - 1);
            if (temp.equals(B)) return answer;
        }
        return -1;
    }
}
