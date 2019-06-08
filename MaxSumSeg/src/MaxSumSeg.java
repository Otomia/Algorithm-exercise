/**
 * ����Ӷκ�����
 *
 * @author liu
 *
 */

public final class MaxSumSeg{

	public static int maxSum(int[] seq, int[] bestSeg){
		
//		return maxSumBruteForce1(seq, bestSeg);
		
//		return maxSumBruteForce2(seq, bestSeg);

//		return maxSumRec(seq, 0, seq.length-1);

		return maxSumProg(seq, bestSeg);
	}
	/**
	 * ���������������������Ӷκ�O(n^3)
	 * @param seq ��������
	 * @param bestSeg ���ز������洢���Ͷε���ʼ�ͽ����±�
	 * @return ����Ӷκ�
	 */
	public static int maxSumBruteForce1(int[] seq, int[] bestSeg){

		int len = seq.length;
		int sum = Integer.MIN_VALUE;

		for(int i=0; i<len; i++){//�Ӷ���ʼλ��
			for(int j=i; j<len; j++){//�Ӷν���λ��
				int thissum = 0;
				for(int k=i; k<=j; k++){//�Ӷ����
					thissum += seq[k];
				}
				if(thissum > sum){//��ǰ�Ӷκ����
					sum = thissum;
					bestSeg[0] = i;
					bestSeg[1] = j;
				}
			}
		}
		return sum;
	}

	/**
	 * Same as ������ O(n^2)
	 */
	public static int maxSumBruteForce2(int[] seq, int[] bestSeg){

		int len = seq.length;
		int sum = Integer.MIN_VALUE;

		for(int i=0; i<len; i++){//�Ӷ���ʼλ��
			int thissum = 0;
			for(int j=i; j<len; j++){//�Ӷν���λ��				
				thissum += seq[j];				
				if(thissum > sum){//��ǰ�Ӷκ����
					sum = thissum;
					bestSeg[0] = i;
					bestSeg[1] = j;
				}
			}
		}
		return sum;
	}

	/**
	 * ���η��������Ӷκ�O(nlogn)
	 * @param seq ��������
	 * @param left ������ʼλ��
	 * @param right ���н���λ��
	 * @param 
	 * @return ����Ӷκ�
	 */
	public static int maxSumRec(int[] seq, int left, int right){

		int sum;

		if(left == right){
			sum = seq[left];
		}
		else{

			int center = (left+right)/2;

			int leftSum = maxSumRec(seq, left, center);
			int rightSum = maxSumRec(seq, center+1, right);

			//��center��������Ӷκ�
			int maxSumL = Integer.MIN_VALUE, lefts = 0;

			for(int i=center; i>=left; i--){
				lefts += seq[i];
				if(lefts > maxSumL){
					maxSumL = lefts;
				}
			}

			//��center��������Ӷκ�
			int maxSumR = Integer.MIN_VALUE, rights = 0;

			for(int i=center+1; i<=right; i++){
				rights += seq[i];
				if(rights > maxSumR){
					maxSumR = rights;
				}
			}

			sum = maxSumL + maxSumR;
		
			if((leftSum > sum) && (leftSum >= rightSum)){//ȡ���
				sum = leftSum;
			}
			if((rightSum > sum) && (rightSum > leftSum)){//ȡ�Ҷ�
				sum = rightSum;
			}
		}
		return sum;
	}

	/**
	 * ��̬�滮�㷨�������Ӷκ�O(n)
	 * @param seq ��������
	 * @param bestSeg ���ز������洢���Ͷε���ʼ�ͽ����±�
	 * @return ����Ӷκ�
	 */
	public static int maxSumProg(int[] seq, int[] bestSeg){
		
		int n = seq.length;
		int sum = Integer.MIN_VALUE, partSum = 0;
		int bestiMaybe = 0;//���ڱ��ÿ�����¿�ʼ��͵ĳ�ʼλ���±�
	
		bestSeg[0] = 0;	
	
		for(int i=0; i<n; i++){		
			if(partSum > 0) {
				partSum += seq[i];
			}
			else {
				partSum = seq[i];
				bestiMaybe = i;  //��ǵ�ǰ��ʼ�±�
			}		
			if(partSum > sum) {
				sum = partSum;
				bestSeg[0] = bestiMaybe; //���³�ʼ�±�
				bestSeg[1] = i;  //�������ν����±�
			}
		}
		return sum;
	}

	/**
	 * 2018�격ʿ��ѧ����
	 * ��֪A���ɲ���ͬ�������ɵ����У�����㷨��A����ĵ��������ӶΡ�
	 * ��ĵ�������������
	 */
	//public static int longestASCSeq(int[] seq, int[]){
		//return 0;
	//}

	public static void main(String[] args){
		
//		int[] sequence = {-2, 11, -4, 13, -5, -2};
		int[] sequence = {2, -5, 8, 11, -3, 4, 6};
//		int[] sequence = {-1, 1};

		int[] bestSeg = {0, 0};

		int seqLen = sequence.length;

		System.out.print("The integer sequence: " + sequence[0]);
		for(int i=1; i<seqLen; i++)
			System.out.print(", " + sequence[i]);
		System.out.println();

		System.out.println("The max sum of segment in the sequence: " + maxSum(sequence, bestSeg));
		System.out.println("The best segment: " + "from sequence[" + bestSeg[0] + "] to sequence[" + bestSeg[1] + "]");
	}
}