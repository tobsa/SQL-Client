package SQLClient;

import javax.swing.JFrame;

public class SQLClient {
    public static void main(String[] args) {
        SQLFrame frame = new SQLFrame();
        frame.setTitle("Interactive SQL Client");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
