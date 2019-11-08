package me.changyi.interview

import me.changyi.interview.test.Test
import kotlin.math.pow

// 面试题14：剪绳子
// 题目：给你一根长度为n绳子，请把绳子剪成m段（m、n都是整数，n>1并且m≥1）。
// 每段的绳子的长度记为k[0]、k[1]、……、k[m]。k[0]*k[1]*…*k[m]可能的最大乘
// 积是多少？例如当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此
// 时得到最大的乘积18。

class Q14_CuttingRope : Test() {

    private fun maxProductAfterCutting(length: Int): Int {
        when {
            length < 2 -> return 0
            length == 2 -> return 1
            length == 3 -> return 2
        }

        var timeOf3 = length / 3
        if (length % 3 == 1) {
            timeOf3 -=1
        }
        val timeOf2 = (length - timeOf3 * 3) / 2
        return (3.toDouble().pow(timeOf3.toDouble()) * 2.toDouble().pow(timeOf2.toDouble())).toInt()
    }

    override fun runTest() {
        test(maxProductAfterCutting(1), 0)
        test(maxProductAfterCutting(2), 1)
        test(maxProductAfterCutting(3), 2)
        test(maxProductAfterCutting(4), 4)
        test(maxProductAfterCutting(5), 6)
        test(maxProductAfterCutting(6), 9)
        test(maxProductAfterCutting(10), 36)
        test(maxProductAfterCutting(50), 86093442)
    }
}