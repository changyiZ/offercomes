package me.changyi.interview

import me.changyi.interview.test.Test

class Q10_Fibonacci : Test() {

    private val fStarts = intArrayOf(0, 1)

    private fun fibonacci(n: Int) : Int {
        if (n < 2) {
            return fStarts[n]
        }
        var f1 = fStarts[0]
        var f2 = fStarts[1]
        for (index  in 2..n) {
            f2 += f1
            f1 = f2 - f1
        }
        return f2
    }

    override fun runTest() {
        /**
         * Test(0, 0);
        Test(1, 1);
        Test(2, 1);
        Test(3, 2);
        Test(4, 3);
        Test(5, 5);
        Test(6, 8);
        Test(7, 13);
        Test(8, 21);
        Test(9, 34);
        Test(10, 55);

        Test(40, 102334155);
         */
        test(fibonacci(0), 0)
        test(fibonacci(1), 1)
        test(fibonacci(2), 1)
        test(fibonacci(3), 2)
        test(fibonacci(4), 3)
        test(fibonacci(5), 5)
        test(fibonacci(6), 8)
        test(fibonacci(7), 13)
        test(fibonacci(8), 21)
        test(fibonacci(9), 34)
        test(fibonacci(10), 55)
        test(fibonacci(40), 102334155)
    }
}