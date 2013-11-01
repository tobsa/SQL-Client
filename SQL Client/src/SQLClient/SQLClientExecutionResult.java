package SQLClient;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class SQLClientExecutionResult extends JFrame {
    
    public SQLClientExecutionResult(JTable table) {
        setLayout(new BorderLayout());      
        
        Border border1 = BorderFactory.createCompoundBorder(new EmptyBorder(25, 25,  5, 25), new TitledBorder("SQL Execution Results"));
        Border border2 = BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), new EtchedBorder());
        
        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.add(table.getTableHeader(), BorderLayout.NORTH);
        panel1.add(table, BorderLayout.CENTER);
        panel1.setBorder(BorderFactory.createCompoundBorder(border1, border2));
        
        JButton button = new JButton("Close");
        button.addActionListener(new ButtonCloseListener());
        
        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel2.add(button);
        
        add(panel1, BorderLayout.NORTH);
        add(panel2, BorderLayout.CENTER);
        
        pack();
    }
      
    private class ButtonCloseListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }
}
