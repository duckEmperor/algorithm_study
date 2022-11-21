package 제진명.nov2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_boj_1283_단축키지정 {
    static StringTokenizer st;
    static boolean [] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        visited = new boolean[26];

        for (int i = 0 ; i < N ; i++) {
            String input = br.readLine();

            int index = keyWordIndex(input);

            if (index == -1) {
                sb.append(input + "\n");
                continue;
            }

            String answer = "";
            answer += input.substring(0, index) + "[" + input.charAt(index) + "]" + input.substring(index + 1);
            sb.append(answer + "\n");

        }

        System.out.println(sb);
    }

    static int keyWordIndex (String words) {
        String lowerWord = words.toLowerCase();
        st = new StringTokenizer(lowerWord);
        int index = -1;
        int now = 0;
        while(st.hasMoreTokens()) {
            String word = st.nextToken();
            if (!visited[word.charAt(0) - 'a']) {
                visited[word.charAt(0) - 'a'] = true;
                return now;
            };
            if (index == -1) {
                for (int i = 1 ; i < word.length() ; i++) {
                    if (!visited[word.charAt(i) - 'a']) {
                        index = now + i;
                        break;
                    };
                }
            }
            now += word.length() + 1;
        }

        if (index != -1) visited[lowerWord.charAt(index) - 'a'] = true;

        return index;
    }
}
