/**
 * 单源最短路径问题
 *
 * @author liujia
 * 
 */

public final class ShortestPath{

	private static final int M = Integer.MAX_VALUE;
//	private static final int M = 200;

	private static String path = "";

	/**
	 *单源最短路径问题Dijkstra算法
	 *
	 * @param e 输入参数，带权有向图边权值
	 * @param v 输入参数 源顶点
	 * @param dist 输出参数，从源只经过s中的顶点到目的顶点的最短路径
	 * @param prev 输出参数，顶点获得最短距离的前驱顶点
	 */
	public static void dijkstra(int[][] e, int v, int[] dist, int[] prev){
		
		int vNum = e.length;//顶点个数

		boolean[] s = new boolean[vNum];//顶点是否加入集合标记

		//初始化dist和prev,s为空集
		for(int i=0; i<vNum; i++){
			dist[i] = e[v][i];
			s[i] = false;//集合S为空
			if(dist[i] == M) prev[i] = -1;//源点到顶点i不可达
			else prev[i] = v;
		}

		//顶点v加入集合S
		dist[v] = 0;
		s[v] = true;

		//vNum-1次迭代，使得所有可达顶点加入集合
		for(int i=0; i<vNum-1; i++){

			int temp = M;
			int u = v;

			//遍历余下所有顶点，寻找距离源点最近的顶点与最近距离
			for(int j=0; j<vNum; j++){
				if(!s[j] && dist[j]<temp){//当前顶点未加入集合S，且从源点可达该顶点
					temp = dist[j];
					u = j;
				}
			}

			s[u] = true;//将当前距离源点最近顶点u加入集合

			//遍历顶点，计算各顶点经过u到达源点的最近距离，并更新顶点距离源点的最近距离
			for(int j=0; j<vNum; j++){

				if(!s[j] && e[u][j]<M){
					int newdist = dist[u] + e[u][j];//计算通过新加入顶点u之后，源点通过u到达顶点j距离
					if(newdist<dist[j]){//加入顶点u后，源点到达顶点j距离变小
						dist[j] = newdist;
						prev[j] = u;
					}
				}
			}
		}
	}

	/**
	 * 最短路径追溯
	 *
	 * @param prev 输入参数，顶点获得最短路径的前驱顶点
	 * @param sourceVertex 输入参数，源点
	 * @param endVertex 输入参数，目标顶点
	 */
	public static void traceback(int[] prev, int sourceVertex, int endVertex){

		if(prev[endVertex]!= -1 && prev[endVertex]!=sourceVertex){
			traceback(prev, sourceVertex, prev[endVertex]);
		}
//		System.out.print(prev[endVertex] + "-");
		path = path + prev[endVertex] + "-";
	}

	public static void printPath(int[] prev, int sourceVertex, int endVertex){

		traceback(prev, sourceVertex, endVertex);

//		System.out.print(endVertex);

		path += endVertex;
		System.out.println(path);

		System.out.println();
	}



	public static void main(String[] args){	

		int[][] directedGraph = {{0, 10, M, 30, 100},
			                     {M, 0, 50, M, M},
			                     {M, M, 0, M, 10},
		                         {M, M, 20, 0, 60},
						         {M, M, M, M, 0}};

		int vNum = directedGraph.length;

		int sourceVertex = 0;
		int endVertex = 4;

		int[] dist = new int[vNum];
		int[] prev = new int[vNum];

		System.out.println("The Directed Graph: ");
		for(int i=0; i<vNum; i++){
			for(int j=0; j<vNum; j++){
				if(directedGraph[i][j] == M)	System.out.print("   x");
				else System.out.printf("%4d", directedGraph[i][j]);
			}
			System.out.println();
		}

		dijkstra(directedGraph, sourceVertex, dist, prev);

		System.out.println("The min path vertex " +  sourceVertex + " to all vertex:");
		for(int i=0; i<vNum; i++){
			System.out.print("vertex " + i + " : ");
			if(dist[i] == M)	System.out.println('x');
			else	System.out.println(dist[i]);
		}
		System.out.println();

		System.out.println("The min path " + sourceVertex + " to vertex " + endVertex + " is: ");
		printPath(prev, sourceVertex, endVertex);
	}	
}