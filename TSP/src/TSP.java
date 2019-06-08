/**
 * ������(Traveling Salesman Problem)����
 *
 * @author liujia
 * @version
 */
 final class Traveling{

	//�����
	private int noEdge;

	//����ͼ�ڽӾ���
	private int[][] g;

	//�����ģ���������
	private int n;

	//��������
	private int sv;

	//��ǰ��
	private int[] x;

	//��ǰ���Ž�
	private int[] bestx;

	//��ǰ��������
	private int cc;

	//��ǰ��С��������
	private int bestc;

	/**
	 * ���캯��
	 *
	 * @param initialG ����ͼ
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
	 * �ݹ���ݺ���
	 *
	 * @param i ������������
	 */
	public void backtrack(int i){

		//����Ҷ�ڵ�
		if(i == n){
			//��������ֵ�����Ž�����
			if((cc + g[x[n-1]][x[0]] < bestc)){
				
				for(int j=0; j<n; j++){
					bestx[j] = x[j];//�������Ž�
//					System.out.println(bestx[j]);//����
				}
				bestc = cc + g[x[n-1]][x[0]];
//				System.out.println(bestc);//����
			}
		}
		else{
			for(int j=i; j<n; j++){
				//�Ƿ�ɽ���x[j]�����ж�
				if(cc + g[x[i-1]][x[j]] < bestc){//��֦������Ҳ���Բ���֦
					//��������
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
	 * ��ȡ����ֵ
	 *
	 * @return ���·��ֵ
	 */
	public int getMinCost() {
		
		return bestc;
	}

	/**
	 * ��ȡ���ŷ���
	 *
	 * @return ���·������
	 */
	public int[] getMinCostPath(){

		return bestx;
	}
}

public final class TSP{

	//��ʾ����ͼ���ڽӶ��㲻�ɴû�б�
//	public static final int M = Integer.MAX_VALUE;//���ֵ��������������ܻ��������
	public static final int M = 100;

	/**
	 * �����̹��ܶ�·��
	 *
	 * @param cityG ���о�������ͼ���ڽӾ����ʾ
	 * @param path ���ز��������·����������
	 * @return ���·��ֵ
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
			System.out.print("��" + path[i]);
		}
		System.out.println("��" + path[0]);
	}
}
