package SQLClient;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class SQLFrame extends JFrame {
    private Database database = new Database();
    private JTextField loginURLTextField = new JTextField("jdbc:sqlserver://hitsql-db.hb.se:56077", 25);
    private JTextField textField = new JTextField(25);
    private JPasswordField passwordField = new JPasswordField(25);
    private JButton connectButton = new JButton("Connect");
    private JTextArea commandTextArea = new JTextArea(20, 50);
    private JButton commandClearButton = new JButton("Clear");
    private JButton commandExecuteButton = new JButton("Execute Query");
    private JButton commandPrevButton = new JButton("Prev Command");
    private JButton commandNextButton = new JButton("Next Command");
    private List<String> usedCommands = new ArrayList();
    private int usedCommandIndex;
    
    public SQLFrame() {        
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("commands.ser"));
            usedCommands = (List<String>)input.readObject();
            usedCommandIndex = usedCommands.size();
            input.close();
        } 
        catch (IOException | ClassNotFoundException ex) {}
        
        setLayout(new FlowLayout(FlowLayout.LEFT));  
        
        JPanel loginPanel   = createSQLLogin();
        JPanel commandPanel = createSQLCommandPanel();
        
        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.add(loginPanel, BorderLayout.NORTH);
        panel1.add(commandPanel,BorderLayout.CENTER);
        
        add(panel1);
        
        pack();
    }
    
    private JPanel createSQLLogin() {        
        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.add(new JLabel("Database URL"), BorderLayout.NORTH);
        panel1.add(new JLabel("Username"),     BorderLayout.CENTER);
        panel1.add(new JLabel("Password"),     BorderLayout.SOUTH);
        panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.add(loginURLTextField, BorderLayout.NORTH);
        panel2.add(textField, BorderLayout.CENTER);
        panel2.add(passwordField, BorderLayout.SOUTH);
        panel2.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel panel3 = new JPanel(new BorderLayout());
        panel3.add(panel1, BorderLayout.WEST);
        panel3.add(panel2, BorderLayout.CENTER);
        panel3.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 0, 10), new TitledBorder("Enter Database Information")));
                
        connectButton.addActionListener(new ButtonConnectListener());
        
        JPanel panel4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel4.add(connectButton);
        panel4.setBorder(new EmptyBorder(0, 10, 0, 10));
        
        JPanel loginPanel = new JPanel(new BorderLayout());
        loginPanel.add(panel3, BorderLayout.NORTH);
        loginPanel.add(panel4, BorderLayout.CENTER);
        
        return loginPanel;
    }
    
    private JPanel createSQLCommandPanel() {      
        commandTextArea.append("");
        
        JPanel panel1 = new JPanel();
        panel1.add(new JScrollPane(commandTextArea));
        panel1.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5, 10, 0, 10), new TitledBorder("SQL Command")));
        
        commandPrevButton.addActionListener(new ButtonPrevListener());
        commandNextButton.addActionListener(new ButtonNextListener());
        commandClearButton.addActionListener(new ButtonClearListener());
        commandExecuteButton.addActionListener(new ButtonExecuteListener());
        
        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel2.add(commandPrevButton);
        panel2.add(commandNextButton);
        panel2.add(commandClearButton);
        panel2.add(commandExecuteButton);
        panel2.setBorder(new EmptyBorder(0, 10, 10, 10));
        
        commandPrevButton.setEnabled(false);
        commandNextButton.setEnabled(false);
        commandTextArea.setEnabled(false);
        commandClearButton.setEnabled(false);
        commandExecuteButton.setEnabled(false);
        
        JPanel commandPanel = new JPanel(new BorderLayout());
        commandPanel.add(panel1, BorderLayout.NORTH);
        commandPanel.add(panel2, BorderLayout.CENTER);
        return commandPanel;
    }
        
    private class ButtonConnectListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {                
                database.login(loginURLTextField.getText(), textField.getText(), new String(passwordField.getPassword()));
                
                loginURLTextField.setEnabled(false);
                textField.setEnabled(false);
                passwordField.setEnabled(false);
                connectButton.setEnabled(false);
                commandPrevButton.setEnabled(true);
                commandNextButton.setEnabled(true);
                commandTextArea.setEnabled(true);
                commandClearButton.setEnabled(true);
                commandExecuteButton.setEnabled(true);
            } 
            catch (ClassNotFoundException | SQLException ex) {
                new MessageDialog(SQLFrame.this, ex.toString());
            }
        }
    }
    
    private class ButtonPrevListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(usedCommands.isEmpty())
                return;
            
            usedCommandIndex--;
            if(usedCommandIndex < 0) {
                usedCommandIndex = 0;
                return;
            }
            
            commandTextArea.setText(usedCommands.get(usedCommandIndex));
        }
    }
    
    private class ButtonNextListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {           
            if(usedCommands.isEmpty())
                return;
           
            usedCommandIndex++;
            if(usedCommandIndex >= usedCommands.size()) {
                usedCommandIndex = usedCommands.size() - 1;
                return;
            }
            
            commandTextArea.setText(usedCommands.get(usedCommandIndex));
        }
    }
    
    private class ButtonClearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            usedCommandIndex = usedCommands.size();
            commandTextArea.setText("");
        }
    }
    
    private DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();

        Vector<String> columnNames = new Vector();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++)
            columnNames.add(metaData.getColumnName(column));

        Vector<Vector<Object>> data = new Vector();
        while (rs.next()) {
            Vector<Object> vector = new Vector();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++)
                vector.add(rs.getObject(columnIndex));
                
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);
    }
    
    private class ButtonExecuteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {                
                ResultSet rs = database.executeQuery(commandTextArea.getText());
                
                if(!usedCommands.get(usedCommands.size() - 1).equals(commandTextArea.getText())) {
                    usedCommands.add(commandTextArea.getText());
                    usedCommandIndex = usedCommands.size() - 1;

                    if(usedCommands.size() > 25)
                        usedCommands.remove(0);

                    ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("commands.ser"));
                    output.writeObject(usedCommands);
                    output.close();
                }
 
                SQLClientExecutionResult result = new SQLClientExecutionResult(new JTable(buildTableModel(rs)));
                result.setTitle("SQL Execution Results");
                result.setLocationRelativeTo(null);
                result.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                result.setVisible(true);
                
            } catch (SQLException ex) {
                new MessageDialog(SQLFrame.this, ex.toString());
            } catch (IOException ex) {}
        }
    }
}
