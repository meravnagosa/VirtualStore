
public class Product {
protected String description;
protected double price;
protected int discountPercent;

public Product(String description, double price, int discountPercent) {
	super();
	this.description = description;
	this.price = price;
	this.discountPercent = discountPercent;	
}

public String toString() {
	return description+" Price: "+price;
}
public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public double getPrice() {
	return price;
}

public void setPrice(double price) {
	this.price = price;
}


}
