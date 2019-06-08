/**
* This class use for tesing text match algorithms
*
* @author iiujia
* @version 1.1
* @date 2019-5-27
*/

import java.util.regex.Pattern;

public final class MyStringMatch{

	/**
	 * �����ַ���ƥ���㷨ʵ��
	 *@param t �ı��ַ���
	 *@param p ģʽ�ַ���
	 *@return �����ҳɹ��������ı���һ��ƥ���Ӵ��еĵ�һ���ַ���λ�ã����򷵻�-1
	 */
	public static int bruteForceStringMatch(String t, String p){
		
		char[] text = t.toCharArray();
		char[] pattern = p.toCharArray();

		return bruteForceStringMatch(text, pattern);
	}
	
	/**
	 * �����ַ���ƥ���㷨ʵ��
	 *@param t �ı��ַ�����
	 *@param p ģʽ�ַ�����
	 *@return �����ҳɹ��������ı���һ��ƥ���Ӵ��еĵ�һ���ַ���λ�ã����򷵻�-1
	 */
	public static int bruteForceStringMatch(char[] t, char[] p){

		int n=t.length;
		int m=p.length;

		for(int i=0; i<=(n-m); i++){
			int j=0;
			while(j<m && p[j]==t[i+j])
				j=j+1;
			if(j==m)
				return i;
		}
		return -1;
/*
		String text = new String(t);
		String pattern = new String(p);
		boolean b = Pattern.matches(".*" + pattern + ".*", text);
		if(b == true) return 1;
		else return 0;
*/
	}	

	public static void main(String[] args){
		char[] text={'N', 'O', 'B', 'O', 'D', 'Y', '_', 'N', 'O', 'T', 'I', 'C', 'E', 'D', '_', 'H', 'I', 'M'};
		char[] pattern={'N', 'O', 'T'};

		for(int i=0; i<text.length; i++)
			System.out.print(text[i]+" ");
		System.out.println();
		for(int i=0; i<pattern.length; i++)
			System.out.print(pattern[i]+" ");
		System.out.println();
		
		System.out.println(bruteForceStringMatch(text, pattern));
	}
}


/*
#include <iostream>
using namespace std;


int SubString(string text,string pattern){
    int m = text.size();
    int n = pattern.size();
    // ����ƥ��
    for(int i = 0;i < m - n;++i){
        int j = 0;
        while(j < n && text[i+j] == pattern[j]){
            ++j;
        }//while
        // match
        if(j == n){
            return i;
        }//if
    }//for
    return -1;
}

int main(){
    string text("hello world!");
    string pattern("o wo");
    int result = SubString(text,pattern);
    cout<<"�±�λ��->"<<result<<endl;
    return 0;
}
*/