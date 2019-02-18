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
	
//	public static int getInternetExplorerVersion(String userAgent) {
//		String version = null;
//		int i = userAgent.indexOf("MSIE");
//
//		if (i == -1) {
//			return -1;
//		}
//		if (userAgent.charAt(i + 6) == '.') {
//			version = userAgent.substring(i + 5, i + 6);
//		} else {
//			version = userAgent.substring(i + 5, i + 7);
//		}
//		if (!isNumber(version)) {
//			return -1;
//		}
//		return Integer.parseInt(version);
//	}

//	public static boolean isNumber(String str) {
//		if (str == null || str.isEmpty()){
//			return false;
//		}
//		for (int i = 0; i < str.length(); i++) {
//			if (!Character.isDigit(str.charAt(i))){
//				return false;
//			}
//		}
//		return true;
//	}		

}
