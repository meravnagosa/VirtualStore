import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class CustomerMenu implements User {
	Store store;
	CustomerMenu(Store store){
		this.store = store;
	}
	@Override
	public void createAccount() {
		Scanner s = new Scanner(System.in);
		String fName = firstNameInput();
		String lName = lastNameInput();
		String uName = userNameInput();
		String password = passwordInput();
		System.out.println("Would like to join our VIP club? Enter 1 for yes, 0 for no");
		int vip=s.nextInt();
		s.nextLine();
		while (vip!=1&&vip!=0) {
			System.out.println("Wrong input! Enter only 1 or 0");
			vip=s.nextInt();
			s.nextLine();
		}
		Customer customer = new Customer(uName,password,fName,lName);
		if (vip==1) {
			customer.setClubMem(true);
		}
		else {
			customer.setClubMem(false);
		}

		store.addCustomer(customer);


		return;
	}



	@Override
	public void logIn() {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter your user name");
		String uName = s.nextLine();

		System.out.println("Enter password");
		String password=s.nextLine();
		int count=0;

		for (Customer i : store.getCustomers()) {
			if (i.getUserName().equals(uName)) {
				if(i.getPassword().equals(password)) {
					menuFlow(i);
					break;
				}
			}
			if (count==(store.getCustomers().size())-1) {
				System.out.println("User does not exist");
			}
		}
		return;
	}

	@Override
	public void menuFlow(Customer c) {
		c.purchase(store.getStock());

	}

	@Override
	public String firstNameInput() {
		Scanner s = new Scanner(System.in);
		String fName;
		boolean valid;
		System.out.println("Please insert your first name");
		fName=s.nextLine();

		valid = isValidName(fName);
		while(!valid) {
			System.out.println("Name must contain only letters! Try again");
			fName=s.nextLine();

			valid = isValidName(fName);
		}


		return fName;
	}
	public String lastNameInput() {
		Scanner s = new Scanner(System.in);
		String lName;
		boolean valid;
		System.out.println("Please insert your last name");	
		lName=s.nextLine();

		valid = isValidName(lName);
		while(!valid) {
			System.out.println("Name must contain only letters! Try again");
			lName=s.nextLine();

			valid = isValidName(lName);
		}
		return lName;


	}



	@Override
	public String userNameInput() {
		Scanner s = new Scanner(System.in);
		String uName;
		System.out.println("Please insert user name");

		uName=s.nextLine();

		boolean exist = isExist(uName);
		while (exist) {
			System.out.println("User name already exist, please choose other user name");
			uName=s.nextLine();

			exist = isExist(uName);
		}


		return uName;

	}


	@Override
	public boolean isValidPass(String p) {
		if (p.length()>=6)
			return true;
		else return false;
	}

	@Override
	public boolean isExist(String userName) {

		HashSet<String> userNames = new HashSet<>();
		if(!store.getCustomers().isEmpty()) {
			for (Customer i : store.getCustomers()) {
				userNames.add(i.getUserName());	
			}
		}
		if(userNames.contains(userName)) return true;
		return false;
	}

	@Override
	public boolean isValidName(String n) {

		for (int i=0;i<n.length();i++) {
			if (Character.isDigit((n.charAt(i)))) {
				return false;
			}
		}
		return true;

	}

	@Override
	public String passwordInput() {
		Scanner s = new Scanner(System.in);
		String password;
		System.out.println("Please insert password (minimum 6 characters required)");

		password=s.nextLine();

		boolean isSafePass=isValidPass(password);
		while (!isSafePass) {
			System.out.println("Password too short, insert password with 6 characters at least!");
			password=s.nextLine();

			isSafePass=isValidPass(password);


		}

		return password;
	}

}
