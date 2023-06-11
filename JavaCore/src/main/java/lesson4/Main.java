package lesson4;

public class Main {
	public static void main(String[] args) {
		String[][] array = {{"1", "2", "3", "4"},
							{"5", "6", "7", "8"},
							{"12", "11", "10", "9"},
							{"13", "14", "1f5", "16"}};
		
		System.out.println(summArr4x4(array));
	}
	
	public static Integer summArr4x4(String[][] arr) {
		Integer summ = 0;
		try {
			if (arr.length != 4
						|| arr[0].length != 4
						|| arr[1].length != 4
						|| arr[2].length != 4
						|| arr[3].length != 4) {
				throw new MyArraySizeException("не верный формат массива");
			}
			
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					try {
						summ += Integer.parseInt(arr[i][j]);
					} catch (NumberFormatException e) {
						throw new MyArrayDataException("не верный формат данных в ячейке", i, j);
					}
					
				}
			}
			
		} catch (MyArraySizeException e) {
			System.out.println(e.getMessage());
			summ = null;
		} catch (MyArrayDataException e) {
			System.out.printf("%s, %d %d \n",e.getMessage(), e.getI() + 1, e.getJ() + 1);
			summ = null;
		}
		
		return summ;
	}
}
