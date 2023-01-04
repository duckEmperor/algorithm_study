package 제진명.jenuary2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_boj_1406_에디터 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();

        Deque<Character> left = new LinkedList<>();
        Stack<Character> right = new Stack<>();

        for (int i = 0 ; i < str.length() ; i++) {
            left.add(str.charAt(i));
        }

        int N = Integer.parseInt(br.readLine());

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());

            char com = st.nextToken().charAt(0);

            if (com == 'P') {
                left.add(st.nextToken().charAt(0));
            } else if (com == 'L') {
                if (left.size() > 0) right.push(left.pollLast());
            } else if (com == 'B') {
                if (left.size() > 0) left.removeLast();
            } else if (com == 'D') {
                if (right.size() > 0) left.add(right.pop());
            }
        }

        while (left.size() > 0) {
            sb.append(left.poll());
        }

        while (right.size() > 0) {
            sb.append(right.pop());
        }

        System.out.println(sb);

    }
}
