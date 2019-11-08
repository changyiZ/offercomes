package me.changyi.interview.test

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
}