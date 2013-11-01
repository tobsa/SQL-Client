package SQLClient;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class SQLClientCommand extends JPanel {
    
    public SQLClientCommand() {
        setLayout(new BorderLayout());
        
        JPanel panel1 = new JPanel();
        panel1.add(new JScrollPane(new JTextArea(10, 25)));
        panel1.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5, 10, 0, 10), new TitledBorder("SQL Command")));
        
        JButton button1 = new JButton("Clear");
        JButton button2 = new JButton("Execute Query");
        button1.addActionListener(new ButtonClearListener());
        button2.addActionListener(new ButtonExecuteListener());
        
        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel2.add(button1);
        panel2.add(button2);
        panel2.setBorder(new EmptyBorder(0, 10, 10, 10));
        
        add(panel1, BorderLayout.NORTH);
        add(panel2, BorderLayout.CENTER);
    }
    
    private class ButtonClearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
    }
    
    private class ButtonExecuteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
    }
}
