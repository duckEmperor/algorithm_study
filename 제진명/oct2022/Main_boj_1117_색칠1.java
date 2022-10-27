package 제진명.oct2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_boj_1117_색칠1 {

    static int W, H, f, c, x1, x2, y1, y2;
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

        colorPaper = new int[H][W];

        for (int i = 0 ; i < H ; i++) {
            for (int j = 0 ; j < W ; j++){
                colorPaper[i][j] = 1;
            }
        }

        // 예제에서는 종이를 왼쪽에서 오른쪽으로 위에서 아래로 접고 있는데
        // 종이를 오른쪽에서 왼쪽으로 아래에서 위로 접도록 하여 (0, 0) 좌표가 이동하지 않도록 변경
        f = W - f;
        x1 = W - (x1 + c);
        x2 = W - (x2 + c);

        // 종이를 가로로 접기
        for (int i = 0 ; i < H ; i++) {
            for (int j = 0 ; j < W-f ; j++) {
                colorPaper[i][f-j-1] = colorPaper[i][f-j-1] + colorPaper[i][f+j];
                colorPaper[i][f+j] = 0;
            }
        }

        for (int i = H/(c+1) ; i > 0 ; i--) {
            for (int j = 0 ; j < c ; j++) {
                for (int k = 0 ; k < W ; k++) {
                    colorPaper[i*c-j-1][k] += colorPaper[i*c+j][k];
                    colorPaper[i*c+j][k] = 0;
                }
            }
        }
        int cnt = 0;
        for (int i = x2 ; i < x1 ; i++) {
            for (int j = y1 ; j < y2 ; j++) {
                cnt += colorPaper[j][i];
            }
        }

        System.out.println(H*W - cnt);


//        for (int  i = 0 ; i < H ; i++) {
//            for (int j = 0 ; j < W ; j++) {
//                System.out.print(colorPaper[i][j] + " ");
//            }
//            System.out.println();
//        }
    }
}