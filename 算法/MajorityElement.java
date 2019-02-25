public class MajorityElement {

	/*输入: [3,2,3]
	     输出: 3
	
		输入: [2,2,1,1,1,2,2]
		输出: 2
		*/
	public static void main(String[] args) {
		int[] arr = {2,2,1,1,1,2,2};
		System.out.println(majorityElement(arr));
	}
	
	public static int majorityElement(int[] arr){
		int res = 0, count = 0;
		for (int num: arr) {
			if (count == 0) {
				res = num;
				count++;
			} else if (num == res) {
				count++;
			} else {
				count--;
			}
		}
		return res;
	}
}
