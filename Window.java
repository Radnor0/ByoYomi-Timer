import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Window extends JFrame {

    private JPanel white, black;
    private JLabel whiteTime, whiteOvertime, blackTime, blackOvertime;
    private JButton switchTurn;

    public boolean started = false;

    public Window(String startTime, String overtimes) {
        super("Byo-Yomi Timer");

        white = new JPanel(new BorderLayout());
        white.setBackground(Color.WHITE);
        whiteTime = new JLabel(startTime);
        white.add(whiteTime, BorderLayout.CENTER);
        whiteOvertime = new JLabel("x" + overtimes);
        white.add(whiteOvertime, BorderLayout.EAST);

        black = new JPanel(new BorderLayout());
        black.setBackground(Color.GRAY);
        blackTime = new JLabel(startTime);
        black.add(blackTime, BorderLayout.CENTER);
        blackOvertime = new JLabel("x" + overtimes);
        black.add(blackOvertime, BorderLayout.EAST);

        switchTurn = new JButton("Start");
        switchTurn.addActionListener((a) -> switchTurn());

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        container.add(white);
        container.add(black);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(container, BorderLayout.CENTER);
        getContentPane().add(switchTurn, BorderLayout.SOUTH);

        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public void update(int timeWhite, int timeBlack, int overtimeWhite, int overtimeBlack) {
        whiteTime.setText(String.format("%02d", timeWhite / 60) + ":" + String.format("%02d", timeWhite % 60));
        blackTime.setText(String.format("%02d", timeBlack / 60) + ":" + String.format("%02d", timeBlack % 60));

        whiteOvertime.setText("x" + overtimeWhite);
        blackOvertime.setText("x" + overtimeBlack);

        //System.out.println("Update");
    }

    private void switchTurn() {
        if (!started) {
            started = true;
            switchTurn.setText("End Turn");
        }

        Main.switchTurn();
    }

    public void endGame(boolean blackWin) {
        JOptionPane.showMessageDialog(this, (blackWin ? "Black" : "White") + " has won");
    }
}