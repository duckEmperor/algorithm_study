package 제진명.nov2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Programmers_연속된수의합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int num = Integer.parseInt(st.nextToken());
        int total = Integer.parseInt(st.nextToken());

        int [] answer = solution(num, total);

        for (int a : answer) {
            System.out.print(a + " ");
        }
    }

    static int[] solution(int num, int total) {
        int[] answer = new int[num];

        int index = -49;

        while (true) {
            int temp = 0;
            for (int  i = 0 ; i < num ; i++) {
                temp += index + i;
            }

            if (temp == total) {
                for (int i = 0 ; i < num ; i++) {
                    answer[i] = index + i;
                }
                break;
            } else {
                index++;
            }
        }

        return answer;
    }
}
