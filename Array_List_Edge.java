public class Array_List_Edge  {

private Edge[] array;
public static final int DEFAULT_SIZE = 20000;
private int size = 0;

public Array_List_Edge(){
    this(DEFAULT_SIZE);
}

public Array_List_Edge(int size){
    array = new Edge[size];
}

public void add(Edge element){
    ensureCapacity();
    array[size++] = element;
}

public Edge remove(int index){
    if(index>=size || index < 0 ){throw new RuntimeException("Invalid index");}
    Edge element = (Edge) array[index];
    array[index] = null;
    --size;
  //  compress();
    return element;
}

public Edge get(int index){
    if(index > size){throw new RuntimeException("Invalid index");}
    Edge element = (Edge) array[index];
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
    Edge[] temp = new Edge[array.length*2];
    copy(array,temp);
    array = (Edge[]) temp;
}

private void copy(Object[] src, Object[] dest){
    if(dest.length< src.length){throw new RuntimeException(src+ " cannot be copied into "+dest);}
    for(int i=0;i<src.length; i++){
        dest[i] = src[i];
    }   
}
public boolean contains(Edge elem) {
	for(int i=0;i<size;i++) {
		//Edge temp=(Edge)array[i];
		if(array[i].equals1(elem)) return true;
		//Edge p1=new Edge(0,0,0);
		
	}
	return false;
	
}
}