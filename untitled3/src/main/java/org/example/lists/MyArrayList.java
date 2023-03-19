package org.example.lists;

public class MyArrayList {
	private Integer[] arr = new Integer[1];
	int size = 0;
	
	public void add (int value) {
		if (size == arr.length) {
			addCapacity();
		}
		arr[size] = value;
		size++;
	}
	
	private void addCapacity () {
		int capacity = size + 1;
		Integer [] oldArr = arr;
		arr = new Integer[capacity];
		for (int i = 0; i < oldArr.length; i++) {
			arr[i] = oldArr[i];
		}
	}
	
	public int findByIndex (int index) {
		return arr[index];
	}
	
	public Integer findByValue (int value) {
		for (int i = 0; i < size; i++) {
			if (arr[i] == value) return i;
		}
		return null;
	}
	
	public boolean deleteByIndex (int index) {
		if ((index - 1) <= size) {
			for (int i = index; i < size-1; i++) {
				arr[i] = arr[i+1];
			}
			arr[size-1] = null;
			size--;
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < size; i++) {
			str += arr[i] + " ";
		}
		return str;
	}
	
	public int size() {
		return size;
	}
	
	public boolean deleteByValue (int value) {
		for (int i = 0; i < size; i++) {
			if (arr[i] == value){
				deleteByIndex(i);
				return true;
			}
		}
		return false;
	}
	
}
