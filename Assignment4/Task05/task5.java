import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class task5 extends JFrame implements ActionListener {
    private final JTextField amount = new JTextField(10);
    private final JComboBox<String> currency = new JComboBox<>(new String[]{"USD","EUR"});
    private final JLabel output = new JLabel("Enter an INR amount.");
    private static double rate(String code) { return code.equals("USD") ? 0.012 : 0.011; }

    public task5(int style) {
        super("INR Currency Converter");
        setLayout(new FlowLayout());
        JButton convert = new JButton("Convert");
        add(new JLabel("Amount (INR)")); add(amount); add(currency); add(convert); add(output);
        if (style == 1) {
            convert.addActionListener(this); amount.addActionListener(this);
        } else if (style == 2) {
            ActionListener listener = new NamedHandler();
            convert.addActionListener(listener); amount.addActionListener(listener);
        } else if (style == 3) {
            ActionListener listener = new ActionListener() {
                public void actionPerformed(ActionEvent e) { calculate(); }
            };
            convert.addActionListener(listener); amount.addActionListener(listener);
        } else if (style == 4) {
            ActionListener listener = e -> calculate();
            convert.addActionListener(listener); amount.addActionListener(listener);
        } else throw new IllegalArgumentException("Listener style must be 1, 2, 3, or 4.");
        addWindowListener(new WindowAdapter() { public void windowClosing(WindowEvent e) { dispose(); } });
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); pack(); setLocationRelativeTo(null);
    }
    @Override public void actionPerformed(ActionEvent e) { calculate(); }
    private class NamedHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) { calculate(); }
    }
    private void calculate() {
        try {
            double inr = Double.parseDouble(amount.getText().trim());
            if (!Double.isFinite(inr) || inr < 0) throw new NumberFormatException();
            String code = (String) currency.getSelectedItem();
            String answer = String.format("%.2f %s", inr * rate(code), code);
            output.setText(answer); JOptionPane.showMessageDialog(this, answer);
        } catch (NumberFormatException ex) {
            output.setText("Invalid amount");
            JOptionPane.showMessageDialog(this,"Enter a valid non-negative amount.","Input error",JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void main(String[] args) {
        int style = args.length == 0 ? 4 : Integer.parseInt(args[0]);
        SwingUtilities.invokeLater(() -> new task5(style).setVisible(true));
    }
}
