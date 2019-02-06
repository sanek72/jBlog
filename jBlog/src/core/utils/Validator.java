package core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
	
	public static boolean  loginValid(String s){
		if(s == null){
			return false;
		}
			if(s.indexOf("admin") != - 1) {
				return false;
		}
		Pattern pattern = Pattern.compile("^[a-z0-9admin]{2,16}+", Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(s.trim());
	    return matcher.matches();
	}	
	
	public static boolean  passwordValid(String s){
		if(s == null){
			return false;
		}		
		//Pattern pattern = Pattern.compile("((?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})");
		Pattern pattern = Pattern.compile("^[a-z0-9!@#$%^&*()_+<>]{6,20}+$", Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(s.trim());
	    return matcher.matches();
	}		

	public static boolean emailValid(String s) {
		if(s == null){
			return false;
		}		
		Pattern pattern = Pattern.compile("^([a-z0-9_\\.-]+)@([a-z0-9_\\.-]+)\\.([a-z\\.]{2,6})$",
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(s.trim());
		return matcher.matches();
	}

}
