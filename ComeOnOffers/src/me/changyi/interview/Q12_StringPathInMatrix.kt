package me.changyi.interview

import me.changyi.interview.test.Test

// 面试题12：矩阵中的路径
// 题目：请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有
// 字符的路径。路径可以从矩阵中任意一格开始，每一步可以在矩阵中向左、右、
// 上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入
// 该格子。例如在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字
// 母用下划线标出）。但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个
// 字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。
// A B T G
// C F C S
// J D E H

class Q12_StringPathInMatrix : Test() {

    private fun findStringPath(matrix: Array<CharArray>?, string: String?): List<Pair<Int, Int>>? {
        if (matrix.isNullOrEmpty() || string.isNullOrEmpty()) {
            return null
        }

        val rowSize = matrix.size
        val columnSize = matrix[0].size
        val visitedMatrix = Array(rowSize) {
            BooleanArray(columnSize)
        }
        val path = mutableListOf<Pair<Int, Int>>()

        for (row in 0 until rowSize) {
            for (column in 0 until columnSize) {
                if (
                    doFindPath(
                        string,
                        matrix, visitedMatrix,
                        row, column, rowSize, columnSize,
                        path
                    )
                ) {
                    return path
                }
            }
        }
        return path
    }

    private fun doFindPath(
        string: String,
        matrix: Array<CharArray>, visitedMatrix: Array<BooleanArray>,
        row: Int, column: Int, rowSize: Int, columnSize: Int,
        path: MutableList<Pair<Int, Int>>
    ): Boolean {
        val index = path.size
        if (
            row in 0 until rowSize && column in 0 until columnSize
            && !visitedMatrix[row][column] && string[index] == matrix[row][column]
        ) {
            path.add(row to column)
            visitedMatrix[row][column] = true
            if (path.size == string.length ||
                doFindPath(string, matrix, visitedMatrix, row, column - 1, rowSize, columnSize, path) ||
                doFindPath(string, matrix, visitedMatrix, row - 1, column, rowSize, columnSize, path) ||
                doFindPath(string, matrix, visitedMatrix, row, column + 1, rowSize, columnSize, path) ||
                doFindPath(string, matrix, visitedMatrix, row + 1, column, rowSize, columnSize, path)
            ) {
                return true
            }
            path.removeAt(path.size - 1)
            visitedMatrix[row][column] = false
        }
        return false
    }

    private fun stringToMatrix(string: String?, row: Int, column: Int) : Array<CharArray>? {
        if (row * column != string?.length) {
            return null
        }
        val matrix = mutableListOf<CharArray>()
        var charArray: CharArray? = null
        var offset: Int
        string.forEachIndexed { index, c ->
            offset = index % column
            if (offset == 0) {
                charArray = CharArray(column).also {
                    matrix.add(it)
                }
            }
            charArray?.set(offset, c)
        }
        return matrix.toTypedArray()
    }

    private fun test(matrixString: String?, row: Int, column: Int, string: String?, canBeFound: Boolean) {
        val matrix = stringToMatrix(matrixString, row, column)
        val path = findStringPath(matrix, string)
        val result = if (canBeFound == !path.isNullOrEmpty()) {
            "Passed"
        } else {
            "Failed"
        }
        println("Matrix:")
        matrix?.forEach {
            println(it.contentToString())
        }
        println("string=$string should be found: $canBeFound, path=$path, $result")
    }

    override fun runTest() {
        test("ABTGCFCSJDEH", 3, 4, "BFCE", true)
        test("ABCESFCSADEE", 3, 4, "SEE", true)
        test("ABTGCFCSJDEH", 3, 4, "ABFB", false)
        test("ABCEHJIGSFCSLOPQADEEMNOEADIDEJFMVCEIFGGS", 5, 8, "SLHECCEIDEJFGGFIE", true)
        test("ABCEHJIGSFCSLOPQADEEMNOEADIDEJFMVCEIFGGS", 5, 8, "SGGFIECVAASABCEHJIGQEM", true)
        test("ABCEHJIGSFCSLOPQADEEMNOEADIDEJFMVCEIFGGS", 5, 8, "SGGFIECVAASABCEEJIGOEM", false)
        test("AAAAAAAAAAAA", 3, 4, "AAAAAAAAAAAA", true)
    }
}