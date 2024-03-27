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
        list.add(new Node(1, "张三", "小三"));
        list.add(new Node(2, "李四", "小四"));
        list.add(new Node(3, "王五", "小五"));
        list.add(new Node(4, "赵六", "小六"));
        list.add(new Node(5, "赵六", "小六"));
        list.add(new Node(5, "赵六", "小六"));
        list.add(new Node(5, "赵六", "小六"));
        list.add(new Node(6, "赵六", "小六"));
        list.add(new Node(6, "赵六", "小六"));
        list.add(new Node(6, "赵六", "小六"));
        Node head = reverse2(list.getHead());

        Node tmp = head.getNext();
        if (tmp == null) {
            System.err.println("当前链表为空，不予遍历");
            return;
        }
        while (tmp != null) {
            System.out.println(tmp);
            tmp = tmp.getNext();
        }
    }

    // 单链表反转
    // 不推荐 有些取巧
    public static SingleLinkedList reverse(SingleLinkedList list){
        Node head = list.getHead();
        int length = getLength(head);
        Node[] nodeArr = new Node[length];
        int idx = 0;
        for(Node tmp = head.getNext(); tmp != null; tmp = tmp.getNext()) {
            nodeArr[idx++] = tmp;
        }
        SingleLinkedList result = new SingleLinkedList();
        for (int i = length - 1; i >= 0 ; i--) {
            Node node = nodeArr[i];
            node.setNext(null);
            result.add(node);
        }
        return result;
    }


    // 单链表反转
    // 1. 准备一个空的node -> reverseNode
    // 2. 从原来的链表依次取出节点tmp, 使得 tmp.next -> reverseNode
    // 3. 移动reverseNode
    // 4. 使得head指向新的链表
    public static Node reverse2(Node head){
        Node reverseNode = new Node(-1, null, null);

        while (true) {
            Node node1 = head.getNext();
            if (node1 == null) break;
            Node node2 = node1.getNext();

            node1.setNext(reverseNode);
            head.setNext(node2);
            reverseNode = node1;
        }

        head.setNext(reverseNode);

        // 遍历删除最后的空节点
        Node tmp = head;
        while (tmp.getNext() != null) {
            if (tmp.getNext().getId().equals(-1)) {
                tmp.setNext(tmp.getNext().getNext());
                break;
            }
            tmp = tmp.getNext();
        }
        return head;

    }

    private static void findLastIndexNodeTest() {
        SingleLinkedList list = new SingleLinkedList();
        list.add(new Node(1, "张三", "小三"));
        list.add(new Node(2, "李四", "小四"));
        list.add(new Node(3, "王五", "小五"));
        list.add(new Node(4, "赵六", "小六"));
        list.add(new Node(5, "赵六", "小六"));
        list.add(new Node(6, "赵六", "小六"));
        list.list();
        Node nodeDesc = findLastIndexNode(list.getHead(), 1);
        System.out.println("nodeDesc = " + nodeDesc);
    }


    // 查找单链表中的倒数第k个节点
    public static Node findLastIndexNode(Node head, Integer index) {
        if (head.getNext() == null) {
            return null;
        }
        int length = getLength(head);
        if (index < 0 || index > length) {
            return null;
        }
        Node tmp = head.getNext();
        // head 0 1 2 3 4
        for (int i = 0; i < length - index; i++) {
            tmp = tmp.getNext();
        }
        return tmp;
    }

    public static void baseMethodTest() {
        SingleLinkedList list = new SingleLinkedList();
        System.out.println("----------------------初始添加");
        list.add(new Node(2, "李四", "小四"));
        list.add(new Node(3, "王五", "小五"));
        list.add(new Node(1, "张三", "小三"));
        list.add(new Node(4, "赵六", "小六"));
        list.list();
        System.out.println("----------------------根据id添加");

        SingleLinkedList list1 = new SingleLinkedList();
        list1.addByOrder(new Node(2, "李四", "小四"));
        list1.addByOrder(new Node(3, "王五", "小五"));
        list1.addByOrder(new Node(1, "张三", "小三"));
        list1.addByOrder(new Node(6, "赵六", "小六"));
        list1.addByOrder(new Node(5, "赵六", "小六"));
        list1.addByOrder(new Node(5, "赵六", "小六"));
        list1.addByOrder(new Node(4, "赵六", "小六"));
        list1.list();


        System.out.println("---------------------根据id修改");
        list.update(new Node(4, "李四XXXXXX", "小四XXXXX"));
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
    public static int getLength(Node head) {
        if (head.getNext() == null) {
            return 0;
        }
        int length = 0;
        for (Node tmp = head.getNext(); tmp != null; tmp = tmp.getNext()) {
            length++;
        }
        return length;
    }
}


class Node {
    private final Integer id;
    private String name;
    private String nickname;
    private Node next;

    public Node(Integer id, String name, String nickname) {
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

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
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
    private final Node head = new Node(null,null,null);


    public Node getHead() {
        return head;
    }

    // 普通添加
    public void add(Node node) {
        Node tmp = head.getNext();
        if (tmp == null) {
            head.setNext(node);
            return;
        }
        // 找到最后一个节点
        while (tmp.getNext() != null) {
            tmp = tmp.getNext();
        }
        // 更新节点
        tmp.setNext(node);
    }

    // 根据id的大小进行添加
    public void addByOrder(Node node){
        Node tmp = head;
        while(true) {
            if (tmp.getNext() == null) {
                tmp.setNext(node);
                break;
            }
            if (Objects.equals(tmp.getNext().getId(), node.getId())) {
                System.err.printf("编号为 %s 的英雄已存在，不再插入\n", node.getId());
                break;
            }

            if (tmp.getNext().getId() > node.getId()) {
                node.setNext(tmp.getNext());
                tmp.setNext(node);
                break;
            }

            tmp = tmp.getNext();
        }
    }

    /**
     * 根据newHeroNode 的id进行修改
     */
    public void update(Node newNode) {
        if (head.getNext() == null) {
            System.err.println("当前链表为空，不允许修改");
            return;
        }
        Node tmp = head;
        boolean flag = false;
        while (tmp != null) {
            if (tmp.getNext() == null) {
                break;
            }
            if (tmp.getNext().getId().equals(newNode.getId())) {
                flag = true;
                break;
            }
            tmp = tmp.getNext();
        }
        if (flag) {
            newNode.setNext(tmp.getNext().getNext());
            tmp.setNext(newNode);
            System.out.println("更新成功!");
        } else {
            System.err.printf("链表中不存在id为：%s的数据", newNode.getId());
        }

    }

    public void delete(Integer id) {
        if (head.getNext() == null) {
            System.err.println("当前链表为空，不允许删除");
            return;
        }
        Node tmp = head;
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
        Node tmp = head.getNext();
        if (tmp == null) {
            System.err.println("当前链表为空，不予遍历");
            return;
        }
        while (tmp != null) {
            System.out.println(tmp);
            tmp = tmp.getNext();
        }
    }
}