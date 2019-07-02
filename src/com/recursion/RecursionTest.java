package com.recursion;

/**
 * @author : chenqingsong
 * @date : 2019-06-29 15:08
 */
public class RecursionTest {
    public static void main(String[] args) {
        //先创建一个二维数组
        //地图
        int[][] map = new int[8][7];

        //使用1表示墙
        //上下全部置为1
        for(int i = 0; i < 7; i++){
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for(int i = 0; i < 8; i++){
            map[i][0] = 1;
            map[i][6] = 1;
        }

        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
//        map[1][2] = 1;
//        map[2][2] = 1;

        //地图
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 7; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        //使用递归回溯给小球找路
        setWay2(map, 1, 1);
        //输出新的地图， 小球走过，并标识过得的递归
        System.out.println("小球走过，并标识过得的递归");
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 7; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 使用递归找路
     * @param map 地图
     * @param i  开始位置
     * @param j
     * @return
     */
    //出发地（1，1） 如果到(6,5则找到通路)
    // 约定：当0 表示该点没有走过，1表示1墙，2表示通路可以走，3表示该点已经走过，但走不通
    // 走迷宫时，需要确定策略 下 -> 右 -> 上 -> 下，如果走不通，再回滚
    public static boolean setWay(int[][] map, int i, int j){
        if(map[6][5] == 2){ //找到通路
            return true;
        }else{
            if(map[i][j] == 0){ //如果当前点没有走
                //按照策略走  下 -> 右 -> 上 -> 下
                map[i][j] = 2;  //假定该点可以走通
                if(setWay(map, i + 1, j)){ // 向下走
                    return true;
                }else if(setWay(map, i, j + 1)){ //向右走
                    return true;
                }else if(setWay(map, i - 1, j )){ //向上走
                    return true;
                }else if(setWay(map, i, j  - 1)){ //向左走
                    return true;
                }else{
                    //说明该点走不通，是死路
                    map[i][j] = 3;
                    return false;
                }
            }else{ //如果map[i][j] = 0 可能是1，2，3
                return false;
            }
        }
    }

    /**
     * 使用递归找路 (改变策略 上 -> 右 -> 下 -> 左)
     * @param map 地图
     * @param i  开始位置
     * @param j
     * @return
     */
    public static boolean setWay2(int[][] map, int i, int j) {
        if (map[6][5] == 2) { //找到通路
            return true;
        } else {
            if (map[i][j] == 0) { //如果当前点没有走
                //按照策略走  上 -> 右 -> 下 -> 左)
                map[i][j] = 2;  //假定该点可以走通
                if (setWay2(map, i - 1, j)) { // 向上走
                    return true;
                } else if (setWay2(map, i, j + 1)) { //向右走
                    return true;
                } else if (setWay2(map, i + 1, j)) { //向下走
                    return true;
                } else if (setWay2(map, i, j - 1)) { //向左走
                    return true;
                } else {
                    //说明该点走不通，是死路
                    map[i][j] = 3;
                    return false;
                }
            } else { //如果map[i][j] = 0 可能是1，2，3
                return false;
            }
        }
    }
}
