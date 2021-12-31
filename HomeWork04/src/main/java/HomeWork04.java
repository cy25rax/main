import java.util.Scanner;

public class HomeWork04 {
    public static Scanner scan = new Scanner(System.in);
    public static int size = 5;
    public static char[][] map=new char[size][size];
    public static char emptycell='.';
    public static char fillcellX='X';
    public static char fillcellO='O';

    public static void main(String[] args) {
        int x,y;
        initmap(size);
        showmap();
        System.out.println();

        do {
            do {
                System.out.println("введи координаты x y");
                x = scan.nextInt() - 1;
                y = scan.nextInt() - 1;
            } while (!validcell(x, y));
            fillmap(x, y, fillcellX);
            showmap();
            if (checkWin(fillcellX)) {
                System.out.println("победил человек");
                break;
            }
            if (ismapfull()) {
                System.out.println("ничья");
                break;
            }

            do {
                x = (int) (Math.random() * size);
                y = (int) (Math.random() * size);
            } while (map[x][y] != '.');

            if (proverka()) {               //логика компьютера
                System.out.println("подстановка");
                System.out.println();
            } else {
                fillmap(x, y, fillcellO);
            }
            showmap();
            if (checkWin(fillcellO)) {
                System.out.println("победил компуктер");
                break;
            }

            if (ismapfull()) {
                System.out.println("ничья");
                break;
            }

        } while (true);
    }

    public static boolean ismapfull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j]==emptycell) return false;
            }

        }
        return true;
    }

    public static boolean validcell(int x,int y) {
        if (x<0 || x>size || y<0 || y>size) return false;
        if (map[x][y]==emptycell) return true;
        return false;
    }

    public static boolean checkWin(char symb) {
        int k=0;                                        //счетчик одинаковых символов

        for (int i = 0; i < size; i++) {
            k=0;
            for (int j = 0; j < size; j++) {            //проверка строк
                if (map[i][j] == symb) {
                    k++;
                }
                if (k==3 && size==3) return true;
                if (k==4 && size==5 && map[i][j-1]!=emptycell && map[i][j-2]!=emptycell
                        && map[i][j-3]!=emptycell) { //проверка что все символы подряд
                    return true;
                }
            }
            k=0;
            for (int j = 0; j < size; j++) {            // проверка столбцов
                if (map[j][i]==symb) {
                    k++;
                }
                if (k==3 && size==3) return true;
                if (k==4 && size==5 && map[j-1][i]!=emptycell && map[j-2][i]!=emptycell
                        && map[j-3][i]!=emptycell) {
                    return true;
                }
            }
        }
        if (size==3 && map[0][0]==symb && map[1][1]==symb && map[2][2]==symb){
                return true;                    // проверка диагонали на поле 3 на 3
        } else {
            for (int i = 0; i < size-3; i++) {        // проверка главной диагонали на поле с другими размерностями
                for (int j = 0; j < size - 3; j++) {
                    if (map[i][j] == symb && map[1 + i][1 + j] == symb && map[2 + i][2 + j] == symb
                            && map[3 + i][3 + j] == symb) {
                        return true;
                    }

                }
            }
        }

        if (size==3 && map[2][0]==symb && map[1][1]==symb && map[0][2]==symb) {
            return true;
        } else {
            for (int i = 0; i < size-3; i++) {        // проверка побочной диагонали на поле с другими размерностями
                for (int j = 0; j < size-3; j++) {      // начиная справа на лево вниз
                    if (map[i][size-j-1]==symb && map[i+1][size-j-1-1]==symb && map[i+2][size-j-1-2]==symb
                            && map[i+3][size-j-1-3]==symb) {
                        return true;
                    }

                }
            }
        }

        return false;
    }

    public static boolean proverka() {

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size-2; j++) {            //проверка строк
                if (map[i][j] == fillcellX && map[i][j+1] == fillcellX && map[i][j+2]==emptycell) {
                    map[i][j+2]=fillcellO;          // если символы X X .
                    return true;                    //меняет точку на 0
                }
                if (map[i][j] == fillcellX && map[i][j+1] == emptycell && map[i][j+2]==fillcellX) {
                    map[i][j+1]=fillcellO;          // если символы X . X
                    return true;
                }
                if (map[i][j] == emptycell && map[i][j+1] == fillcellX && map[i][j+2]==fillcellX) {
                    map[i][j]=fillcellO;            // если символы . X X
                    return true;
                }
            }

            for (int j = 0; j < size-2; j++) {            // проверка столбцов
                if (map[j][i] == fillcellX && map[j+1][i] == fillcellX && map[j+2][i]==emptycell) {
                    map[j+2][i]=fillcellO;
                    return true;
                }
                if (map[j][i] == fillcellX && map[j+1][i] == emptycell && map[j+2][i]==fillcellX) {
                    map[j+1][i]=fillcellO;
                    return true;
                }
                if (map[j][i] == emptycell && map[j+1][i] == fillcellX && map[j+2][i]==fillcellX) {
                    map[j][i]=fillcellO;
                    return true;
                }
            }
        }

        for (int i = 0; i < size-2; i++) {
            for (int j = 0; j < size-2; j++) {          // проверка диагонали
                    if (map[i][j] == fillcellX && map[1+i][1+j] == fillcellX && map[2+i][2+j]==emptycell) {
                        map[2+i][2+j]=fillcellO;
                        return true;
                    }
                    if (map[i][j] == fillcellX && map[1+i][1+j] == emptycell && map[2+i][2+j]==fillcellX) {
                        map[1+i][1+j]=fillcellO;
                        return true;
                    }
                    if (map[i][j] == emptycell && map[1+i][1+j] == fillcellX && map[2+i][2+j]==fillcellX) {
                        map[i][j] = fillcellO;
                        return true;
                    }
            }
        }

        for (int i = 0; i < size-2; i++) {
            for (int j = 0; j < size-2; j++) {           // проверка диагонали
                    if (map[i][size-1-j] == fillcellX && map[1+i][size-1-1-j] == fillcellX && map[2+i][size-1-2-j]==emptycell) {
                        map[2+i][size-1-2-j]=fillcellO;
                        return true;
                    }
                    if (map[i][size-1-j] == fillcellX && map[1+i][size-1-1-j] == emptycell && map[2+i][size-1-2-j]==fillcellX) {
                        map[1+i][size-1-1-j]=fillcellO;
                        return true;
                    }
                    if (map[i][size-1-j] == emptycell && map[1+i][size-1-1-j] == fillcellX && map[2+i][size-1-2-j]==fillcellX) {
                        map[i][size-1-j]=fillcellO;
                        return true;
                    }
            }
        }

        return false;
    }

    public static void initmap(int x) {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < x; j++) {
                map[i][j]=emptycell;
            }
        }
    }

    public static void fillmap(int x, int y, char fillcell){
        map[x][y]=fillcell;
    }

    public static void showmap() {
        for (int i = 0; i < size+1; i++) {
            System.out.print(i+" ");
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print(i+1+" ");
            for (int j = 0; j < size; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }


}
