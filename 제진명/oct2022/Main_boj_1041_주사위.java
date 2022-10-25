package 제진명.oct2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_boj_1041_주사위 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int [] dice = new int[6];

        for (int i = 0 ; i < 6 ; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }

        if (N == 1) {
            int sum = 0;
            for (int i = 0 ; i < 6 ; i++) {
                sum += dice[i];
            }
            System.out.println(sum-Arrays.stream(dice).max().getAsInt());
            return;
        }

        int oneMin = Arrays.stream(dice).min().getAsInt();
        int twoMin = Integer.MAX_VALUE;
        int threeMin;

        // 두 면이 바깥으로 보이는 최소값
        // A를 포함하는 경우
        for (int i = 1 ; i < 5 ; i++) {
            twoMin = Math.min(twoMin, dice[0]+dice[i]);
        }
        // F를 포함하는 경우
        for (int i = 1 ; i < 5 ; i++) {
            twoMin = Math.min(twoMin, dice[5]+dice[i]);
        }
        // 둘다 포함하지 않는 경우
        int [] twoMap = {dice[1]+dice[2], dice[1]+dice[3], dice[4]+dice[2], dice[4]+dice[3]};
        twoMin = Math.min(twoMin, Arrays.stream(twoMap).min().getAsInt());

        // 세 면이 바깥으로 보이는 최소값
        int [] threeMap = {dice[0]+dice[1]+dice[2],dice[0]+dice[1]+dice[3],dice[0]+dice[3]+dice[4],dice[0]+dice[4]+dice[2],
                dice[5]+dice[1]+dice[2],dice[5]+dice[1]+dice[3],dice[5]+dice[3]+dice[4],dice[5]+dice[4]+dice[2]};
        threeMin = Arrays.stream(threeMap).min().getAsInt();

        long ans = (5L *N*N- 16L *N+12)*oneMin + (8L *N-12)*twoMin + 4L *threeMin;

        System.out.println(ans);
    }
}
