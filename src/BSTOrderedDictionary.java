import java.util.ArrayList;

public class BSTOrderedDictionary implements BSTOrderedDictionaryADT{
	BSTNode root;
	int numInternalNodes;
	
	//Constructor creates empty ordered dictionary with 1 leaf node and no internal nodes
	public BSTOrderedDictionary() {
		this.root = new BSTNode();
		numInternalNodes = 0;
	}
	
	//return root of tree
	public BSTNode getRoot() {
		return root;
	}
	
	//return number of internal nodes
	public int getNumInternalNodes() {
		return numInternalNodes;
	}
	
	//private get method to return node
	private BSTNode getNode(BSTNode r, String key) {
		//return null if node is leaf and not key
		if (r.getData() == null) {
			return null;
		}
		else if (r.isLeaf() && !r.getData().getName().equals(key)) {
			return null;
		}
		//return node if it holds key value
		else if (key.equals(r.getData().getName())) {
			return r;
		}
		//get left child node if r key is greater than given key
		else if (r.getData().getName().compareTo(key) > 0 && r.getLeftChild() != null) {
			return getNode(r.getLeftChild(), key);
		}
		//get right child node if r key is less than given key
		else if (r.getData().getName().compareTo(key) < 0 && r.getRightChild() != null) {
			return getNode(r.getRightChild(), key);
		}
		else {
			return null;
		}
	}
	
	//gets node in tree with given key
	public ArrayList<MultimediaItem> get (BSTNode r, String key){
		BSTNode p = getNode(r, key);
		//return p array if p with given key exists
		if (p!=null) {
			return p.getData().getMedia();
		}
		//otherwise return null
		else {
			return null;
		}
	}
	
	private void addNode(BSTNode r, String key, MultimediaItem m) {
		//base case
		if (r.getData() == null) {
			r.setData(new NodeData(key));
			r.getData().add(m);
			//increase number of internal nodes counter
			numInternalNodes++;
		}
		//create comparison to key
		int comp = r.getData().getName().compareTo(key);
		//if r key is greater than given key
		if (comp > 0) {
			//if left child exists
			if (r.getLeftChild() != null) {
				addNode(r.getLeftChild(), key, m);
			}
			//if left child is null
			else {
				//create new node
				NodeData n = new NodeData(key);
				n.add(m);
				r.setLeftChild(new BSTNode(r, null, null, n));
				//increase number of internal nodes counter
				numInternalNodes++;
			}
		}
		//if r key is less than given key and right child is null
		if (comp < 0) {
			//If right child exists
			if (r.getRightChild() != null) {
				addNode(r.getRightChild(), key, m);
			}
			else {
				//create new node
				NodeData n = new NodeData(key);
				n.add(m);
				r.setRightChild(new BSTNode(r, null, null, n));
				//increase number of internal nodes counter
				numInternalNodes++;
			}
		}
	}
	
	//puts objects with specified characteristics in node in tree with key
	public void put (BSTNode r, String key, String content, int type) {
		//create multimedia item with specified characteristics
		BSTNode p = getNode(r, key);
		//The key is found in tree
		if (p != null) {
			//create new multimediaitem object
			MultimediaItem newData = new MultimediaItem(content, type);
			//add data to array list
			p.getData().add(newData);
		}
		//The key is not found in tree
		else {
			//create new multimediaitem object
			MultimediaItem newData = new MultimediaItem(content, type);
			//add new node to tree
			addNode(r, key, newData);
		}
	}
	
	//removes node with key if found
	private void removeNode(BSTNode p) throws DictionaryException{
		//node with key not found, throw exception
		if (p == null) {
			throw new DictionaryException("No key to remove");
		}
		//node found
		else {
			//decrement number of internal nodes counter for all instances where node is found
			numInternalNodes--;
			//if p is root
			if (p.getParent() == null) {
				//if p has left child
				if (p.getLeftChild() != null) {
					//set root as left child
					root.setData(p.getLeftChild().getData());
					//set right child to child of largest node
					largestNode(root).setRightChild(p.getRightChild());
				}
				else {
					//set root data to the data of right child of p
					root.setData(p.getRightChild().getData());
				}
			}
			//if p is right child
			else if (p.getParent().getRightChild() == p) {
				//if left child exists
				if (p.getLeftChild() != null) {
					//set left child to node
					p.getParent().setRightChild(p.getLeftChild());
					//set right child to child of largest node
					largestNode(p.getLeftChild()).setRightChild(p.getRightChild());
				}
				else {
					//set right child of parent as right child of p
					p.getParent().setRightChild(p.getRightChild());
				}
			}
			//if p is left child
			else if (p.getParent().getLeftChild() == p) {
				//if left child exists
				if (p.getLeftChild() != null) {
					//set left child to node
					p.getParent().setLeftChild(p.getLeftChild());
					//set right child to child of largest node
					largestNode(p.getLeftChild()).setRightChild(p.getRightChild());
				}
				else {
					//set left child of parent as right child of p
					p.getParent().setLeftChild(p.getRightChild());
				}
			}
		}
	}
	
	//removes node with key if found
	public void remove(BSTNode r, String key) throws DictionaryException{
		BSTNode p = getNode(r, key);
		//node with key not found, throw exception
		if (p == null) {
			throw new DictionaryException("No key to remove");
		}
		//node found
		else {
			//remove entire node, reset r
			removeNode(p);
			r = this.getRoot();
		}
	}
	
	//remove specified multimedia objects in tree with key
	public void remove(BSTNode r, String key, int type) throws DictionaryException{
		BSTNode p = getNode(r, key);
		//if no key in tree, throw exception
		if (p == null) {
			throw new DictionaryException("No key to remove");
		}
		//if key is in tree
		else {
			ArrayList<MultimediaItem> list = p.getData().getMedia();
			//Create list to collect items to be removed
			ArrayList<MultimediaItem> toRemoveList = new ArrayList<MultimediaItem>();
			//Iterate through all list items
			for (MultimediaItem item : list) {
				if (type == item.getType()) {
					toRemoveList.add(item);
				}
			}
			//Remove all items in remove list to list
			list.removeAll(toRemoveList);
			
			if (list.size() == 0) {
				removeNode(p);
			}
		}
	}
	
	//finds node with largest key
	private BSTNode smallestNode(BSTNode r){
		//throw new DictionaryException("No key to remove");
		//find the leftmost child
		while (r.getLeftChild() != null) {
			r = r.getLeftChild();
		}
		
		return r;
	}
	
	//finds node with smallest key
	public NodeData smallest(BSTNode r) {
		return smallestNode(r).getData();
	}
	
	//finds node with largest key
	private BSTNode largestNode(BSTNode r){
		//throw new DictionaryException("No key to remove");
		while (r.getRightChild() != null) {
			r = r.getRightChild();
		}
		return r;
	}
	
	//finds node with largest key
	public NodeData largest(BSTNode r){ // throws DictionaryException{
		//throw new DictionaryException("No key to remove");
		return largestNode(r).getData();
	}

	
	//returns successor of specified key if available
	public NodeData successor (BSTNode r, String key) {
		BSTNode p = getNode(r, key);
		//if there is no right child
		if (p.getRightChild() == null) {
			//if node with given key is the largest node in tree, return null
			if (largestNode(r) == p) {
				return null;
			}
			else {
				BSTNode parent = p.getParent();
				//for when the specified point is the right child of parent
				while (parent != null && p == parent.getRightChild()) {
					p = parent;
					parent = parent.getParent();
				}
				return parent.getData();
			}
		}
		//if node with key has a right child
		else {
			//find the smallest value in right child
			return smallest(p.getRightChild());
		}
	}
	//returns successor of specified key if available
	private BSTNode successorNode (BSTNode r, String key) {
		BSTNode p = getNode(r, key);
		//if there is no right child
		if (p.getRightChild() == null) {
			//if node with given key is the largest node in tree, return null
			if (largestNode(r) == p) {
				return null;
			}
			else {
				BSTNode parent = p.getParent();
				//for when the specified point is the right child of parent
				while (parent != null && p == parent.getRightChild()) {
					p = parent;
					parent = parent.getParent();
				}
				return parent;
			}
		}
		//if node with key has a right child
		else {
			//find the smallest value in right child
			return smallestNode(p.getRightChild());
		}
	}
	
	//returns predecessor of specified key if available
	public NodeData predecessor (BSTNode r, String key) {
		BSTNode p = getNode(r, key);
		//if there is no left child
		if (p.getLeftChild() == null) {
			//if node with given key is the smallest node in tree, return null
			if (smallestNode(r) == p) {
				return null;
			}
			else {
				BSTNode parent = p.getParent();
				//for when the specified point is the left child of parent
				while (parent != null && p == parent.getLeftChild()) {
					p = parent;
					parent = parent.getParent();
				}
				return parent.getData();
			}
		}
		//if node with key has left chidl
		else {
			//find the largest child of left child
			return largest(p.getLeftChild());
		}
	}
}