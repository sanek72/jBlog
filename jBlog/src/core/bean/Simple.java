package core.bean;

import java.io.Serializable;

public class Simple implements Serializable {
	
	private static final long serialVersionUID = -5747664918134235035L;
	
	private String message;	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
