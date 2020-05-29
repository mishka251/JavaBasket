import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

class Form extends JFrame {
    static Panel1 panel1;
    static Panel2 panel2;
    static Panel3 panel3;
    static Panel4 panel4;

    JFileChooser fileChooser;

    Date saveTime = null;

    BucketData data;

    Form(BucketData data) {
        initComponents();
        addHandlers();
        this.data = data;
        showData();
    }

    Form() {
        initComponents();
        addHandlers();
    }

    void initComponents() {
        setLayout(null);
        Date date = new Date();
        data = new BucketData();
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

        fileChooser = new JFileChooser();

        setVisible(true);
    }

    void addHandlers() {
        panel2.accumulator.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                onChangeAccumulator(e);
            }
        });
        panel2.screenSize.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                onChangeScreenSize(e);
            }
        });
        panel2.os.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                onChangeOs(e);
            }
        });
        panel2.autonomousWorkTime.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                onChangeAutonomous(e);
            }
        });
        panel2.model.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                onChangeModel(e);
            }
        });
        panel2.manufacturerCountry.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                onChangeCountry(e);
            }
        });
        panel2.manufacturerBrand.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                onChangeBrand(e);
            }
        });
        panel2.cpuCores.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                onChangeCpu(e);
            }
        });


        panel3.btnSave.addActionListener(this::save);
        panel3.btnShow.addActionListener(this::load);
        panel3.btnClear.addActionListener(this::clear);
        panel3.btnClose.addActionListener(this::close);
        panel3.btnColor.addActionListener(this::invertColors);
        panel3.btnPay.addActionListener(this::pay);
    }

    void onChangeCountry(KeyEvent event) {
        data.setManufacturerCountry(panel2.manufacturerCountry.getText());
    }

    void onChangeBrand(KeyEvent event) {
        data.setManufacturerBrand(panel2.manufacturerBrand.getText());
    }

    void onChangeModel(KeyEvent event) {
        data.setModel(panel2.model.getText());
    }

    void onChangeCpu(KeyEvent event) {
        try {
            int cpu = Integer.parseInt(panel2.cpuCores.getText());
            data.setCpuCores(cpu);
        } catch (Exception ex) {
            //TODO
        }
    }

    void onChangeOs(KeyEvent event) {
        data.setOs(panel2.os.getText());
    }

    void onChangeScreenSize(KeyEvent event) {
        try {
            double screen = Double.parseDouble(panel2.screenSize.getText());
            data.setScreenSize(screen);
        } catch (Exception ex) {
            //TODO
        }
    }

    void onChangeAccumulator(KeyEvent event) {
        try {
            double accumulator = Double.parseDouble(panel2.accumulator.getText());
            data.setAccumulator(accumulator);
        } catch (Exception ex) {
            //TODO
        }
    }

    void onChangeAutonomous(KeyEvent event) {
        try {
            double time = Double.parseDouble(panel2.autonomousWorkTime.getText());
            data.setAutonomousWorkTime(time);
        } catch (Exception ex) {
            //TODO
        }
    }


    void save(ActionEvent e) {
        int dialogResult = fileChooser.showSaveDialog(this);
        if (dialogResult != JFileChooser.APPROVE_OPTION) {
            return;
        }
        File selected = fileChooser.getSelectedFile();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(selected))) {
            oos.writeObject(data);
            this.saveTime = new Date();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    void showData() {
        panel2.cpuCores.setText(Integer.toString(data.getCpuCores()));
        panel2.accumulator.setText(Double.toString(data.getAccumulator()));
        panel2.screenSize.setText(Double.toString(data.getScreenSize()));
        panel2.os.setText(data.getOs());

        panel2.autonomousWorkTime.setText(Double.toString(data.getAutonomousWorkTime()));
        panel2.model.setText(data.getModel());
        panel2.manufacturerCountry.setText(data.getManufacturerCountry());
        panel2.manufacturerBrand.setText(data.getManufacturerBrand());
    }

    void load(ActionEvent event) {
        int dialogResult = fileChooser.showOpenDialog(this);
        if (dialogResult != JFileChooser.APPROVE_OPTION) {
            return;
        }
        File selected = fileChooser.getSelectedFile();
        if (selected.canRead()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(selected))) {
                BucketData loaded = ((BucketData) ois.readObject());
                Form newForm = new Form(loaded);
            } catch (Exception ex) {

                System.out.println(ex.getMessage());
            }
            //showData();

        } else {
            //TODO
        }
    }

    void clear(ActionEvent event) {
        data = new BucketData();
        showData();
    }

    void close(ActionEvent event) {
        System.exit(0);
    }

    Color invertColor(Color color) {
        return new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());
    }

    void invertColors(ActionEvent event) {
        panel1.setBackground(invertColor(panel1.getBackground()));
        panel2.setBackground(invertColor(panel2.getBackground()));
        panel3.setBackground(invertColor(panel3.getBackground()));
        panel4.setBackground(invertColor(panel4.getBackground()));
    }

    void pay(ActionEvent event) {
        PayForm payForm = new PayForm();
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
    JTextField manufacturerCountry;
    JTextField manufacturerBrand;
    JTextField model;
    JTextField cpuCores;
    JTextField screenSize;
    JTextField os;
    JTextField accumulator;
    JTextField autonomousWorkTime;

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
        manufacturerCountry = new JTextField();
        manufacturerBrand = new JTextField();
        model = new JTextField();
        cpuCores = new JTextField();
        screenSize = new JTextField();
        os = new JTextField();
        accumulator = new JTextField();
        autonomousWorkTime = new JTextField();
        manufacturerCountry.setBounds(200, 5, 100, 20);
        manufacturerBrand.setBounds(200, 25, 100, 20);
        model.setBounds(200, 45, 100, 20);
        cpuCores.setBounds(200, 65, 100, 20);
        screenSize.setBounds(200, 85, 100, 20);
        os.setBounds(200, 105, 100, 20);
        accumulator.setBounds(200, 125, 100, 20);
        autonomousWorkTime.setBounds(200, 145, 100, 20);
        label1.setBounds(10, 5, 150, 20);
        label2.setBounds(10, 25, 150, 20);
        label3.setBounds(10, 45, 100, 20);
        label4.setBounds(10, 65, 125, 20);
        label5.setBounds(10, 85, 150, 20);
        label6.setBounds(10, 105, 158, 20);
        label7.setBounds(10, 125, 158, 20);
        label8.setBounds(10, 145, 158, 20);

        add(manufacturerCountry);
        add(manufacturerBrand);
        add(model);
        add(cpuCores);
        add(screenSize);
        add(os);
        add(accumulator);
        add(autonomousWorkTime);

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

        btnSave = jb[0];
        btnShow = jb[1];
        btnClear = jb[2];
        btnPay = jb[3];
        btnClose = jb[4];
        btnColor = jb[5];


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