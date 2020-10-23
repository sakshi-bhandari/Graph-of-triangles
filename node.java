
public class node {
	
	public Point p;
	public Array_List_Point edges;
	
	public node(Point q) {
		p=q;
		edges=new Array_List_Point();
	}
	
	public boolean equals1(node n) {
				if(p.equals1(n.p)&&edges.equals(n.edges)) return true;
		else return false;
	}
}