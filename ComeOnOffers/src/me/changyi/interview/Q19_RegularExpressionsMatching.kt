package me.changyi.interview

import me.changyi.interview.test.Test

// 面试题19：正则表达式匹配
// 题目：请实现一个函数用来匹配包含'.'和'*'的正则表达式。模式中的字符'.'
// 表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。在本题
// 中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"
// 和"ab*ac*a"匹配，但与"aa.a"及"ab*a"均不匹配

class Q19_RegularExpressionsMatching : Test() {

    private fun matchRegExp(string: String?, pattern: String?): Boolean {
        return if (string.isNullOrEmpty() && pattern.isNullOrEmpty()) {
            true
        } else if (!pattern.isNullOrEmpty()) {
            checkIfMatched(string ?: "", 0, pattern.toCharArray(), 0)
        } else {
            false
        }
    }

    private fun checkIfMatched(string: String, i: Int, pattern: CharArray, j: Int): Boolean {
        // 同时达到末尾
        if (i == string.length && j == pattern.size) {
            return true
        } else if (j < pattern.size) {
            // 后面一位是 *
            if (j + 1 < pattern.size && pattern[j + 1] == '*') {
                return if (i < string.length) {
                    if (string[i] == pattern[j] || pattern[j] == '.') {
                        // * 号组合匹配上了
                        pattern[j] = string[i]
                        // 继续用 * 号匹配
                        checkIfMatched(string, i + 1, pattern, j) ||
                                // 也可以直接跳过 * 号匹配
                                checkIfMatched(string, i, pattern, j + 2) ||
                                // 也可以继续下一个匹配校验
                                checkIfMatched(string, i + 1, pattern, j + 2)
                    } else {
                        // * 号组合没有匹配上，跳过
                        checkIfMatched(string, i, pattern, j + 2)
                    }
                } else {
                    (i == string.length) && ((j + 2) == pattern.size)
                }
            } else if (i < string.length) {
                if (pattern[j] == '.' || pattern[j] == string[i]) {
                    return checkIfMatched(string, i + 1, pattern, j + 1)
                }
            }
        }
        return false
    }

    private fun test(string: String?, pattern: String?, expected: Boolean) {
        val passed = if (expected == matchRegExp(string, pattern)) {
            "Passed"
        } else {
            "Failed"
        }
        println("$string match $pattern: $expected, $passed")
    }

    override fun runTest() {
        test("", "", true)
        test("", ".*", true)
        test("", ".", false)
        test("", "c*", true)
        test("a", ".*", true)
        test("a", "a.", false)
        test("a", "", false)
        test("a", ".", true)
        test("a", "ab*", true)
        test("a", "ab*a", false)
        test("aa", "aa", true)
        test("aa", "a*", true)
        test("aa", ".*", true)
        test("aa", ".", false)
        test("ab", ".*", false)
        test("aaa", "aa*", true)
        test("aaa", "aa.a", false)
        test("aaa", "a.a", true)
        test("aaa", ".a", false)
        test("aaa", "a*a", true)
        test("aaa", "ab*a", false)
        test("aaa", "ab*ac*a", true)
        test("aaa", "ab*a*c*a", true)
        test("aaa", ".*", true)
        test("aab", "c*a*b", true)
        test("aaca", "ab*a*c*a", true)
        test("aaba", "ab*a*c*a", false)
        test("bbbba", ".*a*a", true)
        test("bcbbabab", ".*a*a", false)
    }
}