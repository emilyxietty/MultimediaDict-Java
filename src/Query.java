import java.util.*;
import java.io.*;
import java.io.FileReader.*;
import java.util.Stack.*;
import java.io.BufferedReader.*;

public class Query {
	public static void main(String[] args) throws FileNotFoundException{
		BSTOrderedDictionary newDict = new BSTOrderedDictionary();
		Scanner scanner = null;
		String file = args[0];
		File fileContent = new File(file);
		int type;
		BSTNode root = newDict.getRoot();
		ArrayList <MultimediaItem> list;
		
		ShowHTML htmlViewer = new ShowHTML();
		SoundPlayer player = new SoundPlayer();
		PictureViewer picViewer = new PictureViewer();
		
		//check scanner
		try {
			scanner = new Scanner(fileContent);
		}
		catch (FileNotFoundException e){
			e.printStackTrace();
		}
		while (scanner.hasNextLine()) {
            String key = scanner.nextLine();
            String value = scanner.nextLine();
            System.out.println(key);
            System.out.println(value);
            String[] split = value.split("\\.");
            if (split.length != 2) {
            	type = 1;
            }
            else {
	            String x = split[0];
	            String y = split[1];
	            if (y.equals("wav") || y.equals("mid")) {
	    			type = 2;
	            }
	            else if (y.equals("jpg")  || y.equals("gif")) {
	    			type = 3;
	    		}
	            else if (y.equals("html")) {
	    			type = 4;
	            }
	            else {
	            	type = 1;
	            }
            }
            newDict.put(root, key, value, type);
        }
		scanner.close();
		
		//user commands
		StringReader keyboard = new StringReader();
		boolean notEnd = true;
		while (notEnd) {
			String line = keyboard.read("Enter next command: ");
			String[] commandSplit = line.split(" ");
			if (commandSplit[0].equals("first")) {
				NodeData first = newDict.smallest(root);
				if (first == null) {
					System.out.println("The ordered dictionary is empty");
				}
				else {
					System.out.println(first.getName());
				}
			}
			else if (commandSplit[0].equals("last")) {
				NodeData last = newDict.largest(root);
				if (last == null) {
					System.out.println("The ordered dictionary is empty");
				}
				else {
					System.out.println(last.getName());
				}
			}
			else if (commandSplit[0].equals("size")) {
				int size = newDict.getNumInternalNodes();
				System.out.println("There are "+size+" keys in the ordered dictionary");
			}
			else if (commandSplit[0].equals("end")) {
				notEnd = false;
			}
			else {
				System.out.println("Invalid command");
			}
		}
	}
}