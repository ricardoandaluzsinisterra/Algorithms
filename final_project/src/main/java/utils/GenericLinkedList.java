package utils;

import java.util.NoSuchElementException;
@SuppressWarnings("unused")
public class GenericLinkedList<T> {
    protected int count;
    protected Node<T> head;
    protected Node<T> tail;

    public GenericLinkedList(){
        this.head = null;
        this.tail = null;
        count = 0;
    }

    public int size(){
        return count;
    }

    public T get(int pos){
        validatePosition(pos);

        Node<T> current = head;
        for (int i = 0; i < pos; i++) {
            current = current.next;
        }

        return current.data;
    }

    public int indexOf(T value){
        Node<T> current = head;
        for (int i = 0; i < count; i++) {
            if(value.equals(current.data)){
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    public void add(T data){
        Node<T> newNode = new Node<>(data);
        if(head == null){
            head = newNode;
            tail = newNode;
        }else{
            tail.next = newNode;
            tail = newNode;
        }
        count++;
    }

    public void insert(T value, int pos){
        if(pos < 0 || pos > count){
            throw new IndexOutOfBoundsException("Illegal position supplied - position must be within bounds of list");
        }

        if(pos == count){
            add(value);
        }else if(pos == 0){
            addToStart(value);
        }else {
            Node<T> newNode = new Node<>(value);

            Node<T> current = head;
            Node<T> prev = null;
            for (int i = 0; i < pos; i++) {
                prev = current;
                current = current.next;
            }
            newNode.next = current;
            prev.next = newNode;

            count++;
        }
    }

    public void addToStart(T value){
        Node<T> newNode = new Node<>(value);
        if(head == null){
            head = newNode;
            tail = newNode;
        }else{
            newNode.next = head;
            head = newNode;
        }
        count++;
    }

    public T remove(int pos){
        validatePosition(pos);

        if(pos == 0){
            return removeFirst();
        }else if(pos == count-1){
            return removeLast();
        }else{
            Node<T> current = head;
            Node<T> prev = null;

            for (int i = 0; i < pos; i++) {
                prev = current;
                current = current.next;
            }
            T deleted = current.data;
            prev.next = current.next;
            count--;
            return deleted;
        }
    }

    private void validatePosition(int pos) {
        if(pos < 0 || pos >= count){
            throw new IndexOutOfBoundsException("Position must be within the boundaries of the list");
        }
    }

    public T removeFirst(){
        if(head == null){
            throw new NoSuchElementException("Cannot delete from an empty list");
        }

        T deleted = head.data;

        head = head.next;
        if(head == null){
            tail = null;
        }
        count--;

        return deleted;
    }

    public T removeLast(){
        if(head == null){
            throw new NoSuchElementException("Cannot delete from an empty list");
        }

        T deleted = null;
        if(head == tail) {
            deleted = head.data;
            head = null;
            tail = null;
        }else {
            Node<T> current = head;
            while(current.next != tail) {
                current = current.next;
            }
            current.next = null;
            deleted = tail.data;
            tail = current;
        }
        count--;

        return deleted;
    }

    protected static class Node<T>{
        T data;
        Node<T> next;

        public Node(T data){
            this.data = data;
            next = null;
        }
    }
}