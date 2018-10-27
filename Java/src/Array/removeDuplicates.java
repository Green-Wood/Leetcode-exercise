package Array;

import java.util.Arrays;

public class removeDuplicates {
    public int remove(int[] nums) {
        Arrays.sort(nums);
        int waiting = 0;
        for (int moving = 1; moving < nums.length; moving++){
            if (nums[waiting] != nums[moving]){
                nums[waiting + 1] = nums[moving];
                waiting++;
            }
        }
        return waiting + 1;
    }
}
