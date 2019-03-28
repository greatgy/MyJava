
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
}
