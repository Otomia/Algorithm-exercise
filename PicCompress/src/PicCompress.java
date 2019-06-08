/**
 *Picture compress problem
 *
 * @author liujia
 * 
 */

public final class PicCompress{
	
	public static final int SEGLENMAX = 256;//ÿ����������ظ���

	public static final int SEGHEAD = 11;////��ʾÿ�ζ�ͷ��Ϣ�����У�8λΪ�������ظ�����3λΪ����ÿ��������Ҫ�Ĵ洢λ��(�����Ҫ8λ)��

	private static int segNum = 0;

	/**
	* �����С�洢�ռ䷽��
	* 
	* @param p �������ص�ĻҶ�ֵ
	* @param minBits minBits[i]��ʾ���ض�p[1]-p[i]��������ٴ洢λ��,minBits����Ϊ���ظ���+1
	* @param lastSegPexNum  lastSegPexNum[i]��ʾ����p[1]-p[i]֮������أ��õ����Ż��ֵ����һ�����ض������ظ����� lastSegPexNum����Ϊ���ظ���+1
	* @param b b[i]��ʾ����p[1]-p[i]֮������أ����һ���ֶ���ÿ����������洢λ��(�ö����������λ��)��b����Ϊ���ظ���+1
	*/
	public static void segPartition(int[] p, int[] minBits, int[] lastSegPixNum, int[] b){

		int pixNum = p.length;
		minBits[0] = 0;
		lastSegPixNum[0] = 0;
		b[0] = 0;
		int[] pixBit = new int[pixNum+1];//��ʱ�������洢ÿ�����ص���Ҫ��λ��
		pixBit[0] = 0;

		for(int i=1; i<pixNum+1; i++){//���ظ�����1��p.length,������ŷֶ�
			
			pixBit[i] = length(p[i-1]);//���һ���ֶ�ֻ��1������
			int bmax = pixBit[i];
			b[i] = bmax;
			minBits[i] = minBits[i-1] + bmax;
			lastSegPixNum[i] = 1;

			for(int j=2; j<=i && j<=SEGLENMAX; j++){//���һ�����ظ�����2��ʼ����������
				if(bmax < pixBit[i-j+1]) bmax = pixBit[i-j+1];

				if(minBits[i] > minBits[i-j]+j*bmax){
					minBits[i] = minBits[i-j] + j*bmax;
					lastSegPixNum[i] = j;
					b[i] = bmax;
				}
			}
			minBits[i] += SEGHEAD;
		}
	}

	/**
	 * ׷�ٷֶ���Ϣ�͸�������洢λ��
	 * @param pixelNum ���������������
	 * @param lastSegPixNum ������������һ���ֶ����ظ���
	 * @param bitNumForLastSeg ������������һ���ֶ�����洢λ��
	 * @param seg ������������ֶ��е���������
	 * @param bitsForEachSeg������������ֶ��е�ÿ����������洢λ��
	 */
	public static void traceback(int pixelNum, int[] lastSegPixNum, int[] bitNumForLastSeg, int[] seg, int[] bitsForEachSeg){
		if(pixelNum == 0) return;

		traceback(pixelNum-lastSegPixNum[pixelNum], lastSegPixNum, bitNumForLastSeg, seg, bitsForEachSeg);

		seg[segNum] = lastSegPixNum[pixelNum];
		bitsForEachSeg[segNum] = bitNumForLastSeg[pixelNum];
		segNum++;
	}

	public static void output(){
	}

	/**
	 * ��������p��Ҫ�Ķ����ƴ洢λ��
	 * @param p ����ֵ��p>=0
	 * @return ��������ƴ洢λ��
	 */
	private static int length(int p){
		int b = 1;

		int v = p/2;
		while(v > 0){
			b++;
			v = v/2;
		}
		return b;
	}

	public static void main(String[] args){

//		int[] pixel = {0, 2, 168, 138};
		int[] pixel = {10, 12, 15, 255, 1, 2};
//		int[] pixel = {6, 5, 3, 168, 68, 255};
		int pLength = pixel.length;

		int[] minBitNum = new int[pLength+1];
		int[] lastSegPixNum = new int[pLength+1];
		int[] bitNumForLastSeg = new int[pLength+1];

		System.out.println("The pixel value are: ");
		System.out.print(pixel[0]);
		for(int i=1; i<pLength; i++){
			System.out.print(", " + pixel[i]);
		}
		System.out.println();
		segPartition(pixel, minBitNum, lastSegPixNum, bitNumForLastSeg);
		System.out.println("The optimal value is: " + minBitNum[pLength]);

		int[] segment = new int[pLength+1];
		int[] bitsForEachSeg = new int[pLength+1];

		traceback(pLength, lastSegPixNum, bitNumForLastSeg, segment, bitsForEachSeg);

		System.out.print("Decompose into " + segNum + " segments: " + segment[0]);		
		for(int i=1; i<segNum; i++){
			System.out.print(", " + segment[i]);
		}
		System.out.println();

		System.out.print("Bits for each pixel in segments: " + bitsForEachSeg[0]);
		for(int i=1; i<segNum; i++){
			System.out.print(", " + bitsForEachSeg[i]);
		}
		System.out.println();
/*
		System.out.print("Bits for last segment: ");
		for(int i=0; i<=pLength; i++)
			System.out.print(bitNumForLastSeg[i]);
		System.out.println();
		System.out.print("Last segment pixel Number: ");
		for(int i=0; i<=pLength; i++)
			System.out.print(lastSegPixNum[i]);
		System.out.println();
*/
	}
}