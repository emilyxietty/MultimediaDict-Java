public class BSTNode {
	private BSTNode parent;
	private BSTNode leftChild;
	private BSTNode rightChild;
	private NodeData data;
	
	//Constructor created with null instance variables
	public BSTNode(){
		this.leftChild = null;
		this.rightChild = null;
	}
	
	//Constructor created with specified instance variables
	public BSTNode(BSTNode newParent, BSTNode newLeftChild, BSTNode newRightChild, NodeData newData){
		this.parent = newParent;
		this.leftChild = newLeftChild;
		this.rightChild = newRightChild;
		this.data = newData;
	}
	
	//reference parent of node
	public BSTNode getParent() {
		return parent;
	}
	
	//reference left child of node
	public BSTNode getLeftChild() {
		return leftChild;
	}
	
	//reference right child of node
	public BSTNode getRightChild(){
		return rightChild;
	}
	
	//return NodeData stored in node
	public NodeData getData() {
		return data;
	}
	
	//sets parent of node to specified value
	public void setParent(BSTNode newParent){
		parent = newParent;
	}
	
	//sets left child of node to specified value
	public void setLeftChild(BSTNode newLeftChild){
		leftChild = newLeftChild;
	}
	
	//sets right child of node to specified value
	public void setRightChild(BSTNode newrightChild){
		rightChild = newrightChild;
	}
	
	//stores given NodeData object into node 
	public void setData(NodeData newData) {
		data = newData;
	}
	
	//determines if node is a leaf
	boolean isLeaf() {
		//both children are null
		if (leftChild == null && rightChild == null) {
			return true;
		}
		else {
			return false;
		}
	}
}
