
public class Edge implements EdgeInterface{
	
	public Point p1,p2;

	public Edge(Point p1, Point p2) {
		this.p1=p1;
		this.p2=p2;
	}
	
	@Override
	public PointInterface[] edgeEndPoints() {
		
		PointInterface[] p={(PointInterface)p1,(PointInterface)p2};
		// TODO Auto-generated method stub
		return p;
	}
	
	public boolean equals1(Edge e) {
		return p1.equals1(e.p1)&&p2.equals1(e.p2)||p2.equals1(e.p1)&&p1.equals1(e.p2) ? true : false;
	}

	public String toString(){
		return "["+p1.toString()+","+p2.toString()+"]";
	}
	
}
