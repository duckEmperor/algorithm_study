package 제진명.nov2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_boj_9012_괄호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0 ; i < N ; i++) {
            String input = br.readLine();
            if (checkVPS(input)) sb.append("YES\n");
            else sb.append("NO\n");
        }

        System.out.println(sb);
    }

    static boolean checkVPS (String input) {
        int left = 0;
        for (int j = 0 ; j < input.length() ; j++) {
            if (input.charAt(j) == '(') left++;
            else
            if (left == 0) {
                return false;
            } else {
                left--;
            }
        }
        if (left == 0) return true;
        else return false;
    }
}

