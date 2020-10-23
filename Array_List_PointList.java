
public class Array_List_PointList {

		private Array_List_Point[] array;
		public static final int DEFAULT_SIZE = 20000;
		private int size = 0;

		public Array_List_PointList(){
		    this(DEFAULT_SIZE);
		}

		public Array_List_PointList(int size){
		    array = new Array_List_Point[size];
		}

		public void add(Array_List_Point element){
		    ensureCapacity();
		    array[size++] = element;
		}

		public Array_List_Point remove(int index){
		    if(index>=size || index < 0 ){throw new RuntimeException("Invalid index");}
		    Array_List_Point element = (Array_List_Point) array[index];
		    array[index] = null;
		    --size;
		  //  compress();
		    return element;
		}

		public Array_List_Point get(int index){
		    if(index > size){throw new RuntimeException("Invalid index");}
		    Array_List_Point element = (Array_List_Point) array[index];
		    return element;
		}

		public int size(){
		    return this.size;
		}
		
		public int contains(Point elem) {
			for(int i=0;i<size;i++)	for(int j=0;j<array[i].size();j++)	if(array[i].get(j).equals1(elem)) return i;
			return Integer.MAX_VALUE;
		}

		private void ensureCapacity(){
		    if(size < array.length){ return;}
		    resize();
		}

		private void resize(){
			Array_List_Point[] temp = new Array_List_Point[array.length*2];
		    copy(array,temp);
		    array = (Array_List_Point[]) temp;
		}

		private void copy(Object[] src, Object[] dest){
		    if(dest.length< src.length){throw new RuntimeException(src+ " cannot be copied into "+dest);}
		    for(int i=0;i<src.length; i++){
		        dest[i] = src[i];
		    }   
		}

	}
