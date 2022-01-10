import java.util.ArrayList;

public class NodeData {
	String name;
	ArrayList<MultimediaItem> media;
	
	//Constructor creates new NodeData object with attribute and empty media list
	public NodeData(String newName){
		this.name = newName;
		this.media = new ArrayList<MultimediaItem>();
	}
	
	//add new item to media list of object
	public void add(MultimediaItem newItem) {
		media.add(newItem);
	}
	
	//return key name of object
	public String getName() {
		return name;
	}
	
	//return media list in object
	ArrayList<MultimediaItem> getMedia(){
		return media;
	}
}
