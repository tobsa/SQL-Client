package SQLClient;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SQLFrame extends JFrame {
    
    public SQLFrame() {
        setLayout(new FlowLayout(FlowLayout.LEFT));  
        
        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.add(new SQLClientLogin(),  BorderLayout.NORTH);
        panel1.add(new SQLClientCommand(),BorderLayout.CENTER);
        
        JPanel panel2 = new JPanel();
        panel2.add(new SQLClientExecutionResult());
        
        add(panel1);
        add(panel2);
        
        pack();
    }
}
