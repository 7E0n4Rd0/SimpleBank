package model.services;

import java.util.Scanner;

import model.entities.Agency;
import model.excpetion.InvalidDataException;

public abstract class OtherService {
	public static void login(Agency agency) {
		Scanner loginInput = new Scanner(System.in);
		System.out.print("Enter the account number: ");
		int numberAcc = loginInput.nextInt();
		System.out.print("Enter the password: ");
		loginInput.nextLine();
		String passwordAcc = loginInput.nextLine();
		try {
			ValidationService.validateAccount(agency, numberAcc, passwordAcc);
		}catch(InvalidDataException e) {
			System.out.println("Error: " + e.getMessage());
		}
		loginInput.close();
	}
	public static boolean isNumber(String string){
		try {
			Integer.parseInt(string);
		}catch(NumberFormatException e) {
			return false;
		}
		return true;
	}
}