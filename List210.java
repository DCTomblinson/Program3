/**
 * Interface to the linked list in Program #1.
 * The list holds String  values and is built by hand, no JCF.
 * 
 * Note: copy and print aren't specified in the interface.
 * 1) Your implementation will probably include a toString().
 * 2) In your list, create a copy ctor to copy your list.
 */
public interface List210 {
    /**
     * Add item to the end of the list.
     * @param item The item to add, should not be null.
     */
    public void addToEnd( String item);
  
    /**
     * Add item to the beginning of the list.
     * @param item The item to add, should not be null.
     */
    public void addToBeginning(String str);
  
    /**
     * Gets an item in the list.
     * @param item The item to get
     * @return Returns true if item is found; false otw
     */
    public boolean get( String item);
  
    /**
     * Gets the i-th item in the list.
     * @param i The i-th item is found
     * @return Returns the i-th item if found; null otw
     */
    public String get( int i);
  
    /**
     * Returns the first item in the list.
     * @return Returns the first item; null on empty list
     */
    public String getFirst();
  
    /**
     * Returns the last item in the list.
     * @return Returns the last item; null on empty list.
     */
    public String getLast();
  
    /**
     * Remove an item from the list. The first item matching the string
     * is removed.
     * @param item The item to remove
     * @return Returns true on success; false if no matching item
     */
    public boolean remove( String item);
  
    /**
     * Removes the i-th item in the list.
     * @param i The index into the list
     * @return Returns true on success; false if no value at i-th position
     */
    public boolean remove( int i);
  
    /**
     * Returns the size of the list, the number of items.
     * @return Returns the number of items
     */
    public int numItems();
  
    /**
     * Clears the list, removing all items.
     */
    public void clear();
  
    /**
     * Returns true if the list is empty, has no items.
     * @return Returns true if empty
     */
    public boolean isEmpty();
  
    /**
     * Reverse the order of items in the list.
     */
    public void reverse();
  
  }