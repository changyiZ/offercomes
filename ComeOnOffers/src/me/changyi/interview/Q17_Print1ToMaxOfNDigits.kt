package me.changyi.interview

import me.changyi.interview.misc.BigNumberCalculation
import me.changyi.interview.test.Test
import java.lang.StringBuilder

// 面试题17：打印1到最大的n位数
// 题目：输入数字n，按顺序打印出从1最大的n位十进制数。比如输入3，则
// 打印出1、2、3一直到最大的3位数即999。

class Q17_Print1ToMaxOfNDigits : Test() {

    private fun printToMax(length: Int) {
        if (length > 0) {
            val bigNumberCalculation = BigNumberCalculation()
            val resultBuilder = StringBuilder()
            var start = "0"
            while (true) {
                start = bigNumberCalculation.add(start, "1", resultBuilder)!!
                resultBuilder.clear()
                if (start.length > length) {
                    break
                }
                println(start)
            }
            println()
        }
    }

    override fun runTest() {
        printToMax(1)
        printToMax(3)
        // printToMax(10)
    }
}