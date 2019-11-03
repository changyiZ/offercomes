package me.changyi.interview

import me.changyi.interview.test.Test
import java.util.*

// 面试题4：二维数组中的查找
// 题目：在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按
// 照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个
// 整数，判断数组中是否含有该整数。

class Q04_FindInPartiallySortedMatrix : Test {

    private fun findNumberInMatrix(matrix: Array<IntArray>?, row: Int, column: Int, number: Int): Boolean {
        if (row > 0 && column > 0 && row == matrix?.size) {
            matrix.forEach {
                if (column != it.size) {
                    return false
                }
            }
            // 完成安全校验后再做处理
            var rowIndex = 0
            var columnIndex = column - 1
            var flag: Int
            while (rowIndex < row && columnIndex >= 0) {
                flag = matrix[rowIndex][columnIndex]
                when {
                    number == flag -> return true
                    number > flag -> rowIndex++
                    else -> columnIndex--
                }

            }
        }
        return false
    }

    private fun test(matrix: Array<IntArray>?, row: Int, column: Int, number: Int, result: Boolean) {
        val passed = if (result == findNumberInMatrix(matrix, row, column, number)) {
            "Passed"
        } else {
            "Failed"
        }

        print(
            "matrix=${Arrays.toString(matrix)}, row=$row, column=$column\n" +
                    "\t should find the number:$number, $result\n" +
                    "\t $passed\n"
        )
    }

    override fun runTest() {
        test(
            arrayOf(
                intArrayOf(1, 2, 8, 9),
                intArrayOf(2, 4, 9, 12),
                intArrayOf(4, 7, 10, 13),
                intArrayOf(6, 8, 11, 15)
            ),
            4, 4, 7, true
        )
        test(
            arrayOf(
                intArrayOf(1, 2, 8, 9),
                intArrayOf(2, 4, 9, 12),
                intArrayOf(4, 7, 10, 13),
                intArrayOf(6, 8, 11, 15)
            ),
            4, 4, 5, false
        )
        test(
            arrayOf(
                intArrayOf(1, 2, 8, 9),
                intArrayOf(2, 4, 9, 12),
                intArrayOf(4, 7, 10, 13),
                intArrayOf(6, 8, 11, 15)
            ),
            4, 4, 1, true
        )
        test(
            arrayOf(
                intArrayOf(1, 2, 8, 9),
                intArrayOf(2, 4, 9, 12),
                intArrayOf(4, 7, 10, 13),
                intArrayOf(6, 8, 11, 15)
            ),
            4, 4, 15, true
        )
        test(
            arrayOf(
                intArrayOf(1, 2, 8, 9),
                intArrayOf(2, 4, 9, 12),
                intArrayOf(4, 7, 10, 13),
                intArrayOf(6, 8, 11, 15)
            ),
            4, 4, 0, false
        )
        test(
            arrayOf(
                intArrayOf(1, 2, 8, 9),
                intArrayOf(2, 4, 9, 12),
                intArrayOf(4, 7, 10, 13),
                intArrayOf(6, 8, 11, 15)
            ),
            4, 4, 16, false
        )
        test(null, 0, 0, 16, false)
    }
}