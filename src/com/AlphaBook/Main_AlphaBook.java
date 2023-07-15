package com.AlphaBook;

import java.util.Scanner;

public class Main_AlphaBook {

	public static void main(String[] args) {
		homepage();
	}
	
		public static void homepage() {
		
		AlphaBook_details details= new AlphaBook_details();
		AlphaBook A1=details.details();
		
		Scanner scan= new Scanner(System.in);
		System.out.println("******** WELCOME TO ALPHABOOK ********");
		System.out.println("\n1 SignUp \n2 Login\n3 Exit");
		System.out.println("\n"+ "Enter Your Choice :");
	
		int choice=scan.nextInt();
		switch(choice)
		{
		case 1:{
			A1.Signup();
		}
		break;
		case 2:{
			
			A1.Login();
		}
		break;
		case 3:{
			A1.Exit();
		}
		break;
		
		default:
		{
			System.out.println("Invalid Choice");
		}
		}
		
		
				
	}

}
