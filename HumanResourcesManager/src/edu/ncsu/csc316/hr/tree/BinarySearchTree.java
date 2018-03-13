package edu.ncsu.csc316.hr.tree;

import edu.ncsu.csc316.hr.adt.Dictionary;
import edu.ncsu.csc316.hr.adt.Queue;
import edu.ncsu.csc316.hr.adt.Tree;
import edu.ncsu.csc316.hr.list.LinkedList;

/**
 * Class that represents the binary search tree data structure. Specifically,
 * the binary search tree uses the 2-3 search tree implementation design,
 * and as such, maintains the typical search properties and operations.
 * Note that the generic type E extends Comparable of a generic type,
 * which was referenced from the SortedArrayList class from project 3 of
 * CSC216. The authors of the class are Austin Hyder and Jimmy Nguyen.
 *
 * @author Jimmy Nguyen
 * @param <E> the generic element type
 */
public class BinarySearchTree<E extends Comparable<E>> implements Dictionary<E>, Tree<E> {

	/** The maximum number of keys one node can have. */
	public static final int MAX_NUM_KEYS = 2;
	
	/** The reference to the root node of the binary search tree. */
	private Node root;
	/** The size of the binary search tree. */
	private int size;
	
	/**
	 * Constructor that creates an empty binary search tree, initializes
	 * the root field to null, and initializes the size field to 0.
	 */
	public BinarySearchTree() {
		root = null;
		size = 0;
	}
	
	/**
	 * Adds an element and its corresponding key to the binary search tree. 
	 * If the given key or element is null, then a NullPointerException is
	 * thrown. If the element and key will not be added to the root, then
	 * the insert method that takes a node as the additional parameter value
	 * is called.
	 * 
	 * @param k the key to add to the binary search tree
	 * @param e the element to add to the binary search tree
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
			insert(k, e, root);
		}
		size++;
	}
	
	/**
	 * A recursive algorithm that helps add the given key and element to the
	 * binary search tree. If adding the key exceeds the maximum number of
	 * keys allowed for the 2-3 tree implementation, then the split recursive
	 * algorithm is called to resolve overflow. If the given key is a duplicate,
	 * then an IllegalArgumentException is thrown.
	 * 
	 * @param k the key to add to the binary search tree
	 * @param e the element to add to the binary search tree
	 * @param pn the parent node
	 * @throws IllegalArgumentException if the given key is a duplicate
	 */
	public void insert(String k, E e, Node pn) {
		//If there is an overflow, then perform the split operation
		if (isLeaf(pn) && pn.keys[1] != null) {
			split(k, e, pn);
		} else if (isLeaf(pn) && pn.keys[1] == null) {
			//If given key is less than the currently stored key, then
			//add given key to the beginning of the key array
			if (k.compareTo(pn.keys[0]) < 0) {
				pn.keys[1] = pn.keys[0];
				pn.data[1] = pn.data[0];
				pn.keys[0] = k;
				pn.data[0] = e;
			} else if (k.compareTo(pn.keys[0]) > 0) {
				pn.keys[1] = k;
				pn.data[1] = e;
			} else {
				throw new IllegalArgumentException("The given key is a duplicate.");
			}
		} else {
			//IF we get to this point, that means the given node is not a leaf.
			//So, compare, and then make a recursive call to insert.
			if (pn.keys[1] != null) {
				if (k.compareTo(pn.keys[0]) < 0 && k.compareTo(pn.keys[1]) < 0) {
					insert(k, e, pn.left);
				} else if (k.compareTo(pn.keys[0]) > 0 && k.compareTo(pn.keys[1]) < 0) {
					insert(k, e, pn.middle);
				} else {
					insert(k, e, pn.right);
				}
			} else {
			    if (k.compareTo(pn.keys[0]) < 0) {
			    		insert(k, e, pn.left);
			    } else {
			    		insert(k, e, pn.right);
			    }
			}
		}
	}
	
	/**
	 * A recursive algorithm that splits a node into 2 nodes, and the middle key
	 * is moved up to the parent node. If there is still an overflow, then the
	 * split method is recursively called.
	 * 
	 * @param k the key to add to the binary search tree
	 * @param e the element to add to the binary search tree
	 * @param pn the parent node
	 */
	public void split(String k, E e, Node pn) {
		if (k.compareTo(pn.keys[0]) == 0 || (pn.keys[1] != null && k.compareTo(pn.keys[1]) == 0)) {
			throw new IllegalArgumentException("The given key is a duplicate.");
		}
		//If the key is less than the left key, push left key up as the middle key
		if (isLeaf(pn)) {
			if (k.compareTo(pn.keys[0]) < 0 && k.compareTo(pn.keys[1]) < 0) {
				pn.left = new Node(k, e, pn, null, null, null);
				pn.right = new Node(pn.keys[1], pn.data[1], pn, null, null, null);
				pn.keys[1] = null;
				pn.data[1] = null;
				//If the parent node of the given node is not null, continue pushing up.
				//This is to check for overflow
				if (pn.parent != null) {
					split(pn.keys[0], pn.data[0], pn.parent);
				}
			} else if (k.compareTo(pn.keys[0]) > 0 && k.compareTo(pn.keys[1]) < 0) {
				pn.left = new Node(pn.keys[0], pn.data[0], pn, null, null, null);
				pn.right = new Node(pn.keys[1], pn.data[1], pn, null, null, null);
				pn.keys[0] = k;
				pn.data[0] = e;
				pn.keys[1] = null;
				pn.data[1] = null;
				if (pn.parent != null) {
					split(pn.keys[0], pn.data[0], pn.parent);
				}
			} else {
				pn.left = new Node(pn.keys[0], pn.data[0], pn, null, null, null);
				pn.right = new Node(k, e, pn, null, null, null);
				pn.keys[0] = pn.keys[1];
				pn.data[0] = pn.data[1];
				pn.keys[1] = null;
				pn.data[1] = null;
				if (pn.parent != null) {
					split(pn.keys[0], pn.data[0], pn.parent);
				}
			}
		} else {
			//If parent node has room for one more key
			if (pn.keys[1] == null) {
				//Reassign middle and right children of parent node
				if (k.compareTo(pn.keys[0]) > 0) {
					pn.keys[1] = k;
					pn.data[1] = e;
					pn.middle = pn.right.left;
					pn.middle.parent = pn;
					pn.right = pn.right.right;
					pn.right.parent = pn;
				} else {
					pn.keys[1] = pn.keys[0];
					pn.data[1] = pn.data[0];
					pn.keys[0] = k;
					pn.data[0] = e;
					pn.middle = pn.left.right;
					pn.middle.parent = pn;
					pn.left = pn.left.left;
					pn.left.parent = pn;
				}
			} else {
				//If the key pushed up is less than the two keys
				if (k.compareTo(pn.keys[0]) < 0 && k.compareTo(pn.keys[1]) < 0) {
					pn.right = new Node(pn.keys[1], pn.data[1], pn, pn.middle, null, pn.right);
					pn.right.right.parent = pn.right;
					pn.right.left.parent = pn.right;
					pn.middle = null;
					pn.keys[1] = null;
					pn.data[1] = null;
					if (pn.parent != null) {
						split(pn.keys[0], pn.data[0], pn.parent);
					}
				} else if (k.compareTo(pn.keys[0]) > 0 && k.compareTo(pn.keys[1]) < 0) {
					pn.left = new Node(pn.keys[0], pn.data[0], pn, pn.left, null, pn.middle.left);
					pn.left.left.parent = pn.left;
					pn.left.right.parent = pn.left;
					pn.right = new Node(pn.keys[1], pn.data[1], pn, pn.middle.right, null, pn.right);
					pn.right.left.parent = pn.right;
					pn.right.right.parent = pn.right;
					pn.middle = null;
					pn.keys[0] = k;
					pn.data[0] = e;
					pn.keys[1] = null;
					pn.data[1] = null;
					if (pn.parent != null) {
						split(pn.keys[0], pn.data[0], pn.parent);
					}
				} else {
					pn.left = new Node(pn.keys[0], pn.data[0], pn, pn.left, null, pn.middle);
					pn.left.left.parent = pn.left;
					pn.left.right.parent = pn.left;
					pn.middle = null;
					pn.keys[0] = pn.keys[1];
					pn.data[0] = pn.data[1];
					pn.keys[1] = null;
					pn.data[1] = null;
					if (pn.parent != null) {
						split(pn.keys[0], pn.data[0], pn.parent);
					}
				}
			}
		}
	}

	/**
	 * Removes the element and key from the binary search tree based on the
	 * given key. Returns the element that was removed from the list, or null
	 * if the element was not found. If the given key is null, then a 
	 * NullPointerException is thrown.
	 * 
	 * @param k the key to remove the element and associated key from the tree
	 * @return the removed element or null if the element was not found
	 * @throws NullPointerException if the given key is null
	 */
	@Override
	public E remove(String k) {
		if (k == null) {
			throw new NullPointerException("The given key cannot be null.");
		}
		if (root == null) {
			return null;
		} else {
			E removedVal = remove(k, root);
			if (removedVal != null) {
				size--;
			}
			return removedVal;
		}
	}
	
	/**
	 * A recursive algorithm that helps search for the key in the binary search
	 * tree and remove the element and associated key. If removing the key from
	 * the tree results in an underflow, then either the fusion or transfer
	 * operation is performed to resolve underflow.
	 * 
	 * @param k the key to remove the element and associated key from the tree
	 * @param node the current node
	 * @return the removed element or null if the element was not found
	 */
	public E remove(String k, Node node) {
		if (node == null) {
			return null;
		}
		if (node.keys[1] == null) {
			//If we found a match, remove element and key, and find in-order successor
			if (k.compareTo(node.keys[0]) == 0) {
				E removedVal = node.data[0];
				Node successor = findSuccessor(node.right);
				if (successor != null) {
					node.keys[0] = successor.keys[0];
					node.data[0] = successor.data[0];
					if (successor.parent.keys[1] == null) {
						if (successor.parent.left.keys[0] == null) {
							if (successor.parent.right.keys[1] == null) {
								fusion(successor.parent.left);
							} else {
								transfer(successor.parent.left);
							}
						} else if (successor.parent.right.keys[0] == null) {
							if (successor.parent.left.keys[1] == null) {
								fusion(successor.parent.right);
							} else {
								transfer(successor.parent.right);
							}
						}
					} else {
						if (successor.parent.left.keys[0] == null) {
							if (successor.parent.middle.keys[1] == null) {
								fusion(successor.parent.left);
							} else {
								transfer(successor.parent.left);
							}
						}
					}
				} else {
					node.keys[0] = null;
					node.data[0] = null;
					if (node.parent == null) {
						root = null;
					} else {
						if (node.parent.keys[1] == null) {
							if (node == node.parent.left) {
								if (node.parent.right.keys[1] == null) {
									fusion(node);
								} else {
									transfer(node);
								}
							} else {
								if (node.parent.left.keys[1] == null) {
									fusion(node);
								} else {
									transfer(node);
								}
							}
						} else {
							if (node == node.parent.left) {
								if (node.parent.middle.keys[1] == null) {
									fusion(node);
								} else {
									transfer(node);
								}
							} else if (node == node.parent.middle) {
								if (node.parent.left.keys[1] == null && 
										node.parent.right.keys[1] == null) {
									fusion(node);
								} else {
									transfer(node);
								}
							} else {
								if (node.parent.middle.keys[1] == null) {
									fusion(node);
								} else {
									transfer(node);
								}
							}
						}
					}
				}
				return removedVal;
			} else if (k.compareTo(node.keys[0]) < 0) {
				return remove(k, node.left);
			} else {
				return remove(k, node.right);
			}
		} else {
			if (k.compareTo(node.keys[0]) == 0) {
				E removedVal = node.data[0];
				Node successor = findSuccessor(node.middle);
				if (successor != null) {
					node.keys[0] = successor.keys[0];
					node.data[0] = successor.data[0];
					if (successor.parent.keys[1] == null) {
						if (successor.parent.left.keys[0] == null) {
							if (successor.parent.right.keys[1] == null) {
								fusion(successor.parent.left);
							} else {
								transfer(successor.parent.left);
							}
						}
					} else {
						if (successor.parent.left.keys[0] == null) {
							if (successor.parent.middle.keys[1] == null) {
								fusion(successor.parent.left);
							} else {
								transfer(successor.parent.left);
							}
						} else if (successor.parent.middle.keys[0] == null) {
							if (successor.parent.left.keys[1] == null && 
									successor.parent.right.keys[1] == null) {
								fusion(successor.parent.middle);
							} else {
								transfer(successor.parent.middle);
							}
						}
					}
				} else {
					node.keys[0] = node.keys[1];
					node.data[0] = node.data[1];
					node.keys[1] = null;
					node.data[1] = null;
					
				}
				return removedVal;
			} else if (k.compareTo(node.keys[1]) == 0) {
				E removedVal = node.data[1];
				Node successor = findSuccessor(node.right);
				if (successor != null) {
				    node.keys[1] = successor.keys[0];
				    node.data[1] = successor.data[0];
					if (successor.parent.keys[1] == null) {
						if (successor.parent.left.keys[0] == null) {
							if (successor.parent.right.keys[1] == null) {
								fusion(successor.parent.left);
							} else {
								transfer(successor.parent.left);
							}
						}
					} else {
						if (successor.parent.left.keys[0] == null) {
							if (successor.parent.middle.keys[1] == null) {
								fusion(successor.parent.left);
							} else {
								transfer(successor.parent.left);
							}
						} else if (successor.parent.right.keys[0] == null) {
							if (successor.parent.middle.keys[1] == null) {
								fusion(successor.parent.right);
							} else {
								transfer(successor.parent.right);
							}
						}
					}
				} else {
					node.keys[1] = null;
					node.data[1] = null;
				}
				return removedVal;
			} else if (k.compareTo(node.keys[0]) < 0 && k.compareTo(node.keys[1]) < 0) {
				return remove(k, node.left);
			} else if (k.compareTo(node.keys[0]) > 0 && k.compareTo(node.keys[1]) < 0) {
				return remove(k, node.middle);
			} else {
				return remove(k, node.right);
			}
		}
	}
	
	/**
	 * A recursive algorithm that helps find the in-order successor of the
	 * given node. Returns the node that contains the keys and elements.
	 * If the given node is null, then null is returned.
	 * 
	 * @param node the current node to find the in-order successor for
	 * @return the node that contains the keys and elements that are
	 * in-order successor or null if the node is null
	 */
	public Node findSuccessor(Node node) {
		//If the right subtree is null, then return null
		if (node == null) {
			return null;
		} else {
			//If the left node of the given node is null, then return the node
			//that is the in-order successor
			if (node.left == null) {
			    Node successor = new Node(node.keys[0], node.data[0], node.parent, null, null, null);
			    node.keys[0] = null;
			    node.data[0] = null;
			    if (node.keys[1] != null) {
			    	    node.keys[0] = node.keys[1];
			    	    node.data[0] = node.data[1];
			    	    node.keys[1] = null;
			    	    node.data[1] = null;
			    }
			    return successor;
			} else {
				return findSuccessor(node.left);
			}
		}
	}
	
	/**
	 * A recursive algorithm that helps update the binary search tree
	 * when an element and key to remove are found through fusion.
	 * 
	 * @param node the current node
	 */
	public void fusion(Node node) {
		if (node.parent.keys[1] == null) {
			//If given node is to the right of the parent node
			if (node == node.parent.right) {
				node.parent.left.keys[1] = node.parent.keys[0];
				node.parent.left.data[1] = node.parent.data[0];
				node.parent.right = null;
				node.parent.keys[0] = null;
				node.parent.data[0] = null;
				//If the given node is not a leaf, then the node has a child node
				//that needs to be transferred over
				if (!isLeaf(node)) {
				
					node.parent.left.middle = node.parent.left.right;
					if (node.left != null) {
						node.parent.left.right = node.left;
					} else {
						node.parent.left.right = node.right;
					}
					if (node.parent.left.right != null) {
						node.parent.left.right.parent = node.parent.left;
					}
				}
				//If the parent node is the root, change the root reference,
				//and we are done.
				if (node.parent == root) {
					node.parent.left.parent = null;
					root = node.parent.left;
				} else {
					if (node.parent.parent.keys[1] == null) {
						if (node.parent == node.parent.parent.left) {
							if (node.parent.parent.right.keys[1] == null) {
								fusion(node.parent);
							} else {
								transfer(node.parent);
							}
						} else {
							if (node.parent.parent.left.keys[1] == null) {
								fusion(node.parent);
							} else {
								transfer(node.parent);
							}
						}
					} else {
						if (node.parent == node.parent.parent.middle) {
							if (node.parent.parent.left.keys[1] == null && 
									node.parent.parent.right.keys[1] == null) {
								fusion(node.parent);
							} else {
								transfer(node.parent);
							}
						} else {
							if (node.parent.parent.middle.keys[1] == null) {
								fusion(node.parent);
							} else {
								transfer(node.parent);
							}
						}
					}
				}
			} else {
				node.parent.right.keys[1] = node.parent.right.keys[0];
				node.parent.right.data[1] = node.parent.right.data[0];
				node.parent.right.keys[0] = node.parent.keys[0];
				node.parent.right.data[0] = node.parent.data[0];
				node.parent.left = null;
				node.parent.keys[0] = null;
				node.parent.data[0] = null;
				if (!isLeaf(node)) {
					node.parent.right.middle = node.parent.right.left;
					if (node.right != null) {
						node.parent.right.left = node.right;
					} else {
						node.parent.right.left = node.left;
					}
					if (node.parent.right.left != null) {
						node.parent.right.left.parent = node.parent.right;
					}
				}
				if (node.parent == root) {
					node.parent.right.parent = null;
					root = node.parent.right;
					
				} else {
					if (node.parent.parent.keys[1] == null) {
						if (node.parent == node.parent.parent.right) {
							if (node.parent.parent.left.keys[1] == null) {
								fusion(node.parent);
							} else {
								transfer(node.parent);
							}
						} else {
							if (node.parent.parent.right.keys[1] == null) {
								fusion(node.parent);
							} else {
								transfer(node.parent);
							}
						}
					} else {
						if (node.parent.parent.middle.keys[1] == null) {
							fusion(node.parent);
						} else {
							transfer(node.parent);
						}
					}
				}
			}
		} else {
			if (node == node.parent.right) {
				node.keys[0] = node.parent.middle.keys[0];
				node.data[0] = node.parent.middle.data[0];
				node.keys[1] = node.parent.keys[1];
				node.data[1] = node.parent.data[1];
				node.parent.keys[1] = null;
				node.parent.data[1] = null;
				if (node.left != null) {
					if (node.right == null) {
						node.right = node.left;
					} else {
						node.middle = node.left;
					}
				}
			    node.middle = node.parent.middle.right;
			    if (node.middle != null) {
			    		node.middle.parent = node;
			    }
				node.left = node.parent.middle.left;
				if (node.left != null) {
					node.left.parent = node;
				}
				node.parent.middle = null;
				//Since this process is essentially like transferring, we should
				//be done at this point
			} else if (node == node.parent.middle) {
				node.parent.right.keys[1] = node.parent.right.keys[0];
				node.parent.right.data[1] = node.parent.right.data[0];
				node.parent.right.keys[0] = node.parent.keys[1];
				node.parent.right.data[0] = node.parent.data[1];
				node.parent.keys[1] = null;
				node.parent.data[1] = null;
				if (node.parent.right.left != null) {
					if (node.parent.right.right == null) {
						node.parent.right.right = node.parent.right.left;
					} else {
						node.parent.right.middle = node.parent.right.left;
					}
				}
				if (node.parent.right.middle == null) {
					node.parent.right.middle = node.middle;
				}
				if (node.parent.right.middle != null) {
					node.parent.right.middle.parent = node.parent.right;
				}
				if (node.right == null) {
					node.parent.right.left = node.left;
				} else {
					node.parent.right.left = node.right;
				}
				if (node.parent.right.left != null) {
					node.parent.right.left.parent = node;
				}
				node.parent.middle = null;
			} else {
				node.keys[0] = node.parent.keys[0];
				node.data[0] = node.parent.data[0];
				node.keys[1] = node.parent.middle.keys[0];
				node.data[1] = node.parent.middle.data[0];
				node.parent.keys[0] = node.parent.keys[1];
				node.parent.data[0] = node.parent.data[1];
				node.parent.keys[1] = null;
				node.parent.data[1] = null;
				if (node.right != null) {
					if (node.left == null) {
						node.left = node.right;
					} else {
						node.middle = node.right;
					}
				}
				node.middle = node.parent.middle.left;
				if (node.middle != null) {
					node.middle.parent = node;
				}
				node.right = node.parent.middle.right;
				if (node.right != null) {
					node.right.parent = node;
				}
				node.parent.middle = null;
			}
		}
	}
	
	/**
	 * A recursive algorithm that helps update the binary search tree
	 * when an element and key to remove are found through transferring.
	 * 
	 * @param node the current node
	 */
	public void transfer(Node node) {
		//Three node
		if (node.parent.keys[1] != null) {
			if (node == node.parent.right) {
				node.keys[0] = node.parent.keys[1];
				node.data[0] = node.parent.data[1];
				node.parent.keys[1] = node.parent.middle.keys[1];
				node.parent.data[1] = node.parent.middle.data[1];
				node.parent.middle.keys[1] = null;
				node.parent.middle.data[1] = null;
				if (node.left != null) {
					if (node.right == null) {
						node.right = node.left;
					} else {
						node.middle = node.left;
					}
					node.right = node.left;
				}
				node.left = node.parent.middle.right;
				if (node.left != null) {
					node.left.parent = node;
				}
				node.parent.middle.right = node.parent.middle.middle;
				node.parent.middle.middle = null;
				//Since this is transfer, there should be no more additional underflows.
				//So, we are done.
			} else if (node == node.parent.middle) {
				if (node.parent.left.keys[1] != null) {
					node.keys[0] = node.parent.keys[0];
					node.data[0] = node.parent.data[0];
					node.parent.keys[0] = node.parent.left.keys[1];
					node.parent.data[0] = node.parent.left.data[1];
					node.parent.left.keys[1] = null;
					node.parent.left.data[1] = null;
					if (node.left != null ) {
						if (node.right == null) {
							node.right = node.left;
						} else {
							node.middle = node.left;
						}
					}
					node.left = node.parent.left.right;
					if (node.left != null) {
						node.left.parent = node;
					}
					node.parent.left.right = node.parent.left.middle;
					node.parent.left.middle = null;
				} else {
					node.keys[0] = node.parent.keys[1];
					node.data[0] = node.parent.data[1];
					node.parent.keys[1] = node.parent.right.keys[0];
					node.parent.data[1] = node.parent.right.data[0];
					node.parent.right.keys[0] = node.parent.right.keys[1];
					node.parent.right.data[0] = node.parent.right.data[1];
					node.parent.right.keys[1] = null;
					node.parent.right.data[1] = null;
					if (node.right != null) {
						if (node.left == null) {
							node.left = node.right;
						} else {
							node.middle = node.right;
						}
						node.left = node.right;
					}
					node.right = node.parent.right.left;
					if (node.right != null) {
						node.right.parent = node;
					}
					node.parent.right.left = node.parent.right.middle;
					node.parent.right.middle = null;
				}
			} else {
				node.keys[0] = node.parent.keys[0];
				node.data[0] = node.parent.data[0];
				node.parent.keys[0] = node.parent.middle.keys[0];
				node.parent.data[0] = node.parent.middle.data[0];
				node.parent.middle.keys[0] = node.parent.middle.keys[1];
				node.parent.middle.data[0] = node.parent.middle.data[1];
				node.parent.middle.keys[1] = null;
				node.parent.middle.data[1] = null;
				if (node.right != null) {
					if (node.left == null) {
						node.left = node.right;
					} else {
						node.middle = node.right;
					}
				}
				node.right = node.parent.middle.left;
				if (node.right != null) {
					node.right.parent = node;
				}
				node.parent.middle.left = node.parent.middle.middle;
				node.parent.middle.middle = null;
			}
		} else {
			if (node == node.parent.left) {
				node.keys[0] = node.parent.keys[0];
				node.data[0] = node.parent.data[0];
				node.parent.keys[0] = node.parent.right.keys[0];
				node.parent.data[0] = node.parent.right.data[0];
				node.parent.right.keys[0] = node.parent.right.keys[1];
				node.parent.right.data[0] = node.parent.right.data[1];
				node.parent.right.keys[1] = null;
				node.parent.right.data[1] = null;
				if (node.right != null) {
					if (node.left == null) {
						node.left = node.right;
					} else {
						node.middle = node.right;
					}
				}
				node.right = node.parent.right.left;
				if (node.right != null) {
					node.right.parent = node;
				}
				node.parent.right.left = node.parent.right.middle;
				node.parent.right.middle = null;
			} else {
				node.keys[0] = node.parent.keys[0];
				node.data[0] = node.parent.data[0];
				node.parent.keys[0] = node.parent.left.keys[1];
				node.parent.data[0] = node.parent.left.data[1];
				node.parent.left.keys[1] = null;
				node.parent.left.data[1] = null;
				if (node.left != null) {
					if (node.right == null) {
						node.right = node.left;
					} else {
						node.middle = node.left;
					}
				}
				node.left = node.parent.left.right;
				if (node.left != null) {
					node.left.parent = node;	
				}
				node.parent.left.right = node.parent.left.middle;
				node.parent.left.middle = null;
			}
		}
	}

	/**
	 * Returns the size of the binary search tree.
	 * 
	 * @return the size of the binary search tree
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Determines if the binary search tree is empty. Returns tree if the
	 * tree is empty or false otherwise.
	 * 
	 * @return true if the binary search tree is empty or false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Returns the element from the binary search tree based on the given
	 * key. Returns null if the element was not found. If the given key is
	 * null, then a NullPointerException is thrown.
	 * 
	 * @param k the key value to search for
	 * @return the element found or null if the element was not found
	 * @throws NullPointerException if the given key is null
	 */
	@Override
	public E lookUp(String k) {
		if (k == null) {
			throw new NullPointerException("The given key cannot be null.");
		}
		if (root == null) {
			return null;
		} else {
			return lookUp(k, root);
		}
	}
	
	/**
	 * A recursive algorithm that searches for the element based on the given
	 * key value and returns the element from the binary search tree. Returns
	 * null if the element was not found.
	 * 
	 * @param k the key value to search for
	 * @param node the current node
	 * @return the element found or null if the element was not found
	 */
	public E lookUp(String k, Node node) {
		if (node == null) {
			return null;
		} else if (k.compareTo(node.keys[0]) == 0) {
			return node.data[0];
		} else if (node.keys[1] != null && k.compareTo(node.keys[1]) == 0) {
			return node.data[1];
		} else {
			if (node.keys[1] == null) {
				if (k.compareTo(node.keys[0]) < 0) {
					return lookUp(k, node.left);
				} else {
					return lookUp(k, node.right);
				}
			} else {
				if (k.compareTo(node.keys[0]) < 0 && k.compareTo(node.keys[1]) < 0) {
					return lookUp(k, node.left);
				} else if (k.compareTo(node.keys[0]) > 0 && k.compareTo(node.keys[1]) < 0) {
					return lookUp(k, node.middle);
				} else {
					return lookUp(k, node.right);
				}
			}
		}
	}
	
	/**
	 * Determines if the given node is a leaf. Returns true if the node is
	 * a leaf or false otherwise. If the given node is null, then a
	 * NullPointerException is thrown
	 * 
	 * @param node the node to determine if it is a leaf or not
	 * @return true if the given node is a leaf or false otherwise
	 * @throws NullPointerException if the given node is null
	 */
	public boolean isLeaf(Node node) {
		if (node == null) {
			throw new NullPointerException("The given node cannot be null.");
		}
		return node.left == null && node.middle == null && node.right == null;
	}
	
	/**
	 * Determines if the given node is a root. Returns true if the node is
	 * a root or false otherwise. If the given node is null, then a
	 * NullPointerException is thrown.
	 * 
	 * @param node the node to determine if it is a root or not
	 * @return true if the given node is a root or false otherwise
	 * @throws NullPointerException if the given node is null
	 */
	public boolean isRoot(Node node) {
		if (node == null) {
			throw new NullPointerException("The given node cannot be null.");
		}
		return node.parent == null;
	}
	
	/**
	 * Returns the root of the binary search tree.
	 * 
	 * @return the root of the binary search tree
	 */
	public Node root() {
		return root;
	}
	
	/**
	 * Returns a String of all the elements in the tree through level order
	 * traversal. This method is used to help see the shape and contents of
	 * the 2 3 tree implementation. Note that the level-order traversal
	 * algorithm is referenced from the CSC316 Tree lecture slides on page
	 * 22, provided by Jason King.
	 * 
	 * @return the String representation of all the elements in the tree
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
			if (currentNode.data[1] != null) {
				sb.append(currentNode.data[0].toString());
				sb.append(" ");
				sb.append(currentNode.data[1].toString());
				sb.append(", ");
			} else {
				sb.append(currentNode.data[0].toString());
				sb.append(", ");
			}
			if (currentNode.left != null) {
				queue.enqueue(currentNode.left);
			}
			if (currentNode.middle != null) {
				queue.enqueue(currentNode.middle);
			}
			if (currentNode.right != null) {
				queue.enqueue(currentNode.right);
			}
		}
		return sb.toString();
		
	}
	
	/**
	 * The inner class of the BinarySearchTree class that stores keys,
	 * elements, and references to the parent and children nodes.
	 * 
	 * @author Jimmy Nguyen
	 */
	private class Node {
		
		/** The collection of keys that maps the elements stored. */
		private String[] keys;
		/** The elements stored in the tree node. */
		private E[] data;
		/** The reference to the parent node. */
		private Node parent;
		/** The reference to the left node. */
		private Node left;
		/** The reference to the middle node. */
		private Node middle;
		/** The reference to the right node. */
		private Node right;
		
		/**
		 * Constructor that creates an instance of the tree node, initializes
		 * the key and data fields to the given parameter values, and initializes
		 * the parent and children fields to null.
		 * 
		 * @param key the key to set to the tree node
		 * @param data the element to set to the tree node
		 */
		public Node(String k, E e) {
			this(k, e, null, null, null, null);
		}
		
		/**
		 * Constructor that creates an instance of the tree node and initializes the
		 * key, data, parent, and children fields to the given parameter values.
		 * 
		 * @param key the key to set to the tree node
		 * @param data the element to set to the tree node
		 * @param parent the parent node reference to set
		 * @param left the left child node reference to set
		 * @param middle the middle child node reference to set
		 * @param right the right child node reference to set
		 */
		@SuppressWarnings("unchecked")
		public Node(String k, E e, Node parent, Node left, Node middle, Node right) {
			keys = new String[MAX_NUM_KEYS];
			data = (E[]) (new Comparable[MAX_NUM_KEYS]);
			keys[0] = k;
			data[0] = e;
			this.parent = parent;
			this.left = left;
			this.middle = middle;
			this.right = right;
		}
	}

}
