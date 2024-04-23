package hot100.栈;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @Author: jun
 * @Date: 2024/04/21/16:01
 */
public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        int[] ints = main.dailyTemperatures(new int[]{73,74,75,71,69,72,76,73});
        System.out.println(Arrays.toString(ints));

    }


    //739，每日温度
    public int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        //维护，大小数，从最后面开始维护
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = temperatures.length - 1; i >= 0; i--) {
            int curr = temperatures[i];
            boolean success = true;
            //处理为空的情况
            if(stack.isEmpty()) {
                stack.addLast(i);
                result[i] = 0;
                continue;
            }
            while (!stack.isEmpty() && success){
                int index = stack.peekLast();
                int num = temperatures[stack.peekLast()];
                //当前值大于
                if(curr >= num){
                    stack.removeLast();
                }else { // 存在大于你的值
                    stack.add(i);
                    result[i] = index - i;
                    success = false;
                }
            }
            //说明当前数为最大值
            if(success){
                result[i] = 0;
                stack.addLast(i);
            }

        }
        return result;
    }

    //739,字符串解码
    public String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        LinkedList<Integer> stack_multi = new LinkedList<>();  //维护数字
        LinkedList<String> stack_res = new LinkedList<>();     //维护字符集
        for(Character c : s.toCharArray()) {
            if (c == '['){
                stack_multi.addLast(multi);
                multi = 0;
                stack_res.addLast(res.toString());
                res = new StringBuilder();
            }else if(c == ']'){
                //处理总结逻辑 存储方式 3【aw2[sw]aa]
                // list_res   " "      aw       aw_remove
                // list_multi 3        2        2_ remove
                // res    " " null  aw null sw  awswsw    answswaa
                // multi  3   null  2  null 0   null      null
                StringBuilder stringBuilder = new StringBuilder();
                Integer count = stack_multi.removeLast();
                String start = stack_res.removeLast();
                stringBuilder.append(start);
                for (int i = 0; i < count; i++) {
                    stringBuilder.append(res.toString());
                }
                res = stringBuilder;
            }else if(c >= '0' && c <= '9'){
                multi = multi * 10 + (c - '0');
            }else {
                res.append(c);
            }
        }
        return res.toString();
    }


}
