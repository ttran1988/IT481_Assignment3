import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInterface extends JFrame {
    private SQLDataBase sqlDB;
    private JPanel topPanel;
    private JPanel rightPanel;
    private JPanel leftPanel;
    private JScrollPane scrollPane;
    private JTextArea displayResults;
    private JLabel connectionStatus;
    private JButton btnDisconnectDB;
    private JButton btnGetCustomerCount;
    private JButton btnGetCustomerID;
    private JButton btnGetOrderCount;
    private JButton btnGetOrderID;
    private JButton btnGetEmployeeCount;
    private JButton btnGetEmployeeLastName;
    private JButton btnGetCompanyName;
    private JButton btnGetShipName;
    private JTextField tfUserName;
    private JTextField tfUserPass;
    private JTextField tfServerName;
    private JTextField tfDatabaseName;

    UserInterface (){

        //adds top panel getting user information and connecting to DB
        GridBagLayout topPanelGridBag = new GridBagLayout();
        GridBagConstraints topPanelBagConstraints = new GridBagConstraints();
        topPanel = new JPanel(topPanelGridBag);
        topPanel.setBackground(Color.pink);
        topPanel.setBounds(0,0, 600, 200);
        this.add(topPanel);

        //label to display connection status
        connectionStatus = new JLabel("Connection Status: Not Connected");
        connectionStatus.setHorizontalAlignment(JLabel.CENTER);

        //formats the layout for buttons and labels in top panel
        topPanelBagConstraints.gridx = 0;
        topPanelBagConstraints.gridy = 5;
        topPanelBagConstraints.gridwidth = 2;
        topPanelBagConstraints.insets = new Insets(5,5,5,5);
        topPanelBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        topPanelGridBag.setConstraints(connectionStatus, topPanelBagConstraints);
        topPanel.add(connectionStatus);

        //creates and format area to display content results in right panel
        displayResults = new JTextArea();
        displayResults.setEditable(false);
        displayResults.setBackground(Color.cyan);
        displayResults.setMargin(new Insets(10,10,10,10));
        scrollPane = new JScrollPane(displayResults);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //adds right panel to frame, used to display database results
        rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBounds(250,200,350,400);
        this.add(rightPanel);
        rightPanel.add(scrollPane);

        //adds left panel to frame, use for holding various buttons
        leftPanel = new JPanel();
        leftPanel.setBackground(Color.green);
        leftPanel.setBounds(0,200,250,400);
        leftPanel.setLayout(new BoxLayout(leftPanel,BoxLayout.Y_AXIS));
        this.add(leftPanel);

        JLabel lblServerName = new JLabel("Server Name:");
        topPanelBagConstraints.gridx = 0;
        topPanelBagConstraints.gridy = 0;
        topPanelBagConstraints.gridwidth = 1;
        topPanelGridBag.setConstraints(lblServerName, topPanelBagConstraints);
        topPanel.add(lblServerName);

        JLabel lblDatabaseName = new JLabel("Database Name:");
        topPanelBagConstraints.gridx = 0;
        topPanelBagConstraints.gridy = 1;
        topPanelGridBag.setConstraints(lblDatabaseName, topPanelBagConstraints);
        topPanel.add(lblDatabaseName);

        JLabel lblUserName = new JLabel("User Name:");
        topPanelBagConstraints.gridx = 0;
        topPanelBagConstraints.gridy = 2;
        topPanelGridBag.setConstraints(lblUserName, topPanelBagConstraints);
        topPanel.add(lblUserName);

        JLabel lblUserPassword = new JLabel("Password:");
        topPanelBagConstraints.gridx = 0;
        topPanelBagConstraints.gridy = 3;
        topPanelGridBag.setConstraints(lblUserPassword, topPanelBagConstraints);
        topPanel.add(lblUserPassword);

        tfServerName = new JTextField(20);
        topPanelBagConstraints.gridx = 1;
        topPanelBagConstraints.gridy = 0;
        topPanelGridBag.setConstraints(tfServerName, topPanelBagConstraints);
        topPanel.add(tfServerName);

        tfDatabaseName = new JTextField(20);
        topPanelBagConstraints.gridx = 1;
        topPanelBagConstraints.gridy = 1;
        topPanelGridBag.setConstraints(tfDatabaseName, topPanelBagConstraints);
        topPanel.add(tfDatabaseName);

        tfUserName = new JTextField(20);
        topPanelBagConstraints.gridx = 1;
        topPanelBagConstraints.gridy = 2;
        topPanelGridBag.setConstraints(tfUserName, topPanelBagConstraints);
        topPanel.add(tfUserName);

        tfUserPass = new JTextField(20);
        topPanelBagConstraints.gridx = 1;
        topPanelBagConstraints.gridy = 3;
        topPanelGridBag.setConstraints(tfUserPass, topPanelBagConstraints);
        topPanel.add(tfUserPass);

        //disconnect button added to top panel, use to disconnect from database
        btnDisconnectDB = new JButton("Disconnect");
        btnDisconnectDB.setFocusable(false);
        btnDisconnectDB.setEnabled(false);
        topPanelBagConstraints.gridy = 4;
        topPanelBagConstraints.gridx = 0;
        topPanelBagConstraints.gridwidth = 1;
        topPanelGridBag.setConstraints(btnDisconnectDB, topPanelBagConstraints);
        btnDisconnectDB.addActionListener(e -> btnDisconnectDB());
        topPanel.add(btnDisconnectDB);

        //connect button added to top panel, use to connect to database
        JButton btnConnectDB = new JButton("Connect to database");
        btnConnectDB.setFocusable(false);
        topPanelBagConstraints.gridx = 1;
        topPanelBagConstraints.gridy = 4;
        topPanelGridBag.setConstraints(btnConnectDB, topPanelBagConstraints);
        btnConnectDB.addActionListener(e -> btnConnectDB());
        topPanel.add(btnConnectDB);

        //create spacing for buttons
        leftPanel.add((Box.createRigidArea(new Dimension(0, 15))));

        //button to retrieve customer count (added to left panel)
        btnGetCustomerCount = new JButton("Get Customer Count");
        btnGetCustomerCount.setFocusable(false);
        btnGetCustomerCount.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGetCustomerCount.setEnabled(false);
        btnGetCustomerCount.addActionListener(e -> btnGetCustomerCount());
        leftPanel.add(btnGetCustomerCount);

        //create spacing for buttons
        leftPanel.add((Box.createRigidArea(new Dimension(0, 15))));

        //button to retrieve customer ID (added to left panel)
        btnGetCustomerID = new JButton("Get Customer ID");
        btnGetCustomerID.setFocusable(false);
        btnGetCustomerID.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGetCustomerID.setEnabled(false);
        btnGetCustomerID.addActionListener(e -> btnGetCustomerID());
        leftPanel.add(btnGetCustomerID);

        //create spacing for buttons
        leftPanel.add((Box.createRigidArea(new Dimension(0, 15))));

        //button to retrieve order count (added to left panel)
        btnGetOrderCount = new JButton("Get Order Count");
        btnGetOrderCount.setFocusable(false);
        btnGetOrderCount.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGetOrderCount.setEnabled(false);
        btnGetOrderCount.addActionListener(e -> btnGetOrderCount());
        leftPanel.add(btnGetOrderCount);

        //create spacing for buttons
        leftPanel.add((Box.createRigidArea(new Dimension(0, 15))));

        //button to retrieve order ID (added to left panel)
        btnGetOrderID = new JButton("Get Order ID");
        btnGetOrderID.setFocusable(false);
        btnGetOrderID.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGetOrderID.setEnabled(false);
        btnGetOrderID.addActionListener(e -> btnGetOrderID());
        leftPanel.add(btnGetOrderID);

        //create spacing for buttons
        leftPanel.add((Box.createRigidArea(new Dimension(0, 15))));

        //button to retrieve employee count (added to left panel)
        btnGetEmployeeCount = new JButton("Get Employee Count");
        btnGetEmployeeCount.setFocusable(false);
        btnGetEmployeeCount.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGetEmployeeCount.setEnabled(false);
        btnGetEmployeeCount.addActionListener(e -> btnGetEmployeeCount());
        leftPanel.add(btnGetEmployeeCount);

        //create spacing for buttons
        leftPanel.add((Box.createRigidArea(new Dimension(0, 15))));

        //button to retrieve employee last name (added to left panel)
        btnGetEmployeeLastName = new JButton("Get Employee Last Name");
        btnGetEmployeeLastName.setFocusable(false);
        btnGetEmployeeLastName.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGetEmployeeLastName.setEnabled(false);
        btnGetEmployeeLastName.addActionListener(e -> btnGetEmployeeLastName());
        leftPanel.add(btnGetEmployeeLastName);

        //create spacing for buttons
        leftPanel.add((Box.createRigidArea(new Dimension(0, 15))));

        //button to retrieve company name (added to left panel)
        btnGetCompanyName = new JButton("Get Company Name");
        btnGetCompanyName.setFocusable(false);
        btnGetCompanyName.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGetCompanyName.setEnabled(false);
        btnGetCompanyName.addActionListener(e -> btnGetCompanyName());
        leftPanel.add(btnGetCompanyName);

        //create spacing for buttons
        leftPanel.add((Box.createRigidArea(new Dimension(0, 15))));

        //button to retrieve ship name (added to left panel)
        btnGetShipName = new JButton("Get Ship Name");
        btnGetShipName.setFocusable(false);
        btnGetShipName.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGetShipName.setEnabled(false);
        btnGetShipName.addActionListener(e -> btnGetShipName());
        leftPanel.add(btnGetShipName);

        //sets up the frame and loads it
        this.setTitle("IT481 - Assignment 3");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(615,640));
        this.setLayout(null);
        this.pack();
        this.setVisible(true);

    }

    public void btnConnectDB() {
        boolean isValid = true;
        String user = tfUserName.getText();
        String password = tfUserPass.getText();
        String server = tfServerName.getText();
        String database = tfDatabaseName.getText();

        Pattern regexPassword = Pattern.compile("^[a-zA-Z0-9 ]*$");
        Matcher userPass = regexPassword.matcher(password);

        if (user.length() == 0 || password.length() == 0 ||
                server.length() == 0 || database.length() == 0) {
            isValid = false;
            JOptionPane.showMessageDialog(null,"You must enter user name, password, server, and database values");
        }

        else if (password.length() < 6) {
            isValid = false;
            JOptionPane.showMessageDialog(null,"Password length must be 6 characters or more");
        }

        else if (userPass.matches()) {
            isValid = false;
            JOptionPane.showMessageDialog(null,"Password must contains at least one special character!");
        }

        if (isValid) {
            sqlDB = new SQLDataBase("jdbc:sqlserver://;servername="+tfServerName.getText()+
                    ";databaseName="+tfDatabaseName.getText()+
                    ";user="+tfUserName.getText()+
                    ";password="+tfUserPass.getText()+
                    ";trustServerCertificate=true");
            sqlDB.connectToDb();
        }

        if (sqlDB.isConnectionSuccessful() == true) {
            connectionStatus.setText("Connection Status: Connected");
            displayResults.setText("Connected to database");
            btnDisconnectDB.setEnabled(true);
            btnGetCustomerCount.setEnabled(true);
            btnGetCustomerID.setEnabled(true);
            btnGetOrderCount.setEnabled(true);
            btnGetOrderID.setEnabled(true);
            btnGetEmployeeCount.setEnabled(true);
            btnGetEmployeeLastName.setEnabled(true);
            btnGetCompanyName.setEnabled(true);
            btnGetShipName.setEnabled(true);
        } else {
            displayResults.setText("Cannot connect to database");
        }


    }

    public void btnDisconnectDB() {
        if (sqlDB.isConnectionSuccessful() == false) {
            sqlDB.disconnectFromDB();
            connectionStatus.setText("Connection Status: Disconnected");
            displayResults.setText("Disconnected from database");
            btnGetCustomerCount.setEnabled(false);
            btnGetCustomerID.setEnabled(false);
            btnGetOrderCount.setEnabled(false);
            btnGetOrderID.setEnabled(false);
            btnGetEmployeeCount.setEnabled(false);
            btnGetEmployeeLastName.setEnabled(false);
            btnGetCompanyName.setEnabled(false);
            btnGetShipName.setEnabled(false);
        }
    }

    public void btnGetCustomerCount() {
        if (sqlDB.isConnectionSuccessful() == true && sqlDB.getCustomerCount().contains("denied")) {
            displayResults.setText("Permission Denied");
            //displayResults.setText("Customer Count:\n\n" + sqlDB.getCustomerCount());
        } else {
            displayResults.setText("Customer Count:\n\n" + sqlDB.getCustomerCount());
            //displayResults.setText("Cannot connect to database");
        }
    }

    public void btnGetCustomerID() {
        if (sqlDB.isConnectionSuccessful() == true && sqlDB.getCustomerID().contains("denied")) {
            displayResults.setText("Permission Denied");
        } else {
            ArrayList<String> customerIDList = sqlDB.getCustomerID();
            displayResults.setText("Customer ID: \n");
            for (String eachName : customerIDList) {
                displayResults.append(eachName);
            }
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    scrollPane.getViewport().setViewPosition( new Point(0, 0) );
                }
            });
        }
    }

    public void btnGetOrderCount() {
        if (sqlDB.isConnectionSuccessful() == true && sqlDB.getOrderCount().contains("denied")) {
            displayResults.setText("Permission Denied");
        } else {
            displayResults.setText("Order Count:\n\n" + sqlDB.getOrderCount());
        }
    }

    public void btnGetOrderID() {
        if (sqlDB.isConnectionSuccessful() == true && sqlDB.getOrderID().contains("denied")) {
            displayResults.setText("Permission Denied");
        } else {
            ArrayList<String> orderIDList = sqlDB.getOrderID();
            displayResults.setText("Order ID: \n");
            for (String eachName : orderIDList) {
                displayResults.append(eachName);
            }
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    scrollPane.getViewport().setViewPosition( new Point(0, 0) );
                }
            });
        }
    }

    public void btnGetEmployeeCount() {
        if (sqlDB.isConnectionSuccessful() == true && sqlDB.getEmployeeCount().contains("denied")) {
            displayResults.setText("Permission Denied");
        } else {
            displayResults.setText("Employee Count:\n\n" + sqlDB.getEmployeeCount());
        }
    }

    public void btnGetEmployeeLastName() {
        if (sqlDB.isConnectionSuccessful() == true && sqlDB.getEmployeeLastName().contains("denied")) {
            displayResults.setText("Permission Denied");
        } else {
            ArrayList<String> employeeLastNameList = sqlDB.getEmployeeLastName();
            displayResults.setText("Employee Last Name: \n");
            for (String eachName : employeeLastNameList) {
                displayResults.append(eachName);
            }
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    scrollPane.getViewport().setViewPosition( new Point(0, 0) );
                }
            });
        }
    }

    public void btnGetCompanyName() {
        if (sqlDB.isConnectionSuccessful() == true && sqlDB.getCompanyName().contains("denied")) {
            displayResults.setText("Permission Denied");
        } else {
            ArrayList<String> companyNameList = sqlDB.getCompanyName();
            displayResults.setText("Company Name: \n");
            for (String eachName : companyNameList) {
                displayResults.append(eachName);
            }
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    scrollPane.getViewport().setViewPosition( new Point(0, 0) );
                }
            });
        }
    }

    public void btnGetShipName() {
        if (sqlDB.isConnectionSuccessful() == true && sqlDB.getShipName().contains("denied")) {
            displayResults.setText("Permission Denied");
        } else {
            ArrayList<String> shipNameList = sqlDB.getShipName();
            displayResults.setText("Ship Name: \n");
            for (String eachName : shipNameList) {
                displayResults.append(eachName);
            }
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    scrollPane.getViewport().setViewPosition( new Point(0, 0) );
                }
            });
        }
    }
}
