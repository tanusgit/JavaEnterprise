package basics;
import java.sql.*;
import java.text.NumberFormat;
import java.util.*;
/*
 * we always need to add mysql jar to make the connection with the database
 * To do that right click on folder name -> build path -> custom build->
 * add external mysql jar file -> select jar file from downloads or wherever it is
 * -> then finish
 */
public class Hello {
public static void main(String args[]) throws SQLException, ClassNotFoundException {
	
	double principal = (double) read("principal: ", 0);
	double interest = (double) read("interest: ", 0);
	interest = interest / 100 / 12;
	int years = (int) read("years: ", 0);

	double result = mortgage(interest, principal, years); 
	System.out.println("your mortgage is " + result);
	remainingBalance(principal, interest, years);
	
	
	String Query2 = "insert into calculate values(?,?,?,?)";
	//Class.forName("com.mysql.jdbc.Driver"); this is deprecated
	Class.forName("com.mysql.cj.jdbc.Driver");
	
	Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mysql", "root", "Tanusree1@");
	
	//Statement st = con.createStatement();
	//int count = st.executeUpdate(Query);
	//when multiple values coming from somewhere we use preparesatatement
	PreparedStatement st = con.prepareStatement(Query2);
	
	//setting values in question mark ?
	st.setDouble(1, principal);
	st.setDouble(2, interest);
	st.setInt(3, years);
	st.setDouble(4, result);
	int count = st.executeUpdate();
	System.out.println(count + " row/s affected");
	

	st.close();
	con.close();
	
}

public static double read(String prompt, double min) {
	Scanner scan = new Scanner(System.in);
	double value;
	System.out.println(prompt);
	value = scan.nextDouble();
	boolean enter2 = (value < min);
	while (enter2) {
		System.out.println("Enter a number greater than " + min);
		value = scan.nextInt();
		if (value > min)
			enter2 = false;
	}
	return value;
}

public static void remainingBalance(double principal, double interest,
		int years) {
	double balance;
	int months = years * 12;
	for (int i = 1; i <= months; i++) {
		double r = (1 + interest);
		balance = (principal * ((Math.pow(r, months)) - (Math.pow(r, i))))
				/ ((Math.pow(r, months)) - 1);
		System.out.println("After " + i + " payment your mortgage is"
				+ ": "
				+ (NumberFormat.getCurrencyInstance().format(balance)));

	}
}

public static double mortgage(double principal, double interest,
		int years) {
	double res = principal * interest * years;
	return res;
}
}


