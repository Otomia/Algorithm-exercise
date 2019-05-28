/**
 * ��������
 *
 * @author liujia
 * @
 */

public final class Knapsack{

	/**
	 * 0-1�����������װ�ؼ�ֵ�����㷨
	 *
	 * @param kc ��������
	 * @param w ��Ʒ��������
	 * @param v ��Ʒ��ֵ����
	 * @param maxValue ���������,maxValue[n][kc]Ϊn����Ʒ��������Ϊkc����µ����װ�ؼ�ֵ,����ά��Ϊw��kc��ά���ֱ�+1
	 */
	public static int maxLoadValue01(int kc, int[] w, int[] v, int[][] maxValue){

		//�������maxValue��ʼ��		
		int gNum = w.length;
		for(int i=0; i<=gNum; i++)	maxValue[i][0] = 0;//��������Ϊ0��ֵ
		for(int j=0; j<=kc; j++)	maxValue[0][j] = 0;//��ƷΪ0��ֵ

		//�����������ֵ
		for(int g=1; g<=gNum; g++){
			//��������ѭ�������߽�
			int cBound = min(w[g-1], kc+1);

			//��������װ���i����Ʒ����i����Ʒ�������п��ܳ�������������Ҫ��ֹ����Խ��.
			for(int c=1; c<cBound; c++){
				maxValue[g][c] = maxValue[g-1][c];
			}

			for(int c=w[g-1]; c<=kc; c++){
				maxValue[g][c] = max(maxValue[g-1][c], maxValue[g-1][c-w[g-1]]+v[g-1]);
			}
		}
		
		return maxValue[gNum][kc];
	}

	/**
	 * ׷��װ����Ʒ
	 *
	 * @param maxValue ���������
	 * @param weights ��Ʒ��������
	 * @param x �Ƿ�װ����Ʒ����
	 */
	public static void traceback01(int[][] maxValue, int[] weights, int[] x){

		int gNum = maxValue.length-1;
		int kc = maxValue[0].length-1;

		for(int i=gNum; i>0; i--){
			if(maxValue[i][kc] == maxValue[i-1][kc]){//��װ��i����Ʒ����Ӧ����x[i-1]
				x[i-1] = 0;
			}
			else{//װ���i����Ʒ
				x[i-1] = 1;
				kc -= weights[i-1];
			}
		}
	}

	/**
	 * 0-N�����������װ�ؼ�ֵ�����㷨
	 *
	 * @param kc ��������
	 * @param w ��Ʒ��������
	 * @param v ��Ʒ��ֵ����
	 * @param maxValue ���������,maxValue[n][kc]Ϊn����Ʒ��������Ϊkc����µ����װ�ؼ�ֵ,����ά��Ϊw��kc��ά���ֱ�+1
	 */
	public static int maxLoadValue0N(int kc, int[] w, int[] v, int[][] maxValue){

		//�������maxValue��ʼ��		
		int gNum = w.length;
		for(int i=0; i<=gNum; i++)	maxValue[i][0] = 0;//��������Ϊ0��ֵ
		for(int j=0; j<=kc; j++)	maxValue[0][j] = 0;//��ƷΪ0��ֵ

		//�����������ֵ
		for(int g=1; g<=gNum; g++){
			//��������ѭ�������߽�
			int cBound = min(w[g-1], kc+1);

			//��������װ���i����Ʒ����i����Ʒ�������п��ܳ�������������Ҫ��ֹ����Խ��.
			for(int c=1; c<cBound; c++){
				maxValue[g][c] = maxValue[g-1][c];
			}

			for(int c=w[g-1]; c<=kc; c++){
				maxValue[g][c] = max(maxValue[g-1][c], maxValue[g][c-w[g-1]]+v[g-1]);
			}
		}
		return maxValue[gNum][kc];
	}

	/**
	 * the same as traceback01
	 */
	public static void traceback0N(int[][] maxValue, int[] weights, int[] x){

		int gNum = maxValue.length-1;
		int kc = maxValue[0].length-1;

		for(int i=0; i<gNum; i++)	x[i] = 0;

		for(int i=gNum; i>0;){
			if(maxValue[i][kc] == maxValue[i-1][kc]){//��װ��i����Ʒ����Ӧ����x[i-1]
				i--;
			}
			else{//װ���i����Ʒ
				x[i-1]++;
				kc -= weights[i-1];
			}
		}
	}

	private static int max(int a, int b){
		return a > b ? a : b;
	}

	private static int min(int a, int b){
		return a < b ? a : b;
	}


	public static void main(String[] args){	
		
		final int capacity = 10;
		int[] weights = {2, 2, 6, 5, 4};
		int[] values = {6, 3, 5, 4, 6};
		
//		int[] weights = {2, 3, 4, 7};
//		int[] values = {1, 3, 5, 9};

		int[][] maxValue = new int[weights.length+1][capacity+1];

		System.out.println("Knapsack capacity: " + capacity);

		System.out.print("weights: " + weights[0]);
		for(int i=1; i<weights.length; i++)
			System.out.print(", " + weights[i]);
		System.out.println();

		System.out.print("values: " +values[0]);
		for(int i=1; i<values.length; i++)
			System.out.print(", " + values[i]);
		System.out.println();
		
		System.out.println("The max value is under 0-1 constraint: " + maxLoadValue01(capacity, weights, values, maxValue));

		int[] x = new int[weights.length];//��Ʒ�Ƿ�װ������
		traceback01(maxValue, weights, x);

		System.out.print( "The load plan is under 0-1 constraint: " + x[0]);
		for(int i=1; i<x.length; i++)
			System.out.print( ", " + x[i]);
		System.out.println();
		System.out.println();

		System.out.println("The max value is under 0-N constraint: " + maxLoadValue0N(capacity, weights, values, maxValue));
		traceback0N(maxValue, weights, x);
				System.out.print( "The load plan is under 0-N constraint: " + x[0]);
		for(int i=1; i<x.length; i++)
			System.out.print( ", " + x[i]);
		System.out.println();
	}
}
