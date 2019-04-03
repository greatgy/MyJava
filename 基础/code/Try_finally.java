
public class Try_finally {
	
	public static void main(String[] args){
		System.out.println(one());
		System.out.println(two());
		System.out.println(three());
	}
	
	@SuppressWarnings("finally")
	public static int one(){
		int count = 1;
		try {
			count = 2;
			return count;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			count = 3;	
			return 3;
		}
	}
	
	@SuppressWarnings("finally")
	public static int two(){
		int count = 1;
		try {
			count = count / 0;
		} catch (Exception e) {
			count = 2;
			return count;
		} finally {
			count = 3;	
			return count;
		}
	}
	
	@SuppressWarnings("finally")
	public static int three(){
		int count = 1;
		try {
			count = count / 0;
		} catch (Exception e) {
			count = 2;
			return count;
		} finally {
			count = 3;	
		}
		return count;
	}
	// try 和 catch中return后 将操作数栈中的结果复制到局部变量表，然后执行finally中的语句，执行完成后会将局部变量表的那个值压入操作数栈顶作为返回值
	// 当finally中使用return 则返回的是finally中的值，而不是之前复制的了
}
