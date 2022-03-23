package co.ke.jumiaPay.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import antlr.Utils;
import co.ke.jumiaPay.utils.AppConstants;
import co.ke.jumiaPay.utils.Util;


public class Tester {
	public static final String PHONE_VERIFICATION = AppConstants.UGANDA_VERIFICATION;

	private static Pattern p;
	private static Matcher m;
	static String phoneNumber ="(256) 911203317";
	public static void main(String[] args) {
		
		System.out.println(new Util().isValidPhoneNumber(phoneNumber));
		System.out.println(Util.getCountryName(phoneNumber));
		//Phone validation
	    p = Pattern.compile(PHONE_VERIFICATION);
	    m = p.matcher(phoneNumber);
	    boolean isPhoneValid = m.matches();

	    if(!isPhoneValid)
	    {
	        System.out.println("The Phone number is NOT valid!");
	        return;
	    }
	    System.out.println("The Phone number is valid!");

	}
	

}
