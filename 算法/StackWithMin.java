import java.util.ArrayList;
import java.util.List;

/*
 * 增加获取栈中最小值的方法
 */
public class StackWithMin {
	private static List<Integer> data = new ArrayList<>();
	private static List<Integer> mins = new ArrayList<>();
	
	public void push(int num) throws Exception {
		data.add(num);
		if (mins.isEmpty()) {
			mins.add(0);
		} else {
			int min = getMin();
			if (num < min) {
				mins.add(data.size() - 1);
			}
		}
	}
	
	public int pop() throws Exception {
		if (data.isEmpty()) {
			throw new Exception("stack is empty");
		}
		int popIndex = data.size() - 1;
		int minIndex = mins.get(mins.size() - 1);
		if (popIndex == minIndex) {
			mins.remove(minIndex);
		}
		return data.remove(popIndex);
	}
	
	public static int getMin() throws Exception {
		if (data.isEmpty()) {
			throw new Exception("stack is empty");
		}
		int minIndex =  mins.get(mins.size() - 1);
		return data.get(minIndex);
	}
}
