package model.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Set;

import application.UI;
import model.entities.Agency;
import model.excpetion.InvalidCPFExcpetion;
import model.excpetion.InvalidDataException;
import model.excpetion.InvalidNameException;
import model.excpetion.InvalidOperationException;
import model.excpetion.InvalidPhoneNumberException;

public abstract class ValidationService {
	protected static String validatePassword(String passwordString){
		if((passwordString.length() > 4 || passwordString.length() < 4) && !OtherService.isNumber(passwordString)) {
			throw new IllegalArgumentException("\t\t\t\tError: " + UI.ANSI_RED + "The password must contain 4 digits only and caracters different from numbers are not allowed!" + UI.ANSI_RESET);
		}if(passwordString.length() > 4 || passwordString.length() < 4) {
			throw new IllegalArgumentException("\t\t\t\tError: " + UI.ANSI_RED + "The password must contain 4 digits only." + UI.ANSI_RESET);
		}if(!OtherService.isNumber(passwordString)) {
			throw new NumberFormatException("\t\t\t\tError: " + UI.ANSI_RED + "Caracters different from numbers are not allowed!" + UI.ANSI_RESET);
		}
		return passwordString;
	}
	
	// Soon I'll remove this returning Strings and put the try-catch.
	public static void validateCPF(String cpf) throws InvalidCPFExcpetion {
		if(!cpf.matches("^\\d{3}[.]?\\d{3}[.]?\\d{3}[-]?\\d{2}$")) { //This I tried hard to do and that 'validatePhoneNumber'
			throw new InvalidCPFExcpetion("Invalid CPF. Example: 123.456.789-00"); 
		}
	}
	
	protected static void validateName(String name) throws InvalidNameException {
		if(!name.matches("^(?:[\\p{Lu}&&[\\p{IsLatin}]])(?:(?:')?" //I just copied this from stackoverflow. I couldn't do all
				+ "(?:[\\p{Ll}&&[\\p{IsLatin}]]))+(?:\\-(?:[\\p{Lu}&&[\\p{IsLatin}]])(?:(?:')?(?:[\\p{Ll}&&" //this.
				+ "[\\p{IsLatin}]]))+)*(?: (?:(?:e|y|de(?:(?: la| las| lo| los))?|do|dos|da|das|del|van|von|bin|le) )?" 
				+ "(?:(?:(?:d'|D'|O'|Mc|Mac|al\\-))?(?:[\\p{Lu}&&[\\p{IsLatin}]])(?:(?:')?(?:[\\p{Ll}&&" 
				+ "[\\p{IsLatin}]]))+|(?:[\\p{Lu}&&[\\p{IsLatin}]])(?:(?:')?(?:[\\p{Ll}&&[\\p{IsLatin}]]))+(?:\\-"
				+ "(?:[\\p{Lu}&&[\\p{IsLatin}]])(?:(?:')?(?:[\\p{Ll}&&[\\p{IsLatin}]]))+)*))+(?: (?:Jr\\.|II|III|IV))?$")) {
			throw new InvalidNameException("Invalid Name. Make sure you enter the first and last name correctly. ");
		}if(name.length() <= 0) {
			throw new InvalidNameException("The name cannot be blank!!");
		}
	}
	
	protected static void validatePhoneNumber(String phoneNumber) throws InvalidPhoneNumberException{
		if(!phoneNumber.matches("^[(]?[1-9]{2}[)]? ?(?:[2-8]|9[\\d])\\d{3}[-]?\\d{4}$") 
			|| phoneNumber.isBlank() || phoneNumber.isEmpty()){
			throw new InvalidPhoneNumberException("Invalid Phone number. Example: 31 95555-5555");
	 	}
	}
	
	public static boolean validateAgency(File agencyFile, Agency agency) throws InvalidDataException, FileNotFoundException, InvalidOperationException {
		if(agency.getAgencyAddress().startsWith("[0-9]")) {
			throw new IllegalArgumentException("You can't start a adress with numbers!!");
		}else if(agency.getAgencyAddress().isBlank() || agency.getAgencyAddress().isEmpty()) {   
			throw new InvalidDataException("Invalid Adress");
		}
		Set<Agency> agencys = OtherService.loadAgencyList(agencyFile);
		for(Agency ag : agencys) {
			if(ag.getAgencyCode().equals(agency.getAgencyCode()) || ag.getAgencyAddress().equals(agency.getAgencyAddress())) {
				throw new InvalidOperationException("Cannot register an agency on the same address already!");
			}
		}
		return true;
	}
}