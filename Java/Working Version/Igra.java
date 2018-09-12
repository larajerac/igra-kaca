import java.awt.EventQueue;

import javax.swing.JFrame;

public class Igra extends JFrame {

Igra() {
    add(new Okno());
    setResizable(false);
    pack();

    setTitle("KAÈA");
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
}

public static void main(String[] args) {

    // ustvari novo 'okno'
    EventQueue.invokeLater(new Runnable() {
        @Override
        public void run() {
            JFrame frame = new Igra();
            frame.setVisible(true);
        }
    });
}
}