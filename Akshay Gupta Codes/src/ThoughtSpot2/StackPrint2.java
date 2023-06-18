/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ThoughtSpot2;

import java.util.*;

/**
 *
 * @author guptaakshay
 * Write data structure to implement stack with push in O(1)
 * Pop in O(1) 
 * and print stack in O(stackSize)
 */

public class StackPrint2 {

    int currentVersion = 0;
    Map<Integer, Node> versionToNodeMap = new HashMap<>();
    StackToPrint stack = new StackToPrint();

    public void push(int value) {
        Node snapshot = stack.add(value);
        versionToNodeMap.put(++currentVersion, snapshot);
    }

    public void pop() {
        Node snapshot = stack.remove();
        versionToNodeMap.put(++currentVersion, snapshot.next);
    }

    public List<Integer> print(int version) {
        Node snapshot = versionToNodeMap.get(version);
        return getList(snapshot);
    }

    private List<Integer> getList(Node temp) {
        List<Integer> list = new ArrayList<>();
        while (temp != null) {
            list.add(temp.value);
            temp = temp.next;
        }
        return list;
    }
}

class Node {

    int value;
    Node next;

    public Node() {
    }

    public Node(int value) {
        this.value = value;
    }
}

class StackToPrint {

    Node head;

    public StackToPrint() {
        head = null;
    }

    public Node add(int value) {
        Node node = new Node(value);
        if (head == null) {
            head = node;
            return node;
        } else {
            node.next = head;
            head = node;
        }
        return node;
    }

    public Node remove() {
        Node node = head;
        head = head.next;
        return node;
    }
}
