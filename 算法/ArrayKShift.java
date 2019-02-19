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
		for(int i = 0;i< arrays.length/2;i++){
			int temp = arrays[i];
			arrays[i] = arrays[arrays.length-1-i];
			arrays[arrays.length-1-i] = temp;
		}
		
		for(int i = 0;i< k/2;i++){
			int temp = arrays[i];
			arrays[i] = arrays[k-1-i];
			arrays[k-1-i] = temp;
		}
		
		int length = arrays.length - k;
		for(int i = k;i< k + length/2;i++){
			int temp = arrays[i];
			arrays[i] = arrays[arrays.length-1-i + k];
			arrays[arrays.length-1-i + k] = temp;
		}
	}
}
