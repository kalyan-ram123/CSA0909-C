import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class task4 extends Frame {
    private final List<Point> marks = new ArrayList<>();
    private final Label count = new Label("Points: 0");
    public task4() {
        super("Mouse Event Drawing Pad");
        add(count,BorderLayout.SOUTH);
        addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                marks.add(e.getPoint());
                count.setText("Points: " + marks.size());
                repaint();
            }
        });
        addWindowListener(new WindowAdapter() { public void windowClosing(WindowEvent e) { dispose(); } });
        setSize(520,380); setLocationRelativeTo(null);
    }
    @Override public void paint(Graphics g) {
        super.paint(g);
        for (Point p : marks) g.fillOval(p.x-4,p.y-4,8,8);
    }
    public static void main(String[] args) { EventQueue.invokeLater(() -> new task4().setVisible(true)); }
}
