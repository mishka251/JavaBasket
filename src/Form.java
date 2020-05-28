import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

class Form extends JFrame {
    static Panel1 panel1;
    static Panel2 panel2;
    static Panel3 panel3;
    static Panel4 panel4;

    Form() {
        setLayout(null);
        Date date = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd.MM.yyyy");
        setTitle("Редактор Анкет, Егоров С.А. Вариант 8  " + sd.format(date));
        setSize(450, 575);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel1 = new Panel1();
        panel1.setVisible(true);
        add(panel1, BorderLayout.SOUTH);
        panel2 = new Panel2();
        panel2.setVisible(true);
        add(panel2);
        panel3 = new Panel3();
        panel3.setVisible(true);
        add(panel3);
        panel4 = new Panel4();
        panel4.setVisible(true);
        add(panel4);
        setVisible(true);
    }


}

class Panel1 extends JPanel {
    Panel1() {
        setLayout(null);
        setBounds(10, 10, 415, 50);
        setBackground(Color.yellow);
        JLabel label1 = new JLabel("Магазин аксессуаров DNS");
        label1.setFont(new Font("Dialog", Font.PLAIN, 16));
        label1.setBounds(85, 10, 250, 20);
        add(label1, BorderLayout.NORTH);
    }
}

class Panel2 extends JPanel {
    Panel2() {

        setLayout(null);
        setBounds(10, 65, 415, 180);
        setBackground(Color.orange);
        JLabel label1 = new JLabel("Страна-производитель:");
        JLabel label2 = new JLabel("Фирма-производитель:");
        JLabel label3 = new JLabel("Модель:");
        JLabel label4 = new JLabel("Число ядер:");
        JLabel label5 = new JLabel("Размер диагонали:");
        JLabel label6 = new JLabel("Операционная система:");
        JLabel label7 = new JLabel("Емкость аккумулятора:");
        JLabel label8 = new JLabel("Время автономной работы:");
        JTextField area1 = new JTextField();
        JTextField area2 = new JTextField();
        JTextField area3 = new JTextField();
        JTextField area4 = new JTextField();
        JTextField area5 = new JTextField();
        JTextField area6 = new JTextField();
        JTextField area7 = new JTextField();
        JTextField area8 = new JTextField();
        area1.setBounds(200, 5, 100, 20);
        area2.setBounds(200, 25, 100, 20);
        area3.setBounds(200, 45, 100, 20);
        area4.setBounds(200, 65, 100, 20);
        area5.setBounds(200, 85, 100, 20);
        area6.setBounds(200, 105, 100, 20);
        area7.setBounds(200, 125, 100, 20);
        area8.setBounds(200, 145, 100, 20);
        label1.setBounds(10, 5, 150, 20);
        label2.setBounds(10, 25, 150, 20);
        label3.setBounds(10, 45, 100, 20);
        label4.setBounds(10, 65, 125, 20);
        label5.setBounds(10, 85, 150, 20);
        label6.setBounds(10, 105, 158, 20);
        label7.setBounds(10, 125, 158, 20);
        label8.setBounds(10, 145, 158, 20);

        add(area1);
        add(area2);
        add(area3);
        add(area4);
        add(area5);
        add(area6);
        add(area7);
        add(area8);

        add(label1);
        add(label2);
        add(label3);
        add(label4);
        add(label5);
        add(label6);
        add(label7);
        add(label8);
    }
}


class Panel3 extends JPanel {
    JButton[] jb;
    JButton btnSave;
    JButton btnShow;
    JButton btnClear;
    JButton btnPay;
    JButton btnClose;
    JButton btnColor;

    Panel3() {
        setBounds(10, 250, 415, 185);
        setBackground(Color.orange);
        setLayout(null);
        jb = new JButton[6];
        jb[0] = new JButton("Сохранить заказ");
        jb[1] = new JButton("Просмотреть окно корзины");
        jb[2] = new JButton("Очистить поля");
        jb[3] = new JButton("Выход с оплатой");
        jb[4] = new JButton("Выход без сохранения");
        jb[5] = new JButton("Инвентировать цвет заливки полей");


        jb[1].setFont(new Font("Dialog", Font.PLAIN, 11));
        jb[2].setFont(new Font("Dialog", Font.PLAIN, 11));
        jb[3].setFont(new Font("Dialog", Font.PLAIN, 11));
        jb[0].setFont(new Font("Dialog", Font.PLAIN, 11));
        jb[4].setFont(new Font("Dialog", Font.PLAIN, 11));
        jb[5].setFont(new Font("Dialog", Font.PLAIN, 11));
        jb[0].setBounds(10, 10, 180, 50);
        jb[1].setBounds(10, 65, 180, 50);
        jb[2].setBounds(10, 120, 180, 50);
        jb[3].setBounds(210, 10, 180, 50);
        jb[4].setBounds(210, 65, 180, 50);
        jb[5].setBounds(210, 120, 180, 50);
        add(jb[0]);
        add(jb[1]);
        add(jb[2]);
        add(jb[3]);
        add(jb[4]);
        add(jb[5]);
    }
}

class Panel4 extends JPanel {
    Panel4() {
        setLayout(null);
        setBounds(10, 440, 415, 90);
        setBackground(Color.yellow);
        JLabel label1 = new JLabel("Корзина заполнена?");
        JLabel label2 = new JLabel("Стоимость заказа:");
        JLabel label3 = new JLabel("Номер заказа:");

        JLabel label5 = new JLabel("Нет");
        JLabel label6 = new JLabel("0");
        JLabel label7 = new JLabel("0");

        label1.setBounds(10, 5, 130, 20);
        label2.setBounds(10, 25, 120, 20);
        label3.setBounds(10, 45, 120, 20);

        label5.setBounds(140, 5, 103, 20);
        label6.setBounds(140, 25, 74, 20);
        label7.setBounds(140, 45, 77, 20);

        add(label1);
        add(label2);
        add(label3);

        add(label5);
        add(label6);
        add(label7);

    }
}