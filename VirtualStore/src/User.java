import java.util.ArrayList;
import java.util.HashMap;

public interface User {
	public void createAccount();
	public void logIn();
	public void menuFlow(Customer c);
	public String firstNameInput();
	public String lastNameInput();
	public String userNameInput();
	public String passwordInput();
	public boolean isValidPass(String p);
	public boolean isExist(String userName);
	public boolean isValidName(String n);

}
