# Graph of Triangles

This program takes an input file which defines a set of instructions and implements them. The sample `inp.txt` gives the basic format of the type of input file that needs to be provided.

This implementation can add triangles to a graph, get neighbouring triangles, find centroid of a component, find connected components and much more.

### POINT

Contains x, y and z coordinates of type float (initialised by constructor) and an int type comp_num (initialised to 0) which will be used to find the centroid of the component it belongs to later on in the code. 
- float getX, getY, getZ and getXYZ do what their names suggest.
- boolean equals1 compares it to another point.
- toString returns a String in the format (x,y,z).

### EDGE

Contains two Points (initialised by constructor).
- edgeEndPoints returns an array of these two points, that make up the edge.
- boolean equals1 compares two edges.
- toString returns the String format of the edge.


### TRIANGLE

Contains three points (initialised by the constructor) and boolean visited and comp_num (both initialised to false) which will be used to find the the component it belongs to and its centroid respectively later on in the code. 
- triangle_coord returns an array of these end points.
- void set_comp_num takes an input integer and sets the comp_num variable of its points to that integer. It sets its own comp_num to true. This will be used to find the centroid.
- boolean hasedge takes input of an edge returns whether or not the triangle contains that edge.
- boolean hasVertex takes input of a point returns whether or not the triangle contains that point.


### NODE

Contains a point and an arraylist of points called edges, both initialised by the constructor.
- The arraylist edges contains all the points our point is connected to, i.e. contains the points of all the edges that are formed with our point.
- equals1 compares two nodes.


### SHAPE

#### ADD_TRIANGLE
In this method, if the points are not collinear (checked by isCollinear helper function), the three points, three edges and the triangle are added to ArrayList of points, edges and triangles respectively (if not already present).

We've used the adjacency list representation of the graph (specified by the 'graph' variable). 
An additional array list of array list of points named 'components' is maintained that sorts the points into their respective components as soon as a triangle is created. 
'components' contains the list of all the component, each component being the list of all the points in that component.
 This makes it easy to implement all the component related functions in minimalist time. 
Complexity = O(P+E+T) = O(M)     (M=max{P,E,T})

#### TYPE_MESH
Uses a helper function no_of_common_triangles which finds the number of triangles sharing the edge which is passed as input into the method. 
This method iterates through all the edges. If it returns any value greater than 2, mesh type is 3. If it is 2, then we increment the count variable by 1. At the end of the loop, if count equals total number of edges, we conclude that its a proper mesh (1), else returns 2.
If no_of_common_triangles has value 1, we add the edge to an array list of edges named 'edgeboundary' which contains the list of boundary edges.
Complexity = O(T*E)

#### BOUNDARY_EDGES
If mesh type is 1, returns null.
Else, we copy the array list 'edgeboundary' into an array of edgeinterface named dist, and then sort it.
Complexity = O(E^2)
(If we have to return the unsorted array, complexity would be O(1))

#### COUNT_CONNECTED_COMPONENTS
Returns the size of 'components'.
Complexity = O(1)

#### NEIGHBORS_OF_TRIANGLE
Returns null if triangle doesn't exist.
Else, it iterates through all the triangles, and uses a method 'hasedge' defined in the class Triangle to check if the triangle contains the given edge. 
If yes, then it increments the count variable by 1, adds it to an array list of triangles, copies the arraylist to an array and then returns the array.
Complexity = O(T)

#### EDGE_NEIGHBOR_TRIANGLE
Returns the array of edges of the triangle.
Complexity = O(1)

#### VERTEX_NEIGHBOR_TRIANGLE
Returns the array of vertices of the triangle.
Complexity = O(1)

#### EXTENDED_NEIGHBOR_TRIANGLE
Returns null if triangle doesn't exist.
Else, it iterates through all the triangles, and uses a method 'hasVertex' defined in the class Triangle to check if the triangle contains the given vertex. 
If yes, then it increments the count variable by 1, adds it to an array list of triangles, copies the arraylist to an array and then returns the array.
Complexity = O(T)

#### INCIDENT_TRIANGLES
It iterates through all the triangles, and uses a method 'hasVertex' defined in the class Triangle to check if the triangle contains the given point. 
If yes, then it increments the count variable by 1, adds it to an array list of triangles, copies the arraylist to an array and then returns the array.
Complexity = O(T)

#### NEIGHBORS_OF_POINT
This searches for the node in the graph of the given point, copies the array list of points in the node (which are basically the points it is connected to by an edge) to an array and returns the array.
Complexity = O(P)

#### EDGE_NEIGHBORS_OF_POINT
Finds the neighbours using the previous method, creates an array of edge with all the neighbours and returns the array.
Complexity = O(P)

#### FACE_NEIGHBORS_OF_POINT
(Same as Incident Triangles)

#### TRIANGLE_NEIGHBOR_OF_EDGE
It iterates through all the triangles, and uses a method 'hasedge' defined in the class Triangle to check if the triangle contains the given edge. 
If yes, then it increments the count variable by 1, adds it to an array list of triangles, copies the arraylist to an array and then returns the array.
Complexity = O(T)

#### IS_CONNECTED
Uses and returns a recursive helper function ConnRec.
ConnRec starts depth first search from the first triangle and marks all the triangles connected to it as visited. If the second triangle is also marked visited, then ConnRec returns true, else false.
Complexity = O(T)

#### MAXIMUM_DIAMETER
Uses a recursive helper function maxdist.
maxdist takes a triangle as input and returns the dist of the farthest triangle from itself using breadth first search (BFS is used as it returns the required minimum distance in a graph).
MAXIMUM_DIAMETER then checks the maxdist values of all triangles and returns the maximum value
Complexity = O(T^2)

#### CENTROID 
This method iterates through each component in the 'components' variable and for each component, calculates its centroid (taking the average of corresponding coordinates) and put them in the 'ans' array of pointinterface type.
Complexity = O(P)

#### CENTROID_OF_COMPONENT
This method searches for the point in each component. When found, calculates and returns the centroid of the same.
Complexity = O(P)

#### CLOSEST_COMPONENTS
This method iterates through each pair of components in the 'components' variable (using two nested for loops). For each pair, minimum distance is calculated for each pair of points in each of the components and a variable 'd' finds the one which is least of all the pairs and updates the 'ans' variable which is returned.
Complexity = O(P^2)

  
