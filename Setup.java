import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class Setup extends JFrame{

    JTextField mainTimeField, overtimeCountField, overtimeDurationField;

    public Setup() {
        super("Timer Settings");

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        JPanel mainTimePanel = new JPanel();
        JLabel mainTimeLabel = new JLabel("Main Time (min:sec):");
        mainTimeField = new JTextField(10);
        mainTimePanel.add(mainTimeLabel);
        mainTimePanel.add(mainTimeField);
        getContentPane().add(mainTimePanel);

        JPanel overtimeCountPanel = new JPanel();
        JLabel overtimeCountLabel = new JLabel("Byo-Yomi Number:");
        overtimeCountField = new JTextField(10);
        overtimeCountPanel.add(overtimeCountLabel);
        overtimeCountPanel.add(overtimeCountField);
        getContentPane().add(overtimeCountPanel);

        JPanel overtimeDurationPanel = new JPanel();
        JLabel overtimeDurationLabel = new JLabel("Byo-Yomi Time (sec):");
        overtimeDurationField = new JTextField(10);
        overtimeDurationPanel.add(overtimeDurationLabel);
        overtimeDurationPanel.add(overtimeDurationField);
        getContentPane().add(overtimeDurationPanel);

        JButton submit = new JButton("Submit");
        submit.addActionListener((a) -> submit());
        getContentPane().add(submit);

        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void submit() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        Main.setup(mainTimeField.getText(), Integer.parseInt(overtimeCountField.getText()), Integer.parseInt(overtimeDurationField.getText()));
        setVisible(false);
        dispose();
    }
}