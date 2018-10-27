package Exercises;

public class CanJump {
    public boolean canJump(int[] nums) {
        int max = 0;
        for (int i = 0; i <= max && i < nums.length; i++){
            max = Math.max(nums[i] + i, max);
        }
        return max >= nums.length-1;
    }

    public static void main(String[] args){
    }
}
