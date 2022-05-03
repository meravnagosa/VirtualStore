import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class EmployeeMenu implements User {
	Store store;
	EmployeeMenu(Store store){
		this.store = store;
	}

	@Override
	public void createAccount() {
		Scanner s = new Scanner(System.in);
		String fName = firstNameInput();
		String lName = lastNameInput();
		String uName = userNameInput();
		String password = passwordInput();
		Employee employee;
		System.out.println("What is your rank? Enter 1 for Regular Employee,"
				+ "2 for Manager and 3 for Board Member");
		int rank=s.nextInt();
		s.nextLine();
		while (rank!=1&&rank!=2&&rank!=3) {
			System.out.println("Please enter only 1,2,3");
			rank=s.nextInt();
			s.nextLine();
		}
		if (rank==1) {
			employee=new Employee(uName,password,fName,lName,role.Regular_Employee);
		}
		else if (rank==2) {
			employee=new Employee(uName,password,fName,lName,role.Manager);
		}
		else {
			employee=new Employee(uName,password,fName,lName,role.Board_Member);
		}
		store.addEmployee(employee);
		
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

		for (Employee i : store.getEmployees()) {
			if (i.getUserName().equals(uName)) {
				if(i.getPassword().equals(password)) {
					menuFlow(i);
					break;
				}
			}
			if (count==(store.getEmployees().size())-1) {
				System.out.println("User does not exist");

			}
		}
	
		return;

	}
	@Override
	public void menuFlow(Customer e) {
		Scanner s = new Scanner(System.in);
		System.out.println("Hello "+e.toString());
		System.out.println("Please choose option from the menu:");
		System.out.println("1-Introduce clients list\n2-Introduce only clients with club intership\n" +
				"3-Introdce clients who made at least one purchase\n4-introduce the clients who " +
				"have the highest amount of purchases\n5-Add new product to the store\n6-Change inventory" +
				" status for some product\n7-Make a purchase\n8-Log off");
		int selection;
		if(s.hasNextInt()) 
		{	  
			selection=s.nextInt();
			switch(selection){
			case 1:
				if (!store.getCustomers().isEmpty()) {
					for (Customer i : store.getCustomers()) 
						System.out.println(i.getFirstName()+" "+i.getLastName());
				}
				else 
					System.out.println("There are no clients");
				menuFlow(e);
				break;
			case 2:
				if (!store.getCustomers().isEmpty()) {
					for (Customer i : store.getCustomers()) {
						if (i.isClubMem())
							System.out.println(i.getFirstName()+" "+i.getLastName());
					}			
				}
				menuFlow(e);
				break;
			case 3:
				if (!store.getCustomers().isEmpty()) {
					for (Customer i : store.getCustomers()) {
						if (i.getOverAllpurchases()>1)
							System.out.println(i.getFirstName()+" "+i.getLastName());
					}
				}
				
				menuFlow(e);
				
				break;
			case 4:
				int maxP=0;
				if (!store.getCustomers().isEmpty()) {
					maxP = store.getCustomers().get(0).getOverAllpurchases();
					for (Customer i : store.getCustomers()) {
						if (i.getOverAllpurchases()>maxP)
							System.out.println(i.getFirstName()+" "+i.getLastName());
					}
				}
				menuFlow(e);
				
				break;
			case 5:
				System.out.println("Enter description of the product");
				String description=s.nextLine();
				System.out.println("Enter price");
				float price=s.nextInt();
				s.nextLine();
				System.out.println("Enter club member discount");
				int discount=s.nextInt(); s.nextLine();
				store.addProduct(new Product(description,price,discount));
				menuFlow(e);
				break;
			case 6:
				store.updateStatus();
				menuFlow(e);
				break;
			case 7:
				e.purchase(store.getStock());
				menuFlow(e);
				break;
			case 8:
			return;
			}
		}
		
	}

	@Override
	public String firstNameInput() {
		Scanner s = new Scanner(System.in);
		String name;
		boolean valid;
		System.out.println("Please insert your first name");
		name=s.nextLine();
		valid = isValidName(name);
		while(!valid) {
			System.out.println("Name must contain only letters! Try again");
			name=s.nextLine();
			valid = isValidName(name);
		}
		
		return name;
	}
	@Override
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

	@Override
	public boolean isValidPass(String n) {
		if (n.length()>=6)
			return true;
		else return false;
	}

	@Override
	public boolean isExist(String userName) {

		HashSet<String> userNames = new HashSet<>();
		if(!store.getEmployees().isEmpty()) {
			for (Employee i : store.getEmployees()) {
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


}
