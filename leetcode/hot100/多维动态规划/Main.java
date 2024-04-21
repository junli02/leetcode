package hot100.多维动态规划;

/**
 * @Author: jun
 * @Date: 2024/04/16/16:25
 */
public class Main {

    private static final Main excute = new Main();
    public static void main(String[] args) {
        String s = excute.longestPalindrome("bbcbbaadd");
        System.out.println(s);
        int i = excute.longestCommonSubsequence("vozsh", "psnw");
        System.out.println(i);
    }

    /**
     * 最长公共子序列, 两个序列的子序列
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            char c1 = text1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = text2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    public int findCharFromIndex(String s1, char target, int index){
        for (int i = 0; i < s1.length(); i++) {
            if(s1.charAt(i) == target && i > index) return i;
        }
        return -1;
    }


    /**
     * 5.最长回文子串
     * f[i,j] = f[i + 1, j - 1] && s[i] = s[j]
     */
    public String longestPalindrome(String s) {
        //if(s.length() == 1) return s;
        int[][] dp = new int[s.length()][s.length()];
        //初始化dp数组
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = 1;
        }
        //记录结果
        int maxLen = 1;
        int start = 1;
        //从长度开始遍历
        for (int len = 2; len <= s.length(); len++) {
            //从起始点开始遍历所有情况
            for (int i = 0; i < s.length(); i++) {
                int j = i + len - 1;
                if(j >= s.length()) break;

                //状态转移方程 f[i,j] = f[i + 1, j - 1] && s[i] = s[j]
                if(s.charAt(i) == s.charAt(j)){
                    //解决长度只为2的情况 即f[1,2] 无法使用状态转移方程
                    if(i + 1 == j){
                        dp[i][j] = 1;
                    }else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }

                }
                //记录结果
                if(dp[i][j] == 1 && len > maxLen){
                    maxLen = len;
                    start = i;
                }
            }
        }
        return s.substring(start, start + maxLen);
    }

}
