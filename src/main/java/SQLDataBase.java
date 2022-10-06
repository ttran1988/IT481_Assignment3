import java.sql.*;
import java.util.ArrayList;

public class SQLDataBase{
    private String URL;
    private Connection connection;

    public SQLDataBase() {
        this.URL = "jdbc:sqlserver://;servername=DESKTOP-4P5ION6\\sqlexpress;trustServerCertificate=true;integratedSecurity=true;databaseName=Northwind";
    }

    public SQLDataBase(String connection) {
        this.URL = connection;
    }

    public void connectToDb() {
        try {
            connection = DriverManager.getConnection(URL);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void disconnectFromDB() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isConnectionSuccessful() {
        try {
            if (connection.isValid(10)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public String getCustomerCount() {
        String customerCount = "";
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select count(*) from dbo.Customers;");

            while (result.next()) {
                customerCount = result.getString(1);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return customerCount;
    }

    public ArrayList<String> getCustomerID() {
        ArrayList<String> customerIDList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select distinct CustomerID from dbo.Orders;");

            while (result.next()) {
                customerIDList.add("\n" + result.getString(1));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return customerIDList;
    }

    public String getOrderCount() {
        String orderCount = "";
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select count(*) from dbo.Orders;");

            while (result.next()) {
                orderCount = result.getString(1);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return orderCount;
    }

    public ArrayList<String> getOrderID() {
        ArrayList<String> orderIDList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select distinct OrderID from dbo.Orders;");

            while (result.next()) {
                orderIDList.add("\n" + result.getString(1));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return orderIDList;
    }

    public String getEmployeeCount() {
        String employeeCount = "";
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select count(*) from dbo.Employees;");

            while (result.next()) {
                employeeCount = result.getString(1);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return employeeCount;
    }

    public ArrayList<String> getEmployeeLastName() {
        ArrayList<String> employeeLastNameList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select lastname from dbo.Employees;");

            while (result.next()) {
                employeeLastNameList.add("\n" + result.getString(1));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return employeeLastNameList;
    }
}
