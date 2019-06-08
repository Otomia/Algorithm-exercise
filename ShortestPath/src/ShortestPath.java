/**
 * ��Դ���·������
 *
 * @author liujia
 * 
 */

public final class ShortestPath{

	private static final int M = Integer.MAX_VALUE;
//	private static final int M = 200;

	private static String path = "";

	/**
	 *��Դ���·������Dijkstra�㷨
	 *
	 * @param e �����������Ȩ����ͼ��Ȩֵ
	 * @param v ������� Դ����
	 * @param dist �����������Դֻ����s�еĶ��㵽Ŀ�Ķ�������·��
	 * @param prev �����������������̾����ǰ������
	 */
	public static void dijkstra(int[][] e, int v, int[] dist, int[] prev){
		
		int vNum = e.length;//�������

		boolean[] s = new boolean[vNum];//�����Ƿ���뼯�ϱ��

		//��ʼ��dist��prev,sΪ�ռ�
		for(int i=0; i<vNum; i++){
			dist[i] = e[v][i];
			s[i] = false;//����SΪ��
			if(dist[i] == M) prev[i] = -1;//Դ�㵽����i���ɴ�
			else prev[i] = v;
		}

		//����v���뼯��S
		dist[v] = 0;
		s[v] = true;

		//vNum-1�ε�����ʹ�����пɴﶥ����뼯��
		for(int i=0; i<vNum-1; i++){

			int temp = M;
			int u = v;

			//�����������ж��㣬Ѱ�Ҿ���Դ������Ķ������������
			for(int j=0; j<vNum; j++){
				if(!s[j] && dist[j]<temp){//��ǰ����δ���뼯��S���Ҵ�Դ��ɴ�ö���
					temp = dist[j];
					u = j;
				}
			}

			s[u] = true;//����ǰ����Դ���������u���뼯��

			//�������㣬��������㾭��u����Դ���������룬�����¶������Դ����������
			for(int j=0; j<vNum; j++){

				if(!s[j] && e[u][j]<M){
					int newdist = dist[u] + e[u][j];//����ͨ���¼��붥��u֮��Դ��ͨ��u���ﶥ��j����
					if(newdist<dist[j]){//���붥��u��Դ�㵽�ﶥ��j�����С
						dist[j] = newdist;
						prev[j] = u;
					}
				}
			}
		}
	}

	/**
	 * ���·��׷��
	 *
	 * @param prev ������������������·����ǰ������
	 * @param sourceVertex ���������Դ��
	 * @param endVertex ���������Ŀ�궥��
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