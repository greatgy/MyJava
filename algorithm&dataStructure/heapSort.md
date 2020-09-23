    /**
     * @author yangguang
     * @date 2020/9/23 2:16 下午
     * @description
     */
    public class Test {
        public static void main(String[] args) {
            int[] arr = {3,5,9,2,7,4};
            heapSort(arr);
            Arrays.stream(arr).forEach(System.out::println);
        }

        private static void heapSort(int[] a){
            int k = a.length - 1;
            //两个步骤1、建堆 2、排序
            buildheap(a, k);

            while (k>0) {
                swap(a, 0, k);
                k--;
                heapify(a, 0, k);
            }
        }

        private static void buildheap(int[] a, int k){
            //a.length-1 / 2处为最后一个非叶子结点， 从此开始向上堆化
            for (int i=k/2; i>=0;i--) {
                heapify(a, i, k);
            }
        }

        //堆化就是当前结点值和左右子结点值比较 交换
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
