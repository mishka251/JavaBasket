import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;


class Panel5 extends JPanel {
    Panel5() {
        setLayout(null);
        setBounds(10, 10, 320, 110);
        setBackground(Color.yellow);
        JLabel label1 = new JLabel("Число заполненных полей:");
        JLabel label2 = new JLabel("Kоличество ошибок при заполнении полей:");
        JLabel label3 = new JLabel("Число не заполненных полей:");
        JLabel label4 = new JLabel("Дата заказа:");
        JLabel label9 = new JLabel("Время заказа:");
        JLabel label5 = new JLabel("0");
        JLabel label6 = new JLabel("0");
        JLabel label7 = new JLabel("0");
        JLabel label8 = new JLabel("0");
        JLabel label10 = new JLabel("0");
        label1.setBounds(10, 5, 162, 20);
        label2.setBounds(10, 25, 255, 20);
        label3.setBounds(10, 45, 200, 20);
        label4.setBounds(10, 65, 100, 20);
        label9.setBounds(10, 85, 100, 20);
        label5.setBounds(270, 5, 50, 20);
        label6.setBounds(270, 25, 50, 20);
        label7.setBounds(270, 45, 50, 20);
        label8.setBounds(270, 65, 50, 20);
        label10.setBounds(270, 85, 50, 20);
        add(label1);
        add(label2);
        add(label3);
        add(label4);
        add(label5);
        add(label6);
        add(label7);
        add(label8);
        add(label9);
        add(label10);
    }
}

class Form1 extends JFrame {
    Form1() {
        setLayout(null);
        Date date = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd.MM.yyyy");
        setTitle("Егоров С.А. Вариант 8  " + sd.format(date));
        setSize(355, 170);
        Panel5 panel5 = new Panel5();
        panel5.setVisible(true);
        add(panel5);
        setVisible(true);
    }
}