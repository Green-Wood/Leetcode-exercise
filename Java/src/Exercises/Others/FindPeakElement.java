package Exercises.Others;

public class FindPeakElement {
    public int findPeakElement(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        while (lo < hi){
            int mid1 = (hi - lo) / 2 + lo;
            int mid2 = mid1 + 1;
            if (nums[mid1] < nums[mid2]){
                lo = mid2;
            } else {
                hi = mid1;
            }
        }
        return lo;
    }

    public static void main(String[] args){
        System.out.println(new FindPeakElement().findPeakElement(new int[]{1,5,1,2,3,6,7}));
    }
}
