package 제진명.nov2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_boj_1316_그룹단어체커 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int answer = 0;

        loop: for (int i = 0 ; i < N ; i++) {
            String word = br.readLine();
            boolean [] visited = new boolean[26];
            visited[word.charAt(0) - 'a'] = true;
            for (int j = 1 ; j < word.length() ; j++) {
                if (word.charAt(j - 1) != word.charAt(j)) {
                    if (visited[word.charAt(j) - 'a']) continue loop;
                    else visited[word.charAt(j) - 'a'] = true;
                }
            }
            answer++;
        }

        System.out.println(answer);
    }
}
