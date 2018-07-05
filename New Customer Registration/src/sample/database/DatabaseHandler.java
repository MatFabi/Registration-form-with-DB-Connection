package sample.database;

import sample.model.User;

import javax.xml.transform.Result;
import java.sql.*;


public class DatabaseHandler extends Config {
    Connection dbConnection;

    public Connection getDbConnection() throws SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public void SignUpUser(User user) {

        if(!checkUserExistance(user)) {
            String inserts = "INSERT INTO " + Const.CUSTOMER_TABLE + " (" + Const.CUSTOMER_FIRSTNAME + "," + Const.CUSTOMER_LASTNAME
                    + "," + Const.CUSTOMER_TELNUMBER + "," + Const.CUSTOMER_PERSONALID + "," + Const.CUSTOMER_ZIPCODE + ","
                    + Const.CUSTOMER_INFO + ","+Const.CUSTOMER_USERNAME+","+Const.CUSTOMER_PASSWORD + ")" + " VALUES(?,?,?,?,?,?,?,?)";

            try {
                PreparedStatement prst = getDbConnection().prepareStatement(inserts);

                prst.setString(1, user.getFirstName());
                prst.setString(2, user.getLastName());
                prst.setString(3, user.getTelNumber());
                prst.setString(4, user.getPersonalid());
                prst.setString(5, user.getZipCode());
                prst.setString(6, user.getInfo());
                prst.setString(7, user.getUserName());
                prst.setString(8, user.getPassword());

                prst.execute();
                prst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else return;
    }

    public boolean checkUserExistance(User user) {

        int counter = 0;

        String query = "SELECT * FROM " + Const.CUSTOMER_TABLE + " WHERE " + Const.CUSTOMER_PERSONALID + "=?";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setString(1, user.getPersonalid());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                counter++;
            }
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(counter>=1){
            return true;
        }
        return false;
    }


    public ResultSet getUser(User user){
        ResultSet resultSet = null;

        if(!user.getUserName().equals("")|| !user.getPassword().equals("")){
            String query = "SELECT * FROM " +Const.CUSTOMER_TABLE + " WHERE "
                    + Const.CUSTOMER_USERNAME + "=?" + " AND " + Const.CUSTOMER_PASSWORD + "=?";

            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
                preparedStatement.setString(1, user.getUserName());
                preparedStatement.setString(2, user.getPassword());

                resultSet = preparedStatement.executeQuery();
            }   catch(SQLException e){
                e.printStackTrace();
            }

        }else {
            System.out.println("Please enter your credentials");
        }
        return resultSet;
    }
}
