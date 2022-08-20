package A202208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_9095_123더하기 {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        int[] num = new int[11];
        num[1] = 1;
        num[2] = 2;
        num[3] = 4;
        for(int i = 4; i <= 10; i++) {
        	num[i] = num[i-1] + num[i-2] + num[i-3];
        }
        while(T-->0) {
        	System.out.println(num[Integer.parseInt(br.readLine().trim())]);
        }
	}

}
