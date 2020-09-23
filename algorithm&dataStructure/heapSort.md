    /**
     * @author yangguang
     * @date 2020/9/23 2:16 下午
     * @description
     */
    public class Test {
        public static void main(String[] args) {
            int[] arr = {3,5,9,2,7,4};
            //heapSort(arr);
            //Arrays.stream(arr).forEach(System.out::println);
            topN(arr, 2);
            headSort(arr);
        }

        private static void headSort(int[] arr) {
            //两个步骤1、建堆 2、大顶推或小顶堆排序为数组
            buildheap(arr, arr.length - 1);
            heapToArr(arr, arr.length - 1);
            Arrays.stream(arr).forEach(System.out::println);
        }

        private static void topN(int[] arr, int k) {
            //求前K个最大的数，构造小顶堆， K个最小的数，构造大顶堆
            buildheap(arr, k-1);

            //前k个元素以后的元素和堆顶元素比较、交换、堆化
            for (int i = k; i< arr.length;i++) {
                if (arr[i] < arr[0]) {
                    swap(arr, 0, i);
                    heapify(arr, 0, k - 1);
                }
            }

            heapToArr(arr, k-1);

            for (int i=0;i<k;i++) {
                System.out.println(arr[i]);
            }
        }

        //堆结构排序为数组
        private static void heapToArr(int[] a, int k){
            //最后一个元素和首元素交换，最后一个元素为最大或者最小
            //最后一个元素以前的进行堆化
            //一直到首元素为止
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
