package me.changyi.interview

import me.changyi.interview.test.Test
import java.lang.Exception
import java.util.*

// 面试题3（一）：找出数组中重复的数字
// 题目：在一个长度为n的数组里的所有数字都在0到n-1的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，
// 也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。例如，如果输入长度为7的数组{2, 3, 1, 0, 2, 5, 3}，
// 那么对应的输出是重复的数字2或者3。

open class Q03_01_DuplicationInArray : Test {

    protected open fun duplicate(inputArray: IntArray?): IntArray? {
        val size = inputArray?.size ?: return null
        if (size > 1) {
            var index = 0
            var number: Int
            val resultSet = mutableSetOf<Int>()
            while (index < size) {
                number = inputArray[index]
                // 超出范围限制会抛异常
                require(number < size)
                if (index == number) {
                    index++
                } else {
                    if (inputArray[number] == number) {
                        // 找到一个重复的
                        resultSet.add(number)
                        index++
                    } else {
                        // 交换数值，并且保证交换目标的 index 和 数值一致
                        inputArray[index] = inputArray[number]
                        inputArray[number] = number
                    }
                }
            }
            if (resultSet.isNotEmpty()) {
                return resultSet.toIntArray()
            }
        }
        return null
    }

    protected open fun judge(result: IntArray?, answer: IntArray?): Boolean {
        if (answer == null && result == null) {
            return true
        }
        if (answer != null && result != null) {
            val answerSet = answer.toSet()
            val resultSet = result.toSet()
            return answerSet.size == result.size && answerSet.containsAll(resultSet)
        }
        return false
    }

    protected fun test(inputArray: IntArray?, answer: IntArray?) {
        // Copy 一份作为原始输入，以防计算过程中对 input 做更改
        val inputCopy = inputArray?.clone()
        val result = try {
            duplicate(inputArray)
        } catch (exception: Exception) {
            null
        }
        val judge = if (judge(result, answer)) {
            "Passed"
        } else {
            "Failed"
        }
        print(
            "input=${Arrays.toString(inputCopy)}, " +
                    "intermediate=${Arrays.toString(inputArray)}, " +
                    "result=${Arrays.toString(result)}, " +
                    "answer=${Arrays.toString(answer)}" +
                    " |---> $judge\n"
        )
    }

    override fun runTest() {
        test(intArrayOf(2, 1, 3, 1, 4), intArrayOf(1))
        test(intArrayOf(2, 4, 2, 1, 4), intArrayOf(2, 4))
        test(intArrayOf(2, 1, 3, 0, 4), null)
        test(intArrayOf(2, 1, 3, 5, 4), null)
        test(null, null)
    }

}