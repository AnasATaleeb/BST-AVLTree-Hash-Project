package application;

public class AVL<T extends Comparable<T>> extends BST<T> {
	
	private TNode rebalance(TNode nodeN) {
		int diff = getHeightDifference(nodeN);
		if (diff > 1) { // addition was in node's left subtree
			if (getHeightDifference(nodeN.left) > 0)
				nodeN = rotateRight(nodeN);
			else
				nodeN = rotateLeftRight(nodeN);
		} else if (diff < -1) { // addition was in node's right subtree
			if (getHeightDifference(nodeN.right) < 0)
				nodeN = rotateLeft(nodeN);
			else
				nodeN = rotateRightLeft(nodeN);
		}
		return nodeN;
	}

	public void insert(T data) {
		if (isEmpty())
			root = new TNode<>(data);
		else {
			TNode rootNode = root;
			addEntry(data, rootNode);
			root = rebalance(rootNode);
		}
	} 

	public void addEntry(T data, TNode rootNode) {
		assert rootNode != null;
		if (data.compareTo((T) rootNode.data) < 0) { // right into left subtree
			if (rootNode.hasLeft()) {
				TNode leftChild = rootNode.left;
				addEntry(data, leftChild);
				rootNode.left = rebalance(leftChild);
			} else
				rootNode.left = new TNode(data);
		} else { // right into right subtree
			if (rootNode.hasRight()) {
				TNode rightChild = rootNode.right;
				addEntry(data, rightChild);
				rootNode.right = rebalance(rightChild);
			} else
				rootNode.right = new TNode(data);
		} 
	}
	 public TNode delete(T data) {
	        TNode temp = super.delete(data);
	        if (temp != null) {
	            TNode rootNode = root;
	            root = rebalance(rootNode);
	        }
	        return temp;
	    }

	public int height(TNode node) {
		if (node == null)
			return 0;
		return 1 + Math.max(height(node.left), height(node.right));
	}

	public int getHeightDifference(TNode root) {
		return height(root.left) - height(root.right);
	}

	private TNode rotateRight(TNode root) {
		TNode c = root.left;
		root.left = c.right;
		c.right = root;
		return c;
	}

	private TNode rotateLeft(TNode root) {
		TNode c = root.right;
		root.right = c.left;
		c.left = root;
		return c;
	}
	
	

	private TNode rotateRightLeft(TNode root) {
		TNode c = root.right;
		root.right = rotateRight(c);
		return rotateLeft(root);
	}

	private TNode rotateLeftRight(TNode root) {
		TNode c = root.left;
		root.left = rotateLeft(c);
		return rotateRight(root);
	}
}
