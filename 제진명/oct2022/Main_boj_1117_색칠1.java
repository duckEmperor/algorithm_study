package 제진명.oct2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_boj_1117_색칠1 {
    static int W, H, f, c, x1, y1, x2, y2, _f;
    static int [][] colorPaper;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        f = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        x1 = Integer.parseInt(st.nextToken());
        y1 = Integer.parseInt(st.nextToken());
        x2 = Integer.parseInt(st.nextToken());
        y2 = Integer.parseInt(st.nextToken());

        if (isOverflow()) {
            colorPaper = new int[H][2 * f];
            _f = f;
        }
        else {
            colorPaper = new int[H][W];
            _f = W - f;
        }

        x1 = _f - x1;
        x2 = _f - x2;

        fillColorPaper();

        foldRowColorPaper();
        foldColColorPaper();

        System.out.println(H*W - calculrate());
    }

    static boolean isOverflow() {
        return W / 2 < f;
    }

    static void fillColorPaper() {
        for (int i = 0 ; i < H ; i++) {
            for (int j = 0 ; j < W ; j++) {
                if (isOverflow()) colorPaper[i][2 * f - W + j] = 1;
                else colorPaper[i][j] = 1;
            }
        }
    }


    static void foldRowColorPaper() {
        for (int i = 0 ; i < colorPaper[0].length - _f ; i++) {
            for (int j = 0 ; j < H ; j++) {
                colorPaper[j][_f - i - 1] += colorPaper[j][_f + i];
                colorPaper[j][_f + i] = 0;
            }
        }
    }

    static void foldColColorPaper() {
        for (int i = c ; i > 0 ; i--) {
            int _y = H/(c+1) * i;
            for (int j = 0 ; j < H/(c+1) ; j++) {
                for (int k = 0 ; k < W ; k++) {
                    colorPaper[_y - j - 1][k] += colorPaper[_y + j][k];
                    colorPaper[_y + j][k] = 0;
                 }
            }
        }
    }

    static int calculrate() {
        int cnt = 0;
        for (int i = x2 ; i < x1 ; i++) {
            for (int j = y1 ; j < y2 ; j++) {
                cnt += colorPaper[j][i];
            }
        }

        return cnt;
    }
}