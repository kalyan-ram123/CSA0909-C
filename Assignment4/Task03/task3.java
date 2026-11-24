import java.awt.*;
import javax.swing.*;

public class task3 extends JFrame {
    private final JTextField query = new JTextField(22);
    private final JComboBox<String> category = new JComboBox<>(new String[]{"All","Science","Technology","Literature"});
    private final DefaultListModel<String> model = new DefaultListModel<>();
    private final JList<String> results = new JList<>(model);
    private final JLabel status = new JLabel("Ready to search.");

    public task3() {
        super("Campus Library Search");
        JPanel search = new JPanel(new FlowLayout(FlowLayout.LEFT));
        search.add(new JLabel("Keyword")); search.add(query); search.add(category);
        JButton find = new JButton("Search"); search.add(find);
        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.setBorder(BorderFactory.createTitledBorder("Search results"));
        resultPanel.add(new JScrollPane(results),BorderLayout.CENTER);
        JButton previous = new JButton("Previous"), next = new JButton("Next"), reserve = new JButton("Reserve");
        JPanel controls = new JPanel(); controls.add(previous); controls.add(next); controls.add(reserve);
        JPanel footer = new JPanel(new BorderLayout()); footer.add(controls,BorderLayout.CENTER); footer.add(status,BorderLayout.SOUTH);
        add(search,BorderLayout.NORTH); add(resultPanel,BorderLayout.CENTER); add(footer,BorderLayout.SOUTH);
        find.addActionListener(e -> {
            String term = query.getText().trim(); model.clear();
            if (term.isEmpty()) { status.setText("Enter a keyword."); return; }
            model.addElement(term + " – sample book A");
            model.addElement(term + " – sample book B");
            status.setText("Showing sample results in " + category.getSelectedItem() + ".");
        });
        reserve.addActionListener(e -> status.setText(results.getSelectedValue()==null ? "Select a book first." : "Reservation request recorded (demo)."));
        previous.addActionListener(e -> status.setText("Previous page selected."));
        next.addActionListener(e -> status.setText("Next page selected."));
        setDefaultCloseOperation(EXIT_ON_CLOSE); setSize(700,440); setLocationRelativeTo(null);
    }
    public static void main(String[] args) { SwingUtilities.invokeLater(() -> new task3().setVisible(true)); }
}
