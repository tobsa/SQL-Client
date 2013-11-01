package SQLClient;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MessageDialog extends JDialog {
    
    public MessageDialog(JFrame parent, String message) {
        setLayout(new BorderLayout());   
        
        
        JButton button = new JButton("OK");
        button.addActionListener(new ButtonOKListener());
        
        JPanel panel1 = new JPanel();
        panel1.add(new JLabel(message));
        
        JPanel panel2 = new JPanel();
        panel2.add(button);
        
        add(panel1, BorderLayout.NORTH);
        add(panel2, BorderLayout.CENTER);
        
        
        
        pack();
        
        setTitle("Message");
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModal(true);
        setVisible(true);
    }   
    
    private class ButtonOKListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }
}
