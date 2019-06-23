package com.linkedList;

/**
 * @author : chenqingsong
 * @date : 2019-06-23 10:24
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        //创建节点
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //创建链表
        SingLinkedList linkedList = new SingLinkedList();
        linkedList.addByOrder(hero1);
        linkedList.addByOrder(hero2);
        linkedList.addByOrder(hero3);
        linkedList.addByOrder(hero4);

        linkedList.print(linkedList.getHead());

        //修改节点
      /*  HeroNode hero5 = new HeroNode(2, "卢俊义2", "玉麒麟2");
        linkedList.update(hero5);

        //删除
        linkedList.del(4);
        linkedList.del(3);
        linkedList.del(1);

        //显示
        linkedList.show();*/


    }
}

//定义SingleLinkedList 管理我们的英雄
class  SingLinkedList{
    //先初始化一个头结点，头结点不要动, 不存放具体数据
    private HeroNode head = new HeroNode(0, "", "");

    //打印节点
    public void print(HeroNode heroNode){
        if(null != heroNode.next){
            print( heroNode.next);
        }
        System.out.println(heroNode.name);
    }

    //获取头结点
    public HeroNode getHead(){
        return head;
    }

    //添加节点到单项列表,当不考虑编号顺序时
    //1, 找到当前链表的最后一个节点
    //2, 将最后这个节点的next指向新的节点
    public void add(HeroNode heroNode){
        //因为head节点不能动，因此需要一个辅助遍历的变量
        HeroNode temp = head;
        //遍历找到你最后
        while(true){
            //找到链表的最后
            if(null == temp.next){
                break;
            }
            //如果没有找到，后移
            temp = temp.next;
        }
        //当退出循环的时候。temp就指向了链表的最后
        temp.next = heroNode;
    }

    //第二种添加方式（根据排名插入到指定位置）
    public void addByOrder(HeroNode heroNode){
        //因为单链表，因此找到temp的前一个位置
        HeroNode temp = head;
        boolean flag = false; //表示添加的编号是否存在
        while (true){
            if(null == temp.next){
                break;
            }
            if(temp.next.no > heroNode.no){// 位置找到了，就在temp的后面
                break;
            }else if(temp.next.no == heroNode.no){ //说明编号存在
                flag = true;
                break;
            }
            temp = temp.next;  //后移，遍历当前列表
        }
        //判断flag
        if(flag){
            System.out.printf("准备插入的英雄的编号%d已经存在，不能添加\n",heroNode.no);
        }else{
            //插入到链表中，temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改节点信息，根据no编号来修改，即no编号不能改
    //1，根据newHeroNode 的no来修改即可
    public void update(HeroNode newHeroNode){
        //判断是否为null
        if(null == head.next){
            System.out.println("链表为null");
        }

        //找到需要修改的no 编号
        //先定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false;//表示是否找到该节点
        while(true){
            if(null == temp){
                break;//遍历完
            }
            if(temp.no == newHeroNode.no){
                //找到
                flag = true;
                break;
            }
            temp = temp.next;

        }

        //根据flag判断是否找到修改的节点
        if(flag){
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        }else{
            System.out.printf("没有找到编号%d的节点", newHeroNode.no);
        }
    }

    //删除节点
    //1, head 不能动，因此我们需要一个temp辅助节点找到待删除节点的前一个节点
    //2，
    public void del(int no){
        HeroNode temp = head;
        boolean flag = false;//表示是否找到
        while (true){
            if(null == temp.next){
                break;
            }

            if(no == temp.next.no){
                //找到了待删除节点的前一个节点
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if(flag){
            //可以删除
            temp.next = temp.next.next;
        }else{
            System.out.printf("要删除的节点");
        }
    }

    //显示（遍历）
    public void show(){
        //先判断链表是否为null
        if(null == head.next){
            System.out.println("链表为null");
            return;
        }
        //因为头结点不能动，需要一个辅助变量
        HeroNode temp = head.next;
        while(true){
            if(null == temp){
                break;
            }
            //输出节点的信息
            System.out.println(temp.toString());
            //将temp后移， 一定小心
            temp = temp.next;
        }

    }
}

//定义heroNode 每个heroNode对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;  //指向下一个节点

    //构造器
    public HeroNode (int no, String name, String nickName ){
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    //重写toString
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
