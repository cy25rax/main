import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Calculator extends JFrame {
    public static String value="";
    private String [] knopki={"0","1","2","3","4","5","6","7","8","9","/","*","-","+","="};

    public Calculator() {
        setBounds(100, 100, 550, 480);

        JButton[] buttons= new JButton[16];
        setTitle("калькулятор");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        Font font = new Font("Arial", Font.BOLD, 32);

        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(410, 100));
        label.setFont(font);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);
        label.setText(String.valueOf(value));

        setLayout(new FlowLayout());
        buttons[0] = new JButton("c");
        buttons[0].setPreferredSize(new Dimension(100, 100));
        buttons[0].setFont(font);
        add(buttons[0]);


        for(int i = 1; i < 16; i++) {
            buttons[i]=new JButton(knopki[i-1]);
            buttons[i].setFont(font);
            buttons[i].setPreferredSize(new Dimension(100, 100));
            add(buttons[i]);
        }


        for (JButton button:buttons) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (button.getActionCommand()=="=") {
                        calculate();
                        label.setText(String.valueOf(value));
                    }else if (button.getActionCommand()=="c") {
                        value=value.substring(0, value.length() - 1);
                    } else value=value+button.getActionCommand();

                    label.setText(String.valueOf(value));
                }
            });
        }

        setVisible(true);
    }

    public static void main(String[] args) {
        new Calculator();
    }

    public static void calculate(){
        //смысл вычисления такой-все выражение разбивается на массив чисел и на массив знаков действия
        //после умножения или деления(они проходят первыми) 1 знак умножения(деления) удаляется и
        // удаляется 1 число (множетель или делитель). Потом все повторяется пока не останется только
        //сложение которое пройдет в конце
        String str = value;         //выражение калькулятора передается строкой
        boolean znokMinus=false;
        System.out.println(str);
        if (str.indexOf("-")==0) {          //если первое число отрицательное делаем его положительным
            str=str.substring(1,str.length());  //удаляем минус из общей строки (он учтется в конце)
            znokMinus=true;
        }
        String[] numbers = str.split("[-*/+]");
        String str2=str.replaceAll("[.0123456789]","");
        String[] znak = str2.split("");
//        System.out.println(Arrays.toString(numbers));
//        System.out.println(Arrays.toString(znak));

        double znacenie;                                    //вначале производим умножение и деление

        for (int i = 0; i < znak.length; i++) {
            if (znak[i].equals("*")) {
                znacenie=Double.parseDouble(numbers[i]) * Double.parseDouble(numbers[i + 1]);
                numbers[i+1]=String.valueOf(znacenie);
                numbers[i]="";          //после умножения удаляем 1 число
                znak[i]="";             //удаляем 1 знак уножения
                i--;
                str="";                 // создаем новую строку выражения без 1 числа и 1 знака
                for (int j = 0; j < znak.length; j++) {     //которые мы уже перемножили
                    str=str+numbers[j]+znak[j];
                }
                str=str+numbers[numbers.length-1];

//                System.out.println("str"+str);

                //разбиваем новую строку на массив чисел и знаков
                numbers = str.split("[-*/+]");
                str2=str.replaceAll("[.0123456789]","");
                znak = str2.split("");
//                System.out.println(Arrays.toString(numbers));
//                System.out.println(Arrays.toString(znak));
            } else if (znak[i].equals("/")) {
                znacenie=Double.parseDouble(numbers[i]) / Double.parseDouble(numbers[i + 1]);
                numbers[i+1]=String.valueOf(znacenie);
                numbers[i]="";
                znak[i]="";
                i--;
                str="";
                for (int j = 0; j < znak.length; j++) {
                    str=str+numbers[j]+znak[j];
                }
                str=str+numbers[numbers.length-1];

//                System.out.println("str"+str);

                numbers = str.split("[-*/+]");
                str2=str.replaceAll("[.0123456789]","");
                znak = str2.split("");
//                System.out.println(Arrays.toString(numbers));
//                System.out.println(Arrays.toString(znak));
            }
        }
        if (znokMinus==true) {                      //если 1е число было отрицательным
            znacenie=Double.parseDouble("-"+numbers[0]);
        } else {
            znacenie=Double.parseDouble(numbers[0]);
        }

        if (numbers.length==1) {
            System.out.println(znacenie);
        } else {
            for (int i = 0; i < znak.length; i++) {                 // потом все складываем
                if (znak[i].equals("-")) {
                    znacenie = znacenie - Double.parseDouble(numbers[i + 1]);
                } else {
                    znacenie = znacenie + Double.parseDouble(numbers[i + 1]);
                }
            }
        }
        value=String.valueOf(znacenie);
    }
}