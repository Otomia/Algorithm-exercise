/**
 * 求最大公约数
 * 欧几里得算法又称辗转相除法，用于计算两个整数a、b的最大公约数。
 * 其计算原理依赖于下面的定理：gcd（n，m mod n）
 */
public final class EuclidDivisor {
    /**
     * 计算两个非负整数的最大公约数
     * @param m 大于0正整数
     * @param n 大于0正整数
     * @return m和n的最大公约数
     */
    public static int getDivisor(int m,int n){
        if(m%n == 0) return n;
        if(n%m == 0) return m;
        return m >= n ? getDivisor(n,m%n):getDivisor(m,n%m);
    }
    public static void main(String[] args){
        System.out.println(EuclidDivisor.getDivisor(60,24));
    }
}
