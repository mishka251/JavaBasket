import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PayForm extends JFrame {
    JTextField textField;

    PayForm() {
        setLayout(null);
        setSize(350, 120);
        JPanel panel = new JPanel();
        add(panel);
        panel.setSize(350, 120);
        panel.setLayout(null);
        textField = new JTextField();
        panel.add(textField, BorderLayout.SOUTH);
        textField.setBounds(10, 30, 200, 20);
        JLabel label = new JLabel("номер карты");
        label.setBounds(10, 10, 100, 10);
        panel.add(label, BorderLayout.SOUTH);
        panel.setVisible(true);
        setVisible(true);

        JButton ok = new JButton("заплатить");
        ok.setBounds(210, 50, 100, 20);
        panel.add(ok);
        ok.addActionListener(this::obOnClick);
    }

    boolean isValidCardNumber(String cardNumber) {
        if (cardNumber.length() != 16) {
            return false;
        }
        for (char c : cardNumber.toCharArray()) {
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    void obOnClick(ActionEvent event) {
        String cardNumber = textField.getText();
        if (!isValidCardNumber(cardNumber)) {
            JOptionPane.showMessageDialog(this, "Введите 16 цифр без пробелов");
        } else {
            JOptionPane.showMessageDialog(this, "оплачено");
            System.exit(0);
        }
    }
}
