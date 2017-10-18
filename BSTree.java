//@author: Anthony Panisales

public class BSTree {

	class BSTNode {
		BSTNode left;
		String data;
		BSTNode right;
		
		public BSTNode(String newData) {
			left = null;
			data = newData;
			right = null;
		}
		
		public BSTNode getLeft() {
			return left;
		}
		
		public String getData() {
			return data;
		}
		
		public BSTNode getRight() {
			return right;
		}
		
		public void setLeft(BSTNode newLeft) {
			left = newLeft;
		}
		
		public void setData(String newData) {
			data = newData;
		}
		
		public void setRight(BSTNode newRight) {
			right = newRight;
		}
	}
	
	BSTNode root;
	
	public BSTree() {
		root = null;
	}
	
	public void insert(String data) {
		insert(data, root);
	}
	
	public BSTNode insert(String data, BSTNode node) {
		//Base Case
		if (root == null) { //If tree is empty
			node = new BSTNode(data);
			root = node;
			return root;
		} else if (node == null) {
			return new BSTNode(data);
		} else { //Step Case
			if (data.compareTo(node.getData()) < 0) {
				node.setLeft(insert(data, node.getLeft()));
				return node;
			} else { //Duplicates go here
				node.setRight(insert(data, node.getRight()));
				return node;
			}
		}
	}
	
	public boolean find(String data) {
		return find(data, root);
	}
	
	public boolean find(String data, BSTNode node) {
		//Base Case
		if (node == null) {
			return false;
		} else { //Step Case
			if (data.compareTo(node.getData()) == 0) {
				return true;
			} else if (data.compareTo(node.getData()) < 0) {
				return find(data, node.getLeft());
			} else {
				return find(data, node.getRight());
			}
		}
	}
	
	public void delete(String data) {
		delete(data, root);
	}
	
	public BSTNode delete(String data, BSTNode node) {
		//Base Case. Data not found in tree
		if (node == null) {
			return null;
		} else { //Step Case
			if (data.compareTo(node.getData()) == 0) {
				//No child
				if (node.getLeft() == null && node.getRight() == null) {
					if (root == node) {
						root = null; //Tree is empty now
					}
					node = null;
					return node;
				}
				
				//One child
				if (node.getLeft() == null) {
					if (root == node) {
						root = node.getRight();
					}
					return node.getRight();
				} else if (node.getRight() == null) {
					if (root == node) {
						root = node.getLeft();
					}
					return node.getLeft();
				} 
				
				//Two children
				if (node.getRight().getLeft() == null) {
					node.setData(node.getRight().getData());
					node.setRight(node.getRight().getRight());
				} else {
					node.setData(removeSmallest(node.getRight()));
				}
				if (root == node) {
					root = node;
				}
				return node;
			} else if (data.compareTo(node.getData()) < 0) {
				node.setLeft(delete(data, node.getLeft()));
				return node;
			} else {
				node.setRight(delete(data, node.getRight()));
				return node;
			}
				
		}
	}
	
	//For remove method
	public String removeSmallest(BSTNode node) {
		if (node.getLeft().getLeft() == null) {
			String smallest = node.getLeft().getData();
			node.setLeft(node.getLeft().getRight());
			return smallest;
		}
		return removeSmallest(node.getLeft());
	}
	
	public String toStringInOrder() {
		String inOrder = "";
		return toStringInOrder(inOrder, root).trim();
	}
	
	public String toStringInOrder(String inOrder, BSTNode node) {
		if (node == null) {
			return "";
		} else {
			inOrder += toStringInOrder(inOrder, node.getLeft()) + 
					node.getData() + " " + toStringInOrder(inOrder, node.getRight());
			return inOrder;
		}
	}
	
	public String toStringPreOrder() {
		String preOrder = "";
		return toStringPreOrder(preOrder, root).trim();
	}
	
	public String toStringPreOrder(String preOrder, BSTNode node) {
		if (node == null) {
			return "";
		} else {
			preOrder += node.getData() + " " + toStringPreOrder(preOrder, node.getLeft()) + 
					toStringPreOrder(preOrder, node.getRight());
			return preOrder;
		}
	}
		
}
