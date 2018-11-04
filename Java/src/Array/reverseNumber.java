package Array;


/*
求一个数组的逆序数

逆序对：数列a[1],a[2],a[3]…中的任意两个数a[i],a[j] (i<j)，如果a[i]>a[j],那么我们就说这两个数构成了一个逆序对

逆序数：一个数列中逆序对的总数

如数列 3 5 4 8 2 6 9

(5,4)是一个逆序对，同样还有(3,2),(5,2),(4,2)等等

[1, 4, 3, 2] 的逆序数为3

HINT: MergeSort
 */
public class reverseNumber {
    private int[] temp;
    private int count = 0;

    public int count(int[] nums){
        temp = new int[nums.length];
        MergeSort(0, nums.length-1, nums);
        return count;
    }

    private void MergeSort(int lo, int hi, int[] nums){
        if (lo >= hi) return;
        int mid = (hi-lo)/2 + lo;
        MergeSort(lo, mid, nums);
        MergeSort(mid+1, hi, nums);

        int i = lo, j = mid + 1;
        int t = lo;
        while (i <= mid && j <= hi){
            if (nums[i] < nums[j]){
                temp[t++] = nums[i++];
            } else {
                temp[t++] = nums[j++];
                count += mid - i + 1;               // 累积逆序数
            }
        }
        while (i <= mid) temp[t++] = nums[i++];
        while (j <= hi) temp[t++] = nums[j++];
        System.arraycopy(temp, lo, nums, lo, hi-lo+1);
    }

    public static void main(String[] args){
        System.out.println(new reverseNumber().count(new int[]{1, 4, 3, 2}));
    }
}
