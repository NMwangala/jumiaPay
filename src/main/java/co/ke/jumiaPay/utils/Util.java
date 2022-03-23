package co.ke.jumiaPay.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

	private static Pattern pattern;
	private static Matcher matcher;

	public static String isValidPhoneNumber(String phoneNumber) {
		// Phone validation
		String verificationRegex ="";
		
		switch (phoneNumber.substring(1, 4)) {
		case "237":
			verificationRegex =AppConstants.CAMEROON_VERIFICATION;
			break;
		case "251":
			verificationRegex = AppConstants.ETHIOPIA_VERIFICATION;
			break;
		case "212":
			verificationRegex = AppConstants.MOROCCO_VERIFICATION;
			break;
		case "258":
			verificationRegex = AppConstants.MOZAMBIQUE_VERIFICATION;
			break;
		case "256":
			verificationRegex = AppConstants.UGANDA_VERIFICATION;
			break;
		default:	
			verificationRegex = "";
			break;
		}
		pattern = Pattern.compile(verificationRegex);
		matcher = pattern.matcher(phoneNumber);
		boolean isPhoneNumberValid = matcher.matches();

		if (!isPhoneNumberValid) {

			return "Invalid";
		}
		return "Valid";
	}

	public static String getCountryName(String phoneNumber) {
		String country = "";
		switch (phoneNumber.substring(1, 4)) {
		case "237":
			country = "Cameroon";
			break;
		case "251":
			country = "Ethiopia";
			break;
		case "212":
			country = "Morocco";
			break;
		case "258":
			country = "Mozambique";
			break;
		case "256":
			country = "Uganda";
			break;
		default:
			
			country = "Unknown Country";
			break;
		}
		return country;

	}

}
