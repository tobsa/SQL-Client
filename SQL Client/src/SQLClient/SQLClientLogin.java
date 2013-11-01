package SQLClient;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class SQLClientLogin extends JPanel {
    
    public SQLClientLogin() {
        setLayout(new BorderLayout());
        
        JComboBox<String> jcbDatabaseURL = new JComboBox();
        jcbDatabaseURL.addItem("jdbc:sqlserver://hitsql-db.hb.se:56077\"");
        
        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.add(new JLabel("Database URL"), BorderLayout.NORTH);
        panel1.add(new JLabel("Username"),     BorderLayout.CENTER);
        panel1.add(new JLabel("Password"),     BorderLayout.SOUTH);
        panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.add(jcbDatabaseURL, BorderLayout.NORTH);
        panel2.add(new JTextField(25), BorderLayout.CENTER);
        panel2.add(new JPasswordField(25), BorderLayout.SOUTH);
        panel2.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel panel3 = new JPanel(new BorderLayout());
        panel3.add(panel1, BorderLayout.WEST);
        panel3.add(panel2, BorderLayout.CENTER);
        panel3.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 0, 10), new TitledBorder("Enter Database Information")));
                
        JButton button1 = new JButton("Add Database URL");
        JButton button2 = new JButton("Connect");
        button1.addActionListener(new ButtonAddURLListener());
        button2.addActionListener(new ButtonConnectListener());
        
        JPanel panel4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel4.add(button1);
        panel4.add(button2);
        panel4.setBorder(new EmptyBorder(0, 10, 0, 10));
        
        add(panel3, BorderLayout.NORTH);
        add(panel4, BorderLayout.CENTER);
    }
    
    private class ButtonAddURLListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
    }
    
    private class ButtonConnectListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
    }
}
