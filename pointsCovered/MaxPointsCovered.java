package pointsCovered;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 数组中的元素代表有序的数，给定一根绳子的长度，找出绳子上最多可以放多少个数
 */
class MaxPointsCovered {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int len = scanner.nextInt();
        int target = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        int maxCount = getMaxPointsCoveredNoSort(arr, len);
        System.out.println("MaxPointsCovered: " + maxCount);
        System.out.println("----------------------");
        int min = getMinSub(target, arr);
        System.out.println("MinSub: " + min);

    }

    public static int getMaxPointsCovered(int[] arr, int len) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int left = 0;
        int maxCount = 0;
        // 以right为滑动窗口边界
        for (int right = 0; right < arr.length; right++) {
            while (arr[right] - arr[left] > len && left < right) {
                left++;
            }
            int currCount = right - left + 1;
            maxCount = maxCount > currCount ? maxCount : currCount;
        }
        return maxCount;
    }

    /*
     * 变体题：如果给定的数组 arr 不是有序的（乱序），但你还是需要用一根长度为 L 的绳子去覆盖尽可能多的点，你该怎么改代码？
     */
    public static int getMaxPointsCoveredNoSort(int[] arr, int len) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] newArr = arr.clone();
        Arrays.sort(newArr);
        return getMaxPointsCovered(newArr, len);
    }

    /**
     * Leetcode 209
     * 给定一个含有 n 个正整数的数组和一个正整数 target 。
     * 
     * 找出该数组中满足其总和大于等于 target 的长度最小的 子数组 [numsl, numsl+1, ..., numsr-1, numsr]
     * 并返回其长度。如果不存在符合条件的子数组，返回 0 。
     */
    public static int getMinSub(int target, int[] nums) {
        if (nums == null || nums.length == 0 || target < 0) {
            return 0;
        }
        int left = 0;
        int min = Integer.MAX_VALUE;
        int sum = 0;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= target) {
                int currMin = right - left + 1;
                min = min < currMin ? min : currMin;
                sum -= nums[left];
                left++;
            }
        }
        if (min == Integer.MAX_VALUE) {
            return 0;
        }
        return min;
    }
}
