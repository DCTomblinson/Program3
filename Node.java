/**
 * Class for Nodes for Program#01
 * the values are always a string and the pointers next and prev point
 * to other nodes, or null if there are none preceeding of following them
 * It is all built by hand, no JCF.
 */
public class Node{
    private String value;
    public Node next;
    public Node prev;


    /**
     * ctor for a node 
     * @param value which is a string 
     * */
    public Node(String value){
        this.value = value;
        next = null;
        prev = null;

    }

    /**
     * method to retrieve the value in a Node
     * @return the value of a Node
     * */
    public String getValue(){
        return this.value;
    }

    /**
     * method to retrieve the next Node
     * @return the next Node after the current
     * */
    public Node getNext(){
        return this.next;
    }

    /**
     * method to retrieve the previous Node
     * @return the previous Node before the current
     * */
    public Node getPrevious(){
        return this.prev;
    }

    /**
     * method sets the next Node
     * @param newNode Node that is to be pointed at as next
     * */
     public void setNext(Node newNode){
         this.next = newNode;
     }

    /**
     * method sets the prev Node
     * @param newNode Node that is to be pointed at as previous
     * */ 
     public void setPrevious(Node newNode){
         this.prev = newNode;
     }

}