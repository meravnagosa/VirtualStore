import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

public class Store {	
	protected HashMap<Product,Boolean> stock; //false indicates: out of stock, true otherwise
	protected ArrayList<Customer> customers;
	protected ArrayList<Employee> employees;
	public Store() {
		super();
		this.stock = new HashMap<>();
		this.customers = new ArrayList<>();
		this.employees = new ArrayList();
	}
	
	public Store(HashMap<Product, Boolean> stock, ArrayList<Customer> customers, ArrayList<Employee> employees) {
		super();
		this.stock = stock;
		this.customers = customers;
		this.employees = employees;
	}

	public HashMap<Product, Boolean> getStock() {
		return stock;
	}
	public void setStock(HashMap<Product, Boolean> stock) {
		this.stock = stock;
	}
	public ArrayList<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}
	public ArrayList<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(ArrayList<Employee> employees) {
		this.employees = employees;
	}
	public void addProduct(Product p) { 
		if (!stock.containsKey(p)) {
			stock.put(p,true);
			System.out.println("Product has been added");
		}
	}
	public void updateStatus() { 

		HashMap <Integer,Product> printedList=new HashMap<Integer, Product>();
		int productNum=0;
		for (Entry<Product, Boolean> set : stock.entrySet()) {

			if (set.getValue()) {
				printedList.put(productNum++, set.getKey()); 
				System.out.println("Product:" +set.getKey().getDescription()+", Status: In stock");
			}
			else {
				System.out.println("Product: "+set.getKey().getDescription()+", Status: out of stock");; 
			}
		}

		
		
		System.out.println("Please insert the number of a prouct to update status in stock");
		Scanner s = new Scanner(System.in);
		int productNumber = s.nextInt();
	
			int status;
			System.out.println("Does the product is in stock? Insert 1 for YES, 0 for NO");
			status = s.nextInt();
			if (status == 1) { stock.put(printedList.get(productNumber), true); }
			else if(status==0) {stock.put(printedList.get(productNumber), false);}
			else {System.out.println("Wrong choice!");}
		
		s.close();
	}
	
	public void addCustomer(Customer c) {
		customers.add(c);
	}
	public void addEmployee(Employee e) {
		employees.add(e);
	}

	public void printStock() {
		
	}

}

