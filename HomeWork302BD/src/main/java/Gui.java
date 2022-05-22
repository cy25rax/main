import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui {
    private String msg;
    private boolean send=false;
    private boolean isLogged=false;
    JTextArea textArea;

    public boolean getSend(){
        return send;
    }
    public boolean setSend(boolean e) {
        return send = e;
    }
    public boolean isLoggedIn(boolean e) {
        return isLogged=e;
    }
    public String getText(){
        return msg;
    }
    public void setText(String e) {textArea.append(e+"\n");}

    public Gui(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel panel = new JPanel();
        JTextField tf = new JTextField("login1",10);
        JTextField tf2 = new JTextField("pass1",10);
        panel.add(tf);
        panel.add(tf2);
        JButton button = new JButton("send");
        panel.add(button);
        frame.add(BorderLayout.CENTER,panel);
        frame.setLocation(200,200);

        frame.setSize(300, 100);
        frame.setVisible(true);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    msg = "/auth "+tf.getText()+" "+tf2.getText();
                    tf.setText("");
                    tf2.setText("");
                send = true;
                try {
                    Thread.sleep(300);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                if (isLogged){
                    msg="";
                    send=false;
                    panel.setVisible(false);
                    frame.setSize(500, 500);
                    JPanel panel1 = new JPanel();
                    textArea = new JTextArea(20,30);
                    JTextArea textArea2 = new JTextArea(20,20);
                    panel1.add(textArea);
                    panel1.add(textArea2);
                    JTextField textField = new JTextField(30);
                    panel1.add(textField);
                    JButton button1 = new JButton("send");
                    panel1.add(button1);
                    button1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (textField.getText().length()>1){
                                msg = textField.getText();
                                send=true;
                                textField.setText("");
                                textField.requestFocus();
                            }
                        }
                    });
                    frame.add(BorderLayout.CENTER,panel1);
                }
            }
        });

    }

}
