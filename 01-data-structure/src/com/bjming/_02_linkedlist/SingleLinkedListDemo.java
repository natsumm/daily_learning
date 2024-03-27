package com.bjming._02_linkedlist;

import java.util.Objects;

/**
 * @author liuluming <liuluming@ttookk.com>
 * @description 单向链表
 * @create 2024/3/26
 **/
public class SingleLinkedListDemo {

    public static void main(String[] args) {

        SingleLinkedList list = new SingleLinkedList(new HeroNode(null, null, null));
        System.out.println("----------------------初始添加");
        list.add(new HeroNode(2, "李四", "小四"));
        list.add(new HeroNode(3, "王五", "小五"));
        list.add(new HeroNode(1, "张三", "小三"));
        list.add(new HeroNode(4, "赵六", "小六"));
        list.list();
        System.out.println("----------------------根据id添加");

        SingleLinkedList list1 = new SingleLinkedList(new HeroNode(null, null, null));
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
    private final HeroNode head;

    public SingleLinkedList(HeroNode head) {
        this.head = head;
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