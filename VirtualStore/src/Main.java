import java.util.ArrayList;
import java.util.Scanner;


public class Main {
	public static  Scanner s = new Scanner(System.in);
	static Store store = new Store();

	public static void main(String [] args) {
		mainMenu();	
	}
	public static void mainMenu(){
		EmployeeMenu eMenu = new EmployeeMenu(store);
		CustomerMenu cMenu = new CustomerMenu(store);
		int userKind;
		System.out.println("Hello and welcome to our store! Please choose one of the following options:"
				+"\n1 - Create account\n2 - Sign in\n3 - Exit");
		int choice=s.nextInt();
		s.nextLine();
		switch(choice) {
		case 1: 
			System.out.println("Are you a customer or an employee? Choose 1 for customer, 2 for employee");
			userKind = s.nextInt();
			s.nextLine();
			if (userKind==1) {
				cMenu.createAccount();
				mainMenu();
				}
			if (userKind==2) {
			eMenu.createAccount();
			}
			if (userKind!=1&&userKind!=2) {
				System.out.println("Wrong choice!");
				mainMenu();
			}
			break;
		case 2: 
			System.out.println("Are you a customer or an employee? Choose 1 for customer, 2 for employee");
			userKind = s.nextInt();
			s.nextLine();
			if (userKind==1) {
				cMenu.logIn(); 
				mainMenu();
			}
			if (userKind==2) eMenu.logIn();
			if (userKind!=1&&userKind!=2) {
				System.out.println("Wrong choice!");
				
			}
			
			break;

		case 3:
			System.out.println("Bye");
		break;
		default: System.out.println("Invalid choice");
		}
	}
	

}
