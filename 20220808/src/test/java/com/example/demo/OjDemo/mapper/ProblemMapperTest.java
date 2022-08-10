package com.example.demo.OjDemo.mapper;

import com.example.demo.OjDemo.model.Problem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * created by YT
 * description:
 * User:lenovo
 * Data:2022-08-09
 * Time:21:57
 */
@SpringBootTest
class ProblemMapperTest {

    @Autowired
    private ProblemMapper problemMapper;

    @Test
    void addProblem() {
        Problem problem = new Problem();
        problem.setLevel("简单");
        problem.setTitle("两数相加");
        problem.setDescription("给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。\n" +
                "\n" +
                "你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。\n" +
                "\n" +
                "你可以按任意顺序返回答案。\n" +
                "\n" +
                " \n" +
                "\n" +
                "示例 1：\n" +
                "\n" +
                "输入：nums = [2,7,11,15], target = 9\n" +
                "输出：[0,1]\n" +
                "解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。\n" +
                "示例 2：\n" +
                "\n" +
                "输入：nums = [3,2,4], target = 6\n" +
                "输出：[1,2]\n" +
                "示例 3：\n" +
                "\n" +
                "输入：nums = [3,3], target = 6\n" +
                "输出：[0,1]\n" +
                " \n" +
                "\n" +
                "提示：\n" +
                "\n" +
                "2 <= nums.length <= 104\n" +
                "-109 <= nums[i] <= 109\n" +
                "-109 <= target <= 109\n" +
                "只会存在一个有效答案\n" +
                "进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？\n" +
                "\n" +
                "来源：力扣（LeetCode）\n" +
                "链接：https://leetcode.cn/problems/two-sum\n" +
                "著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。");
        problem.setTemplateCode("class Solution {\n" +
                "    public int[] twoSum(int[] nums, int target) {\n" +
                "\n" +
                "    }\n" +
                "}");
        problem.setTestCode("    public int[] mySolution(int[] nums, int target) {\n" +
                "        int[] indexs = new int[2];\n" +
                "\n" +
                "        java.util.HashMap<Integer,Integer> hash = new java.util.HashMap<Integer,Integer>();\n" +
                "        for(int i = 0; i < nums.length; i++){\n" +
                "            if(hash.containsKey(nums[i])){\n" +
                "                indexs[0] = i;\n" +
                "                indexs[1] = hash.get(nums[i]);\n" +
                "                return indexs;\n" +
                "            }\n" +
                "            hash.put(target-nums[i],i);\n" +
                "        }\n" +
                "        return indexs;\n" +
                "    }\n" +
                "\n" +
                "    public boolean compareTo(int[] result, int[] myResult) {\n" +
                "        java.util.Arrays.sort(myResult);\n" +
                "        java.util.Arrays.sort(result);\n" +
                "        if (result.length != myResult.length) {\n" +
                "            return false;\n" +
                "        } else {\n" +
                "            boolean flag = true;\n" +
                "            for (int i = 0; i < result.length; i++) {\n" +
                "                if (result[i] != myResult[i]) {\n" +
                "                    return false;\n" +
                "                }\n" +
                "            }\n" +
                "            return true;\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public static void main(String[] args) {\n" +
                "        Solution solution = new Solution();\n" +
                "        //testcase1\n" +
                "        int[] nums = {2,7,11,15};\n" +
                "        int target = 9;\n" +
                "        int[] result = solution.twoSum(nums, target);\n" +
                "        int[] myResult = solution.mySolution(nums, target);\n" +
                "        boolean flag = solution.compareTo(result, myResult);\n" +
                "        if (flag) {\n" +
                "            System.out.println(\"testcase1 OK\");\n" +
                "        } else {\n" +
                "            System.out.println(\"testcase1 failed!\");\n" +
                "        }\n" +
                "        //testcase2\n" +
                "        nums = new int[]{3, 2, 4};\n" +
                "        target = 6;\n" +
                "        result = solution.twoSum(nums,target);\n" +
                "        myResult = solution.mySolution(nums, target);\n" +
                "        flag = solution.compareTo(result, myResult);\n" +
                "        if (flag) {\n" +
                "            System.out.println(\"testcase2 OK\");\n" +
                "        } else {\n" +
                "            System.out.println(\"testcase2 failed!\");\n" +
                "        }\n" +
                "    }");
        int ret = problemMapper.addProblem(problem);
        System.out.println(ret);
    }

    @Test
    void getProblemList() {
        List<Problem> problems = problemMapper.getProblemList();
        for (Problem p :
                problems) {
            System.out.println(p);
        }
    }

    @Test
    void getProblem() {
        Problem problem = problemMapper.getProblem(1);
        System.out.println(problem);
    }

    @Test
    void getTestCode() {
        String str = problemMapper.getTestCode(1);
        System.out.println(str);
    }
}