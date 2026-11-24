import java.awt.*;
import java.awt.event.*;

public class task1 extends Frame {
    private final TextField reg = new TextField(16);
    private final TextField fee = new TextField(16);
    private final Label message = new Label("Enter student fee details.");

    private String processPayment(String id, String text) {
        if (id.isEmpty()) return "Register number is required.";
        try {
            double amount = Double.parseDouble(text);
            if (!Double.isFinite(amount) || amount <= 0) return "Enter a positive fee amount.";
            return String.format("Demo payment of %.2f recorded for %s.", amount, id);
        } catch (NumberFormatException ex) {
            return "Enter a valid numeric amount.";
        }
    }

    public task1() {
        super("Student Fee Payment");
        setLayout(new BorderLayout(8,8));
        Panel fields = new Panel(new GridLayout(2,2,6,6));
        fields.add(new Label("Register number")); fields.add(reg);
        fields.add(new Label("Fee amount")); fields.add(fee);
        Button pay = new Button("Pay");
        Button clear = new Button("Clear");
        Button receipt = new Button("Receipt");
        Panel buttons = new Panel();
        buttons.add(pay); buttons.add(clear); buttons.add(receipt);
        add(buttons, BorderLayout.NORTH);
        add(fields, BorderLayout.CENTER);
        add(message, BorderLayout.SOUTH);
        pay.addActionListener(e -> message.setText(processPayment(reg.getText().trim(), fee.getText().trim())));
        clear.addActionListener(e -> { reg.setText(""); fee.setText(""); message.setText("Form cleared."); });
        receipt.addActionListener(e -> message.setText("A receipt is available after a successful payment."));
        addWindowListener(new WindowAdapter() { public void windowClosing(WindowEvent e) { dispose(); } });
        setSize(430,180); setLocationRelativeTo(null);
    }
    public static void main(String[] args) { EventQueue.invokeLater(() -> new task1().setVisible(true)); }
}
