/**
 * Ͷ������
 * @author ����
 *
 */
public class Investment {
	/**
	 * ���Ͷ����������㷨
	 * 
	 * @param pNum ��Ŀ����
	 * @param mAmount �����Ͷ��Ǯ��
	 * @param vFun Ͷ��Ч�溯��[pNum-1][m]
	 * @param earning ��Ŀ�����������ֵΪearning[pNum][mAmount],pNum+1��,mAmount+1��
	 * @param t ���Ͷ�ʷ���׷�پ���t[i][j]��ʾi����ĿjԪǮ����£����һ����ĿͶ��Ǯ����pNum+1�У�mAmount+1��
	 * @return �������
	 */
	public static int maxEarning(int pNum, int mAmount, int[][] vFun, int[][] earning, int[][] t){
		//����������earning��׷�پ���t��ʼ��
		initial(pNum, mAmount, vFun, earning, t);
		
		for(int p=1; p<=pNum; p++){//���Բ���������Ŀ����
			
			for(int m=1; m<=mAmount; m++){//���ϳ��������ʽ��
				earning[p][m] = Integer.MIN_VALUE;
				
				//���һ����ĿͶ�ʽ��Բ�������Ѱ���������
				for(int moneyForLastProj=0; moneyForLastProj<=m; moneyForLastProj++){
					int thisEarning = earning[p-1][m-moneyForLastProj] + vFun[p-1][moneyForLastProj];
					
					if(thisEarning > earning[p][m]){
						earning[p][m] = thisEarning;
						t[p][m] = moneyForLastProj;
					}
				}
			}
		}
		return earning[pNum][mAmount];	
	}
	
	public static void traceback(int pNum, int mAmount, int[][] t){
		
		if(pNum == 0 || mAmount == 0) return;
		
		traceback(pNum -1, mAmount - t[pNum][mAmount], t);
		
		System.out.print("The money for project " + pNum + ": " + t[pNum][mAmount]);;
		System.out.println();
	}
	
	/**
	 * ����������earning��׷�پ���t����ֵ
	 */
	private static void initial(int pNum, int mAmount, int[][] vFun, int[][] earning, int[][] t){
		
		//��һ�и���ֵ0����ʾ0����Ŀ�����Ͷ�ʽ������
		for(int i=0; i<=mAmount; i++){
			earning[0][i] = 0;
			t[0][i] = 0;
		}
		//��һ�и���ֵ0����ʾ0�ʽ�����²�ͬ��Ŀ����
		for(int j=0; j<=pNum; j++){
			earning[j][0] = 0;
			t[j][0] = 0;
		}
	}
	
	public static void main(String[] args){
		
		int[][] vestFunction = {{0,11,12,13,14,15},
				                {0,0,5,10,15,20},
				                {02,10,30,32,40},
				                {0,20,21,22,23,24}};
		int projNum = vestFunction.length;
		int moneyAmount = vestFunction[0].length - 1;//Ͷ�ʺ���������0Ԫ����
		int[][] earning = new int[projNum+1][moneyAmount+1];
		int[][] trace = new int[projNum+1][moneyAmount+1];
		
		System.out.println("The vest function: ");
		for(int i=0; i<projNum; i++){
			System.out.print("p" + (i+1) + ": ");
			for(int j=0; j<moneyAmount; j++){
				System.out.print(vestFunction[i][j] + ", ");
			}
			System.out.println();
		}
		System.out.println();
		
		int maxEarnings = maxEarning(projNum, moneyAmount, vestFunction, earning, trace);
		
		System.out.println("The max Earnings: " + maxEarnings);
		
		traceback(projNum,moneyAmount,trace);
	}

}
