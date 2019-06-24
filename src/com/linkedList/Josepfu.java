package com.linkedList;

public class Josepfu {
    public static void main(String[] args) {
        //测试
        CircleSingLinkedList circleSingLinkedList = new CircleSingLinkedList();
        circleSingLinkedList.addBoy(50);
        circleSingLinkedList.show();

        //出圈
        circleSingLinkedList.countBoy(1,2,50);
    }
}

//创建一个环形单向列表
class CircleSingLinkedList{
    //创建一个first节点，当前没有编号
    private Boy first = new Boy(0);

    //添加小孩节点构建成一个环形列表
    public void addBoy(int nums){
        //nums做一个数据校验
        if(nums < 1){
            System.out.println("nums值不正确");
            return;
        }
        Boy curBoy = null; //辅助指针帮助构建环形列表
        //使用一个for循环创建一个环形列表
        for(int i = 1; i <= nums; i++){
            //根据编号创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if(i == 1){
                first = boy;
                first.setNext(first);
                curBoy = first;
            }else{
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历环形列表
    public void show(){
        //判断是否为null
        if(null == first){
            System.out.println("链表为null");
            return;
        }
        //因为first不能动,需要辅助指针完成遍历
        Boy curBoy = first;
        while (true){
            System.out.printf("小孩的编号%d\n", curBoy.getNo());
            if(curBoy.getNext() == first){
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    //计算出圈
    /**
     * @param startNo 表示从第几个小孩
     * @param countNum 表示数几下
     * @param nums 表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums){
        //校验
        if(null == first || startNo < 1 || startNo > nums){
            System.out.println("参数输入有误");
            return;
        }

        //创建辅助指针
        Boy helper = first;
        while(true){
            if(helper.getNext() == first){//helper指向最后一个节点
                break;
            }
            helper = helper.getNext();
        }
        //报数前，先让first 和helper 移动 k - 1 次
        for(int j = 0; j < startNo - 1; j++){
            first   =  first.getNext();
            helper = helper.getNext();
        }

        //报数时，让first 和helper指针同时移动m - 1 次，然后出圈
        //循环操作直到只有一人
        while (true){
            if(helper == first){
                break; //说明只有一个节点
            }
            //让first 和helper指针同时移动countNum - 1
            for(int j = 0; j < countNum - 1; j++){
                first   =  first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的节点就是出圈的节点
            System.out.printf("小孩%d出圈\n", first.getNo());
            //这时将first指向first.next
            first   =  first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的编号%d\n", first.getNo());
    }
}

//创建一个boy类，表示一个节点
class Boy{
    private int no;//编号
    private Boy next;//指向下一个节点

    public Boy (int no){
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
