package me.changyi.interview

import me.changyi.interview.test.Test
import kotlin.math.abs

// 面试题16：数值的整数次方
// 题目：实现函数double Power(double base, int exponent)，求base的exponent
// 次方。不得使用库函数，同时不需要考虑大数问题。

class Q16_Power : Test() {

    private fun power(base: Double, exponent: Int): Double {
        if (base == 0.0) {
            return 0.0
        }
        var result = powerWithUnsignedExponent(base, abs(exponent))
        if (exponent < 0) {
            result = 1 / result
        }
        return result
    }

    private fun powerWithUnsignedExponent(base: Double, exponent: Int): Double {
        if (exponent == 0) {
            return 1.0
        } else if (exponent == 1) {
            return base
        }
        // 右移 == 除以 2
        var result = powerWithUnsignedExponent(base, exponent.shr(1))
        result *= result
        // 高效判断是否为奇数
        if (exponent and 0x01 != 0) {
            result *= base
        }
        return result
    }

    override fun runTest() {
        test(power(2.0, 3), 8.0)
        test(power(-2.0, 3), -8.0)
        test(power(2.0, -3), 0.125)
        test(power(2.0, 0), 1.0)
        test(power(0.0, 0), 0.0)
        test(power(0.0, 4), 0.0)
        test(power(0.0, -4), 0.0)
    }
}