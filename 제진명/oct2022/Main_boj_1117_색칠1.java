package 제진명.oct2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_boj_1117_색칠1 {
    static long W, H, f, c, x1, y1, x2, y2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Long.parseLong(st.nextToken());
        H = Long.parseLong(st.nextToken());
        f = Long.parseLong(st.nextToken());
        c = Long.parseLong(st.nextToken());
        x1 = Long.parseLong(st.nextToken());
        y1 = Long.parseLong(st.nextToken());
        x2 = Long.parseLong(st.nextToken());
        y2 = Long.parseLong(st.nextToken());

        long overlapWidth = Math.min(f, W - f);

        long paintedOverlapPaperCnt = 0;
        long paintedNotOverlapPaperCnt = 0;

        // 가로로 접어서 겹치는 부분에 색을 칠하지 않음
        if (overlapWidth <= x1) {
            paintedNotOverlapPaperCnt = (x2 - x1)*(y2 - y1);
        }
        // 가로로 접어서 겹치는 부분과 겹치지 않는 부분 모두 색을 칠함
        else if (overlapWidth < x2) {
            paintedOverlapPaperCnt = (overlapWidth - x1)*(y2 - y1);
            paintedNotOverlapPaperCnt = (x2 - overlapWidth)*(y2 - y1);
        }
        // 가로로 접어서 겹치는 부분에만 색을 칠함
        else {
            paintedOverlapPaperCnt = (x2 - x1)*(y2 - y1);
        }

        long paintedPaper = (2*paintedOverlapPaperCnt + paintedNotOverlapPaperCnt)*(c+1);
        long ans = H*W - paintedPaper;

        System.out.println(ans);

    }
}