
/**
 * Lengest common sequence problem
 *
 * @author liujia
 * 
 * 
 **/

public final class LCS {

	/**
	 * ���������������㷨
	 * @param s1 ��һ�����У�����Ϊm
	 * @param s2 �ڶ������У�����Ϊn
	 * @param t �����������׷�پ���m+1��,n+1��
	 * @return ����������г���
	 */
	public static int longestComSeq(String s1, String s2, int[][] t){
		char[] seq1 = s1.toCharArray();
		char[] seq2 = s2.toCharArray();

		int[][] res = new int[seq1.length+1][seq2.length+1];
		
		return longestComSeq(seq1, seq2, res, t);
	}
	
	/**
	* ���������������㷨
	* 
	* @param s1 ��һ�����У�����Ϊm
	* @param s2 �ڶ������У�����Ϊn
	* @param res ����������е������������m+1��,n+1�У���������Ϊ0���������ƥ�䳤��Ϊ0��Ϊ���ƹ�ϵ��ֵ
	* @param tra �����������׷�پ���m+1��,n+1��
	* @return ����������г���
	*/
	public static int longestComSeq(char[] s1, char[] s2, int[][] res, int[][] tra) {

		tra[0][0] = 0; 
		
		for(int i=0; i<s1.length+1; i++)	res[i][0] = 0;//��������0�и���ֵ0
		for(int j=1; j<s2.length+1; j++)	res[0][j] = 0;//��������0�и���ֵ0

		for(int i=1; i<s1.length+1; i++){
			for(int j=1; j<s2.length+1; j++){
				if(s1[i-1] == s2[j-1]){//β���ַ�ƥ��
					res[i][j] = res[i-1][j-1] + 1;
					tra[i][j] = 1;//��ʾ����������tra[i-1][j-1]׷��
				}
				else if(res[i-1][j] >= res[i][j-1]){//��ǰ�������������s1β��ǰ��һλ��s2����
					res[i][j] = res[i-1][j];
					tra[i][j] = 2;//2��ʾ��������tra[i-1][j]׷��
				}
				else{//��ǰ�������s2β��ǰ��һλ��s1����
					res[i][j] = res[i][j-1];
					tra[i][j] = 3;//3��ʾ��������tra[i][j-1]׷��
				}
			}
		}
		return res[s1.length][s2.length];
	}

	/**
	* �����������׷���㷨
	* 
	* @param s1 ��һ������
	* @param tra �����������׷�پ���m+1��,n+1��
	* @param tailS1 ��һ�����еĽ���λ�ã�1,2,...,m
	* @param tailS2 �ڶ������еĽ���λ�ã�1,2,...,n
	*/
	public static void traceback(char[] s1, int[][] tra, int tailS1, int tailS2){
		if  (tailS1 == 0 || tailS2 == 0) return;

		if  (tra[tailS1][tailS2] == 1) {//������׷��tra[i-1][j-1]
			traceback(s1, tra, tailS1-1, tailS2-1);
			System.out.print(s1[tailS1-1] + " ");
		}
		else if (tra[tailS1][tailS2] == 2) {//��������׷��tra[i-1][j]
			traceback(s1, tra, tailS1-1, tailS2);
		}
		else traceback(s1, tra, tailS1, tailS2-1);//��������tra[i][j-1]׷��
	}

	public static void main(String[] args){

		char[] s1 = {'a', 'b', 'c', 'b', 'd', 'a', 'b'};
		char[] s2 = {'b', 'd', 'c', 'a', 'b', 'a'};
//		String seq1 = "abcbdab";
//		String seq2 = "bdcaba";
		
//		char[] s1 = {'1', '2', '3', '4', '5', '6', '7', '8'};
//		char[] s2 = {'1', '4', '2', '3', '6', '8', '5', '7'};		

		System.out.print("sequence1: ");
		for(int i=0; i<s1.length; i++){
			System.out.print(s1[i] + " ");
		}
		System.out.println();
		System.out.print("sequence2: ");
		for(int i=0; i<s2.length; i++){
			System.out.print(s2[i] + " ");
		}
		System.out.println();

		int[][] resultMatrix = new int[s1.length+1][s2.length+1];
		int[][] traceMatrix = new int[s1.length+1][s2.length+1];
		
		System.out.println("The length of the longest common sequence is: " + longestComSeq(s1, s2, resultMatrix, traceMatrix));

//		System.out.println("The length of the longest common sequence is: " + longestComSeq(seq1, seq2, traceMatrix));

		System.out.print("The longest common sequece is: ");
		traceback(s1, traceMatrix, s1.length, s2.length);
	}
}