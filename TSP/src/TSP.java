/**
 * 旅行商(Traveling Salesman Problem)问题
 *
 * @author liujia
 * @version
 */
 final class Traveling{

	//无穷大
	private int noEdge;

	//有向图邻接矩阵
	private int[][] g;

	//问题规模，顶点个数
	private int n;

	//出发顶点
	private int sv;

	//当前解
	private int[] x;

	//当前最优解
	private int[] bestx;

	//当前距离或费用
	private int cc;

	//当前最小距离或费用
	private int bestc;

	/**
	 * 构造函数
	 *
	 * @param initialG 有向图
	 */
	public Traveling(int[][] initialG, int sourceVertex, int noEdge){	
		
		this.noEdge = noEdge;
		this.g = initialG;
		this.sv = sourceVertex;
		this.n = g.length;
		this.cc = 0;
		this.bestc = noEdge;

		this.x = new int[n];
		this.bestx = new int[n];

		for(int i=0; i<n; i++){
			x[i] = i;
		}

		swap(x, 0, sv);
	}

	/**
	 * 递归回溯函数
	 *
	 * @param i 排列树求解深度
	 */
	public void backtrack(int i){

		//到达叶节点
		if(i == n){
			//更新最优值与最优解条件
			if((cc + g[x[n-1]][x[0]] < bestc)){
				
				for(int j=0; j<n; j++){
					bestx[j] = x[j];//更新最优解
//					System.out.println(bestx[j]);//调试
				}
				bestc = cc + g[x[n-1]][x[0]];
//				System.out.println(bestc);//调试
			}
		}
		else{
			for(int j=i; j<n; j++){
				//是否可进入x[j]子树判断
				if(cc + g[x[i-1]][x[j]] < bestc){//剪枝条件，也可以不剪枝
					//搜索子树
					swap(x, i, j);
					cc += g[x[i-1]][x[i]];
					backtrack(i+1);
					cc -= g[x[i-1]][x[i]];
					swap(x, i, j);
				}
			}
		}
	}

	private static void swap(int[] list, int k, int i){
		int temp=list[k];
		list[k]=list[i];
		list[i]=temp;
	}

	/**
	 * 获取最优值
	 *
	 * @return 最短路径值
	 */
	public int getMinCost() {
		
		return bestc;
	}

	/**
	 * 获取最优方案
	 *
	 * @return 最短路径向量
	 */
	public int[] getMinCostPath(){

		return bestx;
	}
}

public final class TSP{

	//表示有向图中邻接顶点不可达，没有边
//	public static final int M = Integer.MAX_VALUE;//最大值付最大整数，可能会运算溢出
	public static final int M = 100;

	/**
	 * 求解最短哈密顿路径
	 *
	 * @param cityG 城市距离有向图，邻接矩阵表示
	 * @param path 返回参数，最短路径城市向量
	 * @return 最短路径值
	 */
	public static int minPath(int[][] cityG, int sourceVertex, int[] path, int noEdge){
		
		Traveling tc = new Traveling(cityG, sourceVertex, noEdge);

		tc.backtrack(1);

		int[] p = tc.getMinCostPath();
		for(int i=0; i<cityG.length; i++){
			path[i] = p[i];
		}

		return tc.getMinCost();
	}

	public static void main(String[] args){

		int[][]  directedGraph = {{0, 30, 6,  4},
			                     {30, 0,  5, 10},
			                     {6,  5,  0, 20},
		                         {4,  10, 20, 0}};
/*
		int[][] directedGraph ={{0, 3, 1, 5, 8},
								{3, 0, 6, 7, 9},
								{1, 6, 0, 4, 2},
								{5, 7, 4, 0, 3},
								{8, 9, 2, 3, 0}};
*/		
		int vNum = directedGraph.length;

		int sourceVertex = 0;

		System.out.println("The Directed Graph: ");
		for(int i=0; i<vNum; i++){
			for(int j=0; j<vNum; j++){
				if(directedGraph[i][j] == M)	System.out.print("   x");
				else System.out.printf("%4d",  directedGraph[i][j]);
			}
			System.out.println();
		}

		int[] path = new int[vNum];

		System.out.println("The min cost: " + minPath(directedGraph, sourceVertex, path, M));
		
		System.out.print("The path: " + path[0]);
		for(int i=1; i<path.length; i++){
			System.out.print("→" + path[i]);
		}
		System.out.println("→" + path[0]);
	}
}
