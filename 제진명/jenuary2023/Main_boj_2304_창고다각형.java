package 제진명.jenuary2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_boj_2304_창고다각형 {

    static class Pillar implements Comparable<Pillar> {
        int x, h;
        public Pillar (int x, int h) {
            this.x = x;
            this.h = h;
        }

        @Override
        public int compareTo (Pillar o) {
            return this.x - o.x;
        }
    }

    static int N;
    static Pillar [] pillars;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        pillars = new Pillar[N];

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            pillars[i] = new Pillar(x, h);
        }

        Arrays.sort(pillars);

        // 다음 가장 높은 기둥
        int highestPillar = getNextHighestPillar(0);
        int answer = 0;

        // 처음 부터 가장 높은 기둥 까지는 점점 높아져야함
        for (int i = 1 ; i <= highestPillar ; i++) {
            // 현재 기둥이 이전 기둥보다 높은지 확인
            // 현재 기둥이 이전 기둥보다 낮으면 현재 기둥의 높이를 이전 기둥의 높이와 같게 만들어 줌
            if (pillars[i].h < pillars[i-1].h) {
                pillars[i].h = pillars[i-1].h;
            }

            // 이전 기둥과 현재 기둥 사이의 거리 * 이전 기둥의 높이를 넓이로 계산하여 더해줌
            answer += (pillars[i].x - pillars[i-1].x) * pillars[i-1].h;
        }

        // 가장 높은 기둥의 넓이를 더해줌
        answer += pillars[highestPillar].h;

        while (true) {
            // 그 다음으로 가장 높은 기둥의 인덱스
            int nextHighestPillar = getNextHighestPillar(highestPillar + 1);
            if (nextHighestPillar == -1) break;

            answer += (pillars[nextHighestPillar].x - pillars[highestPillar].x) * pillars[nextHighestPillar].h;

            highestPillar = nextHighestPillar;
        }

        System.out.println(answer);
    }

    static int getNextHighestPillar (int idx) {
        if (idx >= N) return -1;
        int temp = idx;

        for (int i = idx ; i < N ; i++) {
            if (pillars[temp].h > pillars[i].h) continue;
            temp = i;
        }

        return temp;
    }
}
