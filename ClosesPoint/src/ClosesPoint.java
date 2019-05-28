/**
 * This class use for finding closes points
 * 
 * @author ����
 *@version
 *@date 2019-5-27
 */
public class ClosesPoint {
	/**
	 * ���������ƽ��������
	 * @param p ƽ��㼯��
	 * @param closesPointsIndex ���������
	 */
	public static void bruteForceClosesPoints(MyPoint[] p, int[] closesPointsIndex){
		int dmin = Integer.MAX_VALUE;
		int d = -1;
		for(int i=0; i<p.length-1; i++){
			for(int j=i+1; j<p.length; j++){
				d = ((p[i].getX() - p[j].getX())*(p[i].getX() - p[j].getX()))
					+ ((p[i].getY() - p[j].getY())*(p[i].getY() - p[j].getY()));
				
				if(d < dmin){
					dmin = d;
					closesPointsIndex[0] = i;
					closesPointsIndex[1] = j;
				}
			}
		}
		
	}
	
	public static void main(String[] args){
		MyPoint p1 = new MyPoint(0,0);
		MyPoint p2 = new MyPoint(1,1);
		MyPoint p3 = new MyPoint(1,2);
		MyPoint p4 = new MyPoint(3,1);
		
		MyPoint[] p = {p1,p2,p3,p4};
		int[] closesPointsIndex = {-1,-1};
		
		bruteForceClosesPoints(p,closesPointsIndex);
		
		System.out.println(closesPointsIndex[0]);
		System.out.println(closesPointsIndex[1]);
	}
		
		

}
