
public class Point implements PointInterface {
	
	float x,y,z;
	public int comp_num=0;
	
	public Point(float x , float y, float z){
		this.x=x;
		this.y=y;
		this.z=z;
		
	}
	
	public Point() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public float getX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public float getY() {
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public float getZ() {
		// TODO Auto-generated method stub
		return z;
	}

	@Override
	public float[] getXYZcoordinate() {
		// TODO Auto-generated method stub
		float[] f={x,y,z};
		return f;
	}

	public boolean equals1(Point q) {
		return getX()==q.getX()&&getY()==q.getY()&&getZ()==q.getZ() ? true : false;
	}
	
	public String toString(){
		//System.out.println("("+x+","+y+","+z+")");
		return "("+x+","+y+","+z+")";
	}
	
}
