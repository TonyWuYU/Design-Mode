package com.excise;

/**
 * Created by yuwu on 2017/3/23.
 * 二分搜索算法
 * 非递归和递归法
 */
public class BiSearch {
    public static void main(String[] args) {
        int para[] = {1, 3, 5, 7, 9, 11, 13, 14, 15, 17, 18, 19, 27, 29, 32, 34, 36, 56, 78, 98, 123};
        System.out.println("位置是:     " + biSearch(para, 11) + "     " + step_count);
        System.out.println();
        System.out.println(biDSearch(para, 11, 0, para.length - 1));
    }

    public static int step_count = 0;

    /**
     * 非递归二分法
     *
     * @param array
     * @param a
     * @return
     */
    public static int biSearch(int[] array, int a) {
        int lo = 0;
        int high = array.length - 1;
        int mid;
        while (lo <= high) {
            step_count++;
            mid = (lo + high) / 2;
            if (array[mid] == a) {
                return mid;
            } else if (array[mid] < a) {
                lo = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 递归二分
     *
     * @param array
     * @param a
     * @param lo
     * @param hi
     * @return
     */
    public static int biDSearch(int[] array, int a, int lo, int hi) {
        if (lo < hi) {
            int mid = (lo + hi) / 2;
            if (a == array[mid]) {
                return mid;
            } else if (a > array[mid]) {
                return biDSearch(array, a, mid + 1, hi);
            } else {
                return biDSearch(array, a, lo, mid - 1);
            }
        }
        return -1;
    }


}

