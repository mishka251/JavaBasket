import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.swing.*;

class Form extends JFrame {
    static Panel1 panel1;
    static Panel2 panel2;
    static Panel3 panel3;
    static Panel4 panel4;

    JFileChooser fileChooser;

    Date saveTime = null;

    BucketData data;

    int orderNumber = 1;

    Form(BucketData data, Date saveTime) {
        initComponents();
        addHandlers();
        this.data = data;
        this.saveTime = saveTime;
        showData();
        panel4.lblIsFilled.setText("Да");
    }

    Form() {
        initComponents();
        addHandlers();
        panel4.lblIsFilled.setText("Нет");
        panel2.manufacturerBrand.setEditable(false);
    }

    void initComponents() {
        setLayout(null);
        Date date = new Date();
        data = new BucketData();
        SimpleDateFormat sd = new SimpleDateFormat("dd.MM.yyyy");
        setTitle("Редактор Анкет, Егоров С.А. Вариант 8  " + sd.format(date));
        setSize(450, 635);
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

        panel3.btnStatistic.addActionListener(this::showStatistic);
        panel3.btnColor2.addActionListener(this::setColor);
    }

    void onChangeCountry(KeyEvent event) {
        data.setManufacturerCountry(panel2.manufacturerCountry.getText());
        panel2.manufacturerBrand.setEditable(!panel2.manufacturerCountry.getText().equals(""));
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
            panel4.lblIsFilled.setText("Да");
            Random random = new Random();
            orderNumber++;
            panel4.lblNumber.setText(Integer.toString(orderNumber));
            int cost = 20_000 + random.nextInt(10_000);
            panel4.lblCost.setText(Integer.toString(cost));
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
                Form newForm = new Form(loaded, new Date(selected.lastModified()));
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
        panel4.lblIsFilled.setText("Нет");
    }

    void close(ActionEvent event) {
        String surname = "Egorov";
        int first = surname.getBytes()[0];
        int last = surname.getBytes()[surname.length() - 1];
        int result = first * last + 33;
        JOptionPane.showMessageDialog(this, Integer.toString(result), "", JOptionPane.WARNING_MESSAGE);
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

    int filledFieldsCount() {
        int cnt = 0;
        for (int i = 0; i < panel2.textFields.length; i++) {
            if (!panel2.textFields[i].getText().equals("")) {
                cnt++;
            }
        }
        return cnt;
    }

    boolean isInteger(String s) {
        try {
            int i = Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    boolean isDouble(String s) {
        try {
            double i = Double.parseDouble(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    int errorsFieldCount() {
        int count = 0;
        if (!isInteger(panel2.cpuCores.getText())) {
            count++;
        }
        if (!isDouble(panel2.screenSize.getText())) {
            count++;
        }
        if (!isDouble(panel2.accumulator.getText())) {
            count++;
        }
        if (!isDouble(panel2.autonomousWorkTime.getText())) {
            count++;
        }
        return count;
    }

    void showStatistic(ActionEvent event) {
        int filledFields = filledFieldsCount();
        int errors = errorsFieldCount();

        StatisticData sd = new StatisticData(filledFields, errors, saveTime);
        new Form1(sd);
    }

    void setColor(ActionEvent event) {
        Color color = new Color(160);
        for (int i = 0; i < panel3.jb.length; i++) {
            panel3.jb[i].setBackground(color);
        }
        JPanel[] panels = new JPanel[]{
                panel1, panel2, panel3, panel4
        };
        for (int i = 0; i < panels.length; i++) {
            Rectangle bounds = panels[i].getBounds();
            bounds.y += i * 40;
            panels[i].setBounds(bounds);
        }
        JOptionPane.showMessageDialog(this, "Перекрасили, подвинули", "", JOptionPane.QUESTION_MESSAGE);

    }
}

class Panel1 extends JPanel {
    Panel1() {
        setLayout(null);
        setBounds(10, 10, 415, 50);
        setBackground(Color.yellow);
        JLabel label1 = new OvalBackgroundLabel("Магазин аксессуаров DNS");
        label1.setFont(new Font("Dialog", Font.PLAIN, 16));
        label1.setBounds(100, 10, 200, 20);
        label1.setForeground(Color.white);
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

    JTextField[] textFields;

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

        manufacturerCountry.setBounds(310, 5, 100, 20);
        manufacturerBrand.setBounds(310, 25, 100, 20);
        model.setBounds(310, 45, 100, 20);
        cpuCores.setBounds(310, 65, 100, 20);
        screenSize.setBounds(310, 85, 100, 20);
        os.setBounds(310, 105, 100, 20);
        accumulator.setBounds(310, 125, 100, 20);
        autonomousWorkTime.setBounds(310, 145, 100, 20);

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

        textFields = new JTextField[]{
                manufacturerCountry,
                manufacturerBrand,
                model,
                cpuCores,
                screenSize,
                os,
                accumulator,
                autonomousWorkTime,
        };
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
    JButton btnStatistic;
    JButton btnColor2;


    Panel3() {
        setBounds(10, 250, 415, 245);
        setBackground(Color.orange);
        setLayout(null);
        jb = new JButton[8];
        jb[0] = new JButton("Сохранить заказ");
        jb[1] = new JButton("Просмотреть окно корзины");
        jb[2] = new JButton("Очистить поля");
        jb[3] = new JButton("Выход с оплатой");
        jb[4] = new JButton("Выход без сохранения");
        jb[5] = new JButton("Инвентировать цвет заливки полей");
        jb[6] = new JButton("Статистика");
        jb[7] = new JButton("Color");

        btnSave = jb[0];
        btnShow = jb[1];
        btnClear = jb[2];
        btnPay = jb[3];
        btnClose = jb[4];
        btnColor = jb[5];
        btnStatistic = jb[6];
        btnColor2 = jb[7];


        jb[0].setBounds(10, 10, 180, 50);
        jb[1].setBounds(10, 65, 180, 50);
        jb[2].setBounds(10, 120, 180, 50);
        jb[3].setBounds(210, 10, 180, 50);
        jb[4].setBounds(210, 65, 180, 50);
        jb[5].setBounds(210, 120, 180, 50);

        jb[6].setBounds(10, 175, 180, 50);
        jb[7].setBounds(210, 175, 180, 50);

        for (int i = 0; i < jb.length; i++) {
            jb[i].setFont(new Font("Dialog", Font.PLAIN, 11));
            add(jb[i]);
        }
    }
}

class Panel4 extends JPanel {
    JLabel lblIsFilled;
    JLabel lblCost;
    JLabel lblNumber;

    Panel4() {
        setLayout(null);
        setBounds(10, 500, 415, 90);
        setBackground(Color.yellow);
        JLabel label1 = new JLabel("Корзина заполнена?");
        JLabel label2 = new JLabel("Стоимость заказа:");
        JLabel label3 = new JLabel("Номер заказа:");

        lblIsFilled = new JLabel("Нет");
        lblNumber = new JLabel("1");
        lblCost = new JLabel("0");

        label1.setBounds(10, 5, 130, 20);
        label2.setBounds(10, 25, 120, 20);
        label3.setBounds(10, 45, 120, 20);

        lblIsFilled.setBounds(140, 5, 103, 20);
        lblCost.setBounds(140, 25, 77, 20);
        lblNumber.setBounds(140, 45, 74, 20);

        add(label1);
        add(label2);
        add(label3);

        add(lblIsFilled);
        add(lblNumber);
        add(lblCost);

    }
}


class OvalBackgroundLabel extends JLabel {

    public OvalBackgroundLabel(String text) {
        super(text);
    }

    public void paintComponent(Graphics g) {
        g.setColor(new Color(120, 60, 0));
        g.drawOval(0, 0, getWidth(), getHeight());
        g.fillOval(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}