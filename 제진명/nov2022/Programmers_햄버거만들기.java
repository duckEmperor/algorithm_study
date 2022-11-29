package 제진명.nov2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Programmers_햄버거만들기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), ", ");

        int N = st.countTokens();

        int [] ingredient = new int[N];

        for (int i = 0 ; i < N ; i++) {
            ingredient[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(ingredient));
    }

    static int solution(int [] ingredient) {
        int answer = 0;

        Stack<Integer> stack = new Stack<>();

        loop: for (int i = 0 ; i < ingredient.length ; i++) {
            stack.push(ingredient[i]);
            if (stack.peek() != 1 || stack.size() < 4) continue;

            int seq = 3;
            for (int j = stack.size()-2 ; j > stack.size() - 5 ; j--) {
                if (stack.elementAt(j) != seq) continue loop;
                seq--;
            }
            for (int j = 0 ; j < 4 ; j++) {
                stack.pop();
            }
            answer++;
        }

        return answer;
    }
}
