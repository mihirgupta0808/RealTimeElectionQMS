package col106.assignment3.Heap;
import java.util.ArrayList;

public class Heap<T, E extends Comparable> implements HeapInterface <T, E> {
	
	/* 
	 * Do not touch the code inside the upcoming block 
	 * If anything tempered your marks will be directly cut to zero
	*/
	public static void main() {
		HeapDriverCode HDC = new HeapDriverCode();
		System.setOut(HDC.fileout());
	}
	/*
	 * end code
	 */
	
	// write your code here	



	class Data{
		T key ;
		E value ;
	}
	ArrayList<Data> heap = new ArrayList<Data>();
	//int len = 0;
	private int getParent(int i) {
		return (i-1)/2 ;
	}
	private int getLeft(int i) {
		return 2*i + 1;
	}
	private int getRight(int i) {
		return 2*i + 2 ;
	}
	public void insert(T key, E value) {
		//write your code here
		Data pair = new Data();
		pair.key = key ; 
		pair.value = value ; 
		if( heap.size() == 0 ) {
			heap.add(pair);
			//len++;
		}
		else {
			heap.add(pair);
			//len++ ;
			int curr = heap.size()-1;
			//heap.set(curr,pair);
			while( curr != 0 && getParent(curr) >= 0 && (heap.get(curr)).value.compareTo( (heap.get(getParent(curr)).value)) > 0   ) {
				T key1 = (heap.get(curr)).key ;
				T key2 = (heap.get(getParent(curr))).key ;
				(heap.get(getParent(curr))).key = key1 ;
				(heap.get(curr)).key = key2 ; 
				
				E value1 = (heap.get(curr)).value ;
				E value2 = (heap.get(getParent(curr))).value ;
				(heap.get(getParent(curr))).value = value1 ;
				(heap.get(curr)).value = value2 ; 
				curr = getParent(curr) ;
			}
			
		}
		
		/*
		String a = "a";
		String b = "b";
		String c = a ;
		String d = b ;
		b = c ;
		a = d ;
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(d);
		*/
		 
		
		
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
			T key1 = (h.get(rootindex)).key ;
			T key2 = (h.get(largeindex)).key ;
			(h.get(largeindex)).key = key1 ;
			(h.get(rootindex)).key = key2 ; 
			E value1 = (h.get(rootindex)).value ;
			E value2 = (h.get(largeindex)).value ;
			(h.get(largeindex)).value = value1 ;
			(h.get(rootindex)).value = value2 ; 
			h = makeheapfake(largeindex,h);
		}
		
		return h ;
		
		
		
		
		
		
	}
	
	
	private void makeHeap(int index) {
		heap = makeheapfake(index,heap);
		
	}
	

	public E extractMax() {
		//write your code here
		/*E maxv = heap.get(0).value;
		heap.remove(0);
		
		return maxv;
		*/
		T key1 = (heap.get(0)).key ;
		T key2 = (heap.get(heap.size()-1)).key ;
		(heap.get(heap.size()-1)).key = key1 ;
		(heap.get(0)).key = key2 ;
		
		E value1 = (heap.get(0)).value ;
		E value2 = (heap.get(heap.size()-1)).value ;
		(heap.get(heap.size()-1)).value = value1 ;
		(heap.get(0)).value = value2 ;
		
		E val = heap.get(heap.size()-1).value ;
		heap.remove(heap.size()-1);
		makeHeap(0);
		
		
		return val ;
		
		
		
		
		
		
		
	}

	public void delete(T key) {
		//write your code here
		/*
		int delindex = 0;
		for ( int i = 0 ; i < heap.size(); i++) {
			if( heap.get(i).key == key ) {
				delindex = i ; 
				break;
			}
		}
		//Data pair = heap.get(delindex);
		heap.remove(delindex);
		*/
		//len--;
		int index = 0 ;
		for( int i = 0 ; i < heap.size() ; i++) {
			if ( heap.get(i).key == key) {
				index = i ;
				break;
			}
		}
		
		
		while(index != 0){
			T key1 = (heap.get(index)).key ;
			T key2 = (heap.get(getParent(index))).key ;
			(heap.get(getParent(index))).key = key1 ;
			(heap.get(index)).key = key2 ; 
			
			E value1 = (heap.get(index)).value ;
			E value2 = (heap.get(getParent(index))).value ;
			(heap.get(getParent(index))).value = value1 ;
			(heap.get(index)).value = value2 ; 
			index = getParent(index) ;
		}
		
		
		T key1 = (heap.get(0)).key ;
		T key2 = (heap.get(heap.size()-1)).key ;
		(heap.get(heap.size()-1)).key = key1 ;
		(heap.get(0)).key = key2 ;
		
		E value1 = (heap.get(0)).value ;
		E value2 = (heap.get(heap.size()-1)).value ;
		(heap.get(heap.size()-1)).value = value1 ;
		(heap.get(0)).value = value2 ;
		
		//E val = heap.get(heap.size()-1).value ;
		heap.remove(heap.size()-1);
		makeHeap(0);
	}

	public void increaseKey(T key, E value) {
		//write your code here
		int index = 0 ;
		for( int i = 0 ; i < heap.size() ; i++) {
			if ( heap.get(i).key == key) {
				index = i ;
				break;
			}
		}
		
		heap.get(index).value = value ;
		while( index != 0 && heap.get(index).value.compareTo(heap.get(getParent(index)).value) > 0  ){
			T key1 = (heap.get(index)).key ;
			T key2 = (heap.get(getParent(index))).key ;
			(heap.get(getParent(index))).key = key1 ;
			(heap.get(index)).key = key2 ; 
			
			E value1 = (heap.get(index)).value ;
			E value2 = (heap.get(getParent(index))).value ;
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
	
	/*
	public void insert(T key, E value) {
		//write your code here
		
	}

	public E extractMax() {
		//write your code here
		return null;
	}

	public void delete(T key) {
		//write your code here
		
	}

	public void increaseKey(T key, E value) {
		//write your code here
		
	}

	public void printHeap() {
		//write your code here
	}
	*/	
}
