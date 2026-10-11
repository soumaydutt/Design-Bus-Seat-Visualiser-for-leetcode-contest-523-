import javax.swing.*;
import java.awt.*;

/** Interactive Java Swing demonstration of the bus booking rules. */
public class BusBookingDemo {
    private final BusBooking booking = new BusBooking(5);
    private final JButton[][] buttons = new JButton[6][4];
    private final JLabel totalLabel = new JLabel();
    private final JLabel minLabel = new JLabel();
    private final JTextArea history = new JTextArea(8, 25);

    public BusBookingDemo() {
        JFrame frame = new JFrame("Bus Booking Visualizer | Contest 523 Q3");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel container = new JPanel(new BorderLayout(12, 12));
        container.setBackground(new Color(15, 23, 42));
        container.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));

        JLabel title = new JLabel("Bus Booking Visualizer");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        container.add(title, BorderLayout.NORTH);

        JPanel seats = new JPanel(new GridLayout(5, 4, 10, 10));
        seats.setBackground(new Color(15, 23, 42));
        for (int row = 1; row <= 5; row++) {
            for (int col = 0; col < 4; col++) {
                final int r = row;
                final int c = col;
                String name = "" + (char) ('A' + col) + row;
                JButton button = new JButton(name);
                button.setOpaque(true);
                button.addActionListener(event -> {
                    boolean wasBooked = booking.isBooked(r, c);
                    int oldCost = booking.getBookingCost(r, c);
                    booking.toggle(name);
                    int cost = wasBooked ? oldCost : booking.getBookingCost(r, c);
                    history.append((wasBooked ? "Cancelled " : "Booked ") + name + " (" + cost + "s)\n");
                    refresh();
                });
                buttons[row][col] = button;
                seats.add(button);
            }
        }
        container.add(seats, BorderLayout.CENTER);

        JPanel footer = new JPanel(new BorderLayout(8, 8));
        footer.setBackground(new Color(15, 23, 42));
        JPanel stats = new JPanel(new GridLayout(1, 2));
        stats.setBackground(new Color(15, 23, 42));
        for (JLabel label : new JLabel[]{totalLabel, minLabel}) {
            label.setForeground(Color.WHITE);
            stats.add(label);
        }
        footer.add(stats, BorderLayout.NORTH);
        history.setEditable(false);
        history.setBackground(new Color(30, 41, 59));
        history.setForeground(Color.WHITE);
        footer.add(new JScrollPane(history), BorderLayout.CENTER);
        container.add(footer, BorderLayout.SOUTH);

        frame.setContentPane(container);
        frame.setSize(540, 670);
        frame.setLocationRelativeTo(null);
        refresh();
        frame.setVisible(true);
    }

    private void refresh() {
        for (int row = 1; row <= 5; row++) {
            for (int col = 0; col < 4; col++) {
                JButton button = buttons[row][col];
                boolean booked = booking.isBooked(row, col);
                int cost = booking.getBookingCost(row, col);
                button.setText("" + (char) ('A' + col) + row + (booked ? " (" + cost + "s)" : ""));
                button.setBackground(!booked ? new Color(51, 65, 85) :
                        cost == 3 ? new Color(175, 109, 37) : new Color(40, 100, 180));
                button.setForeground(Color.WHITE);
            }
        }
        totalLabel.setText("Total: " + booking.getTotalTime() + "s");
        minLabel.setText("Minimum: " + booking.getMinTime() + "s");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BusBookingDemo::new);
    }
}
