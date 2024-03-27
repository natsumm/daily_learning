package com.bjming._01_sparsearray;

/**
 * @author liuluming <liuluming@ttookk.com>
 * @description
 * @create 2024/3/12
 **/
public class SparseArray01 {

    public static void main(String[] args) {
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][5] = 3;
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        // 遍历获取原数组不为空的个数
        int sum = 0;
        for (int[] row : chessArr1) {
            for (int data : row) {
                if (data != 0) {
                    sum++;
                }
            }
        }
        System.out.println("sum = " + sum);

        int sparseArr1[][] = new int[sum+1][3];
        int cnt = 0;

        sparseArr1[0][0] = 11;
        sparseArr1[0][1] = 11;
        sparseArr1[0][2] = sum;

        for (int i = 0; i < chessArr1.length; i++) {
            int[] row = chessArr1[i];
            for (int j = 0; j < row.length; j++) {
                int data = row[j];
                if (data != 0) {
                    sparseArr1[cnt + 1][0] = i;
                    sparseArr1[cnt + 1][1] = j;
                    sparseArr1[cnt + 1][2] = data;
                    cnt++;
                }
            }
        }


        for (int[] row : sparseArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        System.out.println("开始恢复");
        // 恢复二维数组
        int chessArr2[][] = new int[sparseArr1[0][0]][sparseArr1[0][1]];
        for (int i = 1; i < sparseArr1.length; i++) {
            int x = sparseArr1[i][0];
            int y = sparseArr1[i][1];
            int data = sparseArr1[i][2];
            chessArr2[x][y] = data;
        }

        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }

}
