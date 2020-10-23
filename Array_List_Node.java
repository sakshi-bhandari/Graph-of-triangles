public class Array_List_Node  {

private node[] array;
public static final int DEFAULT_SIZE = 20000;
private int size = 0;

public Array_List_Node(){
    this(DEFAULT_SIZE);
}

public Array_List_Node(int size){
    array = new node[size];
}

public void add(node element){
    ensureCapacity();
    array[size++] = element;
}

public node remove(int index){
    if(index>=size || index < 0 ){throw new RuntimeException("Invalid index");}
    node element = (node) array[index];
    array[index] = null;
    --size;
  //  compress();
    return element;
}

public node get(int index){
    if(index > size){throw new RuntimeException("Invalid index");}
    node element = (node) array[index];
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
    node[] temp = new node[array.length*2];
    copy(array,temp);
    array =  temp;
}

private void copy(Object[] src, Object[] dest){
    if(dest.length< src.length){throw new RuntimeException(src+ " cannot be copied into "+dest);}
    for(int i=0;i<src.length; i++){
        dest[i] = src[i];
    }   
}
public boolean contains(node elem) {
	for(int i=0;i<size;i++) {
		//node temp=(node)array[i];
		if(array[i].equals1(elem)) return true;
		//node p1=new node(0,0,0);
		
	}
	return false;
	
}
}