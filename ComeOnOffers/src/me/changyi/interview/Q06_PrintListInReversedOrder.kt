package me.changyi.interview

import me.changyi.interview.model.LinkedList
import me.changyi.interview.model.Stack
import me.changyi.interview.test.Test
import java.lang.StringBuilder

// 面试题6：从尾到头打印链表(单向)
// 题目：输入一个链表的头结点，从尾到头反过来打印出每个结点的值。

class Q06_PrintListInReversedOrder : Test {

    private fun printBackward(linkedList: LinkedList<Int>?): String? {
        linkedList ?: return null
        val stack = Stack<Int>()
        var node = linkedList.first
        node ?: return null
        do {
            stack.push(node!!.value)
            node = node.next
        } while (node != null)
        val stringBuilder = StringBuilder()
        var value = stack.pop()
        while (value != null) {
            stringBuilder.append(value)
            value = stack.pop()
        }
        return stringBuilder.toString()
    }

    override fun runTest() {
        val linkedList = LinkedList<Int>()
        linkedList.append(1).append(2).append(3).append(4)
        print(printBackward(linkedList))
    }
}