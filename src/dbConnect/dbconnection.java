package dbConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class dbconnection {

	public static void main(String[] args) throws SQLException, InterruptedException {

		String host = "localhost";
		String Port = "3306";

		Connection connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + Port + "/testdb", "root",
				"Shudhvaani@123");
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from employeeInfo where Name='Sharma'");

		while (resultSet.next()) {
			System.getProperty("webdriver.chrome.driver",
					"D:\\Applications setup\\Browserdrivers\\chromedriver_win32\\chromedriver.exe");

			// following lines are used when 403 error comes up in the db connection

			ChromeOptions ops = new ChromeOptions();

			ops.addArguments("--remote-allow-origins=*");

			// Just pass this 'ops' object to ChromeDriver() as argument

			ChromeDriver driver = new ChromeDriver(ops);

			driver.get("https://login.salesforce.com/");

			driver.findElement(By.id("username")).sendKeys(resultSet.getString("Name"));
			driver.findElement(By.id("password")).sendKeys(resultSet.getString("password"));
		}
	}
}
