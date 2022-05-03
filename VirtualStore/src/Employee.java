import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

enum role{
	Regular_Employee,
	Manager,
	Board_Member
}

public class Employee extends Customer{
	public final double THIRTY_PERCENT = 0.3;
	public final double TWENTY_PERCENT = 0.2;
	public final double TEN_PERCENT = 0.1;
	role rank;

	public Employee(String userName, String password, String firstName, String lastName, role rank) {
		super(userName, password, firstName, lastName);
		this.rank = rank;
	}
	public role getRank() {
		return rank;
	}
	public String getPassword() {
		return password;
	}

	public void setRank(role rank) {
		this.rank = rank;
	}

	public String toString() {
		return ("Hello "+this.firstName+" "+this.lastName+" "+rank);
	}

	public void printCart() {
		System.out.println("Your cart so far:");
		double sum=0;
		for (Product i : myCart) {
			System.out.println(i.toString());
			sum+=i.getPrice();
		}	
		
		if (this.rank.equals(role.Board_Member)) {
			sum =sum-(sum*THIRTY_PERCENT);
		}
		if (this.rank.equals(role.Manager)) {
			sum =sum-(sum*TWENTY_PERCENT);
		}
		if (this.rank.equals(role.Regular_Employee)) {
			sum=sum-(sum*TEN_PERCENT);
		}	
		System.out.println("Total Price: "+sum+" NIS");
	}




}
