package me.changyi.interview

// 面试题3（二）：不修改数组找出重复的数字
// 题目：在一个长度为n+1的数组里的所有数字都在1到n的范围内，所以数组中至
// 少有一个数字是重复的。请找出数组中任意一个重复的数字，但不能修改输入的
// 数组。例如，如果输入长度为8的数组{2, 3, 5, 4, 3, 2, 6, 7}，那么对应的
// 输出是重复的数字2或者3。

class Question0302DuplicationInArrayNoEdit : Question0301DuplicationInArray() {

    override fun duplicate(inputArray: IntArray?): IntArray? {
        val size = inputArray?.size ?: return null
        if (size > 1) {
            var start = 1
            var end = size - 1
            var middle: Int
            var result: Pair<Int?, Boolean>
            while (start <= end) {
                middle = (start + end) / 2
                result = isDuplicateInThisRange(inputArray, size, start, middle)
                when {
                    result.first != null -> return intArrayOf(result.first!!)
                    result.second -> end = middle
                    else -> start = middle + 1
                }
            }
        }
        return null
    }

    /**
     * 返回是否重复数字出现在该区间 [min, max]
     * 如果首位重叠，则直接将该重复数字返回，
     * 否则通过计算返回该数字区间是否在数组中重复
     */
    private fun isDuplicateInThisRange(inputArray: IntArray, size: Int, min: Int, max: Int): Pair<Int?, Boolean> {
        require(min <= max)
        var count = 0
        inputArray.forEach {
            require(it > 0)
            require(it < size)
            if (it in min..max) {
                count++
            }
        }
        val duplicated = count > max - min + 1
        val number = if (duplicated && min == max) {
            min
        } else {
            null
        }
        return number to duplicated
    }

    override fun judge(result: IntArray?, answer: IntArray?): Boolean {
        if (answer == null && result == null) {
            return true
        }
        if (answer != null && result != null) {
            val answerSet = answer.toSet()
            val resultSet = result.toSet()
            return answerSet.size >= result.size && answerSet.containsAll(resultSet)
        }
        return false
    }

    override fun runTest() {
        test(intArrayOf(2, 3, 5, 4, 3, 2, 6, 7), intArrayOf(2, 3))
        test(intArrayOf(3, 2, 1, 4, 4, 5, 6, 7), intArrayOf(4))
        test(intArrayOf(1, 2, 3, 4, 5, 6, 7, 1, 8), intArrayOf(1))
        test(intArrayOf(1, 7, 3, 4, 5, 6, 8, 2, 8), intArrayOf(8))
        test(intArrayOf(1, 1), intArrayOf(1))
        test(intArrayOf(1, 2, 6, 4, 5, 3), null)
    }
}