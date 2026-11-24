import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class task6 extends JFrame {
    private static final String URL = System.getenv().getOrDefault("DB_URL","jdbc:mysql://localhost:3306/campus_events");
    private static final String USER = System.getenv().getOrDefault("DB_USER","root");
    private static final String PASSWORD = System.getenv().getOrDefault("DB_PASSWORD","");
    private final JTextField id = new JTextField(9), name = new JTextField(12);
    private final JComboBox<String> event = new JComboBox<>(new String[]{"Tech Symposium","Cultural Fest","Sports Meet"});
    private final JComboBox<String> filter = new JComboBox<>(new String[]{"All","Tech Symposium","Cultural Fest","Sports Meet"});
    private final DefaultTableModel model = new DefaultTableModel(new String[]{"ID","Student ID","Name","Event"},0) {
        public boolean isCellEditable(int r,int c) { return false; }
    };
    private final JTable table = new JTable(model);
    private final JLabel status = new JLabel("Ready");

    public task6() {
        super("Campus Event Registration");
        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT));
        form.add(new JLabel("Student ID")); form.add(id); form.add(new JLabel("Name")); form.add(name); form.add(event);
        JButton add = new JButton("Register"); form.add(add);
        JPanel filterBar = new JPanel(); filterBar.add(new JLabel("Event filter")); filterBar.add(filter);
        JButton refresh = new JButton("Refresh"); filterBar.add(refresh);
        JPanel top = new JPanel(new BorderLayout()); top.add(form,BorderLayout.NORTH); top.add(filterBar,BorderLayout.SOUTH);
        add(top,BorderLayout.NORTH); add(new JScrollPane(table),BorderLayout.CENTER); add(status,BorderLayout.SOUTH);
        add.addActionListener(e -> registerAsync()); refresh.addActionListener(e -> loadAsync());
        setDefaultCloseOperation(EXIT_ON_CLOSE); setSize(820,440); setLocationRelativeTo(null);
        loadAsync();
    }
    private Connection connect() throws SQLException { return DriverManager.getConnection(URL,USER,PASSWORD); }
    private void registerAsync() {
        String studentId=id.getText().trim(), studentName=name.getText().trim(), eventName=(String)event.getSelectedItem();
        if(studentId.isEmpty() || studentName.isEmpty()) { status.setText("Student ID and name are required."); return; }
        status.setText("Registering...");
        new SwingWorker<Void,Void>() {
            protected Void doInBackground() throws Exception {
                try(Connection c=connect(); PreparedStatement p=c.prepareStatement(
                    "INSERT INTO registrations(student_id,student_name,event_name) VALUES(?,?,?)")) {
                    p.setString(1,studentId); p.setString(2,studentName); p.setString(3,eventName); p.executeUpdate();
                }
                return null;
            }
            protected void done() {
                try { get(); status.setText("Registration saved."); loadAsync(); }
                catch(Exception ex) { status.setText("Registration failed: "+rootMessage(ex)); }
            }
        }.execute();
    }
    private void loadAsync() {
        String selected=(String)filter.getSelectedItem(); status.setText("Loading...");
        new SwingWorker<Object[][],Void>() {
            protected Object[][] doInBackground() throws Exception {
                java.util.List<Object[]> rows=new java.util.ArrayList<>();
                String sql="SELECT id,student_id,student_name,event_name FROM registrations "+(selected.equals("All")?"":"WHERE event_name=? ")+"ORDER BY id";
                try(Connection c=connect(); PreparedStatement p=c.prepareStatement(sql)) {
                    if(!selected.equals("All")) p.setString(1,selected);
                    try(ResultSet r=p.executeQuery()) { while(r.next()) rows.add(new Object[]{r.getInt(1),r.getString(2),r.getString(3),r.getString(4)}); }
                }
                return rows.toArray(new Object[0][]);
            }
            protected void done() {
                try { model.setRowCount(0); for(Object[] row:get()) model.addRow(row); status.setText("Loaded "+model.getRowCount()+" registration(s)."); }
                catch(Exception ex) { status.setText("Unable to load: "+rootMessage(ex)); }
            }
        }.execute();
    }
    private String rootMessage(Exception ex) {
        Throwable t=ex; while(t.getCause()!=null) t=t.getCause(); return t.getMessage();
    }
    public static void main(String[] args) {
        try { Class.forName("com.mysql.cj.jdbc.Driver"); }
        catch(ClassNotFoundException ex) { JOptionPane.showMessageDialog(null,"Add MySQL Connector/J to the classpath."); return; }
        SwingUtilities.invokeLater(() -> new task6().setVisible(true));
    }
}
