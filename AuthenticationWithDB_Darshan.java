package miniproject;
import java.util.Scanner;
import java.sql.*;
public class AuthenticationWithDB_Darshan 
{
	public static void main(String args[]) throws ClassNotFoundException, SQLException
	{
		int choice;
		long phno;
		String name,emailid ,password ,address,cpassword ,query;
	    Scanner sc=new Scanner(System.in);
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ authenticationwithdb_darshan", "root", "darshan3009");
		do
		{	
		System.out.println("---AUTHENTICATION SYSTEM---");
		System.out.println("1.Registration\n2.Login\n3.Exist");
		System.out.println("Enter your choice[1-3]: ");
       choice = sc.nextInt();
        if(choice == 1 )
        {
        	query = "insert into user(name,emailid,phno,address,password)values (?,?,?,?,?)";
        	System.out.println("User Registration:");
            System.out.print("Enter your name: ");
            name = sc.next();
            System.out.println("Enter your emailid: ");
            emailid= sc.next();
            System.out.println("Enter your phno: ");
            phno = sc.nextLong();
            System.out.println("Enter your address: ");
            address = sc.next();
            System.out.print("Enter password: ");
            password = sc.next();
            System.out.print("Confirm password: ");
            cpassword = sc.next();
        if(password.equals(cpassword))
        {
        	PreparedStatement p = conn.prepareStatement(query);
        	p.setString(1 , name);
        	p.setString(2, emailid);
        	p.setLong(3, phno);
        	p.setString(4, address);
        	p.setString(5, password);
        	
        	int i = p.executeUpdate();
        	if(i>0)
        	{
        		System.out.println("Registration Successful..");
        	}
        	else
        	{
        		System.out.println("Registration Failed..");
        	}
      
        }
        else
        {
       	 System.out.println("Password Does'nt Match");
        }
        
      } 
      if(choice == 2 )
      {
    	System.out.println("User Login:");
  		System.out.print("Enter your emailid: ");
  		emailid = sc.next();
  		System.out.print("Enter your Password: ");
  		password = sc.next();
  		query = "select Password from User where emailid=?";
  		PreparedStatement loginStmt = conn.prepareStatement(query);
  		loginStmt.setString(1, emailid);
  		ResultSet rs = loginStmt.executeQuery();

  		if (rs.next())
  		{
  			String npassword = rs.getString("password");
  			if (password.equals(npassword)) 
  			{
  				System.out.println("Login Successful..");
  			} 
  			else 
  			{
  				System.out.println("Incorrect Password..");
  			}
  			} 
  			else
	  		{
	  			System.out.println("User not found..");
	  		}

      	} 
      if(choice == 3)
      {
    	  System.out.println("Thank you..");
    	  System.out.println("Please enter a choice between 1 and 3.");
      }
        	   	
		}while(choice!=3);
	}
}