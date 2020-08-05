package col106.assignment3.Election;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Arrays;

public class Election implements ElectionInterface {
	/* 
	 * Do not touch the code inside the upcoming block 
	 * If anything tempered your marks will be directly cut to zero
	*/
	public static void main() {
		ElectionDriverCode EDC = new ElectionDriverCode();
		System.setOut(EDC.fileout());
	}
	/*
	 * end code
	 */
	
	//write your code here 
	class Candidate{
		String name;
		String candID ;
		String state;
		String district;
		String constituency;
		String party ;
		String votes;
	}
	class node{
		// of bst
		Candidate key ;
		String value;
		node left;
		node right;
	}
		
	public class BST{
		/*class node{
			Candidate key ;
			String value;
			node left;
			node right;
		}
		*/
		public node root;
		//private Boolean found = false;
		
		
		
		
		private node insertfake(node r , Candidate key , String value){
			if( r == null ){
				r = new node() ;
				r.key = key ;
				r.value = value ; 
			}
			else if ( Integer.parseInt(value) < Integer.parseInt(r.value) ){
				r.left = insertfake(r.left,key,value) ;
			}
			else{
				r.right = insertfake(r.right,key,value);

			}
			return r ; 
		}
		
	 
	    public void insert(Candidate key, String value) {
			//write your code here
	    	root = insertfake(root,key,value);
	    }

	    public void update(Candidate key, String value) {
			//write your code here
	    	delete(key);
	    	key.votes = value ;
	    	insert(key,value);
	    	
	    }
	    private node findmin(node r) {
	    	if( r == null) return null ; 
	    	if ( r.left == null ) {
	    		return r ; 
	    	}
	    	return findmin(r.left);
	    	
	    }
	    private node deletefake(node r , Candidate key) {
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
	    			Candidate tempkey = temp.key ;
	    			String tempval = temp.value ;
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
	    
	    public void delete(Candidate key) {
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
		
	public class Heap{
		
		class Data{
			Candidate key ;
			String value ;
		}
		ArrayList<Data> heap = new ArrayList<Data>();
		private int getParent(int i) {
			return (i-1)/2 ;
		}
		private int getLeft(int i) {
			return 2*i + 1;
		}
		private int getRight(int i) {
			return 2*i + 2 ;
		}
		Candidate getKey(int i ){
			return heap.get(i).key;
		}
		public void insert(Candidate key, String value) {
			Data pair = new Data();
			pair.key = key ; 
			pair.value = value ; 
			if( heap.size() == 0 ) {
				heap.add(pair);
			}
			else {
				heap.add(pair);
				int curr = heap.size()-1;
				while( curr != 0 && getParent(curr) >= 0 && Integer.parseInt(heap.get(curr).value)> Integer.parseInt((heap.get(getParent(curr)).value))   ) {
					Candidate key1 = (heap.get(curr)).key ;
					Candidate key2 = (heap.get(getParent(curr))).key ;
					(heap.get(getParent(curr))).key = key1 ;
					(heap.get(curr)).key = key2 ; 
					
					String value1 = (heap.get(curr)).value ;
					String value2 = (heap.get(getParent(curr))).value ;
					(heap.get(getParent(curr))).value = value1 ;
					(heap.get(curr)).value = value2 ; 
					curr = getParent(curr) ;
				}
				
			}	
		}
		ArrayList<Data> makeheapfake(int index ,ArrayList<Data> h){
			int li = getLeft(index);
			int ri = getRight(index);
			if(li >= h.size() || ri >= h.size()) {
				return h ;
			}
			int rootindex = index;
			int largeindex = rootindex ; 
			if ( li < h.size()){
				largeindex = li ;
				
			}
			if ( ri < h.size()) {
				if ( h.get(li).value.compareTo(h.get(ri).value) < 0  ) {
					largeindex = ri ;
				}
				
			}
			if( h.get(rootindex).value.compareTo(h.get(largeindex).value) < 0 ){
				Candidate key1 = (h.get(rootindex)).key ;
				Candidate key2 = (h.get(largeindex)).key ;
				(h.get(largeindex)).key = key1 ;
				(h.get(rootindex)).key = key2 ; 
				String value1 = (h.get(rootindex)).value ;
				String value2 = (h.get(largeindex)).value ;
				(h.get(largeindex)).value = value1 ;
				(h.get(rootindex)).value = value2 ; 
				h = makeheapfake(largeindex,h);
			}
			
			return h ;
		}
		private void makeHeap(int index) {
			heap = makeheapfake(index,heap);
			
		}
		public String extractMax() {
			Candidate key1 = (heap.get(0)).key ;
			Candidate key2 = (heap.get(heap.size()-1)).key ;
			(heap.get(heap.size()-1)).key = key1 ;
			(heap.get(0)).key = key2 ;
			
			String value1 = (heap.get(0)).value ;
			String value2 = (heap.get(heap.size()-1)).value ;
			(heap.get(heap.size()-1)).value = value1 ;
			(heap.get(0)).value = value2 ;
			
			String val = heap.get(heap.size()-1).value ;
			heap.remove(heap.size()-1);
			makeHeap(0);
			return val ;
		}

		public void delete(Candidate key) {
			int index = 0 ;
			for( int i = 0 ; i < heap.size() ; i++) {
				if ( heap.get(i).key == key) {
					index = i ;
					break;
				}
			}
			while(index != 0){
				Candidate key1 = (heap.get(index)).key ;
				Candidate key2 = (heap.get(getParent(index))).key ;
				(heap.get(getParent(index))).key = key1 ;
				(heap.get(index)).key = key2 ; 
				
				String value1 = (heap.get(index)).value ;
				String value2 = (heap.get(getParent(index))).value ;
				(heap.get(getParent(index))).value = value1 ;
				(heap.get(index)).value = value2 ; 
				index = getParent(index) ;
			}	
			Candidate key1 = (heap.get(0)).key ;
			Candidate key2 = (heap.get(heap.size()-1)).key ;
			(heap.get(heap.size()-1)).key = key1 ;
			(heap.get(0)).key = key2 ;
			
			String value1 = (heap.get(0)).value ;
			String value2 = (heap.get(heap.size()-1)).value ;
			(heap.get(heap.size()-1)).value = value1 ;
			(heap.get(0)).value = value2 ;
			
			//String val = heap.get(heap.size()-1).value ;
			heap.remove(heap.size()-1);
			makeHeap(0);
		}

		public void increaseKey(Candidate key, String value) {
			//write your code here
			//key.votes = value ; 
			int index = 0 ;
			for( int i = 0 ; i < heap.size() ; i++) {
				if ( heap.get(i).key == key) {
					index = i ;
					break;
				}
			}
			
			heap.get(index).value = value ;
			heap.get(index).key.votes = value ; 
			key.votes = value ; 
			while( index != 0 && heap.get(index).value.compareTo(heap.get(getParent(index)).value) > 0  ){
				Candidate key1 = (heap.get(index)).key ;
				Candidate key2 = (heap.get(getParent(index))).key ;
				(heap.get(getParent(index))).key = key1 ;
				(heap.get(index)).key = key2 ; 
				
				String value1 = (heap.get(index)).value ;
				String value2 = (heap.get(getParent(index))).value ;
				(heap.get(getParent(index))).value = value1 ;
				(heap.get(index)).value = value2 ; 
				index = getParent(index) ;
			}
		    
		}

		public void printHeap() {
			//write your code here
			for (int i = 0 ; i < heap.size(); i++ ) {
				System.out.println(heap.get(i).key + ", " + heap.get(i).value ) ;
			}
		}	
	}
	
	BST bst = new BST();
	Heap heap = new Heap();
	
	
    public void insert(String name, String candID, String state, String district, String constituency, String party, String votes){
		//write your code here 
    	Candidate person = new Candidate();
    	person.name = name ;
    	person.candID = candID ; 
    	
    	person.state = state ;
    	person.district = district ; 
    	person.constituency = constituency ; 
    	person.party = party ;
    	person.votes = votes ;
    	
    	
    	bst.insert(person, person.votes);
    	heap.insert(person, person.votes);
    	
    	
    	
    	
	}
    
	public void updateVote(String name, String candID, String votes){
		//write your code here
		Candidate uc = new Candidate() ; 
		for ( int i = 0 ; i < heap.heap.size(); i++) {
			Candidate c = (Candidate)heap.getKey(i);
			if ( c.candID.equals(candID)) {
				uc = c ;
				break ;
			}
			 
		}
		heap.increaseKey(uc, votes);
		bst.update(uc, votes);
	}
	


	int i = 0 ; 
	void topkc(String constituency , int k , node r ){
		if(r.right != null ) {
			topkc(constituency,k,r.right) ;
			
		}
		
		
		if( r.key.constituency.equals(constituency) ) {
			i++;
			if ( i > k ) {
				return;
			}
			System.out.println(r.key.name + ", " + r.key.candID + ", " + r.key.party);
		}
		if(r.left != null ) {
			topkc(constituency,k,r.left) ;
			
		}
		
	}
	public void topkInConstituency(String constituency, String k){
		//write your code here
		i = 0 ;
		if( bst.root != null){
			node root = bst.root ;
		    int l = Integer.parseInt(k);
			topkc(constituency,l,root);
		}
		
	}
	public void leadingPartyInState(String state){
		//write your code here
		Heap stateheap = new Heap() ; 
		for ( int i = 0 ; i < heap.heap.size() ; i++ ) {
			Candidate c = heap.heap.get(i).key ;
			if(c.state.equals(state)) {
				stateheap.insert(c, c.votes);
			}
		}
		HashMap<String,Integer> pandv = new HashMap<>();
		for(int i = 0 ; i < stateheap.heap.size(); i ++) {
			Candidate c = stateheap.heap.get(i).key ;
			if ( pandv.containsKey(c.party)) {
				Integer oldval = pandv.get(c.party);
				Integer add = Integer.valueOf(c.votes);
				
				pandv.replace(c.party, oldval + add); 
			}
			else {
				Integer val = Integer.valueOf(c.votes);
				pandv.put(c.party , val) ;
			}	
		}
		Iterator it = pandv.entrySet().iterator();
		int mv = 0 ; 
		while (it.hasNext()) { 
            Map.Entry entry = (Map.Entry)it.next(); 
            //entry.getKey();
            int val = (int)entry.getValue();
            if ( val > mv ) {
            	mv = val ; 
            }  
        } 
		
		Iterator it2 = pandv.entrySet().iterator();
		int count = 0 ; 
		while (it2.hasNext()) { 
            Map.Entry entry = (Map.Entry)it2.next(); 
            //entry.getKey();
            int val = (int)entry.getValue();
            if ( val == mv ) {
            	count++; 
            }  
        } 
		String[] arr = new String[count];
		int i = 0 ; 
		Iterator it3 = pandv.entrySet().iterator();
		while (it3.hasNext()) { 
            Map.Entry entry = (Map.Entry)it3.next(); 
            //entry.getKey();
            int val = (int)entry.getValue();
            if ( val == mv ) {
            	arr[i] = (String)entry.getKey();
            	 i++;
            }  
        } 
		Arrays.sort(arr);
		for(int j = 0 ; j < arr.length ; j++) {
			System.out.println(arr[j]);
		}
	}
	public void cancelVoteConstituency(String constituency){
		//write your code here
		Heap h2 = new Heap();
		for(int i = 0 ; i < heap.heap.size(); i++) {
			Candidate c = heap.heap.get(i).key ;
			if(c.constituency.equals( constituency)) {
				bst.delete(c);
			}
			else {
				h2.insert(c, c.votes);
			}
		}
		heap = h2 ;
	}
	public void leadingPartyOverall(){
		//write your code here
		HashMap<String,Integer> pandv = new HashMap<>();
		for(int i = 0 ; i < heap.heap.size(); i ++) {
			Candidate c = heap.heap.get(i).key ;
			if ( pandv.containsKey(c.party)) {
				Integer oldval = pandv.get(c.party);
				Integer add = Integer.valueOf(c.votes);
				
				pandv.replace(c.party, oldval + add); 
			}
			else {
				Integer val = Integer.valueOf(c.votes);
				pandv.put(c.party , val) ;
			}	
		}
		Iterator it = pandv.entrySet().iterator();
		int mv = 0 ; 
		while (it.hasNext()) { 
            Map.Entry entry = (Map.Entry)it.next(); 
            //entry.getKey();
            int val = (int)entry.getValue();
            if ( val > mv ) {
            	mv = val ; 
            }  
        } 
		
		Iterator it2 = pandv.entrySet().iterator();
		int count = 0 ; 
		while (it2.hasNext()) { 
            Map.Entry entry = (Map.Entry)it2.next(); 
            //entry.getKey();
            int val = (int)entry.getValue();
            if ( val == mv ) {
            	count++; 
            }  
        } 
		String[] arr = new String[count];
		int i = 0 ; 
		Iterator it3 = pandv.entrySet().iterator();
		while (it3.hasNext()) { 
            Map.Entry entry = (Map.Entry)it3.next(); 
            //entry.getKey();
            int val = (int)entry.getValue();
            if ( val == mv ) {
            	arr[i] = (String)entry.getKey();
            	 i++;
            }  
        } 
		Arrays.sort(arr);
		for(int j = 0 ; j < arr.length ; j++) {
			System.out.println(arr[j]);
		}
		
		
		
		
	}
	public void voteShareInState(String party,String state){
		//write your code here
		int t = 0 ;
		int p = 0 ;
		for(int i = 0 ; i < heap.heap.size(); i++) {
			if ( heap.heap.get(i).key.state.equals(state)) {
				t+= Integer.parseInt(heap.heap.get(i).key.votes);
				if(heap.heap.get(i).key.party.equals(party)) {
					p+= Integer.parseInt(heap.heap.get(i).key.votes);
				}
			}
		}
		if ( t == 0 ){
			System.out.println(0);
		}
		else{
			//System.out.println((p*100)/t) ;
			System.out.println( (int) (100 * ( (float)p / (float)t) ));
            
		}
		
	}
	
	public void printElectionLevelOrder() {
		//write your code here
		Queue<node> q = new LinkedList<node>(); 
		if ( bst.root != null ) {
			q.add(bst.root); 
			
		}
        
        while (!q.isEmpty())  
        {   
            node temp = q.poll(); 
            System.out.println(temp.key.name + ", " + temp.key.candID + ", " + temp.key.state + ", " + temp.key.district + ", " + temp.key.constituency +", " + temp.key.party + ", " + temp.key.votes);
            if (temp.left != null) { 
                q.add(temp.left); 
            } 
            if (temp.right != null) { 
                q.add(temp.right); 
            } 
        } 
		
	}
	/*
    public void insert(String name, String candID, String state, String district, String constituency, String party, String votes){
		//write your code here 
	}
	public void updateVote(String name, String candID, String votes){
		//write your code here
	}
	public void topkInConstituency(String constituency, String k){
		//write your code here
	}
	public void leadingPartyInState(String state){
		//write your code here
	}
	public void cancelVoteConstituency(String constituency){
		//write your code here
	}
	public void leadingPartyOverall(){
		//write your code here
	}
	public void voteShareInState(String party,String state){
		//write your code here
	}
	
	public void printElectionLevelOrder() {
		//write your code here
		
	}
	*/
}











