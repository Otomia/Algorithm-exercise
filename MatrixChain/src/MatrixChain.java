/**
* Matrix chain problem
*
* @author liujia
* @version 
* @date 2019-5-28
*/

import java.util.*;

public final class MatrixChain{
	
	/**
	 * ����������С�˴�
	 * @param p n�������n+1������ֵ,p[0,...,n]
	 * @param t ���ѵ�׷�پ���,n+1��,t[0,...,n]
	 * @return ������p����С�˴�
	 */
	public static int minMatrixChain(int[] p, int[][] t){

		return recurMatrixChain(p, t, 1, p.length-1);

//		return matrixChain(p, t);
	}

	/**
	 * �ݹ鷽�������������������Ž�
	 * @param p n�������n+1������ֵ,p[0,...,n]
	 * @param t ���ѵ�׷�پ���,n+1��,t[0,...,n]
	 * @param i ����������ʼλ��,i>0
	 * @param j �������Ľ���λ��,j<=n
	 * @return ������p����С�˴�
	 */
	public static int recurMatrixChain(int[] p, int[][] t, int i, int j) {
		if(i == j) return 0;
		
		int minValue = recurMatrixChain(p, t, i, i) + recurMatrixChain(p, t, i+1, j) + p[i-1]*p[i]*p[j];//��������i��λ�öϿ�
		t[i][j] = i;
		for(int k=i+1; k<j; k++){//��i+1���λ�õ�j-1���λ�ñ����Ͽ��������С�˴�
			int temp = recurMatrixChain(p, t, i, k) + recurMatrixChain(p, t, k+1, j) + p[i-1]*p[k]*p[j];
			if(temp < minValue){
				minValue = temp;
				t[i][j] = k;
			}
		}
		return minValue;
	}

	/**
	 * �ǵݹ鷽�������������������Ž�
	 * @param p n�������n+1������ֵ,p[0,...,n]
	 * @param t ���ѵ�׷�پ���,n+1��,t[0,...,n]
	 * @return ������p����С�˴�
	 */
	public static int matrixChain(int[] p, int[][] t){

		int[][] multiplyNumMatrix = new int[p.length][p.length];//��С�˴ξ���
		for(int i=0; i<p.length; i++){
			for(int j=0; j<p.length; j++){
				multiplyNumMatrix[i][j] = -1;
				t[i][j] = -1;//׷�پ���
			}
		}

		for(int i=0; i<multiplyNumMatrix.length; i++) multiplyNumMatrix[i][i] = 0;//һ������˴�Ϊ0��������Ϊ1�ľ������˴�Ϊ0

		for(int r=2; r<p.length; r++){//������2������������
			for(int i=1; i<=p.length-r; i++){//�������в���Ϊr�����е���С�˴���
				int j = i + r - 1;
				multiplyNumMatrix[i][j] = multiplyNumMatrix[i+1][j] + p[i-1]*p[i]*p[j];
				t[i][j] = i;
				for(int k=i+1; k<j; k++){
					int temp = multiplyNumMatrix[i][k] + multiplyNumMatrix[k+1][j] + p[i-1]*p[k]*p[j];
					if(temp < multiplyNumMatrix[i][j]){
						multiplyNumMatrix[i][j] = temp;
						t[i][j] = k;
					}
				}
			}
		}
		return multiplyNumMatrix[1][p.length-1];
	}

	public static void traceback(int i, int j, int[][] t){
		if(i == j)	return;

		traceback(i, t[i][j], t);
		traceback(t[i][j]+1, j, t);

		System.out.print(i + "," + t[i][j] + "and");
		System.out.print(t[i][j]+1);
		System.out.print("," + j);
		System.out.println();
	}

	public static void main(String[] args) {
		
		int[] matrixChain = {30, 35, 15, 5, 10, 20, 25};
		int matrixLength = matrixChain.length - 1;
		int[][] traceMatrix = new int[matrixLength+1][matrixLength+1];

		System.out.println("Matrix: ");
		System.out.print(matrixChain[0] + "��" + matrixChain[1]);
		for(int i=2; i<=matrixLength; i++){
			System.out.print("," + matrixChain[i-1] + "��" + matrixChain[i]);
		}
		System.out.println();

//		int minMultiplyNum = recurMatrixChain(matrixChain, traceMatrix, 1, matrixLength);
//		int minMultiplyNum = matrixChain(matrixChain, traceMatrix);
		int minMultiplyNum = minMatrixChain(matrixChain, traceMatrix);
		System.out.println("The min multiply num: " + minMultiplyNum);

		traceback(1, matrixLength, traceMatrix);
	}
}
