package me.changyi.interview.test

import java.math.BigDecimal

abstract class Test {
    abstract fun runTest()

    protected fun test(result: Int?, expected: Int) {
        println(
            if (result == expected) {
                "Passed"
            } else {
                "Failed"
            }
        )
    }

    protected fun test(result: Double?, expected: Double) {
        println(
            if (
                result != null && BigDecimal(result).compareTo(BigDecimal(expected)) == 0
            ) {
                "Passed"
            } else {
                "Failed"
            }
        )
    }
}