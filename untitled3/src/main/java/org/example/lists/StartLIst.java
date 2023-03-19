package org.example.lists;

public class StartLIst {
	public static void main(String[] args) {
		MyArrayList myArrayList = new MyArrayList();
		myArrayList.add(1);
		myArrayList.add(10);
		myArrayList.add(100);
		System.out.println(myArrayList);
		myArrayList.deleteByValue(10);
		System.out.println("delete value 10: " + myArrayList);
		System.out.println("find index 0: " + myArrayList.findByIndex(0));
		System.out.println("find value 100: " + myArrayList.findByValue(100));
		
		System.out.println();
		
		MyLinkedList myLinkedList = new MyLinkedList();
		myLinkedList.add(1);
		myLinkedList.add(2);
		myLinkedList.add(3);
		myLinkedList.add(5);
		System.out.println("linked list data: " + myLinkedList);
		System.out.println("index value of 5: " + myLinkedList.findByValue(5));
		System.out.println("value index of 3: " + myLinkedList.findByIndex(3));
		myLinkedList.add(4,3);
		myLinkedList.deleteByIndex(2);
		myLinkedList.deleteByValue(1);
		System.out.println(myLinkedList);
	}
}
