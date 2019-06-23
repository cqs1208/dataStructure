package com.linkedList;

/**
 * @author : chenqingsong
 * @date : 2019-06-23 22:18
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        //测试
        //创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        //创建链表
        DoubleLinkedList linkedList = new DoubleLinkedList();
        linkedList.add(hero1);
        linkedList.add(hero2);
        linkedList.add(hero3);
        linkedList.add(hero4);

        //修改
        HeroNode2 hero5 = new HeroNode2(4, "林冲2", "豹子头2");
        linkedList.update(hero5);

        //删除
        linkedList.del(3);

        linkedList.show();


    }

}

class DoubleLinkedList{
    //先初始化一个头结点，头结点不要动, 不存放具体数据
    private HeroNode2 head = new HeroNode2(0, "", "");

    //获取头结点
    public HeroNode2 getHead(){
        return head;
    }

    //显示（遍历）
    public void show(){
        //先判断链表是否为null
        if(null == head.next){
            System.out.println("链表为null");
            return;
        }
        //因为头结点不能动，需要一个辅助变量
        HeroNode2 temp = head.next;
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

    //添加节点到单项列表,当不考虑编号顺序时
    //1, 找到当前链表的最后一个节点
    //2, 将最后这个节点的next指向新的节点
    public void add(HeroNode2 heroNode){
        //因为head节点不能动，因此需要一个辅助遍历的变量
        HeroNode2 temp = head;
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
        //形成一个双向列表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //修改节点信息，根据no编号来修改，即no编号不能改
    //1，根据newHeroNode 的no来修改即可
    public void update(HeroNode2 newHeroNode){
        //判断是否为null
        if(null == head.next){
            System.out.println("链表为null");
        }

        //找到需要修改的no 编号
        //先定义一个辅助变量
        HeroNode2 temp = head.next;
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
    //1, 对于双向列表，我们可以直接找到要删除的这个节点，找到后自我删除
    public void del(int no){
        //判断当前链表是否为null
        if(null == head.next){
            System.out.println("链表为null， 不能删除");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;//表示是否找到
        while (true){
            if(null == temp){
                break;
            }

            if(no == temp.no){
                //找到了待删除节点的前一个节点
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if(flag){
            //可以删除
           // temp.next = temp.next.next;
            temp.pre.next = temp.next;
            //删除最后一个处理
            if(null != temp.next){
                temp.next.pre = temp.pre;
            }
        }else{
            System.out.printf("要删除的节点");
        }
    }

}

//定义HeroNode2，每个HeroNode 对象就是一个节点
class HeroNode2{
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;
    public HeroNode2 pre;

    //构造器
    public HeroNode2(int no, String name, String nickName){
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
