public class Array_List_Triangle  {

	private Triangle[] array;
	public static final int DEFAULT_SIZE = 20000;
	private int size = 0;
	
	public Array_List_Triangle(){
	    this(DEFAULT_SIZE);
	}
	
	public Array_List_Triangle(int size){
	    array = new Triangle[size];
	}
	
	public void add(Triangle element){
	    ensureCapacity();
	    array[size++] = element;
	}
	
	public Triangle remove(int index){
	    if(index>=size || index < 0 ){throw new RuntimeException("Invalid index");}
	    Triangle element = (Triangle) array[index];
	    for(int i=index;i<size-1;i++) {
	    	array[i]=array[i+1];
	    }
	    --size;
	  //  compress();
	    return element;
	}
	
	public Triangle pop() {
		return remove(0);
	}
	
	public Triangle get(int index){
	    if(index > size){throw new RuntimeException("Invalid index");}
	    Triangle element = (Triangle) array[index];
	    return element;
	}
	
	public int size(){
	    return this.size;
	}
	
	private void ensureCapacity(){
	    if(size < array.length){ return;}
	    resize();
	}
	
	private void resize(){
		Triangle[] temp = new Triangle[array.length*2];
	    copy(array,temp);
	    array = (Triangle[]) temp;
	}
	
	private void copy(Object[] src, Object[] dest){
	    if(dest.length< src.length){throw new RuntimeException(src+ " cannot be copied into "+dest);}
	    for(int i=0;i<src.length; i++){
	        dest[i] = src[i];
	    }   
	}
	public boolean contains(Triangle elem) {
		for(int i=0;i<size;i++) {
			//Triangle temp=(Triangle)array[i];
			if(array[i].equals1(elem)) return true;
			//Triangle p1=new Triangle(0,0,0);
			
		}
		return false;
		
	}
}