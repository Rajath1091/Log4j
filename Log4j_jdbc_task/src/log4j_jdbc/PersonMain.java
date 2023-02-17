package log4j_jdbc;

import java.util.Scanner;

public class PersonMain {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		PersonCRUD crud = new PersonCRUD();

		int choice;
		boolean key = true;
		do {
			System.out.println("Enter the choice \n 1.Sign Up \n 2.Sign In \n 3.Sign Out");
			choice = sc.nextInt();
			switch (choice) {
			case 1: {
				System.out.println("Enter the id");
				int id = sc.nextInt();
				System.out.println("Enter the name");
				String name = sc.next();
				System.out.println("Enter the email");
				String email = sc.next();
				System.out.println("Enter the password");
				String password = sc.next();
				System.out.println("Enter the balance");
				double balance = sc.nextDouble();

				Person p = new Person();
				p.setId(id);
				p.setName(name);
				p.setEmail(email);
				p.setPassword(password);
				p.setBalance(balance);

				try {
					crud.savePerson(p);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				break;
			case 2: {
				System.out.println("Enter the email");
				String email = sc.next();
				System.out.println("Enter the password");
				String password = sc.next();

				try {
					boolean result = crud.validatePerson(email, password);
					System.out.println("t");
					if (result == true) {
						int choice1;
						boolean key1 = true;
						do {
							System.out.println("Enter the choice \n 1.Credit balance \n 2.Debit balance \n 3.Exit");
							choice1 = sc.nextInt();
							switch (choice1) {
							case 1: {
								System.out.println("Enter the amount to deposit");
								double amount = sc.nextDouble();

								if (amount > 0) {
									crud.credit(amount, email);
								}
							}
								break;
							case 2: {
								System.out.println("Enter the amount to withdraw");
								double amount = sc.nextDouble();

								crud.debit(amount, email);
							}
								break;
							default:
								key1 = false;
							}
						} while (key1);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				break;

			default:
				key = false;
			}
		} while (key);

	}

}
