package com.search;

import java.util.Arrays;

/**
 * 斐波拉契
 * @author : chenqingsong
 * @date : 2019-07-06 21:19
 */
public class FibonacciSearch {

    public static int maxSize = 20;
    public static void main(String[] args) {
        int[] arr = {1,8,10,89,1000,1234};

        System.out.println(fibSearch(arr, 1234));

    }

    //因为后面我们mid = low + F(k - 1) - 1 ,需要使用到斐波拉契数列，因此需要先获取到斐波拉契数列
    //非递归方式
    public static int[] fib(){
        int[] f = new int[20];
        f[0] = 1;
        f[1] = 1;
        for(int i = 2; i < maxSize; i++){
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    //编写斐波拉契查找算法
    //非递归方式
    /**
     *
     * @param a 数组
     * @param key 需要查找的关键字
     * @return
     */
    public static int fibSearch(int[] a, int key){
        int low = 0;
        int hight = a.length - 1;
        int k = 0; //表示斐波拉契分割数值的下标
        int mid = 0; //存放mid值
        int f[] = fib(); //获取斐波拉契数列

        //获取到斐波拉契分割数值的下标
        while(hight > f[k] - 1){
            k++;
        }
        //因为f[k]的值可能大于a的长度，因此我们需要使用Arrays类，构造一个新的数组，并指向a[]
        //不足的部分使用0填充
        int [] temp = Arrays.copyOf(a, f[k]);
        //需要使用a数组最后的数填充temp
        //temp = {1,8,10,89,1000,1234,0,0} => {1,8,10,89,1000,1234,1234,1234}
        for(int i = hight + 1; i < temp.length; i++){
            temp[i] = a[hight];
        }
        //使用while循环处理，找到我们的数key
        while(low <= hight){
            mid = low + f[k - 1] - 1;
            if(key < temp[mid]){//说明我们应该继续向数组前面查找（左）
                hight = mid - 1;
                //1，全部元素 = 前面的元素 + 后边的元素
                //2，f[k] = f[k-1] + f[k-2]
                //因为前面有f[k-1] 个元素，所以可以继续拆分f[k-1] = f[k-2] + f[k-3]
                //即在f[k-1] 的前面继续查找 k--
                //即下次循环mid = f[k-1-1] - 1
                k--;
            }else if(key > temp[mid]){//说明我们应该继续向数组前面查(右)
                low = mid + 1;
                //为什么是k-2
                //全部元素 = 前面的元素 + 后边的元素
                //f[k] = f[k-1] + f[k-2]
                //因为前面有f[k-2] 个元素，所以可以继续拆分f[k-1] = f[k-3] + f[k-4]
                //即在f[k-2] 的前面继续查找 k-=2
                //即下次循环mid = f[k-1-2] - 1
                k -= 2;
            }else{//找到
                //需要确定，返回的是哪个下标
                if(mid <= hight){
                    return mid;
                }else{
                    return hight;
                }
            }
        }
        return -1;
    }

}
