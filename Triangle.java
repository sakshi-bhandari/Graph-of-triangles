
public class Triangle implements TriangleInterface{
	
	public Point p,q,r;
	boolean visited=false;
//	boolean	comp_num=false;
	int dist_temp=0;

	public Triangle(Point p, Point q, Point r) {
		this.p=p;
		this.q=q;
		this.r=r;
		dist_temp=Integer.MAX_VALUE;
	}
	@Override
	public PointInterface[] triangle_coord() {
		// TODO Auto-generated method stub
		
		PointInterface[] t={(PointInterface)p,(PointInterface)q,(PointInterface)r};
		return t;
	}
	
	
//	public void set_comp_num(int c) {
//		p.comp_num=c;
//		q.comp_num=c;
//		r.comp_num=c;
//		comp_num=true;
//	}
	
	
	public boolean equals1(Triangle t) {
		if(p.equals1(t.p)&&q.equals1(t.q)&&r.equals1(t.r)||p.equals1(t.p)&&r.equals1(t.q)&&q.equals1(t.r)||q.equals1(t.p)&&p.equals1(t.q)&&r.equals1(t.r)||q.equals1(t.p)&&r.equals1(t.q)&&p.equals1(t.r)||r.equals1(t.p)&&p.equals1(t.q)&&q.equals1(t.r)||r.equals1(t.p)&&q.equals1(t.q)&&p.equals1(t.r)) return true;
		return false;
	}
	public boolean hasedge(Edge e) {
		// TODO Auto-generated method stub\
	    Edge e1=new Edge(p,q);
	    Edge e2=new Edge(r,q);
	    Edge e3=new Edge(p,r);
	    if(e.equals1(e1)||e.equals1(e2)||e.equals1(e3)) return true;
		return false;
	}
	

	public boolean hasVertex(Point P) {
		// TODO Auto-generated method stub\
	    if(P.equals1(p)||P.equals1(q)||P.equals1(r)) return true;
		return false;
	}
	
	public String toString(){
		return "["+p.toString()+","+q.toString()+r.toString()+"]";
	}
	
}
