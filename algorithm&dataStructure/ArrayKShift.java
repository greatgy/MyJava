import java.util.Arrays;

public class ArryKShift {
	
	public static void main(String[] args){
		int[] a = {1,2,3,4,5,6,7,8}; 
		arrayKShift(a, 2);
		Arrays.stream(a).forEach(x -> {
			System.out.print(x);
		});
	}
	
	public static void arrayKShift(int[] arrays, int k){
		//整体反转
		for(int i = 0;i< arrays.length/2;i++){
			int temp = arrays[i];
			arrays[i] = arrays[arrays.length-1-i];
			arrays[arrays.length-1-i] = temp;
		}
		
		//前k位反转
		for(int i = 0;i< k/2;i++){
			int temp = arrays[i];
			arrays[i] = arrays[k-1-i];
			arrays[k-1-i] = temp;
		}
		
		//length-k位反转
		for(int i = k;i< k + (arrays.length - k)/2;i++){
			int temp = arrays[i];
			arrays[i] = arrays[arrays.length-1-i + k];
			arrays[arrays.length-1-i + k] = temp;
		}
	}
}
