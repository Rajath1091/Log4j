import java.util.Scanner;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class ValidatePersonInfo {

	static Logger logger = Logger.getLogger(ValidatePersonInfo.class);
	static Layout layout = new PatternLayout("%d{dd-MMM-yyyy}--> %p %m %C %M %l %n");

	public static void main(String[] args) {

		Appender appender = new ConsoleAppender(layout);

		logger.addAppender(appender);

		Person p = new Person();
		p.setAccountId("Raj1091");
		p.setName("Rajath");
		p.setBalance(5000);
		p.setPassword("1234");

		Scanner sc = new Scanner(System.in);

		int count = 1;
		while (count <= 3) {
			System.out.println("Enter the Account id");
			String accountId = sc.next();
			System.out.println("Enter the password");
			String password = sc.next();

			boolean result = validatePerson(p, accountId, password);
			if (result == true) {
				break;
			}
			count++;
		}
		if (count > 3)
			logger.warn("User account blocked for a day");

	}

	public static boolean validatePerson(Person p, String accountId, String password) {
		if (p.getAccountId().equals(accountId) && p.getPassword().equals(password)) {
			logger.info("Valid User Info");
			// Ask for withdraw or credit the amount
			Scanner sc = new Scanner(System.in);
			int key;
			boolean flag = true;
			do {
				System.out.println("Enter the choice \n 1.Withdraw Amount \n 2.Credit Amount \n 3.Logout");
				key = sc.nextInt();
				switch (key) {
				case 1: {
					System.out.println("Enter the amount to be withdrawn");
					int withdraw = sc.nextInt();

					if (withdraw <= p.getBalance()) {
						p.setBalance(p.getBalance() - withdraw);
						logger.info("Withdraw successful");
						logger.info("Balance:" + p.getBalance());
					} else {
						logger.info("Insufficient Balance");
					}
				}
					break;

				case 2: {
					System.out.println("Enter the amount to be credited");
					int credit = sc.nextInt();

					if (credit > 0) {
						p.setBalance(p.getBalance() + credit);
						logger.info("Credit Successful");
						logger.info("Balance:" + p.getBalance());
					}
				}
					break;

				default:
					flag = false;
					logger.info("Logout Successful");
				}
			} while (flag);
			return true;
		} else {
			logger.info("Invalid User Info");
			return false;
		}
	}

}
