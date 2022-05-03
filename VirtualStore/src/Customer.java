import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;


public class Customer {
	protected String userName;
	protected String password;
	protected String firstName;
	protected String lastName;
	protected boolean clubMem;
	protected int overAllpurchases;
	protected ArrayList<Product> myCart;


	public Customer(String userName, String password, String firstName, String lastName) {
		super();
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		//this.clubMem = clubMem;
		this.overAllpurchases=0;
		this.myCart= new ArrayList<>();
	}

	public String toString() {
		if(this.clubMem)
			return ("Hello "+this.firstName+" "+this.lastName+" VIP!");
		else return ("Hello "+this.firstName+" "+this.lastName+"!");
	}

	public HashMap <Integer,Product> getProductList(HashMap<Product,Boolean> stock) {
		HashMap <Integer,Product> printedList=new HashMap<Integer, Product>();
		int productNum=0;
		for (Entry<Product, Boolean> set : stock.entrySet()) {
			if (set.getValue()) {
				printedList.put(productNum++, set.getKey());
			}
			else {
				continue; 
			}
		}	
		return printedList;
	}
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isClubMem() {
		return clubMem;
	}

	public void setClubMem(boolean clubMem) {
		this.clubMem = clubMem;
	}

	public int getOverAllpurchases() {
		return overAllpurchases;
	}

	public void setOverAllpurchases(int overAllpurchases) {
		this.overAllpurchases = overAllpurchases;
	}

	public ArrayList<Product> getMyCart() {
		return myCart;
	}

	public void setMyCart(ArrayList<Product> myCart) {
		this.myCart = myCart;
	}
	
	public void purchase(HashMap<Product,Boolean> stock) {
		HashMap <Integer,Product> printedList = getProductList (stock); // list of products in stock only
		Scanner s = new Scanner(System.in);

		int product;
		System.out.println("Please insert the number of a product you want to buy. For end the purchase insert -1");
		for (Entry<Integer, Product> set :
			printedList.entrySet()) {
			System.out.println(set.getKey() + " = "
					+ set.getValue().toString());
		}
		product=s.nextInt();
		s.nextLine();

		while (product!=-1) {

			if (printedList.containsKey(product)) {
				int amount;
				System.out.println("How many items of this produt would you want?");
				amount = s.nextInt();
				while (amount<1) {
					System.out.println("Plese insert only positive numbers!");
					amount = s.nextInt();
					s.nextLine();
				}
				for (int i = 0; i<amount;i++) {
					this.myCart.add(printedList.get(product));
				}
				printCart();
			}
			else {
				System.out.println("Number not in the list, Try again");	
			}
			System.out.println("Please insert the number of a product you want to buy. For end the purchase insert -1");		
			for (Entry<Integer, Product> set :
				printedList.entrySet()) {
				System.out.println(set.getKey() + " = "
						+ set.getValue().toString());
			}		
			product = s.nextInt();
			s.nextLine();
		}
		if (product==-1) {
			System.out.println("Ending purchase...");
			printCart();
			addPurchace();
			myCart.clear();
		}
		
		return;

	}
	public void printCart() {
		System.out.println("Your cart so far:");
		float sum=0;
		for (Product i : myCart) {
			System.out.println(i.toString());
			sum+=i.getPrice();
		}
		System.out.println("---Total Price: "+sum+" NIS---");
		   
	}
	public void addPurchace() {
		if (!myCart.isEmpty()) {
			overAllpurchases++;
		}
	}


}
