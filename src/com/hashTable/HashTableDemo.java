package com.hashTable;

import java.util.Scanner;

/**
 * @author : chenqingsong
 * @date : 2019-07-07 09:45
 */
public class HashTableDemo {
    public static void main(String[] args) {

        //创建HashTab
        HashTab hashTab = new HashTab(7);

        String key = "";
       Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");
            key = scanner.next();
            switch (key){
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    Emp emp = new Emp(id , name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("输入id");
                    int id1 = scanner.nextInt();
                    hashTab.findEmpById(id1);
                    break;
                case "exit":
                    scanner.close();
                    break;
                default:
                    break;
            }
        }
    }



}

//创建HashTab,管理多条链表
class HashTab {
    private EmpLinkedList[] empLinkedListArray ;
    private int size;

    //构造器
    public HashTab(int size){
        this.size = size;
        empLinkedListArray =  new EmpLinkedList[size];
        for(int i = 0; i < empLinkedListArray.length; i++){
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp){
        //根据员工的id，得到该员工应该加入到那条链表
        int empLinkedListNo = hashFun(emp.id);

        //将emp添加到对应的链表中
        empLinkedListArray[empLinkedListNo].add(emp);
    }

    //查找雇员
    public void findEmpById(int id){
        //根据员工的id，得到该员工应该加入到那条链表
        int empLinkedListNo = hashFun(id);

        //将emp添加到对应的链表中
        Emp emp = empLinkedListArray[empLinkedListNo].findEmpById(id);
        if(null == emp){
            System.out.println("没有找到信息");
        }else{
            System.out.println("该数据再第"+(empLinkedListNo + 1)+"条链表中");
            System.out.printf("数据为：id=%d name=-%s\t", emp.id, emp.name);
            System.out.println();
        }
    }


    //遍历所有链表
    public void list(){
        for(int i = 0; i < size; i++){
            empLinkedListArray[i].list(i);
        }
    }

    //散列，使用取模法
    public int hashFun(int id){
        return id % size;
    }

}

class Emp {
    public int id;
    public String name;

    public Emp next;

    public Emp() {
    }

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

//创建EmpLinkedList，表示链表
class EmpLinkedList {
    //头指针，执行第一个Emp，因此我们这个链表的head是直接指向第一个Emp
    private Emp head; //默认null

    //添加雇员到链表
    //假定当添加雇员时，id是自增长的，即id分配总是从小到大
    public void add(Emp emp){
        //如果是添加第一个雇员
        if(head == null){
            head = emp;
            return;
        }

        //如果不是则使用一个辅助指针帮助定位到最后
        Emp curEmp = head;
        while(true){
            if(curEmp.next == null){ //说明到链表最后
                break;
            }
            curEmp = curEmp.next; //后移
        }

        //退出时直接将emp加入链表
        curEmp.next = emp;
    }

    //遍历
    public void list(int no){
        if(head == null){
            System.out.println("第"+ no +"条链表为null");
            return;
        }
        //
        System.out.println("第"+ no +"条链表为");
        Emp curEmp = head;//辅助指针
        while(true){
            System.out.printf("=> id =%d name=%s\t", curEmp.id, curEmp.name);
            if(curEmp.next == null){//最后节点
                break;
            }else{
                curEmp = curEmp.next;
            }
        }
        System.out.println();
    }

    //查找雇员
    public Emp findEmpById(int id){
        if(head == null){
            System.out.println("链表为null");
            return null;
        }

        //辅助指针
        Emp curEmp = head;
        while (true){
            if(curEmp.id == id){
                break;
            }
            if(curEmp.next == null){
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }
}
