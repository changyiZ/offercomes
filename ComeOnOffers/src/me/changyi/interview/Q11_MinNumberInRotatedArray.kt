package me.changyi.interview

import me.changyi.interview.test.Test
import java.util.*

// 面试题11：旋转数组的最小数字
// 题目：把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
// 输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如数组
// {3, 4, 5, 1, 2}为{1, 2, 3, 4, 5}的一个旋转，该数组的最小值为1。

class Q11_MinNumberInRotatedArray : Test() {

    private fun getMinNumber(numbers: IntArray?): Int? {
        numbers ?: return null

        val size = numbers.size
        var index1 = 0
        var index2 = size - 1
        if (size < 2 || numbers[index1] < numbers[index2]) {
            return numbers[0]
        }
        var middle = index2
        while (index1 < index2) {
            if (index2 - index1 == 1) {
                return numbers[index2]
            }
            middle = (index1 + index2) / 2
            if (numbers[index1] == numbers[index2] && numbers[middle] == numbers[index1]) {
                return getMinInOrder(numbers, index1, index2)
            }
            if (numbers[middle] < numbers[index1]) {
                index2 = middle
            } else {
                index1 = middle
            }
        }
        return middle
    }

    private fun getMinInOrder(numbers: IntArray, start: Int, end: Int): Int {
        var min = numbers[start]
        for (index in (start + 1)..end) {
            if (numbers[index] < min) {
                min = numbers[index]
            }
        }
        return min
    }

    private fun test(numbers: IntArray?, min: Int?) {
        val result = getMinNumber(numbers)
        val passed = if (result == min) {
            "Passed"
        } else {
            "Failed"
        }
        println("array=${Arrays.toString(numbers)}, min=$min, result=$result, $passed")
    }

    override fun runTest() {
        /**
         * // 典型输入，单调升序的数组的一个旋转
        int array1[] = { 3, 4, 5, 1, 2 };
        Test(array1, sizeof(array1) / sizeof(int), 1);

        // 有重复数字，并且重复的数字刚好的最小的数字
        int array2[] = { 3, 4, 5, 1, 1, 2 };
        Test(array2, sizeof(array2) / sizeof(int), 1);

        // 有重复数字，但重复的数字不是第一个数字和最后一个数字
        int array3[] = { 3, 4, 5, 1, 2, 2 };
        Test(array3, sizeof(array3) / sizeof(int), 1);

        // 有重复的数字，并且重复的数字刚好是第一个数字和最后一个数字
        int array4[] = { 1, 0, 1, 1, 1 };
        Test(array4, sizeof(array4) / sizeof(int), 0);

        // 单调升序数组，旋转0个元素，也就是单调升序数组本身
        int array5[] = { 1, 2, 3, 4, 5 };
        Test(array5, sizeof(array5) / sizeof(int), 1);

        // 数组中只有一个数字
        int array6[] = { 2 };
        Test(array6, sizeof(array6) / sizeof(int), 2);

        // 输入nullptr
        Test(nullptr, 0, 0);
         */
        test(intArrayOf(3, 4, 5, 1, 2), 1)
        test(intArrayOf(3, 4, 5, 1, 1, 2 ), 1)
        test(intArrayOf(3, 4, 5, 1, 2, 2 ), 1)
        test(intArrayOf(1, 0, 1, 1, 1), 0)
        test(intArrayOf(1, 2, 3, 4, 5), 1)
        test(intArrayOf(2), 2)
        test(null, null)
    }
}