/**
* This class use for tesing search algorithms
*
* @author liujia
* @version 
* @date 2019
*/

public final class Search{
    /**
     * Find the max element in array a.
     * @param a array contain n elements
     * @return the max value found in a. 
     */
	public static int maxElement(int[] a){
//		return sequentialSearchMax(a);
		return divideSearchMax(a, 0, a.length-1);
	}

	private static int sequentialSearchMax(int[] a){
		int maxVal = a[0];
		for(int i=1; i<a.length; i++){
			if(a[i] > maxVal)
				maxVal = a[i];
		}
		return maxVal;
	}

    /*
     * Find the max value in array a[first..max].
     * @param a array 
	 * @param left low end of range. 
     *              Requires 0 <= left <= right <= a.length-1.
     * @param right high end of range.  
     *              Requires 0 <= left <= right <= a.length-1.
     * @return max value in a
     */	 
	private static int divideSearchMax(int[] a, int left, int right){
		int middle = (left + right)/2;

		if(left >= right) return a[right];
		int leftMax = divideSearchMax(a, left, middle);
		int rightMax = divideSearchMax(a, middle+1, right);
		
		return (leftMax >= rightMax)?leftMax:rightMax;
	}
	
	/**
     * Find the first occurrence of x in sorted array a.
     * @param a array sorted in increasing order (a[0] <= a[1] <= ... <= a[n-1])
     * @param x value to find
     * @return lowest i such that a[i]==x, or -1 if x not found in a. 
     */	
	public static int search (int[] a, int x) {
		 
//		 return sequentialSearch(a, x);
		 return binarySearch(a, x, 0, a.length-1);
//		 return binarySearch(a, x);
	}
	
	/**
	 * ˳������㷨ʵ��
	 * @param a ��n��Ԫ�ص�����
	 * @param x �����Ҽ�ֵ
	 * @return ��һ��ֵ����x��Ԫ��λ��i��a[i]=x�����a�в�����x���򷵻�-1.
	 */
	public static int sequentialSearch(int[] a, int x){
//		for (int i=0; i<a.length; i++){
		for(int i : a){
			if(a[i] == x){
				return i;
			}
		}
		return -1;
	}

    /*
     * Find the first occurrence of x in sorted array a[first..max].
     * @param x value to find
     * @param a array sorted in increasing order 
     *          (a[0] <= a[1] <= ... <= a[n-1])
     * @param first low end of range. 
     *              Requires 0 <= first <= a.length-1.
     * @param max high end of range.  
     *              Requires 0 <= max <= a.length-1.
     * @return lowest i such that first<=i<=max and a[i]==x, 
     *         or -1 if there's no such i. 
     */	 
	public static int binarySearch(int[] a, int x, int left, int right){
		int middle = (left + right)/2;

		if (left > right) return -1;
		if (x < a[middle]) return binarySearch(a, x, left, middle-1);
		if (x > a[middle]) return binarySearch(a, x, middle+1, right);
		if (x == a[middle]) {
			if (middle > 0 && x == a[middle-1] )
				return binarySearch(a, x, left, middle-1);
			else 
				return middle;
		}
		return -1;
	}

	/**
	 * Same as binarySearch(int[] a, int x, int left, int right)
	 */
	public static int binarySearch(int[] a, int x){
		int left = 0;
		int right = a.length-1;

		while (left <= right) {
			int middle = (left+right)/2;
			if (x < a[middle]) right = middle-1;
			if (x > a[middle]) left = middle+1;
			if (x == a[middle]) {
				if (middle > 0 && x == a[middle-1])
					right = middle-1;
				else
					return middle;
			}
		}
		return -1;
	}

	public static void main(String[] args){

		int[] a = {3,5,0,8,8,-1,15};
		int x = 8;

		System.out.print("Array: ");
		for (int i=0; i<a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
//		System.out.println("Search for: " + x);		
//		System.out.println("Result index: " + search(a,x));

		System.out.println("The max value in a: " + maxElement(a));
	}
}
