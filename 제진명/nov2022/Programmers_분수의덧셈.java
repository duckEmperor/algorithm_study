package 제진명.nov2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Programmers_분수의덧셈 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        System.out.println(solution(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
    }

    static int[] solution(int denum1, int num1, int denum2, int num2) {

        int lcm = (num1 * num2) / gcd(Math.max(num1, num2), Math.min(num1, num2));
        int denum = denum1*(lcm/num1) + denum2*(lcm/num2);

        for (int i = 2 ; i <= Math.min(denum, lcm) ; i++) {
            if (denum % i == 0 && lcm % i == 0) {
                denum /= i;
                lcm /= i;
                i -= 1;
            }
        }

        return new int [] {denum, lcm};
    }

    static int gcd(int num1, int num2) {
        while (num1 > 0) {
            int temp = num2;
            num2 = num1;
            num1 = temp%num1;
        }
        return num2;
    }
}
