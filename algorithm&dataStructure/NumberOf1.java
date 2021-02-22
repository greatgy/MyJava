public class NumberOf1 {
		
	//n&(n-1)  可以用来判断整数中1的个数，及是否是2的幂次数
	public static void main(String[] args){
		int reult = solution(15);
		System.out.println(reult);
	}
	public static int solution(int n) {
        int count = 0; 
        while(n != 0){
            count++;
            n = n & (n - 1);
        }
        return count;
    }
	
	public static boolean is2Power(int n){
		return n> 0 && (n &(n-1)) == 0;
	}
	
	//获取整数中最后一个1的值
	public statistic int getLastOne(int k) {
		return k & (-k);
	}
}
