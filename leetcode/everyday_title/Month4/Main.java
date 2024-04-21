package everyday_title.Month4;

import java.nio.charset.IllegalCharsetNameException;
import java.util.*;
import java.util.concurrent.*;

/**
 * @Author: jun
 * @Date: 2024/04/18/16:03
 */
public class Main implements Runnable {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        Main main = new Main();
        threadPoolExecutor.execute(main);
        Future<?> fu = threadPoolExecutor.submit(main);

    }

    //寻找冠军队伍 grid[i][j]  = 1 i 比 j 厉害;
    public int findChampion(int[][] grid) {
        //1. i 比 j 墙
        //1.找到第一行比 0 强的
        for (int i = 0; i < grid.length; i++) {
            boolean success = true;
            for (int j = 0; j < grid[0].length; j++) {
                if (!(grid[i][j] == 1 && success && i != j)) {
                    success = false;
                    break;
                }
            }
            if (success) {
                return i;
            }
        }
        return 0;
    }

    public int[] findOriginalArray(int[] changed) {
        if(changed.length % 2 != 0) return new int[]{};
        //结果集合
        int[] result = new int[changed.length / 2];
        int j = 0;
        Arrays.sort(changed);
        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < changed.length; i++) {
            hashMap.put(changed[i], hashMap.getOrDefault(changed[i], 0) + 1);
        }
        for (int i = 0; i < changed.length; i++) {
            Integer count = hashMap.get(changed[i]);
            if(count > 0){
                //不存在,返回错误
                if(hashMap.get(changed[i] * 2) <= 0){
                    return new int[1];
                }else {
                    result[j++] = changed[i];
                    //减去当前数 和 两倍数
                    hashMap.put(changed[i] * 2, hashMap.get(changed[i] * 2) - 1);
                    hashMap.put(changed[i], hashMap.get(changed[i]) - 1);
                }
            }
        }
        return result;
    }

    @Override
    public void run() {
        System.out.println("hi");
        if(true)
        throw new RuntimeException("错误");
        System.out.println("www");
    }
}
