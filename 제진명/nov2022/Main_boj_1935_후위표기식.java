package 제진명.nov2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_boj_1935_후위표기식 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String input = br.readLine();

        int [] nums = new int[N];

        for (int i = 0 ; i < N ; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        Stack<Double> stack = new Stack<>();

        for (int i = 0 ; i < input.length() ; i++) {
            char c = input.charAt(i);
            if (c >= 'A' && c <= 'Z') stack.push((double)nums[c - 'A']);
            else if (c == '+') {
                stack.push(stack.pop() + stack.pop());
            }else if (c == '-') {
                stack.push(- stack.pop() + stack.pop());
            }else if (c == '*') {
                stack.push(stack.pop() * stack.pop());
            }else if (c == '/') {
                stack.push(1 / stack.pop() * stack.pop());
            }
        }

        System.out.println(String.format("%.2f", stack.pop()));

    }
}
