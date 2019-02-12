public class Sort {
	static int[] nums = new int[] { 11, 2, 3, 45, 5, 6 };

	public static void main(String[] args) {
		//bubble_sort();
		quick_sort(0, nums.length - 1);
		//select_sort();
		for (int i = 0; i < nums.length; i++) {
			System.out.println(nums[i]);
		}
	}

	// 冒泡排序
	private static void bubble_sort() {
		for (int i = 0; i < nums.length - 1; i++) { // i表示需要比较几趟
			for (int j = 0; j < nums.length - 1 - i; j++) { // j表示每趟比较次数s
				if (nums[j] < nums[j + 1]) {
					int temp = nums[j];
					nums[j] = nums[j + 1];
					nums[j + 1] = temp;
				}
			}
		}
	}

	// 快速排序
	private static void quick_sort(int left, int ritht) {
		int i, j, temp, t;
		if (left > ritht) {
			return;
		}
		temp = nums[left]; //以第一个数为基准点，j在右侧逐个判断对应值是否大于基准数，i在左侧逐个判断对应值是否小于等于基准数，i小于j时找到不符合的后i和j的对应值交换，继续查找
		i = left;          //直到i=j 此时交换当前值与基准数，完成第一次查找，当前数可以确定了左侧都比当前值小，右侧都比当前值大， 左右两侧数据分别重复上述操作
		j = ritht;
		while (i != j) {
			while (nums[j] > temp && i < j) {  //基准数选在左边则从右侧开始  因为要保证相遇数小于基准数
				j--;							//基准数选在右边则从左侧开始  因为要保证相遇数大于基准数
			}
			while (nums[i] <= temp && i < j) {
				i++;
			}
			if (i < j) {
				t = nums[i];
				nums[i] = nums[j];
				nums[j] = t;
			}
		}
		nums[left] = nums[i];
		nums[i] = temp;
		quick_sort(left, i - 1);  //递归排序左侧的数据
		quick_sort(i + 1, ritht); //递归排序右侧的数据
	}
	
	//选择排序
	private static void select_sort(){ //遍历右侧所有找到最大的和当前的交换
		int max;
		int temp;
		for (int i = 0; i < nums.length; i++) {
			max = i;
			for (int j = i+1; j < nums.length; j++) {
				if (nums[j] > nums[max]) {
					max = j;
				}
			}
			temp = nums[i];
			nums[i] = nums[max];
			nums[max] = temp ;
		}
	}
}
