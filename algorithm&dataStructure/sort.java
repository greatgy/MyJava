public class Sort {
	static int[] nums = new int[] { 11, 2, 3, 45, 5, 6 };

	public static void main(String[] args) {
		//bubble_sort();
		//quick_sort(0, nums.length - 1);
		//select_sort();
		insert_sort();
		for (int i = 0; i < nums.length; i++) {
			System.out.println(nums[i]);
		}
	}

	// 冒泡排序
	private static void bubble_sort() { //O(n^2) 稳定
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

	// topN
	    public static int topN(int[] nums, int left, int right, int n) {

		int p = partition(nums, left, right);

		if (p == n) {
		    return nums[p];
		} else if (p > n) {
		    return topN(nums, left, p - 1, n);
		} else {
		    return topN(nums, p + 1, right, n);
		}
	    }

	// 快速排序
	    public static void quick_sort(int[] nums, int left, int right) {
		if (left >= right) {
		    return;
		}

		int p = partition(nums, left, right);

		sort(nums, left, p - 1);
		sort(nums, p + 1, right);
	    }

	    public static int partition(int[] nums, int left, int right) {
		int p = right;
		int i = left;
		for (int j=left; j<right; j++){
		    if (nums[j] > nums[p]) {
			swap(nums, i, j);
			i++;
		    }
		}
		swap(nums, i, p);
		return i;
	    }

	    public static void swap(int[] nums, int left, int right) {
		if (left == right) {
		    return;
		}
		int tmp = nums[left];
		nums[left] = nums[right];
		nums[right] = tmp;
	    }
	
	//选择排序
	private static void select_sort(){ //遍历右侧所有找到最大的和当前的交换
		int max;                       //O(n^2) 不稳定 快于冒泡
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
	
	
	//插入排序
	private static void insert_sort(){ //O(n^2) 稳定 快于选择和冒泡
		int temp = 0;
		for (int i = 1; i < nums.length; i++) {
			for(int j=i;j>0;j--){
				if (nums[j] < nums[j-1]) {
					temp = nums[j];
					nums[j] = nums[j-1];
					nums[j-1] = temp ;
				} else {
					break;
				}
			}
		}
	}
}

