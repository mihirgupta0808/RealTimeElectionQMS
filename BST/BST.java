package col106.assignment3.BST;
import java.util.Queue ;
import java.util.LinkedList; 

public class BST<T, E extends Comparable> implements BSTInterface<T, E>  {
	/* 
	 * Do not touch the code inside the upcoming block 
	 * If anything tempered your marks will be directly cut to zero
	*/
	public static void main() {
		BSTDriverCode BDC = new BSTDriverCode();
		System.setOut(BDC.fileout());
	}
	/*
	 * end code
	 * start writing your code from here
	 */
	
	//write your code here 
	class node{
		T key ;
		E value;
		node left;
		node right;
	}
	node root;
	private Boolean found = false;
	
	//This is just the standard Comparable contract. In a.compareTo(b),
	//if they are equal compareTo returns 0, if a < b returns a 
	//negative number (usually -1), if a > b returns a positive number (usually 1).
	//– sjr Feb 16 '11 at 8:33
	
	
	private node insertfake(node r , T key , E value){
		if( r == null ){
			r = new node() ;
			r.key = key ;
			r.value = value ; 
		}
		else if ( value.compareTo(r.value) < 0){
			r.left = insertfake(r.left,key,value) ;
		}
		else{
			r.right = insertfake(r.right,key,value);

		}
		return r ; 
	}
	
 
    public void insert(T key, E value) {
		//write your code here
    	root = insertfake(root,key,value);
    }

    public void update(T key, E value) {
		//write your code here
    	delete(key);
    	insert(key,value);
    }
    private node findmin(node r) {
    	if( r == null) return null ; 
    	if ( r.left == null ) {
    		return r ; 
    	}
    	return findmin(r.left);
    	
    }
    private node deletefake(node r , T key) {
    	if ( r == null ) return null ; 
    	if ( r.key == key ) { 
    		if ( r.right == null ) {
    			return r.left ;
    		}
    		else if ( r.left == null ) {
    			return r.right ;
    		}
    		else {
    			node temp = findmin(r.right);
    			T tempkey = temp.key ;
    			E tempval = temp.value ;
    			r.right = deletefake(r.right,tempkey);
    			r.key =tempkey ;
    			r.value = tempval ;
    			return r ; 
    		}
    	}
    	else {
    		
    		if ( r.left!= null) {
    			r.left = deletefake(r.left,key);
    		}
    		
    		if ( r.right!= null) {
    			r.right = deletefake(r.right,key);
    		}
    		
    	}
    	return r ; 
    }
    
    public void delete(T key) {
		//write your code here
    	root = deletefake(root,key);
    }

    public void printBST () {
        if (root == null)  {
        	return; 
        }
        Queue<node> print = new LinkedList<node>();  
        print.add(root); 
        while (print.isEmpty() == false)  
        { 
            node current = print.poll(); 
            System.out.println(current.key + ", " + current.value); 
            if (current.left != null) { 
                print.add(current.left); 
            } 
            if (current.right != null) { 
                print.add(current.right); 
            } 
        } 
    }

    

}