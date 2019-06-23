package com.array;

public class SparseArray {
    public static void main(String[] args) {
        // 创建原始的二维数组
        // 0:表示没有棋子;1:表示黑子;2:表示白子;
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[3][2] = 2;
        //输出原始的二维数组
      /*  System.out.println("原始的二维数组");
        for(int[] row : chessArr1){
            for(int data : row){
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }*/

        // 将二维数组转稀疏数组
        // 1 遍历二维数组，得到非0的数据的个数
        int sum = 0;
        for(int i = 0; i < 11; i ++){
            for(int j = 0; j < 11; j++){
                if(0 != chessArr1[i][j]){
                    sum += 1;
                }
            }
        }
        // 2 创建稀疏数组
        int sparseArr[][] = new int[sum + 1][3];
        //给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        // 遍历二维数组，将非0的值存放到sparseArr中
        int count = 0; // 用户记录是第几个非0
        for(int i = 0; i < 11; i ++){
            for(int j = 0; j < 11; j++){
                if(0 != chessArr1[i][j]){
                    count ++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }

        //输出
        System.out.println();
        System.out.println("得到的稀疏数组为~~~~~~~~~~~~~~");
        for(int i = 0; i < sparseArr.length; i++){
            System.out.printf("%d\t%d\t%d\n", sparseArr[i][0],
                    sparseArr[i][1],sparseArr[i][2]);

        }


        // 将稀疏数组转化为二维数组
        // 1 读取稀疏数组第一行的值，转化为原始的二维数组
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        // 2 从第二行开始遍历 并赋值给二维数组
        for(int  i = 1; i < sparseArr.length; i++){
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        //输出恢复后的数组
        System.out.println();
        System.out.println("恢复后的二维数组···········");
        for(int[] row : chessArr2){
            for(int data : row){
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
