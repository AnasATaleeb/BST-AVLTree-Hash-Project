package application;

public class BST<T extends Comparable<T>> {

	protected TNode<T> root;

	public BST() {
	}

	public T find(T data) {
		return find(data, root);
	}

	public T find(T data, TNode node) {
		if (node != null) {
			int comp = node.data.compareTo(data);
			if (comp == 0)
				return (T) node.getData();
			else if (comp > 0 && node.hasLeft())
				return find(data, node.left);
			else if (comp < 0 && node.hasRight())
				return find(data, node.right);
		}
		return null;
	}

	public TNode largest() {
		return largest(root);
	}

	public TNode<T> largest(TNode node) {
		if (node != null) {
			if (!node.hasRight())
				return (node);
			return largest(node.right);
		}
		return null;
	}

	public TNode smallest() {
		return smallest(root);
	}

	public TNode<T> smallest(TNode node) {
		if (node != null) {
			if (!node.hasLeft())
				return (node);
			return smallest(node.left);
		}
		return null;
	}

	public int height() {
		return height(root);
	}

	public int height(TNode node) {
		if (node == null)
			return 0;
		if (node.isLeaf())
			return 1;
		int left = 0;
		int right = 0;
		if (node.hasLeft())
			left = height(node.left);
		if (node.hasRight())
			right = height(node.right);
		return (left > right) ? (left + 1) : (right + 1);
	}

	public void insert(T data) {
		if (isEmpty())
			root = new TNode(data);
		else {
			if (find(data) != null) {
				System.out.println("This item is already exists");
				return;
			} else
				insert(data, root);
		}
	}

	private void insert(T data, TNode node) {
		if (data.compareTo((T) node.data) >= 0) { // insert into right subtree
			if (!node.hasRight())
				node.right = new TNode(data);
			else
				insert(data, node.right);
		} else { // insert into left subtree
			if (!node.hasLeft())
				node.left = new TNode(data);
			else
				insert(data, node.left);
		}
	}

	public boolean isEmpty() {
		if (root != null)
			return false;
		else
			return true;
	}

	public TNode delete(T data) {
        TNode current = root; 
        TNode parent = root;
        boolean isLeftChild = false;
        if (isEmpty())
            return null; // tree is empty
        while (current != null && !current.data.equals(data)) {
            parent = current;
            if (data.compareTo((T) current.data) < 0) {
                current = current.left;
                isLeftChild = true;
            } else {
                current = current.right;
                isLeftChild = false;
            }
        }
        if (current == null)
            return null; // node to be deleted not found
        // case 1: node is a leaf
        if (!current.hasLeft() && !current.hasRight()) {
            if (current == root) // tree has one node
                root = null;
            else {
                if (isLeftChild)
                    parent.left = null;
                else
                    parent.right = null;
            }
        } else if (current.hasLeft()) { // current has left child only
            if (current == root) {
                root = current.left;
            } else if (isLeftChild) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        } else if (current.hasRight()) { // current has right child only
            if (current == root) {
                root = current.right;
            } else if (isLeftChild) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else {
            TNode successor = getSuccessor(current);
            if (current == root)
                root = successor;
            else if (isLeftChild) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
            successor.left = current.left;
        }
        return current;
    }


	private TNode getSuccessor(TNode node) {
		TNode parentOfSuccessor = node;
		TNode successor = node;
		TNode current = node.right;
		while (current != null) {
			parentOfSuccessor = successor;
			successor = current;
			current = current.left;
		}
		if (successor != node.right) { // fix successor connections
			parentOfSuccessor.left = successor.right;
			successor.right = node.right;
		}
		return successor;
	}

	public void traverseInOrder() {
		traverseInOrder(root);
	}

	public int count() {
		if (root != null)
			return count(root, 0);
		else
			return 0;
	}

	private int count(TNode node, int count) {
		if (node != null) {
			count++;
			if (node.getLeft() != null)
				count = count(node.getLeft(), count);
			if (node.getRight() != null)
				count = count(node.getRight(), count);
		} else {
			return 0;
		}
		return count;
	}

	public int countLeafes() {
		return countLeafes(root, 0);
	}

	private int countLeafes(TNode node, int count) {
		if (node != null) {
			if (!node.isLeaf()) {
				count = countLeafes(node.getLeft(), count);
				count = countLeafes(node.getRight(), count);
			} else {
				return 1 + count;
			}
		} else {
			return 0;
		}
		return count;
	}

	private String s = "";

	private void traverseInOrder(TNode node) {
		if (node != null) {
			if (node.getLeft() != null) {
				traverseInOrder(node.getLeft());
			}
			s += node.getData() + "\n";
			System.out.print(node.getData() + " ");
			if (node.getRight() != null) {
				traverseInOrder(node.getRight());
			}
		}
	}
	
	public String toStringInorder() { 
        return toStringInorder(root);
    }

    private String toStringInorder(TNode<T> root) {
        String s = "";
        if (root == null) {
            return "";
        }

        s += toStringInorder(root.left);
        s += root.toString()+"\n";
        s += toStringInorder(root.right);
        return s;
    }

}
