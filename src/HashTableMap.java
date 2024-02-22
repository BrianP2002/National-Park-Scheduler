// --== CS400 File Header Information ==--
// Name: Lin Ha
// Email: lha2@wisc.edu
// Notes to Grader:

import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.LinkedList;

public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {

    protected Object[] list;
    private int size;

    private class Node {
	protected KeyType key;
	protected ValueType value;

	protected Node(KeyType key, ValueType value) {
	    this.key = key;
	    this.value = value;
	}
    }

    /**
     * Constructor of HashTableMap with capacity of 10.
     */
    public HashTableMap() {
	this.list = new Object[10];
	for (int i = 0; i < this.list.length; ++i) {
	    this.list[i] = new LinkedList<Node>();
	}
	this.size = 0;
    }

    /**
     * Constructor of HashTableMap with the given capacity.
     * 
     * @param capacity the given capacity
     */
    public HashTableMap(int capacity) {
	this.list = new Object[capacity];
	for (int i = 0; i < this.list.length; ++i) {
	    this.list[i] = new LinkedList<Node>();
	}
	this.size = 0;
    }

    /**
     * This is a private helper method that can double the capacity of the hash
     * table and rehash the all nodes to their new position.
     */
    @SuppressWarnings("unchecked")
    private void growCapacity() {
	// create a new single dimension array to store nodes
	Object[] newList = new Object[this.list.length * 2];
	for (int i = 0; i < newList.length; ++i) {
	    newList[i] = new LinkedList<Node>();
	}

	// rehashing
	Node crtNode;
	int hashValue;
	for (int i = 0; i < this.list.length; ++i) {
	    while (((LinkedList<HashTableMap<KeyType, ValueType>.Node>) this.list[i]).size() > 0) {
		crtNode = ((LinkedList<HashTableMap<KeyType, ValueType>.Node>) this.list[i]).poll();
		hashValue = Math.abs(crtNode.key.hashCode()) % newList.length;

		Node newNode = new Node(crtNode.key, crtNode.value);
		((LinkedList<HashTableMap<KeyType, ValueType>.Node>) newList[hashValue]).add(newNode);
	    }
	}

	this.list = newList;
    }

    /**
     * This method can put a new key-value pair into a proper position.
     * 
     * @param key   the given key
     * @param value the given value
     * @return true if the new key-value pair is settled properly, false if key is
     *         null or is already existed in the hash table
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean put(KeyType key, ValueType value) {
	// check if the given key is null
	if (key == null) {
	    return false;
	}

	// calculate the hash value of the key
	int hashValue = Math.abs(key.hashCode()) % this.list.length;

	// check that if there is any repetition
	for (Node crt : ((LinkedList<HashTableMap<KeyType, ValueType>.Node>) this.list[hashValue])) {
	    if (crt.key.equals(key)) {
		return false;
	    }
	}

	// add the new node to the LinkedList
	((LinkedList<HashTableMap<KeyType, ValueType>.Node>) this.list[hashValue]).add(new Node(key, value));

	// increment the size, check the capacity
	size += 1;
	if ((double) size / (double) this.list.length >= 0.85) {
	    growCapacity();
	}
	return true;
    }

    /**
     * This method returns the corresponding value of the given key.
     * 
     * @param key the given key
     * @throws NoSuchElementException if there is no such key existed in this hash
     *                                table
     */
    @SuppressWarnings("unchecked")
    @Override
    public ValueType get(KeyType key) throws NoSuchElementException {
	// check that if the given key is null
	if (key == null) {
	    throw new NoSuchElementException("The given key is null!");
	}

	// calculate the hashValue
	int hashValue = Math.abs(key.hashCode()) % this.list.length;

	// traverse the hash table and check that if there is such key existed
	for (Node crt : ((LinkedList<HashTableMap<KeyType, ValueType>.Node>) this.list[hashValue])) {
	    if (crt.key.equals(key)) {
		return crt.value;
	    }
	}
	throw new NoSuchElementException("No such key existed in this hash table!");
    }

    /**
     * Return the size of this hash table.
     * 
     * @return the size of this hash table
     */
    @Override
    public int size() {
	return this.size;
    }

    /**
     * This method check that if the given key is contained in the hash table or
     * not.
     * 
     * @param key the given key
     * @return true if the given key existed in the hash table, false otherwise.
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean containsKey(KeyType key) {
	// check that if the given key is null
	if (key == null) {
	    return false;
	}

	// calculate the hashValue
	int hashValue = Math.abs(key.hashCode()) % this.list.length;

	// traverse the hash table and check that if there is such key existed
	for (Node crt : ((LinkedList<HashTableMap<KeyType, ValueType>.Node>) this.list[hashValue])) {
	    if (crt.key.equals(key)) {
		return true;
	    }
	}

	// if not found, then return false
	return false;
    }

    /**
     * This method remove the key-value pair from the hash table corresponds to the
     * given key and returns the value of this pair if the key is existed in this
     * hash table.
     * 
     * @param key the given key
     * @return the value corresponds to the key, null if the given key is not in the
     *         hash table
     */
    @SuppressWarnings("unchecked")
    @Override
    public ValueType remove(KeyType key) {
	// check that if the given key is null
	if (key == null) {
	    return null;
	}

	// calculate the hashValue
	int hashValue = Math.abs(key.hashCode()) % this.list.length;

	int index = 0;
	// traverse the hash table and check that if there is such key existed
	for (Node crt : ((LinkedList<HashTableMap<KeyType, ValueType>.Node>) this.list[hashValue])) {
	    if (crt.key.equals(key)) {
		((LinkedList<HashTableMap<KeyType, ValueType>.Node>) this.list[hashValue]).remove(index);
		this.size -= 1;
		return crt.value;
	    }
	    index += 1;
	}

	return null;
    }

    /**
     * Remove all the key-value pair of this hash table.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
	for (int i = 0; i < this.list.length; ++i) {
	    ((LinkedList<HashTableMap<KeyType, ValueType>.Node>) this.list[i]).clear();
	}
	this.size = 0;

    }

    /**
     * Get an ArrayList that contains all the keys within it.
     * 
     * @return an ArrayList that contains all the keys.
     */
    public ArrayList<KeyType> keySet() {
	ArrayList<KeyType> result = new ArrayList<KeyType>();
	for (int i = 0; i < this.list.length; ++i) {
	    for (Node crt : (LinkedList<Node>) this.list[i]) {
		result.add(crt.key);
	    }
	}
	return result;
    }

    /**
     * Get an ArrayList that contains all the values within it.
     * 
     * @return an ArrayList that contains all the values.
     */
    public ArrayList<ValueType> valueSet() {
	ArrayList<ValueType> result = new ArrayList<ValueType>();
	for (int i = 0; i < this.list.length; ++i) {
	    for (Node crt : (LinkedList<Node>) this.list[i]) {
		result.add(crt.value);
	    }
	}
	return result;
    }

}
