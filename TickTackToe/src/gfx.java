import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gfx {
    Grid grid = new Grid();
    JFrame f = new JFrame();
    int a = 300, b = 20;

    JButton b1 = cell(50 + a, 50+60, grid);
    JButton b2 = cell(300 + a + b, 50+60, grid);
    JButton b3 = cell(550 + a + (2 * b), 50+60, grid);
    JButton b4 = cell(50 + a, 300 + b+60, grid);
    JButton b5 = cell(300 + a + b, 300 + b+60, grid);
    JButton b6 = cell(550 + a + (2 * b), 300 + b+60, grid);
    JButton b7 = cell(50 + a, 550 + b + b+60, grid);
    JButton b8 = cell(300 + a + b, 550 + b + b+60, grid);
    JButton b9 = cell(550 + a + (2 * b), 550 + b + b +60, grid);

    JButton reset = resetButton(1300, 400);


    void gameWindow() {



        ImageIcon icon = new ImageIcon("tack.png");

        f.setIconImage(icon.getImage());
        f.setTitle("ZeroKata");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setSize(1920, 1080);

        f.getContentPane().setBackground(new Color(0x234188));
        f.setLayout(null);


        f.add(b1);
        f.add(b2);
        f.add(b3);
        f.add(b4);
        f.add(b5);
        f.add(b6);
        f.add(b7);
        f.add(b8);
        f.add(b9);

        f.add(reset);
        f.setVisible(true);


    }

    void CheckWin() throws InterruptedException {

        char p1 = ' ';
        char p2 = ' ';
        char p3 = ' ';
        char p4 = ' ';
        char p5 = ' ';
        char p6 = ' ';
        char p7 = ' ';
        char p8 = ' ';
        char p9 = ' ';

        String g1 = this.b1.getText();
        if (!g1.isBlank()) {
            p1 = g1.charAt(0);
        }
        String g2 = this.b2.getText();
        if (!g2.isBlank()) {
            p2 = g2.charAt(0);
        }
        String g3 = this.b3.getText();
        if (!g3.isBlank()) {
            p3 = g3.charAt(0);
        }
        String g4 = this.b4.getText();
        if (!g4.isBlank()) {
            p4 = g4.charAt(0);
        }
        String g5 = this.b5.getText();
        if (!g5.isBlank()) {
            p5 = g5.charAt(0);
        }
        String g6 = this.b6.getText();
        if (!g6.isBlank()) {
            p6 = g6.charAt(0);
        }
        String g7 = this.b7.getText();
        if (!g7.isBlank()) {
            p7 = g7.charAt(0);
        }
        String g8 = this.b8.getText();
        if (!g8.isBlank()) {
            p8 = g8.charAt(0);
        }
        String g9 = this.b9.getText();
        if (!g9.isBlank()) {
            p9 = g9.charAt(0);
        }

        char winner = ' ';
        if (p1 == p2 && p2 == p3 && p1 != ' ') winner = p1;
        if (p4 == p5 && p5 == p6 && p4 != ' ') winner = p4;
        if (p7 == p8 && p8 == p9 && p7 != ' ') winner = p7;
        if (p1 == p4 && p4 == p7 && p1 != ' ') winner = p1;
        if (p2 == p5 && p5 == p8 && p2 != ' ') winner = p2;
        if (p3 == p6 && p6 == p9 && p3 != ' ') winner = p3;
        if (p1 == p5 && p5 == p9 && p1 != ' ') winner = p1;
        if (p3 == p5 && p5 == p7 && p3 != ' ') winner = p3;


        if (winner != ' ') {


            JFrame fr = new JFrame();
            fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fr.setBackground(Color.GREEN);
            fr.setSize(1920, 1080);


            JLabel label = new JLabel(" "+winner + " is the Winner");

            label.setFont(new Font("Arial", Font.PLAIN, 250));
            label.setForeground(new Color(0x000000));
            fr.add(label);
            fr.setVisible(true);
            javax.swing.Timer timer = new javax.swing.Timer(2000, new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    fr.dispose();
                    resetGrid();
                }
            });
            timer.setRepeats(false);
            timer.start();


        } else if (this.grid.turn == 10) {
            JFrame fr = new JFrame();
            fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fr.setBackground(Color.GREEN);
            fr.setSize(1920, 1080);


            JLabel label = new JLabel(" It's a Draw mate");

            label.setFont(new Font("Arial", Font.PLAIN, 250));
            label.setForeground(new Color(0x000000));
            fr.add(label);
            fr.setVisible(true);
            javax.swing.Timer timer = new javax.swing.Timer(2500, new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    fr.dispose();

                    resetGrid();
                }
            });
            timer.setRepeats(false);
            timer.start();
        }


    }


    JButton cell(int a, int b, Grid grid) {
        JButton button = new JButton();
        final boolean[] actionAllowed = {true};
        button.setBounds(a, b, 250, 250);
        button.setBackground(new Color(0xF6A529));
        button.setForeground(Color.BLACK);
        button.setVisible(true);
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (grid.turn % 2 != 0) {
                    actionAllowed[0] = false;
                    button.setBackground(new Color(0xF3CC86));
                    button.setText("X");
                    button.setFont(new Font("Arial", Font.PLAIN, 100));
                    // button.setEnabled(false);
                    for (ActionListener al : button.getActionListeners()) {
                        button.removeActionListener(al);
                    }

                    button.setForeground(Color.BLACK);
                    grid.turn++;
                    try {
                        CheckWin();
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    button.setBackground(new Color(0xF3CC86));
                    button.setText("O");
                    actionAllowed[0] = false;
                    button.setFont(new Font("Arial", Font.PLAIN, 100));
                    //  button.setEnabled(false);
                    for (ActionListener al : button.getActionListeners()) {
                        button.removeActionListener(al);
                    }


                    button.setForeground(Color.BLACK);
                    grid.turn++;
                    try {
                        CheckWin();
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        };
        button.addActionListener(actionListener);

        return button;
    }


    JButton resetButton(int a, int b) {
        JButton button = new JButton();
        button.setBounds(a, b, 350, 250);
        button.setBackground(new Color(0xF6A529));
        button.setForeground(Color.BLACK);
        button.setText("Reset");
        button.setFont(new Font("Arial", Font.PLAIN, 100));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                resetGrid();

            }
        });


        button.setVisible(true);
        return button;

    }

    void resetGrid() {
       resetButtonState(b1);
       resetButtonState(b2);
       resetButtonState(b3);
       resetButtonState(b4);
       resetButtonState(b5);
       resetButtonState(b6);
       resetButtonState(b7);
       resetButtonState(b8);
       resetButtonState(b9);

        grid.turn = 1;
    }

    void resetButtonState(JButton button) {

        for (ActionListener al : button.getActionListeners()) {
            button.removeActionListener(al);
        }


        button.setText("");
        final boolean[] actionAllowed = {true};
        button.setBackground(new Color(0xF6A529));
        button.setForeground(Color.BLACK);
        button.setVisible(true);
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (grid.turn % 2 != 0) {
                    actionAllowed[0] = false;
                    button.setBackground(new Color(0xF3CC86));
                    button.setText("X");
                    button.setFont(new Font("Arial", Font.PLAIN, 100));
                    // button.setEnabled(false);
                    for (ActionListener al : button.getActionListeners()) {
                        button.removeActionListener(al);
                    }
                    button.setForeground(Color.BLACK);
                    grid.turn++;
                    try {
                        CheckWin();
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    button.setBackground(new Color(0xF3CC86));
                    button.setText("O");
                    actionAllowed[0] = false;
                    button.setFont(new Font("Arial", Font.PLAIN, 100));
                    //  button.setEnabled(false);
                    for (ActionListener al : button.getActionListeners()) {
                        button.removeActionListener(al);
                    }

                    button.setForeground(Color.BLACK);
                    grid.turn++;
                    try {
                        CheckWin();
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        };
        button.addActionListener(actionListener);
        }
}


class Grid {
    int turn = 1;
}

