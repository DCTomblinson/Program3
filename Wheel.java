/**
 * the wheel class for Program#01
 * imports scanner and random to run properly
 * has a name, a state of spinning, a list, and a copy list
 */
import java.util.Random;
import java.util.Scanner;
public class Wheel{
    
    private String name;
    private boolean spinning;
    public MyList list;
    private MyList reloadList;
    Scanner sc = new Scanner(System.in);

    /**
     * ctor for a wheel object that is default
     */
    public Wheel(){
        this.name = "Wheel";
        this.spinning = false;
        this.list = new MyList();
        this.reloadList = new MyList(list);
    }
    
    /**
     * gets the size of list
     * @return numItems
     */
    public int length(){
        int numItems=list.numItems();
        return numItems;
    }

    /**
     * prints all of the items in the list or a special case if empty
     */
    public void printW(){
        if(list.numItems()==0){
            System.out.println("This is an empty list.");
        }
        
        for(int i = 0; i < list.numItems(); i++){
            System.out.println(list.get(i));
        }
    }

    /**
     * prints the first item in the list
     * or null if empty
     */
    public void printF(){
        if(list.getFirst()==null){
            System.out.println("null");
        }
        
        else{
            System.out.println(list.getFirst());
        }
    }

    /**
     * prints the last item in the list
     * or null if empty
     */
    public void printL(){
        if(list.getLast()==null){
            System.out.println("null");
        }
        
        else{
            System.out.println(list.getLast());
        }
    }

    /**
     * clears the list
     */
    public void clear(){
        list.clear();
    }

    /**
     * adds a new node to the end of the list
     * the value of the node is received via user input
     */
    public void add(String s){
        
        list.addToEnd(s);
        
        //set the new reloadlist to the current list
        reloadList = list;
        spinning = false;
    }

    /**
     * names the wheel after getting the name from user
     */
    public void name(){
        System.out.println("What would you like the wheel to be named?");
        this.name = sc.nextLine();
    }

    /**
     * calls the spin methods in the proper order
     */
    public void spinSet(){
        this.spin1();
        this.spin2();
    }

    /**
     * sets spinning to true and prints out a custom message to 
     * wow the user while they wait for spin2
     */
    public void spin1(){
        if(spinning == false){
            spinning=true;
        }
        for(int i = 0 ; i < 4; i++){
            System.out.println(this.name + " Spinning....");
        }
    }

    /**
     * gets a random number, finds the randomth element,
     * and then prints it
     */
    public String random(){
        
        //special case if it is an empty list
        if(list.numItems() == 0){
            System.out.println("There are no items to randomize.");
            return null;
        }
        
        else{
            //build random object to get a random integer for the list
            Random random = new Random();
            int ranNum = random.nextInt(list.numItems()-1);
            String ranStr = list.get(ranNum);
            return ranStr;
        }
    }
    
    /**
     * calls performs random method on its own
     * and then removes the node at the
     * randomth index
     */
    public void spin2(){
        if(list.numItems() == 0){
            System.out.println("There are no items to spin.");
        }
        else{
            //build random object to get a random integer for the list
            Random random = new Random();
            int ranNum = random.nextInt(list.numItems()-1);
            String ranStr = list.get(ranNum);
            
            //Print and remove the randomth node
            System.out.println(ranStr);
            list.remove(ranNum);
        }
    }

    /**
     * reloads an old list if items have been reoved and none added
     */
    public void reload(){
        if(reloadList != null){
            list = reloadList;
        }
    }

    /**
     * reverses the list and sets the reloadlist to the new list
     */
    public void reverse(){
        list.reverse();
        reloadList = list;
        spinning = false;
    }

    /**
     * Removes an item from the list with value Str
     * @param str the string value of the object to be removed 
     */
    public void remove(String str){
        list.remove(str);
    }
}