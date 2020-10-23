public class Shape implements ShapeInterface {
	
    public Array_List_Node graph=new Array_List_Node();
    public Array_List_Point points=new Array_List_Point();
    public Array_List_Edge edges =new Array_List_Edge();
    public Array_List_Triangle triangles =new Array_List_Triangle(); 
    public Array_List_Edge edgeboundary =new Array_List_Edge();
    public Array_List_PointList components = new Array_List_PointList();
    public int kkk=0;
	
	public boolean isCollinear(float [] t){
		if((t[3]-t[0])/(t[6]-t[0])==(t[4]-t[1])/(t[7]-t[1])&&(t[3]-t[0])/(t[6]-t[0])==(t[5]-t[2])/(t[8]-t[2])) return true;
		else return false;
	}
	
	
	public node Search_point_in_graph (Point q) {
		for(int i=0;i<graph.size();i++) {
			if(graph.get(i).p.equals1(q)) return graph.get(i);
		}
		node n=null;
		return n;
	}
	
	
	public boolean ADD_TRIANGLE(float [] triangle_coord){
		
		Point p1=new Point(triangle_coord[0],triangle_coord[1],triangle_coord[2]);
		Point p2=new Point(triangle_coord[3],triangle_coord[4],triangle_coord[5]);
		Point p3=new Point(triangle_coord[6],triangle_coord[7],triangle_coord[8]);
		Triangle T=new Triangle(p1,p2,p3);
		//Triangle T1=new Triangle(p2,p3,p1);
		Edge e1=new Edge(p1,p2);
		Edge e2=new Edge(p2,p3);
		Edge e3=new Edge(p3,p1);
		//System.out.println(T.equals1(T1));
		if(isCollinear(triangle_coord)) {
			System.out.println("collinear");
			return false;
		}
		else {
			if(!edges.contains(e1)) edges.add(e1);
			if(!edges.contains(e2)) edges.add(e2);
			if(!edges.contains(e3)) edges.add(e3);
			if(!triangles.contains(T)) triangles.add(T);
			if(!points.contains(p1)) { 
				points.add(p1); 
			    node n1=new node(p1);
			    graph.add(n1);
			}
			if(!points.contains(p2)) {
				points.add(p2);
				node n1=new node(p2);
			    graph.add(n1);
			}
			if(!points.contains(p3)) {
				points.add(p3);
				node n1=new node(p3);
			    graph.add(n1);
			}
			node n=Search_point_in_graph(p1);
			if(n!=null) {
				if(!n.edges.contains(p2)) n.edges.add(p2);
				if(!n.edges.contains(p3)) n.edges.add(p3);
			}
			node nq=Search_point_in_graph(p2);
			if(nq!=null) {
				if(!nq.edges.contains(p1)) nq.edges.add(p1);
				if(!nq.edges.contains(p3)) nq.edges.add(p3);
			}
			node ne=Search_point_in_graph(p3);
			if(ne!=null) {
				if(!ne.edges.contains(p2)) ne.edges.add(p2);
				if(!ne.edges.contains(p1)) ne.edges.add(p1);
			}
			
			//triangles.
			//System.out.println(points.size()+" edgetotal =  "+edges.size()+"  triangle created=  "+triangles.size());
			
			boolean add=false;
			for(int i=0;i<components.size();i++) {
				if(components.get(i).contains(p1)&&components.get(i).contains(p2)) { components.get(i).add(p3); add=true; break; }
				if(components.get(i).contains(p2)&&components.get(i).contains(p3)) { components.get(i).add(p1); add=true; break; }
				if(components.get(i).contains(p3)&&components.get(i).contains(p1)) { components.get(i).add(p2); add=true; break; }
			}
			if(!add) {
				Array_List_Point temp = new Array_List_Point();
				temp.add(p1);
				temp.add(p2);
				temp.add(p3);
				components.add(temp);
			}
			kkk=0;
			return true;
		}

	}
		
	
	public int TYPE_MESH(){
		
		int c=0;
		int l=triangles.size();
		//System.out.println("This is TYPE_MESH");
		int e=edges.size();
		for(int i=0;i<e;i++) {
			int temp=no_of_common_triangles(edges.get(i));
			if(temp>2) return 3;
			if(temp==2) c++; 
		}
		kkk++;
		if(c==edges.size()) return 1;
		else return 2;
		}
	

	private int no_of_common_triangles(Edge e) {
		Point p1=e.p1;
		Point p2=e.p2;
		int count=0,z=triangles.size();
		for(int i=0;i<z;i++){
			Triangle t=triangles.get(i);
			if(t.hasedge(e)) count++;
		}
		
		if(count==1&&kkk==0) edgeboundary.add(e);
		
		return count;
	}

	
	public int COUNT_CONNECTED_COMPONENTS(){
		
//		Triangle t=triangles.get(0);
//		int c=0;
//		dfs(t);
//		c=1;
//		while(left_unvisit()!=null){
//			dfs(left_unvisit());
//			c++;
//		}
//		//System.out.println("This is COUNT_CONNECTED_COMPONENTS");
//		reset_visit();
//		return c;
		
		return components.size();
	}
	
	
//	private Triangle left_unvisit() {
//		
//		int t=triangles.size();
//		for(int i=0;i<t;i++){
//			if(triangles.get(i).visited==false) return triangles.get(i);
//		}
//		return null;
//	}
//
//
//	public boolean dfs(Triangle T1){
//		
//		//System.out.println(true);
//		//if(T2.visited) return true;
//		T1.visited=true;
//		float[] tri={T1.p.getX(),T1.p.getY(),T1.p.getZ(),T1.q.getX(),T1.q.getY(),T1.q.getZ(),T1.r.getX(),T1.r.getY(),T1.r.getZ()};
//		TriangleInterface[] t=NEIGHBORS_OF_TRIANGLE(tri);
//		int z=t.length;
//		for(int i =0;i<z;i++){
//			Triangle temp=(Triangle) t[i];
//			if(!temp.visited)	dfs(temp);
//		}
//		return false;
//	}
	
	
	public EdgeInterface [] BOUNDARY_EDGES(){
		
//		Array_List_Edge boundaryedges =new Array_List_Edge();
		if(TYPE_MESH()==1) 	return null;
		
//		for(int i=0;i<edges.size();i++) {
//			int temp=no_of_common_triangles(edges.get(i));
//			if(temp==1) boundaryedges.add(edges.get(i));
//		}
//		
//		EdgeInterface[] blank=new EdgeInterface[boundaryedges.size()];
//		for(int i=0;i<boundaryedges.size();i++){
//			blank[i]=boundaryedges.get(i);
//		}
		
		int e=edgeboundary.size();
		EdgeInterface[] ans=new EdgeInterface[e];
		float[] dist=new float[e];
		for(int i=0;i<e;i++)	dist[i]=(edgeboundary.get(i).p1.getX()-edgeboundary.get(i).p2.getX())*(edgeboundary.get(i).p1.getX()-edgeboundary.get(i).p2.getX())+(edgeboundary.get(i).p1.getY()-edgeboundary.get(i).p2.getY())*(edgeboundary.get(i).p1.getY()-edgeboundary.get(i).p2.getY())+(edgeboundary.get(i).p1.getZ()-edgeboundary.get(i).p2.getZ())*(edgeboundary.get(i).p1.getZ()-edgeboundary.get(i).p2.getZ());
		for(int i=0;i<e;i++) {
			int d=0;
			float f=Integer.MAX_VALUE;
			for(int j=0;j<e;j++) {
				if(f>dist[j]) {
					f=dist[j];
					d=j;
				}
			}
			ans[i]=edgeboundary.get(d);
			dist[d]=Integer.MAX_VALUE;
		}
		//System.out.println("This is BOUNDARY_EDGES");
		return ans;
	}
	
		
	public TriangleInterface [] NEIGHBORS_OF_TRIANGLE(float [] triangle_coord){
		
		Array_List_Triangle ans =new Array_List_Triangle();
		int c=0;
		Point p1=new Point(triangle_coord[0],triangle_coord[1],triangle_coord[2]);
		Point p2=new Point(triangle_coord[3],triangle_coord[4],triangle_coord[5]);
		Point p3=new Point(triangle_coord[6],triangle_coord[7],triangle_coord[8]);
		Triangle T=new Triangle(p1,p2,p3);
		Edge e1=new Edge(p1,p2);
		Edge e2=new Edge(p2,p3);
		Edge e3=new Edge(p3,p1);
		if(!triangles.contains(T)){
			return null;
		}
		int z=triangles.size();
		for(int i=0;i<z;i++){
			Triangle temp=triangles.get(i);
			if(!temp.equals1(T)) if(temp.hasedge(e1)||temp.hasedge(e2)||temp.hasedge(e3)){ 
				ans.add(temp);
				c++;
			}
		}
		TriangleInterface [] ansfinal=new TriangleInterface[c];
		for(int i=0;i<c;i++){
			ansfinal[i]=ans.get(i);
		}
		//System.out.println("This is NEIGHBORS_OF_TRIANGLE");
		return ansfinal;
	}

	
	public EdgeInterface [] EDGE_NEIGHBOR_TRIANGLE(float [] triangle_coord){
		
		Point p1=new Point(triangle_coord[0],triangle_coord[1],triangle_coord[2]);
		Point p2=new Point(triangle_coord[3],triangle_coord[4],triangle_coord[5]);
		Point p3=new Point(triangle_coord[6],triangle_coord[7],triangle_coord[8]);
		Triangle T=new Triangle(p1,p2,p3);
		Edge e1=new Edge(p1,p2);
		Edge e2=new Edge(p2,p3);
		Edge e3=new Edge(p3,p1);
		if(!triangles.contains(T)){
			return null;
		}
		EdgeInterface [] ans = new EdgeInterface [3];
		ans[0]=e1;
		ans[1]=e2;
		ans[2]=e3;
		//System.out.println("This is EDGE_NEIGHBOR_TRIANGLE");
		return ans;
	}
	
	
	public PointInterface [] VERTEX_NEIGHBOR_TRIANGLE(float [] triangle_coord){
		
		Point p1=new Point(triangle_coord[0],triangle_coord[1],triangle_coord[2]);
		Point p2=new Point(triangle_coord[3],triangle_coord[4],triangle_coord[5]);
		Point p3=new Point(triangle_coord[6],triangle_coord[7],triangle_coord[8]);
		Triangle T=new Triangle(p1,p2,p3);
		if(!triangles.contains(T)){
			return null;
		}
		PointInterface [] ans = new PointInterface [3];
		ans[0]=p1;
		ans[1]=p2;
		ans[2]=p3;
		//System.out.println("This is VERTEX_NEIGHBOR_TRIANGLE");
		return ans;
	}
	
	
	public TriangleInterface [] EXTENDED_NEIGHBOR_TRIANGLE(float [] triangle_coord){
		
		Array_List_Triangle ans =new Array_List_Triangle();
		int c=0;
		Point p1=new Point(triangle_coord[0],triangle_coord[1],triangle_coord[2]);
		Point p2=new Point(triangle_coord[3],triangle_coord[4],triangle_coord[5]);
		Point p3=new Point(triangle_coord[6],triangle_coord[7],triangle_coord[8]);
		Triangle T=new Triangle(p1,p2,p3);
		if(!triangles.contains(T)){
			return null;
		}
		int z=triangles.size();
		for(int i=0;i<z;i++){
			Triangle temp=triangles.get(i);
			if(!temp.equals1(T)) if(temp.hasVertex(p1)||temp.hasVertex(p2)||temp.hasVertex(p3)){ 
				ans.add(temp);
				c++;
			}
		}
		TriangleInterface [] ansfinal=new TriangleInterface[c];
		for(int i=0;i<c;i++){
			ansfinal[i]=ans.get(i);
		}
		//System.out.println("This is EXTENDED_NEIGHBOR_TRIANGLE");
		return ansfinal;
	}
	
	
	public TriangleInterface [] INCIDENT_TRIANGLES(float [] point_coordinates){
		
		Array_List_Triangle ans =new Array_List_Triangle();
		int c=0;
		Point p=new Point(point_coordinates[0],point_coordinates[1],point_coordinates[2]);
		for(int i=0;i<triangles.size();i++){
			Triangle temp=triangles.get(i);
			if(temp.hasVertex(p)){ 
				ans.add(temp);
				c++;
			}
		}
		if(c==0) return null;
		TriangleInterface [] ansfinal=new TriangleInterface[c];
		for(int i=0;i<c;i++){
			ansfinal[i]=ans.get(i);
		}
		//System.out.println("This is INCIDENT_TRIANGLES");
		return ansfinal;
	}
	
	
	public PointInterface [] NEIGHBORS_OF_POINT(float [] point_coordinates){
		
		Point p=new Point(point_coordinates[0],point_coordinates[1],point_coordinates[2]);
		node n=Search_point_in_graph(p); 
		int z=n.edges.size();
		PointInterface [] ans = new PointInterface [z];
		for(int i=0;i<z;i++) ans[i]=n.edges.get(i);
		//System.out.println("This is NEIGHBORS_OF_POINT");
		return ans;
	}
	
	
	public EdgeInterface [] EDGE_NEIGHBORS_OF_POINT(float [] point_coordinates){
		
		Point p=new Point(point_coordinates[0],point_coordinates[1],point_coordinates[2]);
		PointInterface [] ans = NEIGHBORS_OF_POINT(point_coordinates);
		int a=ans.length;
		if(a==0) return null;
		EdgeInterface [] ansfinal=new EdgeInterface[edges.size()];
		for(int i=0;i<a;i++){
			Point q=(Point) ans[i];
			ansfinal[i]=new Edge(p,q);
		}
		//System.out.println("This is EDGE_NEIGHBORS_OF_POINT");
		return ansfinal;
	}
	
	
	public TriangleInterface [] FACE_NEIGHBORS_OF_POINT(float [] point_coordinates){ 
		
		//System.out.println("This is FACE_NEIGHBORS_OF_POINT");
		return INCIDENT_TRIANGLES(point_coordinates);
	}
	
	
	public TriangleInterface [] TRIANGLE_NEIGHBOR_OF_EDGE(float [] edge_coordinates){ 
		
		Point p1=new Point(edge_coordinates[0],edge_coordinates[1],edge_coordinates[2]);
		Point p2=new Point(edge_coordinates[3],edge_coordinates[4],edge_coordinates[5]);
		Edge e=new Edge(p1,p2);
		Array_List_Triangle ans =new Array_List_Triangle();
		int c=0,z=triangles.size();
		for(int i=0;i<z;i++){
			Triangle temp=triangles.get(i);
			if(temp.hasedge(e)){ 
				ans.add(temp);
				c++;
			}
		}
		if(c==0) return null;
		TriangleInterface [] ansfinal=new TriangleInterface[c];
		for(int i=0;i<c;i++){
			ansfinal[i]=ans.get(i);
		}
		//System.out.println("This is TRIANGLE_NEIGHBOR_OF_EDGE");
		return ansfinal;
	}
	
	
	public boolean IS_CONNECTED(float [] triangle_coord_1, float [] triangle_coord_2){
		
		Point p1=new Point(triangle_coord_1[0],triangle_coord_1[1],triangle_coord_1[2]);
		Point p2=new Point(triangle_coord_1[3],triangle_coord_1[4],triangle_coord_1[5]);
		Point p3=new Point(triangle_coord_1[6],triangle_coord_1[7],triangle_coord_1[8]);
		Triangle T1=new Triangle(p1,p2,p3);
		Point p4=new Point(triangle_coord_2[0],triangle_coord_2[1],triangle_coord_2[2]);
		Point p5=new Point(triangle_coord_2[3],triangle_coord_2[4],triangle_coord_2[5]);
		Point p6=new Point(triangle_coord_2[6],triangle_coord_2[7],triangle_coord_2[8]);
		Triangle T2=new Triangle(p4,p5,p6);
		
		if(!triangles.contains(T1)||!triangles.contains(T2)){
			return false;
		}
		
		//System.out.println("This is IS_CONNECTED");
		//if(ConnRec(T1,T2)) System.out.println(true);
		//else System.out.println(false);
		boolean b=ConnRec(T1,T2);
		reset_visit();
		return b;
				
	}
	
	
	private void reset_visit() {
		
		int t=triangles.size();
		for(int i=0;i<t;i++) {
			triangles.get(i).visited=false;
			triangles.get(i).dist_temp=Integer.MAX_VALUE;
		}
		
	}


	public boolean ConnRec(Triangle T1, Triangle T2){
		
		//System.out.println(true);
		if(T2.visited) return true;
		T1.visited=true;
		float[] tri={T1.p.getX(),T1.p.getY(),T1.p.getZ(),T1.q.getX(),T1.q.getY(),T1.q.getZ(),T1.r.getX(),T1.r.getY(),T1.r.getZ()};
		TriangleInterface[] t=NEIGHBORS_OF_TRIANGLE(tri);
		int z=t.length;
		for(int i =0;i<z;i++){
			Triangle temp=(Triangle) t[i];
			if(!temp.visited)	ConnRec(temp,T2);
		}
		return false;
	}

	
	public int MAXIMUM_DIAMETER(){
		
		int d=0;
		for(int i=0;i<triangles.size();i++) if(d<maxdist(triangles.get(i))) d=maxdist(triangles.get(i));		
		return d;
	}
	
	
	private int maxdist(Triangle t) {
      
	    t.dist_temp=0;
	    Array_List_Triangle q=new Array_List_Triangle();
	    q.add(t);
	    t.visited=true;
	    Triangle T1=new Triangle(null,null,null);
	    while(q.size()!=0) {
	    	T1=q.pop();
	    	float[] triangle_coord={T1.p.getX(),T1.p.getY(),T1.p.getZ(),T1.q.getX(),T1.q.getY(),T1.q.getZ(),T1.r.getX(),T1.r.getY(),T1.r.getZ()};
			TriangleInterface[] temp=NEIGHBORS_OF_TRIANGLE(triangle_coord);
			for(int i=0;i<temp.length;i++) {
				Triangle tri=(Triangle) temp[i];
				if(!tri.visited) {
					q.add(tri);
					tri.dist_temp=T1.dist_temp+1;
					tri.visited=true;
				}
			}
	    }
	    int ans=0;
	    for(int i=0;i<triangles.size();i++) {
	    	if(triangles.get(i).dist_temp>ans) ans=triangles.get(i).dist_temp;
	    }
	    reset_visit();
		return ans;
	}
	
	
	public PointInterface [] CENTROID (){
		
//		int c=1;
//		while(left_comp_num()!=null){
//			find_comp_num(left_comp_num(),c);
//			c++;
//		}
//		//System.out.println("This is CENTROID");
//		
//		int x=c-1;
//		PointInterface[] ans = new PointInterface[x];
//		for(int i=1;i<=x;i++) {
//			int y=0;
//			float X=0,Y=0,Z=0;
//			for(int j=0;j<points.size();j++) {
//				Point p=points.get(j);
//				if (p.comp_num==i) {
//					X+=p.getX();
//					Y+=p.getY();
//					Z+=p.getZ();
//					y++;
//				}
//			}
//			Point temp=new Point(X/y,Y/y,Z/y);
//			ans[i-1]=temp;
//		}
//		
//		reset_comp_num();
//		return ans;
		
		int x=components.size();
		PointInterface[] ans = new PointInterface[x];
		for(int i=0;i<x;i++) {
			int y=components.get(i).size();
			float X=0,Y=0,Z=0;
			for(int j=0;j<y;j++) {
				Point p=components.get(i).get(j);
				X+=p.getX();
				Y+=p.getY();
				Z+=p.getZ();
			}
			Point temp=new Point(X/y,Y/y,Z/y);
			ans[i]=temp;
		}
		
		return ans;
		
	}
	
	
//	private void reset_comp_num() {
//		
//		int p=points.size(),t=triangles.size();
//		for(int i=0;i<p;i++) points.get(i).comp_num=0;
//		for(int i=0;i<t;i++) triangles.get(i).comp_num=false;
//		
//	}
//
//
//	private Triangle left_comp_num() {
//		
//		int t=triangles.size();
//		for(int i=0;i<t;i++){
//			if(!triangles.get(i).comp_num) return triangles.get(i);
//		}
//		return null;
//	}
//
//
//	public void find_comp_num(Triangle T,int c){
//		
//		T.set_comp_num(c);
//		float[] tri={T.p.getX(),T.p.getY(),T.p.getZ(),T.q.getX(),T.q.getY(),T.q.getZ(),T.r.getX(),T.r.getY(),T.r.getZ()};
//		TriangleInterface[] t=NEIGHBORS_OF_TRIANGLE(tri);
//		for(int i =0;i<t.length;i++){
//			Triangle temp=(Triangle) t[i];
//			if(!temp.comp_num)	find_comp_num(temp,c);
//		}
//	}

		
	public PointInterface CENTROID_OF_COMPONENT (float [] point_coordinates){

		Point p=new Point(point_coordinates[0],point_coordinates[1],point_coordinates[2]);
//		int size=triangles.size();
//		for(int i=0;i<size;i++) {
//			Triangle T=triangles.get(i);
//			if(T.hasVertex(p)) {	find_comp_num(T, 1);	break;	}
//		}
//		int y=0;
//		float X=0,Y=0,Z=0;
//		int z=points.size();
//		for(int j=0;j<z;j++) {
//			Point q=points.get(j);
//			if (q.comp_num==1) {
//				X+=q.getX();
//				Y+=q.getY();
//				Z+=q.getZ();
//				y++;
//			}
//		}
//		Point ans=new Point(X/y,Y/y,Z/y);
//		reset_comp_num();
//		return ans;
		
		int x=components.size();
		Point ans=new Point();
		for(int i=0;i<x;i++) {
			if(components.get(i).contains(p)) {
				int y=components.get(i).size();
				float X=0,Y=0,Z=0;
				for(int j=0;j<y;j++) {
					Point q=components.get(i).get(j);
					X+=q.getX();
					Y+=q.getY();
					Z+=q.getZ();
				}
				ans=new Point(X/y,Y/y,Z/y);
				break;
			}
		}
		return ans;
		
	}


	public 	PointInterface [] CLOSEST_COMPONENTS(){
		
		int a=components.size();
		PointInterface[] ans = new PointInterface[2];
		Point p1=new Point();
		Point p2=new Point();
		float d=Float.MAX_VALUE;
		for(int i=0;i<a-1;i++) {
			int b= components.get(i).size();
			for(int j=i+1;j<a;j++) {
				int c= components.get(j).size();
				for(int k=0; k<b; k++) {
					for (int l=0; l<c; l++) {
						float e= (components.get(i).get(k).getX()-components.get(j).get(l).getX())*(components.get(i).get(k).getX()-components.get(j).get(l).getX())+(components.get(i).get(k).getY()-components.get(j).get(l).getY())*(components.get(i).get(k).getY()-components.get(j).get(l).getY())+(components.get(i).get(k).getZ()-components.get(j).get(l).getZ())*(components.get(i).get(k).getZ()-components.get(j).get(l).getZ());
						if(e<d) {
							d=e;
							p1=new Point (components.get(i).get(k).getX(),components.get(i).get(k).getY(),components.get(i).get(k).getZ());
							p2=new Point (components.get(j).get(l).getX(),components.get(j).get(l).getY(),components.get(j).get(l).getZ());
						}
					}
				}
			}
		}
		ans[0]=p1;
		ans[1]=p2;
		return ans;
	}
	
}

