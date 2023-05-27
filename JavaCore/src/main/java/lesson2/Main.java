package lesson2;

import java.util.Random;
import java.util.Scanner;

public class Main {
	private static final int WIN_COUNT = 5;
	private static final int FIELD_SIZE_X = 11;
	private static final int FIELD_SIZE_Y = 11;
	private static final char DOT_HUMAN = 'X';
	private static final char DOT_AI = 'O';
	private static final char DOT_EMPTY = '•';
	private static final Scanner SCANNER = new Scanner(System.in);
	private static final Random random = new Random();
	private static final char[][] FIELD = new char[FIELD_SIZE_X][FIELD_SIZE_Y];
	
	public static void main(String[] args) {
		while (true) {
			initialize();
			printField();
			while (true) {
				humanTurn();
				printField();
				if (isWin(DOT_HUMAN, "Вы победили!")) {
					break;
				}
				aiTurn();
				printField();
				if (isWin(DOT_AI, "Компьютер победил!")) {
					break;
				}
			}
			System.out.println("Желаете сыграть еще раз? (Y - да)");
			if (!SCANNER.next().equalsIgnoreCase("Y")) {
				break;
			}
		}
	}
	
	private static void initialize() {
		for (int x = 0; x < FIELD_SIZE_X; x++) {
			for (int y = 0; y < FIELD_SIZE_Y; y++) {
				FIELD[x][y] = DOT_EMPTY;
			}
		}
	}
	
	private static void printField() {
		System.out.print("  ");
		for (int i = 1; i < FIELD_SIZE_Y + 1; i++) {
			System.out.print(i + " ");
		}
		System.out.println();
		
		for (int i = 0; i < FIELD_SIZE_X; i++) {
			System.out.print(i + 1 + " ");
			for (int j = 0; j < FIELD_SIZE_Y; j++) {
				System.out.print(FIELD[i][j] + " ");
			}
			System.out.println();
		}
		
		for (int i = 0; i < FIELD_SIZE_X * 2 + 2; i++) {
			System.out.print("-");
		}
		System.out.println();
	}
	
	private static void humanTurn() {
		int x, y;
		do {
			System.out.printf("Введите координаты хода X (от 1 до %d), ", FIELD_SIZE_X);
			System.out.printf("введите координаты хода Y (от 1 до %d) через пробел >>> ", FIELD_SIZE_Y);
			x = SCANNER.nextInt() - 1;
			y = SCANNER.nextInt() - 1;
		} while (!isCellValid(x, y) || !isCellEmpty(x, y));
		FIELD[x][y] = DOT_HUMAN;
	}
	
	static boolean isCellEmpty(int x, int y) {
		return FIELD[x][y] == DOT_EMPTY;
	}
	
	static boolean isCellValid(int x, int y) {
		return x >= 0 && x < FIELD_SIZE_X && y >= 0 && y < FIELD_SIZE_Y;
	}
	
	static void aiTurn() {
		if (newAiTurn(DOT_AI)) return;
		if (newAiTurn(DOT_HUMAN)) return;
		int x, y;
		//при рандоме для лучшей защиты нужно поставить нолик по диагонали если возможно
		for (int i = 0; i < FIELD_SIZE_X; i++) {
			for (int j = 0; j < FIELD_SIZE_Y; j++) {
				if (FIELD[i][j] == DOT_HUMAN) {
					try {
						if (FIELD[i + 1][j - 1] == DOT_EMPTY) {
							FIELD[i + 1][j - 1] = DOT_AI;
							return;
						}
					} catch (Exception e) {
					}
					try {
						if (FIELD[i - 1][j + 1] == DOT_EMPTY) {
							FIELD[i - 1][j + 1] = DOT_AI;
							return;
						}
					} catch (Exception e) {
					}
					try {
						if (FIELD[i - 1][j - 1] == DOT_EMPTY) {
							FIELD[i - 1][j - 1] = DOT_AI;
							return;
						}
					} catch (Exception e) {
					}
					try {
						if (FIELD[i + 1][j + 1] == DOT_EMPTY) {
							FIELD[i + 1][j + 1] = DOT_AI;
							return;
						}
					} catch (Exception e) {
					}
				}
			}
		}
		do {
			x = random.nextInt(FIELD_SIZE_X);
			y = random.nextInt(FIELD_SIZE_Y);
		} while (!isCellEmpty(x, y));
		FIELD[x][y] = DOT_AI;
	}
	
	static boolean isOneStepToWin(char c) {
//		xxx. xx.xx x.xx .xxx
//		для линий стобцов и диагоналей
		for (int i = 0; i < FIELD_SIZE_X; i++) {
			for (int j = 0; j < FIELD_SIZE_Y - 3; j++) {
				if (FIELD[i][j] == c && FIELD[i][j + 1] == c && FIELD[i][j + 2] == DOT_EMPTY && FIELD[i][j + 3] == c) {
					FIELD[i][j + 2] = DOT_AI;
					return true;
				}
				if (FIELD[i][j] == c && FIELD[i][j + 1] == DOT_EMPTY && FIELD[i][j + 2] == c && FIELD[i][j + 3] == c) {
					FIELD[i][j + 1] = DOT_AI;
					return true;
				}
				if (FIELD[i][j] == DOT_EMPTY && FIELD[i][j + 1] == c && FIELD[i][j + 2] == c && FIELD[i][j + 3] == c) {
					FIELD[i][j] = DOT_AI;
					return true;
				}
				if (FIELD[i][j] == c && FIELD[i][j + 1] == c && FIELD[i][j + 2] == c && FIELD[i][j + 3] == DOT_EMPTY) {
					FIELD[i][j + 3] = DOT_AI;
					return true;
				}
			}
		}
		for (int i = 0; i < FIELD_SIZE_X - 3; i++) {
			for (int j = 0; j < FIELD_SIZE_Y; j++) {
				if (FIELD[i][j] == c && FIELD[i + 1][j] == c && FIELD[i + 2][j] == DOT_EMPTY && FIELD[i + 3][j] == c) {
					FIELD[i + 2][j] = DOT_AI;
					return true;
				}
				if (FIELD[i][j] == c && FIELD[i + 1][j] == DOT_EMPTY && FIELD[i + 2][j] == c && FIELD[i + 3][j] == c) {
					FIELD[i + 1][j] = DOT_AI;
					return true;
				}
				if (FIELD[i][j] == DOT_EMPTY && FIELD[i + 1][j] == c && FIELD[i + 2][j] == c && FIELD[i + 3][j] == c) {
					FIELD[i][j] = DOT_AI;
					return true;
				}
				if (FIELD[i][j] == c && FIELD[i + 1][j] == c && FIELD[i + 2][j] == c && FIELD[i + 3][j] == DOT_EMPTY) {
					FIELD[i + 3][j] = DOT_AI;
					return true;
				}
			}
		}
		for (int i = 0; i < FIELD_SIZE_X - 3; i++) {
			for (int j = 0; j < FIELD_SIZE_Y - 3; j++) {
				if (FIELD[i][j] == c && FIELD[i + 1][j + 1] == c && FIELD[i + 2][j + 2] == DOT_EMPTY && FIELD[i + 3][j + 3] == c) {
					FIELD[i + 2][j + 2] = DOT_AI;
					return true;
				}
				if (FIELD[i][j] == c && FIELD[i + 1][j + 1] == DOT_EMPTY && FIELD[i + 2][j + 2] == c && FIELD[i + 3][j + 3] == c) {
					FIELD[i + 1][j + 1] = DOT_AI;
					return true;
				}
				if (FIELD[i][j] == DOT_EMPTY && FIELD[i + 1][j + 1] == c && FIELD[i + 2][j + 2] == c && FIELD[i + 3][j + 3] == c) {
					FIELD[i][j] = DOT_AI;
					return true;
				}
				if (FIELD[i][j] == c && FIELD[i + 1][j + 1] == c && FIELD[i + 2][j + 2] == c && FIELD[i + 3][j + 3] == DOT_EMPTY) {
					FIELD[i + 3][j + 3] = DOT_AI;
					return true;
				}
			}
		}
		for (int i = 0; i < FIELD_SIZE_X - 3; i++) {
			for (int j = 0; j < FIELD_SIZE_Y - 3; j++) {
				if (FIELD[i + 3][j] == c && FIELD[i + 2][j + 1] == c && FIELD[i + 1][j + 2] == DOT_EMPTY && FIELD[i][j + 3] == c) {
					FIELD[i + 1][j + 2] = DOT_AI;
					return true;
				}
				if (FIELD[i + 3][j] == c && FIELD[i + 2][j + 1] == c && FIELD[i + 1][j + 2] == c && FIELD[i][j + 3] == DOT_EMPTY) {
					FIELD[i][j + 3] = DOT_AI;
					return true;
				}
				if (FIELD[i + 3][j] == c && FIELD[i + 2][j + 1] == DOT_EMPTY && FIELD[i + 1][j + 2] == c && FIELD[i][j + 3] == c) {
					FIELD[i + 2][j + 1] = DOT_AI;
					return true;
				}
				if (FIELD[i + 3][j] == DOT_EMPTY && FIELD[i + 2][j + 1] == c && FIELD[i + 1][j + 2] == c && FIELD[i][j + 3] == c) {
					FIELD[i + 3][j] = DOT_AI;
					return true;
				}
			}
		}
		return false;
	}
	
	static boolean newAiTurn(char c) {
		if (isOneStepToWin(DOT_AI) && WIN_COUNT > 3) return true;
		if (isOneStepToWin(DOT_HUMAN) && WIN_COUNT > 3) return true;
		//проверка всех строк на X.X XX. .XX
		for (int i = 0; i < FIELD_SIZE_X; i++) {
			for (int j = 0; j < FIELD_SIZE_Y - 2; j++) {
				if (FIELD[i][j] == c && FIELD[i][j + 1] == c && FIELD[i][j + 2] == DOT_EMPTY) {
					FIELD[i][j + 2] = DOT_AI;
					return true;
				}
				if (FIELD[i][j] == c && FIELD[i][j + 1] == DOT_EMPTY && FIELD[i][j + 2] == c) {
					FIELD[i][j + 1] = DOT_AI;
					return true;
				}
				if (FIELD[i][j] == DOT_EMPTY && FIELD[i][j + 1] == c && FIELD[i][j + 2] == c) {
					FIELD[i][j] = DOT_AI;
					return true;
				}
			}
		}
		//проверка всех столбцов на X.X XX. .XX
		for (int i = 0; i < FIELD_SIZE_X - 2; i++) {
			for (int j = 0; j < FIELD_SIZE_Y; j++) {
				if (FIELD[i][j] == c && FIELD[i + 1][j] == c && FIELD[i + 2][j] == DOT_EMPTY) {
					FIELD[i + 2][j] = DOT_AI;
					return true;
				}
				if (FIELD[i][j] == c && FIELD[i + 1][j] == DOT_EMPTY && FIELD[i + 2][j] == c) {
					FIELD[i + 1][j] = DOT_AI;
					return true;
				}
				if (FIELD[i][j] == DOT_EMPTY && FIELD[i + 1][j] == c && FIELD[i + 2][j] == c) {
					FIELD[i][j] = DOT_AI;
					return true;
				}
			}
		}
		//проверка всех главных диагоналей на X.X XX. .XX
		for (int i = 0; i < FIELD_SIZE_X - 2; i++) {
			for (int j = 0; j < FIELD_SIZE_Y - 2; j++) {
				if (FIELD[i][j] == c && FIELD[i + 1][j + 1] == c && FIELD[i + 2][j + 2] == DOT_EMPTY) {
					FIELD[i + 2][j + 2] = DOT_AI;
					return true;
				}
				if (FIELD[i][j] == c && FIELD[i + 1][j + 1] == DOT_EMPTY && FIELD[i + 2][j + 2] == c) {
					FIELD[i + 1][j + 1] = DOT_AI;
					return true;
				}
				if (FIELD[i][j] == DOT_EMPTY && FIELD[i + 1][j + 1] == c && FIELD[i + 2][j + 2] == c) {
					FIELD[i][j] = DOT_AI;
					return true;
				}
			}
		}
		//проверка всех вторых диагоналей на X.X XX. .XX
		for (int i = 0; i < FIELD_SIZE_X - 2; i++) {
			for (int j = 0; j < FIELD_SIZE_Y - 2; j++) {
				if (FIELD[i + 2][j] == c && FIELD[i + 1][j + 1] == c && FIELD[i][j + 2] == DOT_EMPTY) {
					FIELD[i][j + 2] = DOT_AI;
					return true;
				}
				if (FIELD[i + 2][j] == c && FIELD[i + 1][j + 1] == DOT_EMPTY && FIELD[i][j + 2] == c) {
					FIELD[i + 1][j + 1] = DOT_AI;
					return true;
				}
				if (FIELD[i + 2][j] == DOT_EMPTY && FIELD[i + 1][j + 1] == c && FIELD[i][j + 2] == c) {
					FIELD[i + 2][j] = DOT_AI;
					return true;
				}
			}
		}
		return false;
	}
	
	static boolean newCheck(char c) {
		int counter;
		for (int i = 0; i <= FIELD_SIZE_X - WIN_COUNT; i++) {
			for (int j = 0; j <= FIELD_SIZE_Y - WIN_COUNT; j++) {
				
				// проверка строк в подмассиве
				for (int ii = 0; ii < WIN_COUNT; ii++) {
					counter = 0;
					for (int jj = 0; jj < WIN_COUNT; jj++) {
						if (FIELD[i + ii][j + jj] == c) {
							counter++;
						}
					}
					if (counter == WIN_COUNT) return true;
				}
				
				// проверка столбцов в подмассиве
				for (int ii = 0; ii < WIN_COUNT; ii++) {
					counter = 0;
					for (int jj = 0; jj < WIN_COUNT; jj++) {
						if (FIELD[i + jj][j + ii] == c) {
							counter++;
						}
					}
					if (counter == WIN_COUNT) return true;
				}
				
				//проверка главной диагонали
				counter = 0;
				for (int k = 0; k < WIN_COUNT; k++) {
					if (FIELD[i + k][j + k] == c) counter++;
				}
				if (counter == WIN_COUNT) return true;
				
				//проверка второй диагонали
				counter = 0;
				for (int k = 0; k < WIN_COUNT; k++) {
					if (FIELD[i + WIN_COUNT - k - 1][j + k] == c) counter++;
				}
				if (counter == WIN_COUNT) return true;
			}
		}
		return false;
	}
	
	static boolean isDraw() {
		for (int x = 0; x < FIELD_SIZE_X; x++) {
			for (int y = 0; y < FIELD_SIZE_Y; y++)
				if (isCellEmpty(x, y)) return false;
		}
		return true;
	}
	
	static boolean isWin(char c, String str) {
		if (newCheck(c)) {
			System.out.println(str);
			return true;
		}
		if (isDraw()) {
			System.out.println("Ничья!");
			return true;
		}
		return false;
	}
	
}