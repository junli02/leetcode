package hot100.回溯;


import com.sun.webkit.dom.EntityReferenceImpl;
import netscape.security.UserTarget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: jun
 * @Date: 2024/04/22/19:06
 */
public class Main {
    /**
     * void backtracking(参数) {
     * if (终止条件) {
     * 存放结果;
     * return;
     * }
     * <p>
     * for (选择：本层集合中元素（树中节点孩子的数量就是集合的大小）) {
     * 处理节点;
     * backtracking(路径，选择列表); // 递归
     * 回溯，撤销处理结果
     * }
     * }
     */
    public static void main(String[] args) {
        Main main = new Main();
        List<List<Integer>> permute = main.permute(new int[]{1, 2, 3, 4});
        System.out.println(permute);
        System.out.println(main.subsets(new int[]{1, 2, 3}));
        System.out.println(main.letterCombinations("23"));
        System.out.println(main.combinationSum(new int[]{2, 3, 6, 7}, 7));
        char[][] arr = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        System.out.println(main.exist(arr, "ABCB"));
        List<List<String>> aab = main.partition("aa");
        System.out.println(aab);
    }

    //最长回文子串
    /*public String longestPalindrome(String s) {
        int length = s.length();
        int[][] dp = new int[length][length];
        //状态初始化
        for (int i = 0; i < length; i++) {
            dp[i][i] = 1;
        }
        for (int len = 2; len <= length; len++) {
            for (int i = 0; i < length; i++) {
                int j = i + len - 1;
                if (j >= length) break;
                if (s.charAt(i) == s.charAt(j)) {
                    if (i + 1 == j) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
            }
        }

        int maxLen = 0;
        int start = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < length; j++) {
                if(dp[i][j] == 1 && (j - i + 1) > maxLen) {
                    maxLen = j - i + 1;
                    start = i;
                }
            }
        }
        return s.substring(start, start + maxLen);
    }*/


    public List<List<String>> partition(String s) {
        int length = s.length();
        int[][] dp = new int[length][length];
        //状态初始化
        for (int i = 0; i < length; i++) {
            dp[i][i] = 1;
        }
        //判断是否为回文子串
        for (int len = 2; len <= length; len++) {
            for (int i = 0; i < length; i++) {
                int j = i + len - 1;
                if (j >= length) break;
                if (s.charAt(i) == s.charAt(j)) {
                    if (i + 1 == j) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
            }
        }

        List<List<String>> res = new ArrayList<>();
        backtracking(new ArrayList<>(), 0, s, dp, res);
        return res;
    }


    public void backtracking(List<String> mid, int start, String s, int[][] dp, List<List<String>> res) {
        //剪枝
        //if(dp[start][start + length - 1] == 0) return;

        //终止条件
        if(s.length() == start){
            res.add(new ArrayList<>(mid));
            return;
        }

        for (int len = 1; len <= s.length(); len++) {
            if(start + len > s.length()) break;
            if(dp[start][start + len - 1] == 1){
                String substring = s.substring(start, start + len);
                //添加
                mid.add(substring);
                backtracking(mid, start + len, s, dp, res);

                //撤消
                mid.remove(mid.size() - 1);
            }
        }


    }

    boolean success = false;
    //判断是否存在对应字符串,效率很低,下面这个是较为好的解决方案

    /**
     * class Solution {
     * public boolean exist(char[][] board, String word) {
     * char[] words = word.toCharArray();
     * for(int i = 0; i < board.length; i++) {
     * for(int j = 0; j < board[0].length; j++) {
     * if (dfs(board, words, i, j, 0)) return true;
     * }
     * }
     * return false;
     * }
     * boolean dfs(char[][] board, char[] word, int i, int j, int k) {
     * if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != word[k]) return false;
     * if (k == word.length - 1) return true;
     * board[i][j] = '\0';
     * boolean res = dfs(board, word, i + 1, j, k + 1) || dfs(board, word, i - 1, j, k + 1) ||
     * dfs(board, word, i, j + 1, k + 1) || dfs(board, word, i , j - 1, k + 1);
     * board[i][j] = word[k];
     * return res;
     * }
     * }
     * 作者：Krahets
     * 链接：https://leetcode.cn/problems/word-search/solutions/2361646/79-dan-ci-sou-suo-hui-su-qing-xi-tu-jie-5yui2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {

        List<String> list = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) list.add(i + "," + j);
            }
        }
        int[][] used = new int[board.length][board[0].length];
        backtracking(board, list, word, 1, used);
        return success;
    }

    public void backtracking(char[][] board, List<String> list, String word, int start, int[][] used) {
        //剪枝
        if (success) return;
        if (list.isEmpty()) return;

        //结果
        if (start == word.length()) {
            success = true;
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            String[] split = list.get(i).split(",");
            int x = split[0].charAt(0) - '0';
            int y = split[1].charAt(0) - '0';
            used[x][y] = 1;
            List<String> data = find(x, y, word.charAt(start), board, used);

            backtracking(board, data, word, start + 1, used);

            used[x][y] = 0;
        }
    }

    private List<String> find(int m, int n, char c, char[][] board, int[][] used) {
        int[] i = {0, 0, -1, 1};
        int[] j = {-1, 1, 0, 0};
        List<String> data = new ArrayList<>();
        for (int k = 0; k < i.length; k++) {
            int x = m + i[k];
            int y = n + j[k];
            if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && board[x][y] == c && used[x][y] == 0) {
                data.add(x + "," + y);
            }
        }
        return data;
    }

    //22.括号生成
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtracking(new StringBuilder(), res, n, n);
        return res;
    }

    //左括号,和右括号的加法数量决定, 遍历所有的情况,进行适当的剪枝
    public void backtracking(StringBuilder string, List<String> res, int left, int right) {

        //剪枝
        if (right > left) {
            return;
        }

        //结果
        if (left == 0 && right == 0) {
            res.add(string.toString());
            return;
        }

        if (left != 0) {
            string.append("(");
            backtracking(string, res, left - 1, right);
            string.deleteCharAt(string.length() - 1);
        }

        if (right != 0) {
            string.append(")");
            backtracking(string, res, left, right - 1);
            string.deleteCharAt(string.length() - 1);
        }
    }

    //3. 组合总和
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtracking(new ArrayList<>(), 0, candidates, res, target, 0);
        return res;
    }

    //start奇怪重复问题  一个数只能取n次, 到下一个不能倒回来取出前面的数字
    public void backtracking(List<Integer> path, int start, int[] candidates, List<List<Integer>> res, int target, int sum) {
        //剪枝
        if (sum > target) return;
        else if (sum == target) { //获取结果
            res.add(new ArrayList<>(path));
        }

        for (int i = start; i < candidates.length; i++) {
            //添加
            path.add(candidates[i]);
            sum += candidates[i];

            backtracking(path, i, candidates, res, target, sum);

            //撤销
            path.remove(path.size() - 1);
            sum -= candidates[i];
        }
    }

    //17. 电话号码的字母组合
    public List<String> letterCombinations(String digits) {
        if ("".equals(digits)) return new ArrayList<>();
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(2, "abc");
        hashMap.put(3, "def");
        hashMap.put(4, "ghi");
        hashMap.put(5, "jkl");
        hashMap.put(6, "mno");
        hashMap.put(7, "pqrs");
        hashMap.put(8, "tuv");
        hashMap.put(9, "wxyz");
        List<String> data = new ArrayList<>();
        for (int i = 0; i < digits.length(); i++) {
            data.add(hashMap.get(digits.charAt(i) - '0'));
        }
        List<String> res = new ArrayList<>();
        backtracking(new StringBuilder(), data, res);
        return res;
    }

    public void backtracking(StringBuilder string, List<String> data, List<String> res) {
        //终止条件
        if (data.size() == string.length()) {
            res.add(string.toString());
            return;
        }

        int start = string.length();
        String str = data.get(start);

        for (int i = 0; i < str.length(); i++) {
            //添加
            string.append(str.charAt(i));

            backtracking(string, data, res);

            //撤销
            string.deleteCharAt(string.length() - 1);
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtracking(new ArrayList<Integer>(), nums, res, 0);
        return res;
    }

    public void backtracking(List<Integer> data, int[] nums, List<List<Integer>> res, int start) {
        //终止条件
        res.add(new ArrayList<>(data));

        for (int i = start; i < nums.length; i++) {
            //添加
            data.add(nums[i]);

            backtracking(data, nums, res, i + 1);

            //撤销
            data.remove(data.size() - 1);
        }
    }

    //全部排列, ...
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        boolean[] used = new boolean[nums.length];
        ArrayList<Integer> list = new ArrayList<>();
        backtracking(list, nums, res, used);
        return res;
    }

    public void backtracking(List<Integer> data, int[] nums, List<List<Integer>> res, boolean[] used) {
        //终止条件
        if (data.size() == nums.length) {
            res.add(new ArrayList<>(data));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                data.add(nums[i]);
                used[i] = true;

                backtracking(data, nums, res, used);

                data.remove(data.size() - 1);
                used[i] = false;
            }


        }
    }
}
