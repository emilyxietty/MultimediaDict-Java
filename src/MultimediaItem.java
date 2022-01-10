public class MultimediaItem {
	String content;
	int type;
	
	//Constructor creates new Multimedia object with specified instance variables
	public MultimediaItem(String newContent, int newType) {
		this.content = newContent;
		this.type = newType;
	}
	
	//returns content of node
	public String getContent() {
		return content;
	}

	//returns type of node
	public int getType() {
		return type;
	}
}