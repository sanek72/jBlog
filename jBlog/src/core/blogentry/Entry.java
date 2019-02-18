package core.blogentry;

public class Entry {
	
	private int id;
	private String type;
	private String subtype;
	private boolean show;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSubtype() {
		return subtype;
	}

	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String toString(){
		String s = "id = "  + id + "\n" 
	+ "type = " + type + "\n"
	+ "subtype = " + subtype + "\n"
	+ "show = " + show + "\n"
	+ "name = " + name;
		return s;
	}

}
