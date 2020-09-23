public class Test {
    public static void main(String[] args) {
        int[] arr = {3,5,9,2,7,4};
        heapSort(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }

    private static void heapSort(int[] a){
        buildheap(a);
        int k = a.length - 1;
        while (k>0) {
            swap(a, 0, k);
            k--;
            heapify(a, 0, k);
        }
    }

    private static void buildheap(int[] a){
        for (int i=(a.length-1)/2; i>=0;i--) {
            heapify(a, i, a.length - 1);
        }
    }

    private static void heapify(int[] arr, int i, int n){
        while (true) {
            int max = i;
            if (2*i+1 <= n && arr[2*i+1] > arr[max]) {
                max = 2*i + 1;
            }
            if (2*i+2 <= n && arr[2*i+2] > arr[max]) {
                max = 2*i + 2;
            }
            if (max == i) {
                break;
            }
            swap(arr, i, max);
            i = (i-1)/2;
        }
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
