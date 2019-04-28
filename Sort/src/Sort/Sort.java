package Sort;
/**
 * The package of different sort algorithm
 * 
 * @author ����
 * @date 2019-4-28
 */
import java.util.*;
public class Sort {
	/**
	 * selection sort algorithm
	 * @param a the array to be sorted
	 * array a will be sorted in non-descending order (a[0] <= a[1] <= ... <= a[n-1])
	 */
	public static void sort(int[] a){
		selectionSort(a);
		//bubbleSort(a);
		//insertSort(a);
		//mergeSort(a,0,a.length-1);
		//quickSort(a,0,a.length-1);
	}
	/**
	 * selection sort algorithm
	 * @param a the array to be sorted
	 * array a will be sorted in non-descending order (a[0] <= a[1] <= ... <= a[n-1])
	 */
	private static void selectionSort(int[] a){
		for(int i=0; i<a.length-1; i++){
			int min=i;
			for(int j=i+1; j<a.length; j++){
				if(a[j]<a[min]){
					min=j;
				}
			}
			swap(a,i,min);
		}
	}
	/**
	 * bubble sort algorithm
	 * @param a the array to be sorted
	 * array a will be sorted in non-descending order (a[0] <= a[1] <= ... <= a[n-1])
	 */
	private static void bubbleSort(int[] a){
		for(int i=0; i<a.length-1; i++){
			for(int j=0; j<a.length-1; j++){
				if(a[j]>a[j+1]) swap(a,j,j+1);
			}
		}
	}
	/**
	 * insert sort algorithm
	 * @param a the array to be sorted
	 * array a will be sorted in non-descending order (a[0] <= a[1] <= ... <= a[n-1])
	 */
	private static void insertSort(int[] a){
		int key;
		for(int i=0; i<a.length; i++){
			key = a[i];
			int j = i-1;
			while(j>=0 && a[j] > key){
				a[j+1] = a[j];
				j--;
			}
			a[j+1] = key;
		}
	}
	private static void mergeSort(int[] a,int left,int right){
		int[] b = new int[a.length];
		mSort(a,b,left,right);
	}
	
	private static void mSort(int[] a,int[] b,int left,int right){
		if(left < right){
			int i = (left + right)/2;
			mSort(a,b,left,i);
			mSort(a,b,i+1,right);
			merge(a,b,left,i,right);//�ϲ�������b
			copy(a,b,left,right);//���ƻ�����a
		}
	}
	
	private static void merge(int[] a,int[] b,int l, int m,int r){
		int i = 1,j = m+1, k = l;
		
		while(i <= m && j <= r){
			if(a[i] <= a[j]) b[k++] = a[i++];
			else b[k++] = a[j++];
		}
		if(i > m) for(int q = j; q <= r; q++) b[k++] = a[q];
		else for(int q = i; q <= m; q++) b[k++] = a[q];
	}
	
	private static void copy(int[] a, int[] b, int left, int right){//��������b��Ԫ�ص�����a
		for(int i = left; i <= right; i++)
			a[i] = b[i];
	}
	
	private static void quickSort(int[] a, int left, int right){
		if(left < right){
			//int q = partition(a,left,right);
			int q = randomizedPartition(a,left,right);
			quickSort(a,left,q-1);
			quickSort(a,q+1,right);
		}
	}
	private static int kthSelect(int[] a,int left,int right,int k){
		if(left == right)
			return a[left];
		int q = partition(a,left,right);
		//int q = randomizedPartition(a,left,right);
		int j = q-left+1;//qΪ����λ�ã�ת��Ϊ���λ��
		
		if(k == j) return a[q];
		if(k < j)
			return kthSelect(a,left,q-1,k);
		else return kthSelect(a,q+1,right,k-j);
	}
	
	private static int randomizedPartition(int[] a, int left, int right){
		Random random = new Random();
		int i = random.nextInt(right-left+1) + left;
		swap(a,i,left);
		return partition(a,left,right);
	}
	
	private static int partition(int[] a,int left, int right){
		int i =left, j = right + 1;
		int x = a[left];
		
		while(true){
			while(a[++i] < x && i < right);
			while(a[--j] > x);
			if( i >= j) break;
			swap(a,i,j);
		}
		a[left] = a[j];
		a[j] = x;
		return j;
	}
	
	private static void swap(int[] list,int k,int i){
		int temp = list[k];
		list[k] = list[i];
		list[i] = temp;
	}
	
	public static void main(String[] args){
		
		int[] a = {45,32,67,54,34,32,21,25,67,98};
		
		for(int i = 0; i < 10; i++)
			System.out.print(a[i] + " ");
		System.out.println();
		
		sort(a);
		for(int i = 0; i < 10; i++)
			System.out.print(a[i] + " ");
		System.out.println();
	}

}
