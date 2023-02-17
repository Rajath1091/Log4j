package log4j_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class PersonCRUD {

	static Logger logger = Logger.getLogger(PersonCRUD.class);
	static Layout layout = new PatternLayout("%d{dd-MMM-yyyy}->%m %n");

	public Connection getConnection() throws Exception {
		Appender appender = new ConsoleAppender(layout);

		logger.addAppender(appender);

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/log4j_db", "root", "root");
		return connection;
	}

	public void savePerson(Person person) throws Exception {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PERSON VALUES (?,?,?,?,?)");
		preparedStatement.setInt(1, person.getId());
		preparedStatement.setString(2, person.getName());
		preparedStatement.setString(3, person.getEmail());
		preparedStatement.setString(4, person.getPassword());
		preparedStatement.setDouble(5, person.getBalance());
		preparedStatement.execute();
		connection.close();
		logger.info("SignUp successful");
	}

	public boolean validatePerson(String email, String password) throws Exception {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PERSON WHERE EMAIL=?");
		preparedStatement.setString(1, email);
		ResultSet resultSet = preparedStatement.executeQuery();
		String receivedemail = null, receivedpassword = null;
		while (resultSet.next()) {
			receivedemail = resultSet.getString("email");
			receivedpassword = resultSet.getString("password");
		}
		connection.close();
		if (email.equals(receivedemail) && password.equals(receivedpassword)) {
			logger.info("SignIn successful");
			return true;
		} else {
			logger.info("Invalid email & password");
			return false;
		}
	}

	public void credit(double amount, String email) throws Exception {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PERSON WHERE EMAIL=?");
		preparedStatement.setString(1, email);
		ResultSet resultSet = preparedStatement.executeQuery();
		double balance = 0;
		while (resultSet.next()) {
			balance = resultSet.getDouble("balance") + amount;
		}
		preparedStatement = connection.prepareStatement("UPDATE PERSON SET BALANCE=? WHERE EMAIL=?");
		preparedStatement.setDouble(1, balance);
		preparedStatement.setString(2, email);
		preparedStatement.execute();
		connection.close();
		logger.info("Credit successful");
	}

	public void debit(double amount, String email) throws Exception {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PERSON WHERE EMAIL=?");
		preparedStatement.setString(1, email);
		ResultSet resultSet = preparedStatement.executeQuery();
		double balance = 0;
		while (resultSet.next()) {
			balance = resultSet.getDouble("balance");
		}
		if (amount <= balance) {
			preparedStatement = connection.prepareStatement("UPDATE PERSON SET BALANCE=? WHERE EMAIL=?");
			preparedStatement.setDouble(1, balance);
			preparedStatement.setString(2, email);
			preparedStatement.execute();
			connection.close();
			logger.info("Debit successful");
		} else
			logger.info("Insufficient Balance");
	}

}
