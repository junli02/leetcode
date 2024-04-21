package hot100.普通数组;

import java.util.Arrays;

/**
 * @Author: jun
 * @Date: 2024/04/21/14:29
 */
public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        int[] ints = main.productExceptSelf(new int[]{1, 2, 3, 4});
        System.out.println(Arrays.toString(ints));
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        main.rotate(arr, 3);
        System.out.println(Arrays.toString(arr));
    }

    //旋转图像， 1.使用辅助数组
    //寻找规律， 先水平翻转， 在对称翻转
    public void rotate(int[][] matrix) {
        for (int i = 0; i < matrix.length / 2; i++) {
            for (int j = 0; j < matrix.length; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[matrix.length - i - 1][j];
                matrix[matrix.length - i - 1][j] = tmp;
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
    }

    //189。轮转数组
    //数组反转 ， 1 2 3 4 5 6 7
    // 7 6 5     4 3 2 1
    // 5 6 7     1 2 3 4
    public void rotate(int[] nums, int k) {
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] arr, int start, int end){
        while (start < end){
            int tmp = arr[start];
            arr[start] = arr[end];
            arr[end] = tmp;
            start ++;
            end --;
        }
    }
    //238. 除以自身以外数组的乘积 1.一个数组维护前缀乘积 ， 一个数组维护后缀乘积
    public int[] productExceptSelf(int[] nums) {
        int[] answer = new int[nums.length];
        answer[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            answer[i] = nums[i - 1] * answer[i - 1];
        }
        int[] end = new int[nums.length];
        end[nums.length - 1] = 1;
        for (int i = end.length - 2; i >= 0; i--) {
            end[i] = nums[i + 1] * end[i + 1];
        }
        for (int i = 0; i < nums.length; i++) {
            answer[i] = answer[i] * end[i];

        }
        return answer;
    }
}
