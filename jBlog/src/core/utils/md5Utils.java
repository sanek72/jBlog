package core.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class md5Utils {

	public static String md5Apache(String password) {
		return DigestUtils.md5Hex(password);
	}
	
	public boolean check_md5Passwords(String password1, String password2){
		return password1.equals(password2);
	}
}
