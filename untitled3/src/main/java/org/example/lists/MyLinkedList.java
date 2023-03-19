package org.example.lists;

public class MyLinkedList {
	private Node head;
	private Node tail;
	private int size = 0;
	
	public Integer findByValue (int value) {
		Node node = head;
		int iter = 0;
		do {
			iter++;
			if (node.value == value) return iter;
		} while ((node = node.next) != null);
		return null;
	}
	
	public Integer findByIndex (int index) {
		Node node = head;
		int iter = 0;
		do {
			iter++;
			if (index == iter) return node.value;
		} while ((node = node.next) != null);
		return null;
	}
	
	public void add (int value) {
		Node last = tail;
		Node newNode = new Node();
		newNode.prev = last;
		newNode.value = value;
		
		tail = newNode;

		if (last == null) {
			head = newNode;
		} else {
			last.next = newNode;
		}
		size++;
	}
	
	public void deleteByIndex(int index) {
		Node node = head;
		int iter = 0;
		do {
			iter++;
			if (index == iter) {
				if (node.prev == null) {
					node.next.prev = null;
					head = node.next;
					return;
				}
				if (node.next == null) {
					tail = node.prev;
					node.prev.next = null;
					return;
				}
				node.prev.next = node.next;
				node.next.prev = node.prev;
				return;
			}
		} while ((node = node.next) != null);
	}
	
	public void deleteByValue (int value) {
		Node node = head;
		do {
			if (value == node.value) {
				if (node.prev == null) { // value в голове
					node.next.prev = null;
					head = node.next;
					return;
				}
				if (node.next != null) { // value в середине
					node.prev.next = node.next;
					node.next.prev = node.prev;
				} else node.prev.next = null; // value в конце
				return;
			}
		} while ((node = node.next) != null);
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		Node node = head;
		do {
			str.append(node.value).append(" ");
		} while ((node = node.next) != null);
		return str.toString();
	}
	
	public boolean add (int value, int index) {
		if ((index) < size) {
			Node node = head;
			int iter = 0;
			do {
				if (iter == (index - 1)) {
					Node newNode = new Node();
					newNode.value = value;
					newNode.prev = node;
					newNode.next = node.next;
					node.next.prev = newNode;
					node.next = newNode;
					size++;
					return true;
				}
				iter++;
			} while ( (node = node.next) != null);
		}
		return false;
	}
	
	public int size() {
		return size;
	}
	
	class Node {
		private int value;
		private Node next;
		private Node prev;
	}
}
