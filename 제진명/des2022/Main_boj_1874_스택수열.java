package 제진명.des2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_boj_1874_스택수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int stackIndex = 2;
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        sb.append("+");
        for (int i = 0 ; i < N ; i++) {
            int now = Integer.parseInt(br.readLine());

            // 스택이 비었을 때 반드시 하나 넣기
            if (stack.empty()) {
                stack.push(stackIndex);
                stackIndex++;
                sb.append("\n+");
            }

            // 스택의 제일 윗 숫자와 현재 수열의 숫자가 일치하는 경우
            if (stack.peek() == now) {
                stack.pop();
                sb.append("\n-");
            }
            // 스택의 제일 윗 숫자보다 현재 출력할 수열의 숫자가 큰 경우
            else if (stack.peek() < now) {
                for (int j = stackIndex ; j <= now ; j++) {
                    stack.push(j);
                    stackIndex++;
                    sb.append("\n+");
                }
                // 마지막에 올린 숫자를 pop
                stack.pop();
                sb.append("\n-");
            }
            // 스택의 제일 윗 숫자보다 현재 출력할 수열의 숫자가 작은 경우 수열을 만들 수 없음.
            else {
                System.out.println("NO");
                return;
            }

        }

        System.out.println(sb);
    }
}
