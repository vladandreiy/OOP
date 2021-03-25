package com.oop.lab.lab09;

public class MyList<E> {
    private E[] array;
    private int size;

    public MyList(int len) {
        if (len <= 0)
            throw new IllegalArgumentException("List dimension should be bigger than 0");
        array = (E[]) new Object[len];
        size = 0;
    }

    public void add(E x) {
        if (this.array.length <= this.size)
            this.doubleSize();
        this.array[this.size] = x;
        this.size++;
    }

    public void doubleSize() {
        E[] new_array = (E[]) new Object[2 * this.size];
        System.arraycopy(this.array, 0, new_array, 0, this.array.length);
        this.array = new_array;
    }

    public void print() {
        StringBuilder printMessage = new StringBuilder("[");
        for (int i = 0; i < this.size; i++) {
            printMessage.append(this.array[i]).append(", ");
        }
        printMessage.delete(printMessage.length() - 2, printMessage.length());
        printMessage.append("]");
        System.out.println(printMessage);
    }

    public boolean find(E x) {
        for (E element : array)
            if (x.equals(element))
                return true;
        return false;
    }

    public static void main(String[] args) {
        MyList<Integer> list1 = new MyList<>(3);
        list1.add(1);
        list1.add(2);
        list1.add(4);
        list1.print();
        if (list1.find(1))
            System.out.println("Numarul 1 se regaseste in lista");
        if(list1.find(5))
            System.out.println("Numarul 5 nu se regaseste in lista");
        MyList<String> list2 = new MyList<>(1);
        list2.add("ay");
        list2.add("yo");
        list2.add("s");
        list2.print();
        try {
            MyList<Double> list3 = new MyList<>(-1);
        } catch (IllegalArgumentException e) {
            System.err.println("List dimension should be bigger than 0");
        }
    }

}
