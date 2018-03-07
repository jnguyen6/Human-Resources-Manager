package edu.ncsu.csc316.hr.tree;

import edu.ncsu.csc316.hr.adt.Queue;
import edu.ncsu.csc316.hr.adt.Tree;
import edu.ncsu.csc316.hr.list.ArrayBasedList;
import edu.ncsu.csc316.hr.list.LinkedList;

/**
 * Class that represents the general tree data structure, which uses
 * the linked representation as the underlying data structure.
 * 
 * @author Jimmy Nguyen
 * @param <E> the generic element type
 */
public class GeneralTree<E> implements Tree<E> {

	/** The reference to the root node of the general tree. */
	private Node root;
	/** The size of the general tree. */
	private int size;
	
	/**
	 * Constructor that creates an empty general tree, initializes the
	 * root field to null, and initializes the size field to 0.
	 */
	public GeneralTree() {
		root = null;
		size = 0;
	}
	
	/**
	 * Returns the element stored in the tree node. If the given node is null,
	 * then a NullPointerException is thrown.
	 * 
	 * @param node the node to retrieve the element from
	 * @return the element stored in the tree node
	 * @throws NullPointerException if the given node is null
	 */
	public E getElement(Node node) {
		if (node == null) {
			throw new NullPointerException("The given node cannot be null.");
		}
		return node.data;
	}
	
	/**
	 * Returns the key stored in the tree node. If the given node is null,
	 * then a NullPointerException is thrown.
	 * 
	 * @param node the node to retrieve the key from
	 * @return the key stored in the tree node
	 * @throws NullPointerException if the given node is null
	 */
	public String getKey(Node node) {
		if (node == null) {
			throw new NullPointerException("The given node cannot be null.");
		}
		return node.key;
	}

	/**
	 * Returns the root node of the general tree.
	 * 
	 * @return the root node of the general tree
	 */
	public Node root() {
		return root;
	}

	/**
	 * Returns the parent node of the given node. If the given node is null,
	 * then a NullPointerException is thrown.
	 * 
	 * @param node the node to find the parent for
	 * @return the parent node of the given node
	 * @throws NullPointerException if the given node is null
	 */
	public Node parent(Node node) {
		if (node == null) {
			throw new NullPointerException("The given node cannot be null.");
		}
		return node.parent;
	}

	/**
	 * Returns the number of children nodes that the given parent node
	 * has. If the given node is null, then a NullPointerException is
	 * thrown.
	 * 
	 * @param node the node to retrieve the number of children nodes
	 * @return the number of children nodes
	 * @throws NullPointerException if the given node is null
	 */
	public int numChildren(Node node) {
		if (node == null) {
			throw new NullPointerException("The given node cannot be null.");
		}
		return node.children.size();
	}
	
	/**
	 * Returns the collection of children nodes that the given parent
	 * node has. If the given node is null, then a NullPointerException
	 * is thrown.
	 * 
	 * @param node the node to obtain the collection of children nodes from
	 * @return the collection of children nodes
	 * @throws NullPointerException if the given node is null
	 */
	public ArrayBasedList<Node> getChildren(Node node) {
		if (node == null) {
			throw new NullPointerException("The given node cannot be null.");
		}
		return node.children;
	}
	
	/**
	 * Adds an element and its corresponding key to the tree. If the given
	 * key or element is null, then a NullPointerException is thrown
	 * 
	 * @param k the key to add to the tree
	 * @param e the element to add to the tree
	 * @throws NullPointerException if the given key or element is null
	 */
	@Override
	public void insert(String k, E e) {
		if (k == null || e == null) {
			throw new NullPointerException("The key or element to add cannot be null.");
		}
		if (root == null) {
			root = new Node(k, e);
		} else {
			Node n = new Node(k, e, root, new ArrayBasedList<Node>());
			root.children.add(n);
		}
		size++;
	}
	
	/**
	 * Adds an element and its corresponding key to a new node and connects the new
	 * node with the given parent node from the tree. If the given key/element or
	 * parent node is null, then a NullPointerException is thrown. If the parent node
	 * is null, but the root is null, then the reference to the root node is updated 
	 * accordingly.
	 * 
	 * @param k the key to add to the new node
	 * @param e the element to add to the new node
	 * @param pn the parent node to connect with the newly created node
	 * @throws NullPointerException if the given key/element or parent node is null
	 */
	public void insert(String k, E e, Node pn) {
		if (root == null) {
			insert(k, e);
		} else {
			if (k == null || e == null || pn == null) {
				throw new NullPointerException("The given key/element or node cannot be null.");
			}
			Node n = new Node(k, e, pn, new ArrayBasedList<Node>());
			pn.children.add(n);
			size++;
		}
	}

	/**
	 * Removes and returns an element from the tree, given a key. Note that
	 * the general tree does not have a defined natural ordering, so the remove
	 * method will thus be left unimplemented. If the tree is empty, then an
	 * IllegalArgumentException is thrown. If the given key is null, then
	 * a NullPointerException is thrown.
	 * 
	 * @param k the key to remove the element from
	 * @return the removed element or null if there are no elements to remove
	 * @throws IllegalArgumentException if the tree is empty
	 * @throws NullPointerException if the given key is null
	 */
	@Override
	public E remove(String k) {
		if (isEmpty()) {
			throw new IllegalArgumentException("The tree is empty.");
		}
		if (k == null) {
			throw new NullPointerException("The given key cannot be null.");
		}
		E removedVal = null;
		if (k.equals(getKey(root))) {
		    removedVal = getElement(root);
			if (numChildren(root) == 0) {
				root = null;
			} else {
			    root = getChildren(root).remove(size() - 1);
			}
			size--;
		} else {
			for (int i = 0; i < numChildren(root); i++) {
				if (k.equals(getKey(getChildren(root).get(i)))) {
					removedVal = getElement(getChildren(root).get(i));
					getChildren(root).remove(i);
					size--;
					break;
				}
			}
		}
		return removedVal;
	}
	
	/**
	 * Removes and returns an element from the given node in the tree, given a key.
	 * If the tree is empty, then an IllegalArgumentException is thrown. If the 
	 * given key or node is null, then a NullPointerException is thrown.
	 * 
	 * @param k the key to remove the element from
	 * @param node the current node to remove the element and key from
	 * @return the removed element or null if there are no elements to remove
	 * @throws IllegalArgumentException if the tree is empty
	 * @throws NullPointerException if the given key or node is null
	 */
	public E remove(String k, Node node) {
		if (isEmpty()) {
			throw new IllegalArgumentException("The tree is empty.");
		}
		if (k == null) {
			throw new NullPointerException("The given key cannot be null.");
		}
		if (node == null) {
			throw new NullPointerException("The given node cannot be null.");
		}
		if (node == root) {
			if (k.equals(node.key)) {
				E removedVal = node.data;
				if (node.children.size() == 0) {
					root = null;
				} else {
					root = node.children.remove(size() - 1);
				}
				size--;
				return removedVal;
			}
		} else {
			for (int i = 0; i < node.parent.children.size(); i++) {
				if (k.equals(node.parent.children.get(i).key)) {
					E removedVal = node.parent.children.get(i).data;
					node.parent.children.remove(i);
					size--;
					return removedVal;
				}
			}
		}
		return null;
	}
	
	/**
	 * Sets the element of the given node to the given element and key. Returns
	 * the element that was replaced. If the given key/element to set or the given 
	 * node is null, then a NullPointerException is thrown. If the general
	 * tree is empty, then an IllegalArgumentException is thrown.
	 * 
	 * @param k the key to set to the given node
	 * @param e the element to set to the given node
	 * @param node the node to set the element to
	 * @return the element that was replaced
	 * @throws NullPointerException if the given key/element to set or node is
	 * null
	 * @throws IllegalArgumentException if the general tree is empty
	 */
	public E setElement(String k, E e, Node node) {
		if (isEmpty()) {
			throw new IllegalArgumentException("The tree is empty.");
		}
		if (k == null || e == null || node == null) {
			throw new NullPointerException("The given key/element or node cannot be null.");
		}
		E replacedVal = getElement(node);
		node.key = k;
		node.data = e;
		return replacedVal;
	}
	
	/**
	 * Returns the size of the general tree.
	 * 
	 * @return the size of the general tree
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Determines if the general tree is empty. Returns true if the general
	 * tree is empty or false otherwise.
	 * 
	 * @return true if the tree is empty or false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
	
	/**
	 * Returns a string of all the elements in the tree through level order
	 * traversal. This method is used to help see the shape and contents of
	 * the 2 3 tree implementation. Note that the level-order traversal
	 * algorithm is referenced from the CSC316 Tree lecture slides on page
	 * 22, provided by Jason King.
	 * 
	 * @return the string representation of all the elements in the tree
	 */
	public String elementsInLevelOrder() {
		StringBuilder sb = new StringBuilder();
		sb.append("");
		Queue<Node> queue = new LinkedList<Node>();
		if (root == null) {
			return sb.toString();
		} else {
			queue.enqueue(root);
		}
		while (!queue.isEmpty()) {
			Node currentNode = queue.dequeue();
			sb.append(currentNode.data.toString());
			sb.append(", ");
			int num = 0;
			while (num < currentNode.children.size()) {
				queue.enqueue(currentNode.children.get(num));
				num++;
			}
		}
		return sb.toString();
	}
		
	
	/**
	 * The inner class of the GeneralTree class that stores the key,
	 * element, a reference to the parent node, and a collection of
	 * child nodes.
	 * 
	 * @author Jimmy Nguyen
	 */
	public class Node {
		
		/** The key that maps the element stored. */
		public String key;
		/** The element stored in the tree node. */
		public E data;
		/** The reference to the parent node. */
		public Node parent;
		/** The collection of child node references. */
		public ArrayBasedList<Node> children;
		
		/**
		 * Constructor that creates an instance of the tree node, initializes
		 * the key and data fields to the given parameter values, and initializes
		 * the parent and children fields to null.
		 * 
		 * @param key the key to set to the tree node
		 * @param data the element to set to the tree node
		 */
		public Node(String key, E data) {
			this(key, data, null, new ArrayBasedList<Node>());
		}
		
		/**
		 * Constructor that creates an instance of the tree node and initializes the
		 * key, data, parent, and children fields to the given parameter values.
		 * 
		 * @param key the key to set to the tree node
		 * @param data the element to set to the tree node
		 * @param parent the parent node reference to set
		 * @param children the collection of child node references to set
		 */
		public Node(String key, E data, Node parent, ArrayBasedList<Node> children) {
			this.key = key;
			this.data = data;
			this.parent = parent;
			this.children = children;
		}
	}
}
