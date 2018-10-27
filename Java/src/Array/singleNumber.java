package Array;

public class singleNumber {
    public int find(int[] nums) {
        int n = 0;
        for (int i = 0; i < nums.length; i++){
            n = n ^ nums[i];
        }
        return n;
    }

    public static void main(String[] args){
        int[] nums = {1, 1, 3, 2, 3};
        singleNumber si = new singleNumber();
        System.out.println(si.find(nums));
    }
}
