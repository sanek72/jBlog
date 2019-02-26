package core.utils;

public class StringUtils {

	public static String passwordGenerator(){
		int passlen = 10;
		String password = "";
		String characters = "!@#$%^&*()_+QWERTYUIOPLKJHGFDSAZXCVBNMqwertyuioplkjhgfdsazxcvbnm1234567890";
		for(int i =0; i <= passlen; i++){
			password += characters.charAt((int) (Math.random() * characters.length()));
		}
		
		return password;
	}
	

}
