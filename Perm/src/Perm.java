/**
 * parmutation algorithm
 * @author liujia
 */

public class Perm {
    /**
     * 实现序列全排列输出
     * @param list 待排序序列
     * @param k 排列序列起始位置
     * @param m 排列序列结束位置
     */
    public static void perm(int[] list,int k,int m){
        if(k==m){
            for(int i=0; i<=m; i++)
                System.out.print(list[i]);
            System.out.println();
        }
        else{
            for(int i=k; i<=m; i++){
                swap(list,k,i);
                perm(list,k+1,m);
                swap(list,k,i);
            }
        }
    }
    public static void swap(int[] list,int k,int i){
        int temp=list[k];
        list[k]=list[i];
        list[i]=temp;
    }
    public static void main(String[] args){
        int [] list = new int[5];
        for(int i=0; i<5; i++)
            list[i]=i;
        perm(list,0,4);
    }
}
