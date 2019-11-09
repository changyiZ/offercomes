package me.changyi.interview

import me.changyi.interview.test.Test

// 面试题15：二进制中1的个数
// 题目：请实现一个函数，输入一个整数，输出该数二进制表示中1的个数。例如
// 把9表示成二进制是1001，有2位是1。因此如果输入9，该函数输出2。

class Q15_NumberOf1InBinary : Test() {

    private fun count1(number: Long): Int {
        var count = 0
        var n = number
        while (n > 0) {
            n = n and (n - 1)
            count++
        }
        return count
    }

    override fun runTest() {
        test(count1(0), 0)
        test(count1(1), 1)
        test(count1(10), 2)
        test(count1(0x7FFFFFFF), 31)
        test(count1(0xFFFFFFFF), 32)
        test(count1(0x80000000), 1)
    }
}