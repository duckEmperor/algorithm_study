package 제진명.nov2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_boj_1283_단축키지정 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        boolean [] visited = new boolean[26];

        for (int i = 0 ; i < N ; i++) {
            String input = br.readLine();
            st = new StringTokenizer(input);

            // 먼저 단어 첫번째 글자가 단축키로 지정되어 있는지 확인.
            String answer = "";
            boolean isRegist = false;
            boolean isFirst = true;
            while(st.hasMoreTokens()) {
                String word = st.nextToken();
                String _word = word.toLowerCase();
                if (!isFirst) answer += " ";
                if (!isRegist && !visited[_word.charAt(0) - 'a']) {
                    answer += "[" + word.charAt(0) + "]" + word.substring(1);
                    visited[_word.charAt(0) - 'a'] = true;
                    isRegist = true;
                } else {
                    answer += word;
                }
                isFirst = false;
            }

            if (isRegist) {
                sb.append(answer + "\n");
                continue;
            };

            String _input = input.toLowerCase();
            int index = -1;
            for (int j = 0 ; j < _input.length() ; j++) {
                // 공백인경우
                if (_input.charAt(j) - 'a' < 0 ) continue;
                if (!visited[_input.charAt(j) - 'a']) {
                    index = j;
                    visited[_input.charAt(j) - 'a'] = true;
                    break;
                }
            }
            if (index == -1) {
                sb.append(input + "\n");
                continue;
            }
            answer = "";
            answer += input.substring(0, index) + "[" + input.charAt(index) + "]" + input.substring(index + 1);
            sb.append(answer + "\n");

        }

        System.out.println(sb);
    }
}
