package me.changyi.interview

import me.changyi.interview.test.Test

// 面试题5：替换空格
// 题目：请实现一个函数，把字符串中的每个空格替换成"%20"。例如输入“We are happy.”，
// 则输出“We%20are%20happy.”。

class Q05_ReplaceSpaces : Test {

    private fun replaceSpaces(inputString: String?): String? {
        inputString ?: return inputString
        val inputArray = inputString.toCharArray()
        var spacesCount = 0
        inputArray.forEach {
            if (' ' == it) {
                spacesCount++
            }
        }
        return if (spacesCount > 0) {
            val charArray = CharArray(inputString.length + spacesCount * 2)
            inputArray.copyInto(charArray)
            // 原始字符串的末尾坐标
            var end = inputString.length - 1
            // 新字符串的末尾坐标
            var right = charArray.size - 1
            while (end != right) {
                if (charArray[end] == ' ') {
                    charArray[right--] = '0'
                    charArray[right--] = '2'
                    charArray[right--] = '%'
                } else {
                    charArray[right--] = charArray[end]
                }
                end--
            }
            String(charArray)
        } else {
            inputString
        }
    }

    private fun test(inputString: String?, replaced: String?) {
        val output = replaceSpaces(inputString)
        val result = if (replaced == null && output == null ||
            output?.equals(replaced) == true
        ) {
            "Passed"
        } else {
            "Failed"
        }
        print("input=$inputString, replaced=$replaced, output=$output, $result\n")
    }

    override fun runTest() {
        test("hello world", "hello%20world")
        test(" helloworld", "%20helloworld")
        test("helloworld ", "helloworld%20")
        test("hello  world", "hello%20%20world")
        test(null, null)
        test("", "")
        test(" ", "%20")
        test("helloworld", "helloworld")
    }
}