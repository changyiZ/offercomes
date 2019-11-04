package me.changyi.interview.algorithm

import me.changyi.interview.test.Test

class Searching : Test() {

    private fun binarySearch(numbers: IntArray, target: Int): Int {
        val length = numbers.size
        if (length > 0) {
            // 先排序
            Sorting().quickSort(numbers)
            // 再搜索
            var left = 0
            var right = length - 1
            var pivot: Int
            var middle: Int
            while (left <= right) {
                pivot = (left + right) / 2
                middle = numbers[pivot]
                when {
                    target == middle -> return pivot
                    target > middle -> left = pivot + 1
                    else -> right = pivot - 1
                }
            }
        }
        return -1
    }

    private fun test(numbers: IntArray, target: Int) {
        val index = binarySearch(numbers, target)
        println("Find $target in ${numbers.contentToString()}: $index")
    }

    override fun runTest() {
        test(intArrayOf(1, 5, 9, 0, 1, 2, 6, 6, 6, 7, 5), 9)
        test(intArrayOf(1, 3, 1, 2, 3, 8, 8, 1, 8, 4, 6), 8)
        test(intArrayOf(1), 9)
    }
}