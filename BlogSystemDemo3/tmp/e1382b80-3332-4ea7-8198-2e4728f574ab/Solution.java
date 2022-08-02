import java.util.*;
class Solution {
    public int[] twoSum(int[] nums, int target) {

    }
    public int[] mySolution(int[] nums, int target) {
        int[] indexs = new int[2];

        HashMap<Integer,Integer> hash = new HashMap<Integer,Integer>();
        for(int i = 0; i < nums.length; i++){
            if(hash.containsKey(nums[i])){
                indexs[0] = i;
                indexs[1] = hash.get(nums[i]);
                return indexs;
            }
            hash.put(target-nums[i],i);
        }
        return indexs;
    }

    public boolean compareTo(int[] result, int[] myResult) {
        Arrays.sort(myResult);
        Arrays.sort(result);
        if (result.length != myResult.length) {
            return false;
        } else {
            boolean flag = true;
            for (int i = 0; i < result.length; i++) {
                if (result[i] != myResult[i]) {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        //testcase1
        int[] nums = {2,7,11,15};
        int target = 9;
        int[] result = solution.twoSum(nums, target);
        int[] myResult = solution.mySolution(nums, target);
        boolean flag = solution.compareTo(result, myResult);
        if (flag) {
            System.out.println("testcase1 OK");
        } else {
            System.out.println("testcase1 failed!");
        }
        //testcase2
        nums = new int[]{3, 2, 4};
        target = 6;
        result = solution.twoSum(nums,target);
        myResult = solution.mySolution(nums, target);
        flag = solution.compareTo(result, myResult);
        if (flag) {
            System.out.println("testcase2 OK");
        } else {
            System.out.println("testcase2 failed!");
        }
    }
}