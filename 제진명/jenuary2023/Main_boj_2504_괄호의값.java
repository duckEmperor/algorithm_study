package 제진명.jenuary2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main_boj_2504_괄호의값 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        Stack<Character> stack = new Stack<>();

        int answer = 0;
        int temp = 1;

        for (int i = 0 ; i < S.length() ; i++) {
            char c = S.charAt(i);

            if (c == '(') {
                stack.push(c);
                temp *= 2;
            } else if (c == '[') {
                stack.push(c);
                temp *= 3;
            } else if (c == ')') {
                if (stack.isEmpty() || stack.peek() != '(') {
                    answer = 0;
                    break;
                } else if (S.charAt(i - 1) == '(') answer += temp;
                stack.pop();
                temp /= 2;
            } else if (c == ']') {
                if (stack.isEmpty() || stack.peek() != '[') {
                    answer = 0;
                    break;
                } else if (S.charAt(i - 1) == '[') answer += temp;
                stack.pop();
                temp /= 3;
            }
        }

        if (!stack.isEmpty()) answer = 0;
        System.out.println(answer);

    }
}
