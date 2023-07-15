package com.AlphaBook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class AlphaBook_implementation implements AlphaBook{
	Main_AlphaBook home;
	Scanner scan= new Scanner(System.in);

	@Override
	public void Login() {
		System.out.println("*********************WELCOME TO LOGIN PAGE**********************");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn1 =DriverManager.getConnection("jdbc:mysql://localhost:3306/alpha_book","root", "root");
			PreparedStatement ps1=conn1.prepareStatement("select * from alpha_book");
			ResultSet rs=ps1.executeQuery();

			System.out.println("Enter Your Email Id :");
			String email=scan.next();
			Boolean flag=false;
			String pwd="";
			while(rs.next())
			{
				if(email.equals(rs.getString("EmailID")))
				{
					flag=true;
					pwd=rs.getString("PassWord");
					break;
				}
			}
			if(flag){
				System.out.println("Enter Your Password :");
				String password1=scan.next();

				if(pwd.equals(password1)) 
				{
					System.out.println("\n"+"Login successfull......");
					System.out.println("\n************User Details*************");
					System.out.println("\n"+"FirstName :  "+rs.getString("FirstName"));
					System.out.println("LastName  :  "+rs.getString("LastName"));
					System.out.println("ContactNo :  "+rs.getString("ContactNo"));
					System.out.println("Gender    :  "+rs.getString("Gender"));
					System.out.println("DOB       :  "+rs.getString("DOB")+"\n");

					System.out.println("*************************************"+"\n");
					System.out.println("1 Logout\n");
					System.out.println("Enter Your Choice\n");
					int choice=scan.nextInt();

					switch(choice)
					{
					case 1:home.homepage();
					break;
					default:System.out.println("Invalid Choice");
					break;
					}


				}
				else {
					int c=0;
					while(true)
					{

						System.out.println("\n"+"wrong password...!!!");
						System.out.println("Re-Enter password.... ");
						password1=scan.next();
						if(pwd.equals(password1)) 
						{
							System.out.println("\n"+"Login successfull......");
							System.out.println("\n************User Details*************");
							System.out.println("\n"+"FirstName :  "+rs.getString("FirstName"));
							System.out.println("LastName  :  "+rs.getString("LastName"));
							System.out.println("ContactNo :  "+rs.getString("ContactNo"));
							System.out.println("Gender    :  "+rs.getString("Gender"));
							System.out.println("DOB       :  "+rs.getString("DOB")+"\n");

							System.out.println("***************************************"+"\n");
							System.out.println("1 Logout");
							System.out.println("Enter Your Choice");
							int choice=scan.nextInt();

							switch(choice)
							{
							case 1:home.homepage();
							break;
							default:System.out.println("Invalid Choice");
							break;
							}
						}
						else
						{
							c++;
							if(c==2)
							{
								System.out.println("\n"+"Attempts over...!!!");
								break;
							}
						}
					}

					System.out.println("\n1 Forgot password \n2 Login  ");

					System.out.println("Enter Your Choice.....");
					int choice=scan.nextInt();

					switch(choice) {
					case 1:{

						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection conn2 =DriverManager.getConnection("jdbc:mysql://localhost:3306/alpha_book","root", "root");
						PreparedStatement ps2=conn2.prepareStatement("update alpha_book set PassWord=? where EmailID=?");
						System.out.println("Enter Email Id...");
						String Email =scan.next();
						while(true)
						{
							System.out.println("\n"+"Enter New Password...");
							System.out.println("****password must be 8 characters and atleast one special character****"+"\n");
							String N_pwd=scan.next();

							if(check(N_pwd)) 
							{
								ps2.setString(1,N_pwd);
								ps2.setString(2,Email);
								ps2.execute();
								ps2.close();
								System.out.println("Password Updated Successfully...");
								System.out.println("\n1 Homepage \n2 Login");
								System.out.println("Enter choice...");
								int Choice=scan.nextInt();
								switch(Choice)
								{
								case 1:home.homepage();
								break;
								case 2:Login();
								break;
								default:System.out.println("invalid choice.....");
								}
							}
							else {
								System.out.println("Enter Correct Password...!!!!");

							}
						}

					}
					case 2:{Login();}
					break;
					default:System.out.println("invalid choice...");

					}
				}
			}

			else {
				System.out.println("\n"+"User doesn't exist");
				System.out.println("\n"+"Please Sign up to continue....\n");
				home.homepage();
			}

		}
		catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void Signup() {
		System.out.println("*********************WELCOME TO SIGNUP PAGE**********************");
		//Email id Validation........
		System.out.println("Enter Your Email Id : ");
		String email=scan.next();
		Boolean flag=true;
		if(email.length()>10)
		{
			if(email.substring(email.length()-10,email.length()).contains("@gmail.com")) {
				flag=false;
			}
			if(flag) {
				while(true)
				{
					System.out.println("\n"+"Enter Correct Email Id: ");
					String email1=scan.next();
					email=email1;
					if(email.substring(email.length()-10,email.length()).contains("@gmail.com")) {
						break;
					}
				}
			}
		}
		else {
			System.out.println("Enter valid Email Id....!!!\n");
			Signup();

		}

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn1 =DriverManager.getConnection("jdbc:mysql://localhost:3306/alpha_book","root", "root");
			PreparedStatement ps1=conn1.prepareStatement("select * from alpha_book");
			ResultSet rs=ps1.executeQuery();
			while(rs.next()) {

				if(email.equals(rs.getString("EmailID")))
				{
					System.out.println("\n"+"User Already Exist...!!!");
					System.out.println("\n"+"please login to continue...\n");
					Login();
					break;
				}
			}

			//password validation.............
			System.out.println("\n"+"Enter  Password :");
			System.out.println("****password must be 8 characters and atleast one special character****"+"\n");
			String password=scan.next();
			if(check(password)) {
				password=password;
			}
			else {
				while(true)
				{
					System.out.println("\n"+"Re-Enter Password.....");
					System.out.println("\n"+"****password must be 8 characters and atleast one special character****");
					String pwd=scan.next();
					if(check(pwd)) {
						password=pwd;
						break;
					}
				}
			}
			System.out.println("\n"+"Enter Your First Name :");
			String firstname=scan.next();
			System.out.println("\n"+"Enter Your Last Name :");
			String lastname=scan.next();

			//Gender validation.....
			
			System.out.println("\n"+"Select Gender");
			System.out.println("1 Male\n2 Female\n3 other\n");
			int gender_choice=scan.nextInt();
			String Gender=null;
			while(true) {
				
			if(gender_choice>3) {
			
				System.out.println("invalid choice....");
				System.out.println("\n"+"Select Gender");
				System.out.println("1 Male\n2 Female\n3 other\n");
				int choice=scan.nextInt();
				gender_choice=choice;
			}
			else {
				switch(gender_choice)
				{
				case 1:{
					String male="Male";
					Gender=male;}
				break;
				case 2:{String female="Female";
				Gender=female;}
				break;
				case 3:{String Other="Other";
				Gender=Other;}
				break;
				default:System.out.println("invalid choice....");
				}
				break;
			}
			
			
			}
		
			//contact number validation.......
			System.out.println("\n"+"Enter 10-digit Contact Number :");
			String c_no=scan.next();
			while(true) {
				if(c_no.length()!=10){
					
						System.out.println("\n"+"Enter valid Number...");
						String cn=scan.next();
						c_no=cn;
					
				}
				else if(!(c_no.charAt(0)=='6'||c_no.charAt(0)=='7'||c_no.charAt(0)=='8'||c_no.charAt(0)=='9')) {
					System.out.println("\n"+"Enter valid Number...");
					String cn=scan.next();
					c_no=cn;
				}
				
				else {
					break;
				}
			}
			System.out.println("\n"+"Enter Your Date of Birth :"+"\n");
			String date= scan.next();
			System.out.println("1 Submit\n2 Cancel \n3 Back");
			System.out.println("\n"+"Enter Your Choice :");
			int choice2=scan.nextInt();
			switch(choice2) {
			case 1:{
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn2 =DriverManager.getConnection("jdbc:mysql://localhost:3306/alpha_book","root", "root");
				PreparedStatement ps=conn2.prepareStatement("insert into alpha_book values(?,?,?,?,?,?,?)");
				ps.setString(1, email);
				ps.setString(2, password);
				ps.setString(3, firstname);
				ps.setString(4, lastname);
				ps.setString(5, Gender);
				ps.setString(6, c_no);
				ps.setString(7, date);
				ps.execute();
				conn2.close();
				ps.close();
				System.out.println("Account Created succefully..... ");
				break;

			}

			case 2: Signup();
			break;
			case 3: home.homepage();
			break;
			default :System.out.println("Invalid choice....!!!!"); 
			}

		}  
		catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void Exit() {
		System.out.println("Thank You for using Alphabook...");
		System.exit(0);

	}
	public Boolean check(String pwd) {
		int spl=0;
		for(int i=0;i<pwd.length();i++) 
		{
			if(pwd.length()<8) 
			{
				return false;
			}

			if(!(pwd.charAt(i)>=65 && pwd.charAt(i)<=90
					|| pwd.charAt(i)>=97 && pwd.charAt(i)<=122
					|| pwd.charAt(i)>=48 && pwd.charAt(i)<=57))
			{
				spl++; 
			}
		}
		if(spl!=1)
		{

			return false;
		}
		return true;
	}

}
class AlphaBook_details {

	public AlphaBook details()
	{
		AlphaBook ab=new AlphaBook_implementation();
		return  ab;
	}
}
