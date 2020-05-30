import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;


class Panel5 extends JPanel {
    JLabel lblFilled;
    JLabel lblErrors;
    JLabel lblNotFilled;
    JLabel lblOrderDate;
    JLabel lblOrderTime;

    Panel5() {
        setLayout(null);
        setBounds(10, 10, 320, 110);
        setBackground(Color.yellow);
        JLabel label1 = new JLabel("Число заполненных полей:");
        JLabel label2 = new JLabel("Количество ошибок при заполнении полей:");
        JLabel label3 = new JLabel("Число не заполненных полей:");
        JLabel label4 = new JLabel("Дата заказа:");
        JLabel label9 = new JLabel("Время заказа:");
        lblFilled = new JLabel("0");
        lblErrors = new JLabel("0");
        lblNotFilled = new JLabel("0");
        lblOrderDate = new JLabel("0");
        lblOrderTime = new JLabel("0");

        label1.setBounds(10, 5, 162, 20);
        label2.setBounds(10, 25, 255, 20);
        label3.setBounds(10, 45, 200, 20);
        label4.setBounds(10, 65, 100, 20);
        label9.setBounds(10, 85, 100, 20);

        lblFilled.setBounds(270, 5, 50, 20);
        lblErrors.setBounds(270, 25, 50, 20);
        lblNotFilled.setBounds(270, 45, 50, 20);
        lblOrderDate.setBounds(270, 65, 50, 20);
        lblOrderTime.setBounds(270, 85, 50, 20);

        add(label1);
        add(label2);
        add(label3);
        add(label4);
        add(label9);

        add(lblFilled);
        add(lblErrors);
        add(lblNotFilled);
        add(lblOrderDate);
        add(lblOrderTime);
    }
}

class Form1 extends JFrame {
    Form1(StatisticData data) {
        setLayout(null);
        Date date = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd.MM.yyyy");
        setTitle("Егоров С.А. Вариант 8  " + sd.format(date));
        setSize(355, 170);
        Panel5 panel5 = new Panel5();
        panel5.setVisible(true);
        add(panel5);
        setVisible(true);

        panel5.lblErrors.setText(Integer.toString(data.errors));
        panel5.lblFilled.setText(Integer.toString(data.filledFields));
        panel5.lblNotFilled.setText(Integer.toString(8 - data.filledFields));
        if(data.orderTime!=null){
            panel5.lblOrderDate.setText(sd.format(data.orderTime));
            panel5.lblOrderTime.setText((new SimpleDateFormat("HH.mm")).format(data.orderTime));
        }else{
            panel5.lblOrderDate.setText("Не сохранено");
            panel5.lblOrderTime.setText("Не сохранено");
        }

    }
}