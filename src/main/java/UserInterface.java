import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UserInterface extends JFrame {
    private SQLDataBase sqlDB;
    private JPanel topPanel;
    private JPanel rightPanel;
    private JPanel leftPanel;
    private JScrollPane scrollPane;
    private JTextArea displayResults;
    private JLabel connectionStatus;
    private JButton btnGetCustomerCount;
    private JButton btnGetCustomerID;
    private JButton btnGetOrderCount;
    private JButton btnGetOrderID;
    private JButton btnGetEmployeeCount;
    private JButton btnGetEmployeeLastName;

    UserInterface (){
        //initialize objects
        sqlDB = new SQLDataBase();
        GridBagLayout topPanelGridBag = new GridBagLayout();
        GridBagConstraints topPanelBagConstraints = new GridBagConstraints();

        //adds top panel for to hold database connection buttons
        topPanel = new JPanel();
        topPanel.setBackground(Color.pink);
        topPanel.setBounds(0,0, 600, 100);
        topPanel.setLayout(topPanelGridBag);
        this.add(topPanel);

        //label to display connection status
        connectionStatus = new JLabel("Connection Status: Not Connected");
        connectionStatus.setHorizontalAlignment(JLabel.CENTER);

        //formats the layout for buttons and labels in top panel
        topPanelBagConstraints.gridx = 0;
        topPanelBagConstraints.gridy = 2;
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
        rightPanel = new JPanel();
        rightPanel.setBounds(250,100,350,500);
        rightPanel.setLayout(new BorderLayout());
        this.add(rightPanel);
        rightPanel.add(scrollPane);

        //adds left panel to frame, use for holding various buttons
        leftPanel = new JPanel();
        leftPanel.setBackground(Color.green);
        leftPanel.setBounds(0,100,250,500);
        leftPanel.setLayout(new BoxLayout(leftPanel,BoxLayout.Y_AXIS));
        this.add(leftPanel);

        //disconnect button added to top panel, use to disconnect from database
        JButton btnDisconnectDB = new JButton("Disconnect");
        btnDisconnectDB.setFocusable(false);
        topPanelBagConstraints.gridy = 1;
        topPanelBagConstraints.gridx = 0;
        topPanelBagConstraints.gridwidth = 1;
        topPanelGridBag.setConstraints(btnDisconnectDB, topPanelBagConstraints);
        btnDisconnectDB.addActionListener(e -> btnDisconnectDB());
        topPanel.add(btnDisconnectDB);

        //connect button added to top panel, use to connect to database
        JButton btnConnectDB = new JButton("Connect to database");
        btnConnectDB.setFocusable(false);
        topPanelBagConstraints.gridx = 1;
        topPanelBagConstraints.gridy = 1;
        topPanelGridBag.setConstraints(btnConnectDB, topPanelBagConstraints);
        btnConnectDB.addActionListener(e -> btnConnectDB());
        topPanel.add(btnConnectDB);

        //create spacing for buttons
        leftPanel.add((Box.createRigidArea(new Dimension(0, 25))));

        //button to retrieve customer count (added to left panel)
        btnGetCustomerCount = new JButton("Get Customer Count");
        btnGetCustomerCount.setFocusable(false);
        btnGetCustomerCount.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGetCustomerCount.setEnabled(false);
        btnGetCustomerCount.addActionListener(e -> btnGetCustomerCount());
        leftPanel.add(btnGetCustomerCount);

        //create spacing for buttons
        leftPanel.add((Box.createRigidArea(new Dimension(0, 25))));

        //button to retrieve customer ID (added to left panel)
        btnGetCustomerID = new JButton("Get Customer ID");
        btnGetCustomerID.setFocusable(false);
        btnGetCustomerID.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGetCustomerID.setEnabled(false);
        btnGetCustomerID.addActionListener(e -> btnGetCustomerID());
        leftPanel.add(btnGetCustomerID);

        //create spacing for buttons
        leftPanel.add((Box.createRigidArea(new Dimension(0, 25))));

        //button to retrieve order count (added to left panel)
        btnGetOrderCount = new JButton("Get Order Count");
        btnGetOrderCount.setFocusable(false);
        btnGetOrderCount.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGetOrderCount.setEnabled(false);
        btnGetOrderCount.addActionListener(e -> btnGetOrderCount());
        leftPanel.add(btnGetOrderCount);

        //create spacing for buttons
        leftPanel.add((Box.createRigidArea(new Dimension(0, 25))));

        //button to retrieve order ID (added to left panel)
        btnGetOrderID = new JButton("Get Order ID");
        btnGetOrderID.setFocusable(false);
        btnGetOrderID.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGetOrderID.setEnabled(false);
        btnGetOrderID.addActionListener(e -> btnGetOrderID());
        leftPanel.add(btnGetOrderID);

        //create spacing for buttons
        leftPanel.add((Box.createRigidArea(new Dimension(0, 25))));

        //button to retrieve employee count (added to left panel)
        btnGetEmployeeCount = new JButton("Get Employee Count");
        btnGetEmployeeCount.setFocusable(false);
        btnGetEmployeeCount.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGetEmployeeCount.setEnabled(false);
        btnGetEmployeeCount.addActionListener(e -> btnGetEmployeeCount());
        leftPanel.add(btnGetEmployeeCount);

        //create spacing for buttons
        leftPanel.add((Box.createRigidArea(new Dimension(0, 25))));

        //button to retrieve employee last name (added to left panel)
        btnGetEmployeeLastName = new JButton("Get Employee Last Name");
        btnGetEmployeeLastName.setFocusable(false);
        btnGetEmployeeLastName.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGetEmployeeLastName.setEnabled(false);
        btnGetEmployeeLastName.addActionListener(e -> btnGetEmployeeLastName());
        leftPanel.add(btnGetEmployeeLastName);

        //sets up the frame and loads it
        this.setTitle("IT481 - Assignment 2");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(615,640);
        this.setLayout(null);
        this.setVisible(true);

    }

    public void btnConnectDB() {
        sqlDB.connectToDb();
        if (sqlDB.isConnectionSuccessful() == true) {
            connectionStatus.setText("Connection Status: Connected");
            displayResults.setText("Connected to database");
            btnGetCustomerCount.setEnabled(true);
            btnGetCustomerID.setEnabled(true);
            btnGetOrderCount.setEnabled(true);
            btnGetOrderID.setEnabled(true);
            btnGetEmployeeCount.setEnabled(true);
            btnGetEmployeeLastName.setEnabled(true);
            rightPanel.revalidate();
        } else {
            displayResults.setText("Cannot connect to database");
            rightPanel.revalidate();
        }
    }

    public void btnDisconnectDB() {
        sqlDB.disconnectFromDB();
        if (sqlDB.isConnectionSuccessful() == false) {
            connectionStatus.setText("Connection Status: Disconnected");
            displayResults.setText("Disconnected from database");
            btnGetCustomerCount.setEnabled(false);
            btnGetCustomerID.setEnabled(false);
            btnGetOrderCount.setEnabled(false);
            btnGetOrderID.setEnabled(false);
            btnGetEmployeeCount.setEnabled(false);
            btnGetEmployeeLastName.setEnabled(false);
            rightPanel.revalidate();
        }
    }
    public void btnGetCustomerCount() {
        if (sqlDB.isConnectionSuccessful() == true) {
            displayResults.setText("Customer Count:\n\n" + sqlDB.getCustomerCount());
            rightPanel.revalidate();
        } else {
            displayResults.setText("Cannot connect to database");
            rightPanel.revalidate();
        }
    }

    public void btnGetCustomerID() {
        if (sqlDB.isConnectionSuccessful() == true) {
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
            rightPanel.revalidate();
        } else {
            displayResults.setText("Cannot connect to database");
            rightPanel.validate();
        }
    }

    public void btnGetOrderCount() {
        if (sqlDB.isConnectionSuccessful() == true) {
            displayResults.setText("Order Count:\n\n" + sqlDB.getOrderCount());
            rightPanel.revalidate();
        } else {
            displayResults.setText("Cannot connect to database");
            rightPanel.revalidate();
        }
    }

    public void btnGetOrderID() {
        if (sqlDB.isConnectionSuccessful() == true) {
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
            rightPanel.revalidate();
        } else {
            displayResults.setText("Cannot connect to database");
            rightPanel.validate();
        }
    }

    public void btnGetEmployeeCount() {
        if (sqlDB.isConnectionSuccessful() == true) {
            displayResults.setText("Employee Count:\n\n" + sqlDB.getEmployeeCount());
            rightPanel.revalidate();
        } else {
            displayResults.setText("Cannot connect to database");
            rightPanel.revalidate();
        }
    }

    public void btnGetEmployeeLastName() {
        if (sqlDB.isConnectionSuccessful() == true) {
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
            rightPanel.revalidate();
        } else {
            displayResults.setText("Cannot connect to database");
            rightPanel.validate();
        }
    }
}
