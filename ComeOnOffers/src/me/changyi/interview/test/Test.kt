package me.changyi.interview.test

abstract class Test {
    abstract fun runTest()

    protected fun test(result: Int?, answer: Int) {
        println(
            if (result == answer) {
                "Passed"
            } else {
                "Failed"
            }
        )
    }
}