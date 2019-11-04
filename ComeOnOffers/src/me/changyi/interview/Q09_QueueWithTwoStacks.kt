package me.changyi.interview

import me.changyi.interview.model.Stack
import me.changyi.interview.test.Test

// 面试题9：用两个栈实现队列
// 题目：用两个栈实现一个队列。队列的声明如下，请实现它的两个函数appendTail
// 和deleteHead，分别完成在队列尾部插入结点和在队列头部删除结点的功能。

class Q09_QueueWithTwoStacks : Test() {

    override fun runTest() {
        val queue = Queue<Int>()
        queue.add(1).add(2).add(3)
        test(queue.poll(), 1)
        test(queue.poll(), 2)
        queue.add(4)
        test(queue.poll(), 3)
        queue.add(5)
        test(queue.poll(), 4)
        test(queue.poll(), 5)
    }
}

class Queue<T> {
    private val stack1 = Stack<T>()
    private val stack2 = Stack<T>()

    fun add(item: T): Queue<T> {
        stack1.push(item)
        return this
    }

    fun poll(): T? {
        if (stack2.isEmpty) {
            do {
                stack1.pop()?.let {
                    stack2.push(it)
                }
            } while (!stack1.isEmpty)
        }
        return stack2.pop()
    }
}