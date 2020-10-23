public class Array_List_Point  {

private Point[] array;
public static final int DEFAULT_SIZE = 20000;
private int size = 0;

public Array_List_Point(){
    this(DEFAULT_SIZE);
}

public Array_List_Point(int size){
    array = new Point[size];
}

public void add(Point element){
    ensureCapacity();
    array[size++] = element;
}

public Point remove(int index){
    if(index>=size || index < 0 ){throw new RuntimeException("Invalid index");}
    Point element = (Point) array[index];
    array[index] = null;
    --size;
  //  compress();
    return element;
}

public Point get(int index){
    if(index > size){throw new RuntimeException("Invalid index");}
    Point element = (Point) array[index];
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
    Point[] temp = new Point[array.length*2];
    copy(array,temp);
    array = (Point[]) temp;
}

private void copy(Object[] src, Object[] dest){
    if(dest.length< src.length){throw new RuntimeException(src+ " cannot be copied into "+dest);}
    for(int i=0;i<src.length; i++){
        dest[i] = src[i];
    }   
}
public boolean contains(Point elem) {
	for(int i=0;i<size;i++) {
		//Point temp=(Point)array[i];
		if(array[i].equals1(elem)) return true;
		//Point p1=new Point(0,0,0);
		
	}
	return false;
	
}
}