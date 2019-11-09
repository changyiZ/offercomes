package me.changyi.interview.misc

import me.changyi.interview.test.Test

class BigNumberCalculation : Test() {

    fun add(a: String?, b: String?, resultBuilder: StringBuilder? = null) : String? {
        if (a.isNullOrBlank()) {
            return b
        }
        if (b.isNullOrBlank()) {
            return a
        }
        // 方便起见，设定 number1 的长度 >= number2
        val number1: String
        val number2: String
        if (a.length > b.length) {
            number1 = a
            number2 = b
        } else {
            number1 = b
            number2 = a
        }
        // 正式开始计算
        val result = resultBuilder ?: StringBuilder()
        var index1 = number1.length - 1
        var index2 = number2.length - 1
        var takeOver = 0
        var sum: Int
        while (index1 >= 0) {
            sum = number1[index1--].toDigit() + takeOver
            if (index2 >= 0) {
                sum += number2[index2--].toDigit()
            }
            if (sum > 9) {
                sum -= 10
                takeOver = 1
            } else {
                takeOver = 0
            }
            result.append(sum)
        }
        if (takeOver > 0) {
            result.append(1)
        }

        return result.reverse().toString()
    }

    private fun Char.toDigit() : Int {
        if (this in '0'..'9') {
            return this - '0'
        }
        return 0
    }

    private fun testLong(number1: Long, number2: Long) {
        val expected = number1 + number2
        val result = add(number1.toString(), number2.toString())
        val passed = if (result?.toLong() == expected) {
            "Passed"
        } else {
            "Falied"
        }
        println("$number1+$number2=$result, excepted=$expected\n\t$passed")
    }

    // IntMax = 2147483647
    override fun runTest() {
        testLong(1, 1)
        testLong(1, 0)
        testLong(23456789, 0)
        testLong(0, 0)
        testLong(456, 789)
        testLong(2147483647, 2147483647)
        testLong(2147483648, 2147483648)
        testLong(222147483648, 992147483648)
    }
}