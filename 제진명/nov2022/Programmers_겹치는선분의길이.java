package 제진명.nov2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Programmers_겹치는선분의길이 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        input = input.substring(2, input.length()-2);

        StringTokenizer st = new StringTokenizer(input, "], [");
        int [][] lines = new int[st.countTokens()/2][2];

        for (int i = 0 ; i < lines.length ; i++) {
            lines[i][0] = Integer.parseInt(st.nextToken());
            lines[i][1] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(lines));

    }

    static int solution(int[][] lines) {
        int [] visited = new int[201];

        for (int i = 0 ; i < lines.length ; i++) {
            for (int j = lines[i][0] ; j < lines[i][1] ; j++) {
                visited[j + 100]++;
            }
        }

        int answer = 0;

        for (int i = 0 ; i < visited.length ; i++) {
            if (visited[i] > 1) answer++;
        }

        return answer;
    }
}
