package me.changyi.interview

import me.changyi.interview.test.Test

// 面试题21：调整数组顺序使奇数位于偶数前面
// 题目：输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有
// 奇数位于数组的前半部分，所有偶数位于数组的后半部分。

class Q21_ReorderArray : Test() {

    private fun <T> reorderArray(array: Array<T>, orderFunction: (T) -> Boolean) {
        if (array.size > 1) {
            var start = 0
            var end = array.size - 1
            var temp: T
            while (start < end) {
                while (start < end && orderFunction.invoke(array[start])) {
                    start++
                }
                while (start < end && !orderFunction.invoke(array[end])) {
                    end--
                }
                if (start < end) {
                    temp = array[start]
                    array[start] = array[end]
                    array[end] = temp

                }
            }
        }
    }

    private fun test(array: Array<Int>) {
        reorderArray(array) {
            it and 1 != 0
        }
        println(array.contentToString())
    }

    override fun runTest() {
        test(arrayOf(1, 2, 3, 4, 5, 6, 7))
        test(arrayOf(2, 4, 6, 1, 3, 5, 7))
        test(arrayOf(1, 3, 5, 7, 2, 4, 6))
        test(arrayOf(1))
        test(arrayOf(2))
    }
}