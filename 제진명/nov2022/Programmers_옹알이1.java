package 제진명.nov2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Programmers_옹알이1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String [] babbling = new String[N];

        String input = br.readLine();
        int index = 0;
        for (int i = 0 ; i < input.length() ; i++) {
            String word = "";
            if (input.charAt(i) == '"') {
                i++;
                while(input.charAt(i) != '"') {
                    word += input.charAt(i);
                    i++;
                }
                i++;
            }
            if (word.equals("")) continue;
            babbling[index] = word;
            index++;
        }

        System.out.println(Solution(babbling));
    }

    static int Solution (String [] babbling) {
        int answer = 0;

        String [] word = {"aya", "ye", "woo", "ma"};

        loop: for (int i = 0 ; i < babbling.length ; i++) {
            boolean [] visited = new boolean [babbling[i].length()];
            subLoop: for (int j = 0 ; j < word.length ; j++) {
                int index = babbling[i].indexOf(word[j]);
                if (index == -1) continue;
                for (int k = 0 ; k < word[j].length() ; k++) {
                    if (visited[index + k] == true) {
                        for (int l = 0 ; l < k ; k++) {
                            visited[index + l] = false;
                            continue subLoop;
                        }
                    }
                    visited[index + k] = true;
                }
            }
            for (int j = 0 ; j < babbling[i].length() ; j++) {
                if (!visited[j]) continue loop;
            }
            answer++;
        }

        return answer;
    }
}
