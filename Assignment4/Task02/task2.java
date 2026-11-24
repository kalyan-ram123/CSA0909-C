import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class task2 extends JFrame {
    private final JTextField patient = new JTextField(12);
    private final JComboBox<String> department = new JComboBox<>(new String[]{"Cardiology","Neurology","Orthopaedics"});
    private final JComboBox<String> doctor = new JComboBox<>();
    private final JComboBox<String> time = new JComboBox<>(new String[]{"09:00","10:00","11:00","14:00","15:00"});
    private final JLabel status = new JLabel("Select a department and enter patient ID.");
    private final Map<String, java.util.List<String>> directory = new LinkedHashMap<>();

    public task2() {
        super("Hospital Appointment Desk");
        directory.put("Cardiology", Arrays.asList("Dr. Anil","Dr. Kavya"));
        directory.put("Neurology", Arrays.asList("Dr. Ravi","Dr. Sana"));
        directory.put("Orthopaedics", Arrays.asList("Dr. Hari","Dr. Neha"));
        JPanel form = new JPanel(new GridLayout(4,2,8,8));
        form.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));
        form.add(new JLabel("Patient ID")); form.add(patient);
        form.add(new JLabel("Department")); form.add(department);
        form.add(new JLabel("Doctor")); form.add(doctor);
        form.add(new JLabel("Time slot")); form.add(time);
        JButton book = new JButton("Book appointment");
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.LEFT));
        footer.add(book); footer.add(status);
        add(form,BorderLayout.CENTER); add(footer,BorderLayout.SOUTH);
        department.addActionListener(e -> updateDoctors());
        book.addActionListener(e -> book());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(540,260); setLocationRelativeTo(null);
        updateDoctors();
    }
    private void updateDoctors() {
        doctor.removeAllItems();
        java.util.List<String> names = directory.get(department.getSelectedItem());
        if (names != null) for (String name : names) doctor.addItem(name);
    }
    private void book() {
        if (patient.getText().trim().isEmpty()) { status.setText("Patient ID is required."); return; }
        String confirmation = "Appointment request: " + patient.getText().trim() + ", "
                + doctor.getSelectedItem() + ", " + time.getSelectedItem();
        status.setText("Appointment booked (demo).");
        JOptionPane.showMessageDialog(this, confirmation);
    }
    public static void main(String[] args) { SwingUtilities.invokeLater(() -> new task2().setVisible(true)); }
}
