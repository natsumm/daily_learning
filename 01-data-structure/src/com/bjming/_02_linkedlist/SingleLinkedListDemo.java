package com.bjming._02_linkedlist;

import java.util.Objects;

/**
 * @author liuluming <liuluming@ttookk.com>
 * @description 单向链表
 * @create 2024/3/26
 **/
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        //baseMethodTest();
        //findLastIndexNodeTest();
        SingleLinkedList list = new SingleLinkedList();
        list.add(new HeroNode(1, "张三", "小三"));
        list.add(new HeroNode(2, "李四", "小四"));
        list.add(new HeroNode(3, "王五", "小五"));
        list.add(new HeroNode(4, "赵六", "小六"));
        list.add(new HeroNode(5, "赵六", "小六"));
        list.add(new HeroNode(6, "赵六", "小六"));
        SingleLinkedList result = reverse2(list.getHead());
        //result.list();

    }

    // 单链表反转
    // 不推荐 有些取巧
    public static SingleLinkedList reverse(SingleLinkedList list){
        HeroNode head = list.getHead();
        int length = getLength(head);
        HeroNode[] nodeArr = new HeroNode[length];
        int idx = 0;
        for(HeroNode tmp = head.getNext(); tmp != null; tmp = tmp.getNext()) {
            nodeArr[idx++] = tmp;
        }
        SingleLinkedList result = new SingleLinkedList();
        for (int i = length - 1; i >= 0 ; i--) {
            HeroNode node = nodeArr[i];
            node.setNext(null);
            result.add(node);
        }
        return result;
    }

    // TODO 优化此方法
    public static SingleLinkedList reverse2(HeroNode head){
        HeroNode reverseNode = new HeroNode(null, null, null);

        while (true) {
            HeroNode node1 = head.getNext();
            if (node1 == null) break;
            HeroNode node2 = node1.getNext();

            node1.setNext(reverseNode);
            head.setNext(node2);
            reverseNode = node1;
        }

        head.setNext(reverseNode);




        HeroNode temp = head.getNext();
        if (temp == null) {
            System.err.println("当前链表为空，不予遍历");
            return null;
        }
        while (temp != null) {
            System.out.println(temp);
            temp = temp.getNext();
        }
        return null;
    }

    private static void findLastIndexNodeTest() {
        SingleLinkedList list = new SingleLinkedList();
        list.add(new HeroNode(1, "张三", "小三"));
        list.add(new HeroNode(2, "李四", "小四"));
        list.add(new HeroNode(3, "王五", "小五"));
        list.add(new HeroNode(4, "赵六", "小六"));
        list.add(new HeroNode(5, "赵六", "小六"));
        list.add(new HeroNode(6, "赵六", "小六"));
        list.list();
        HeroNode nodeDesc = findLastIndexNode(list.getHead(), 1);
        System.out.println("nodeDesc = " + nodeDesc);
    }


    // 查找单链表中的倒数第k个节点
    public static HeroNode findLastIndexNode(HeroNode head, Integer index) {
        if (head.getNext() == null) {
            return null;
        }
        int length = getLength(head);
        if (index < 0 || index > length) {
            return null;
        }
        HeroNode tmp = head.getNext();
        // head 0 1 2 3 4
        for (int i = 0; i < length - index; i++) {
            tmp = tmp.getNext();
        }
        return tmp;
    }

    public static void baseMethodTest() {
        SingleLinkedList list = new SingleLinkedList();
        System.out.println("----------------------初始添加");
        list.add(new HeroNode(2, "李四", "小四"));
        list.add(new HeroNode(3, "王五", "小五"));
        list.add(new HeroNode(1, "张三", "小三"));
        list.add(new HeroNode(4, "赵六", "小六"));
        list.list();
        System.out.println("----------------------根据id添加");

        SingleLinkedList list1 = new SingleLinkedList();
        list1.addByOrder(new HeroNode(2, "李四", "小四"));
        list1.addByOrder(new HeroNode(3, "王五", "小五"));
        list1.addByOrder(new HeroNode(1, "张三", "小三"));
        list1.addByOrder(new HeroNode(6, "赵六", "小六"));
        list1.addByOrder(new HeroNode(5, "赵六", "小六"));
        list1.addByOrder(new HeroNode(5, "赵六", "小六"));
        list1.addByOrder(new HeroNode(4, "赵六", "小六"));
        list1.list();


        System.out.println("---------------------根据id修改");
        list.update(new HeroNode(4, "李四XXXXXX", "小四XXXXX"));
        list.list();

        System.out.println("---------------------删除");
        list.delete(4);
        list.delete(4);
        list.delete(4);
        list.delete(4);
        list.list();

        int length = getLength(list.getHead());
        System.out.println("length = " + length);
    }

    /**
     * 获取到单链表的节点个数
     * @param head 链表的头结点
     * @return 有效节点个数
     */
    public static int getLength(HeroNode head) {
        if (head.getNext() == null) {
            return 0;
        }
        int length = 0;
        for (HeroNode tmp = head.getNext(); tmp != null; tmp = tmp.getNext()) {
            length++;
        }
        return length;
    }
}


class HeroNode {
    private final Integer id;
    private String name;
    private String nickname;
    private HeroNode next;

    public HeroNode(Integer id, String name, String nickname) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public HeroNode getNext() {
        return next;
    }

    public void setNext(HeroNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}

class SingleLinkedList {
    private final HeroNode head = new HeroNode(null,null,null);


    public HeroNode getHead() {
        return head;
    }

    // 普通添加
    public void add(HeroNode heroNode) {
        HeroNode temp = head.getNext();
        if (temp == null) {
            head.setNext(heroNode);
            return;
        }
        // 找到最后一个节点
        while (temp.getNext() != null) {
            temp = temp.getNext();
        }
        // 更新节点
        temp.setNext(heroNode);
    }

    // 根据id的大小进行添加
    public void addByOrder(HeroNode heroNode){
        HeroNode temp = head;
        while(true) {
            if (temp.getNext() == null) {
                temp.setNext(heroNode);
                break;
            }
            if (Objects.equals(temp.getNext().getId(), heroNode.getId())) {
                System.err.printf("编号为 %s 的英雄已存在，不再插入\n", heroNode.getId());
                break;
            }

            if (temp.getNext().getId() > heroNode.getId()) {
                heroNode.setNext(temp.getNext());
                temp.setNext(heroNode);
                break;
            }

            temp = temp.getNext();
        }
    }

    /**
     * 根据newHeroNode 的id进行修改
     */
    public void update(HeroNode newHeroNode) {
        if (head.getNext() == null) {
            System.err.println("当前链表为空，不允许修改");
            return;
        }
        HeroNode tmp = head;
        boolean flag = false;
        while (tmp != null) {
            if (tmp.getNext() == null) {
                break;
            }
            if (tmp.getNext().getId().equals(newHeroNode.getId())) {
                flag = true;
                break;
            }
            tmp = tmp.getNext();
        }
        if (flag) {
            newHeroNode.setNext(tmp.getNext().getNext());
            tmp.setNext(newHeroNode);
            System.out.println("更新成功!");
        } else {
            System.err.printf("链表中不存在id为：%s的数据", newHeroNode.getId());
        }

    }

    public void delete(Integer id) {
        if (head.getNext() == null) {
            System.err.println("当前链表为空，不允许删除");
            return;
        }
        HeroNode tmp = head;
        boolean flag = false;
        while (tmp != null) {
            if (tmp.getNext() == null) {
                break;
            }
            if (tmp.getNext().getId().equals(id)) {
                flag = true;
                break;
            }
            tmp = tmp.getNext();
        }

        if (flag) {
            tmp.setNext(tmp.getNext().getNext());

        }
    }

    // 遍历
    public void list() {
        HeroNode temp = head.getNext();
        if (temp == null) {
            System.err.println("当前链表为空，不予遍历");
            return;
        }
        while (temp != null) {
            System.out.println(temp);
            temp = temp.getNext();
        }
    }
}