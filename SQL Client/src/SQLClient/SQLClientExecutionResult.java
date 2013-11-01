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

public class SQLClientExecutionResult extends JPanel {
    
    public SQLClientExecutionResult() {
        setLayout(new BorderLayout());
        
        JPanel panel1 = new JPanel();
        panel1.add(new JScrollPane(new JTextArea(20, 50)));
        panel1.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 0, 10), new TitledBorder("SQL Execution Results")));
        
        JButton button = new JButton("Clear");
        button.addActionListener(new ButtonClearListener());
        
        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel2.add(button);
        
        add(panel1, BorderLayout.NORTH);
        add(panel2, BorderLayout.CENTER);
    }
    
    private class ButtonClearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
    }
}
