/**
 * Linked list class for Program#01
 * implements List210 to make code shareable in class
 * all linked lists are built by hand, no JCF
 */
public class MyList implements List210{
    private Node head;
    private Node tail;

    /**
     * ctor to create an object of the class withno nodes in the list
     */
    public MyList(){
        head = null;
        tail = null;
    }

    /**
     * copy ctor for an object of class MyList
     * @param list
     */
    public MyList(MyList list){
        head = list.head;
        tail = list.tail;
    }

    /**
     * Add item to the end of the list.
     * @param item The item to add, should not be null.
     */
    public void addToEnd(String item){
        Node n = new Node(item);

        //Case of an empty list
        if(this.head==null){
            this.head=n;
            this.tail=n;
        }

        //Case of there not being an empty list
        else{
            n.setPrevious(tail);
            tail.setNext(n);
            this.tail=n;
        }

    }

    /**
     * Add item to the beginning of the list.
     * @param item The item to add, should not be null.
     */
    public void addToBeginning(String item){
        Node n = new Node(item);

        //Case of there being an empty list
        if(head==null){
            head=n;
            tail=n;
        }

        //Case of there not being an empty list
        else{
            n.setNext(head);
            head.setPrevious(n);
            head=n;
        }
    }
    
    /**
     * Returns the size of the list, the number of items.
     * @return Returns the number of items
     */
    public int numItems(){
        int itemCount= 0;
        Node n = head;
        
        //As long as it is not the end of the list count it and increment
        while(n != null){
            itemCount++;
            n=n.getNext();
        }
        
        //return the number of items in the list
        return itemCount;
    }
    
    /**
     * Returns the first item in the list.
     * @return Returns the first item; null on empty list
     */
    public String getFirst(){
        String fir = null;
        
        /*determine if the list is empty, then get the appropriate
        first value and return it*/
        if(head != null){
            fir=head.getValue();
            return fir;
        }

        else{
            return fir;
        }
    }

    /**
     * Returns the last item in the list.
     * @return Returns the last item; null on empty list.
     */
    public String getLast(){
        String last = null;
        
        /*determine if the list is empty, then get the appropriate
        last value and return it*/
        if(tail != null){
            last=tail.getValue();
            return last;
        }

        else{
            return last;
        }
    }

    /**
     * Clears the list, removing all items.
     */
    public void clear(){
        head=null;
        tail=null;
    }

    /**
     * Returns true if the list is empty, has no items.
     * @return Returns true if empty
     */
    public boolean isEmpty(){
        if(head.equals(null) && tail.equals(null)){
            return true;
        }

        else{
            return false;
        }
    }

    /**
     * Gets an item in the list.
     * @param item The item to get
     * @return Returns true if item is found; false otw
     */
    public boolean get(String item){
        boolean found = false;
        Node n = head;
        int count = 0;
        
        /*in the case that it is not yet found and it has not incremented
        through the entire list of items yet the loop executes*/
        while(!n.getValue().equals(item) && !found && (count < this.numItems())){

            //if it's found then set it to true and return it
            if(n.getValue().equals(item)){
                found = true;
                return found;
            }
            
            //otherwise increment it and increase the count
            else{
                n = n.getNext();
                count++;
            }
        }
        
        //return the false if the string was not found
        return found;
    }

    /**
     * Gets the i-th item in the list.
     * @param i The i-th item is found
     * @return Returns the i-th item if found; null otw
     */

    public String get(int i){
        Node n = head;
        int count = 0;
        
        //Until it increments to ith term and is a valid index
        if( i < this.numItems()){
            while((count < i)){
                n = n.getNext();
                count++;
            }
            
            //return the node at index i
            return n.getValue();
        }

        //in case the value at i is null
        else{
            System.out.println("There is no value at index " + i);
	    return null;
        }
    }

    /**
     * Reverse the order of items in the list.
     */
    public void reverse(){
        Node temp = null;
        Node tailTemp = head; 
        Node current = head; 
        
        /* swap next and prev for all nodes of  
         doubly linked list */
        while (current != null) { 
            temp = current.prev; 
            current.prev = current.next; 
            current.next = temp; 
            current = current.prev; 
            } 
      
        /* Before changing head, check for the cases like empty  
         list and list with only one node */
        if (temp != null) { 
            head = temp.prev; 
        }
        
        //set the tail properly
        tail = tailTemp;
    }

    /**
     * removes a node from the linked list and properly
     * sets pointers
     * @param n the node to remove
     */
    public void removeNode(Node n){
        //case for removing the head
        if( n == head){
            head = head.getNext();
            head.setPrevious(null);
        }

        //case for reoving the tail
        else if(n == tail){
            tail = tail.getPrevious();
            tail.setNext(null);
        }

        //case for removing any node other than the head or tail
        else{
            Node after = n.getNext();
            Node before = n.getPrevious();
            before.setNext(after);
            after.setPrevious(before);
        }
    }
    
    /**
     * Removes the i-th item in the list.
     * @param i The index into the list
     * @return Returns true on success; false if no value at i-th position
     */
    public boolean remove(int i){
        Node n = head;
        int count = 0;
        
        //Until it increments to ith term and is a valid index
        if(i < this.numItems()){
            while((count < i)){
                n = n.getNext();
                count++;
            }
            
            //remove and return the node at index i
            this.removeNode(n);
            return true;
        }

        //return fasle if the index was not valid or was null
        else{
            return false;
        }
    }

    /**
     * Remove an item from the list. The first item matching the string
     * is removed.
     * @param item The item to remove
     * @return Returns true on success; false if no matching item
     */
    public boolean remove(String item){
        boolean found = false;
        Node n = head;
        int count = 0;
        
        /*in the case that it is not yet found and it has not incremented
        through the entire list of items yet the loop executes*/
        while(!n.getValue().equals(item) && !found && (count < this.numItems())){

            //if it's found then delete the node and set found to true and return it
            if(n.getValue().equals(item)){
                found = true;
                this.removeNode(n);
                return found;
            }
            
            //otherwise increment it and increase the count
            else{
                n = n.getNext();
                count++;
            }
        }
        
        //return the false if the string was not found
        return found;
    }

}