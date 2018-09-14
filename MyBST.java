import java.util.*;
/**
 * A class that implements a binary search tree. 
 * @author Rebecca Hom
 * @since April. 22, 2017
 * @param <E> The type of elements in the set
 */
public class MyBST<E extends Comparable<E>> {
	protected BSTNode<E> root;
	protected int size = 0;

	/**
	 * Default constructor
	 */
	public MyBST(){
	}
	
	/**
	 * A constructor that sets the root of the binary search tree
	 * @param root The BST node that will be the root of the binary search tree
	 */
	public MyBST(BSTNode<E> root){
		this.root = root;
	}
	
	/**
	 * Retrieves the data field root's information
	 * @return root The root node of the binary search tree
	 */
	public BSTNode<E> getRoot(){
		return root;
	}
	
	/**
	 * Changes the value of the root of the binary search tree
	 * @param root The BST node that will be the new root node of the binary search tree
	 */
	public void setRoot(BSTNode<E> root){
		this.root = root;
	}
	
	/**
	 * Adds the specified element to the set if it is not already present
	 * @param e A specific element of type E that is to be added to the BST
	 * @return True if the element is added, false otherwise
	 */
	public boolean add(E e) throws ClassCastException, NullPointerException{
		//Check if the data is null
		if (e == null){
			throw new NullPointerException("Null values cannot be added");
		}
		if (root == null){
			BSTNode<E> tempNode = new BSTNode<E>(e);
			root = tempNode;
			size += 1;
			return true;
		}
		
		return addBSTNode(root,e);
	}
	
	/**
	 * A private, recursive method that helps the add method traverse the binary search tree
	 * @param node The BST node that the method checks for one or more null child references
	 * @param data The information of the new BST node that is to be added to the binary search tree
	 * @return A new BST node with the new data if the binary search tree does not already contain
	 * that BST node; otherwise, the node that is passed as an argument is returned
	 */
	private boolean addBSTNode(BSTNode<E> node, E data){
		if (node == null){ //Check if the spot is empty so that the new node can be inserted
			BSTNode<E> newNode = new BSTNode<E>(data); //Create a new node to hold the data
			node = newNode;
			size += 1; //Increment the counter since a new node is being added
			return true;
		}

		else if (data.compareTo(node.getData()) == 0){
			return false;
		}
		else if (data.compareTo(node.getData()) < 0){ //If the new data is smaller than the current data, add it to the left
			if (node.getLeft() == null){
				BSTNode<E> tempNode = new BSTNode<E>(data);
				node.setLeft(tempNode);
				size += 1;
				return true;
			}
			else{
				addBSTNode(node.getLeft(),data);
			}
		}
		
		else{
			if (node.getRight() == null){
				BSTNode<E> tempNode = new BSTNode<E>(data);
				node.setRight(tempNode);
				size += 1;
				return true;
			}
			else{
				addBSTNode(node.getRight(),data);
			}
		}
		return false;
	}
	
	/**
	 * Removes the specified element from the binary search tree if it is present
	 * @param o Object to be removed from the binary search tree if it exists in the tree
	 * @return true if the object is successfully removed, false otherwise
	 * @throws ClassCastException If the specified object cannot be compared with the items in the tree
	 * @throws NullPointerException If the specified object is null
	 */
	public boolean remove(Object o) throws ClassCastException, NullPointerException{
		if (o == null){ //Check if the object is null
			throw new NullPointerException("Data is an invalid type");
		}
		@SuppressWarnings("unchecked")
		E e = (E)o; //Cast the object into generic type E
		//Check if the root is null; if it is, there is nothing to remove
		if (root == null){
			return false;
		}
		BSTNode<E> tempNode = new BSTNode<E>(e); //Create a temporary node to hold the data
		if (contains(e)){ //Check if the data is in the tree and if so, remove it
			size -= 1;
			return removeBSTNode(root,e);
			
		}
		return false; //The data is not in the tree, so return false
	}
	
	/**
	 * A method that traverses through the binary search tree 
	 * @param node The node that is inspected by the method; if this is null, then the
	 * method will stop traversing through the tree
	 * @param data The data to be removed from the tree
	 * @return The value of the removed node
	 */
	private boolean removeBSTNode(BSTNode<E> node, E data){
		//Check if there are no children
		if (node.getRight() == null && node.getLeft() == null){
			if (node.getData().equals(data)){
				node = null;
				return true;
			}
			return false;
		}
		
		//Check if there is 1 child
		else if (node.getLeft() == null){
			if (node.getRight().getData().equals(data)){
				node.setRight(null);
			}
			else if (node.getData().equals(data)){
				//remove
			}
			else{
				removeBSTNode(node.getRight(),data);
			}
		}
		else if (node.getRight() == null){
			if (node.getLeft().getData().equals(data)){
				node.setLeft(null);
			}
			else{
				removeBSTNode(node.getLeft(),data);
			}
		}
		
		//Check if there are 2 children
		else if (data.compareTo(node.getData()) > 0){
			if (node.getData().equals(data)){
				node = new BSTNode<E>(getPredecessor(node));
			}
			else{
				removeBSTNode(node.getLeft(),data);
			}
		}
		else if (data.compareTo(node.getData()) < 0){
			if (node.getData().equals(data)){
				node = new BSTNode<E>(getPredecessor(node));
			}
			else{
				removeBSTNode(node.getRight(),data);
			}
		}
		return true;
	}
	
	/**
	 * An iterator that looks for the predecessor (the rightmost node) of a BST
	 * @param node The node that is iterated over
	 * @return The data of the predecessor node
	 */
	private E getPredecessor(BSTNode<E> node){
		while(node.getRight() != null){
			node = node.getRight();
		}
		return node.getData();
	}
	
	/**
	 * Checks if the tree contains the specified element
	 * @param o The element that is checked
	 * @return True if the element is in the BST, false otherwise
	 */
	public boolean contains(Object o){
		E e = (E)o; //Cast the object into generic type E
		return containsBSTNode(e,root);
	}
	
	/**
	 * The private recursive method that helps the contain method traverse through the binary search tree
	 * @param data The data that the method checks for in the BST
	 * @param current The BST node that the method is currently searching
	 * @return true if the binary search tree contains the data, false otherwise;
	 */
	private boolean containsBSTNode(E data, BSTNode<E> current){
		if (current == null){ //Check if the node is empty
			return false;
		}
		else if(data.compareTo(current.getData()) < 0){ //If the data is smaller than the data in the 
			//current node, then move to the left of the tree
				containsBSTNode(data,current.getLeft());
			}
		else if(data.compareTo(current.getData()) > 0){ //If the data is larger than the data in the 
			//current node, then move to the right of the tree
				containsBSTNode(data,current.getRight());
		}
		return true; //If the data is the same as the data of the node, then the tree contains that data
	}
	
	/**
	 * Retrieves the first or lowest element (the deepest, leftmost leaf) in the binary search tree
	 * @return The data of the lowest node in the binary search tree
	 * @throws NoSuchElementException If the binary search tree is empty
	 */
	public E first() throws NoSuchElementException{
		if (root == null){ //Check if the binary search tree is empty
			throw new NoSuchElementException("Set is empty");
		}
		
		return findFirst(root);
	}
	
	/**
	 * Private recursive method that helps first() traverse through the binary search tree
	 * @param current The BST node that the method is inspecting 
	 * @return The data of the lowest node in the binary search tree
	 */
	private E findFirst(BSTNode<E> current){
		//Continuously move to the left until the node is null
		if (current.getLeft() != null){
			findFirst(current.getLeft());
		}
		
		return current.getData();
	}
	
	/**
	 * Returns the last or highest element (the deepest, rightmost leaf) in the binary search tree
	 * @return The data of the highest node in the binary search tree
	 * @throws NoSuchElementException If the binary search tree is empty
	 */
	public E last() throws NoSuchElementException{
		if (root == null){ //Check if the binary search tree is empty
			throw new NoSuchElementException("Set is empty");
		}
		return findLast(root);
	}
	
	/**
	 * Private recursive method that helps last() traverse through the binary search tree
	 * @param current The BST node that the method is inspecting
	 * @return The data of the highest node in the binary search tree
	 */
	private E findLast(BSTNode<E> current){
		//Continuously move to the right until the node is null
		if (current.getRight() != null){
			findLast(current.getRight());
		}
		
		return current.getData();
	}
	
	public String toString(){
		String tempString = "[ " + toStringHelper(root) + " ]";
		return tempString;
	}
	
	private String toStringHelper(BSTNode<E> node){
		if (node == null){
			return "";
		}
		return toStringHelper(node.getLeft()) + " " + node.getData().toString() + " " + toStringHelper(node.getRight());
	}
}