/**
 * ��·�������������
 *
 * @author liujia
 * 
 */

public final class MNS{

	/**
	 * ���������������㷨
	 * @param perm ˳����������ӵ��һ������
	 * @param size �������Ŀ�洢���󣬽�Ϊperm����+1
	 * @return �����������Ŀ
	 */
	public static int maxNetSet(int[] perm, int[][] size){
		int portNum = perm.length;

		//���󸳳�ֵ���϶�0�����������(i=0)���������Ϊ0
		for(int j=0; j<=portNum; j++)	size[0][j] = 0;

		for(int i=1; i<=portNum; i++){//�϶˽���������
			for(int j=0; j<perm[i-1]; j++){//�¶˽���������
				size[i][j] = size[i-1][j];//��i�ŵ����޷���
			}
			for(int j=perm[i-1]; j<=portNum; j++){//�¶˽���������
				size[i][j] = max(size[i-1][perm[i-1]-1]+1, size[i-1][j]);//ȡ�ŵ�i�ŵ��ߺͲ��ŵ�i�ŵ����е��������ֵ
			}
		}
		return size[portNum][portNum];
	}

	/**
	 * ׷�����ɷ��õ��߱��
	 * @param perm �����������б��
	 * @param size ���ɷ��õ�������׷�پ���
	 * @param lines ���ز������洢���ɷ��õ��߱��
	 */
	public static void traceback(int[] perm, int[][] size, int[] lines){

		int n = perm.length;
		int j = n;
		int linesNum = 0;//���õ�������������

		for(int i=n; i>0; i--){
			if(size[i][j] != size[i-1][j]){//�ŵ�i�ŵ���
				lines[linesNum++] = i;
				j = perm[i-1] - 1;//�Ž���i�ŵ���֮����j�±�Ϊperm[i-1],perm[i-1]��Ӧ��i�ŵ�������λ��
			}
		}
	}

	public static void outputLines(int[] perm, int[][] size, int linesNum){
		
		int n = perm.length;
		int[] lines = new int[linesNum];

		traceback(perm, size, lines);

		System.out.print("Lines No: " + lines[linesNum-1]);
		for(int i=linesNum-2; i>=0; i--){//������߱�ţ���ջ˳��
			System.out.print(", " + lines[i]);
		}
		System.out.println();
	}

	private static int max(int a, int b){
		return a > b ? a : b;
	}

	public static void main(String[] args){
		
		int[] perm = {1, 4, 2, 3, 6, 8, 5, 7};
		int n = perm.length;
		int[][] size = new int[n+1][n+1];

		int[] lines = new int[n];
		int[] linesNum = new int[1];

		System.out.print("�Ͻ��������: " + perm[0]);
		for(int i=1; i<n; i++)
			System.out.print(", " + perm[i]);
		System.out.println();

		System.out.print("�½��������: " + 1);
		for(int i=2; i<=n; i++)
			System.out.print(", " + i);
		System.out.println();

		int maxLinesNum = maxNetSet(perm, size);
		System.out.println(maxLinesNum);

		outputLines(perm, size, maxLinesNum);
	}
}