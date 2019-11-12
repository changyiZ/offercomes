package me.changyi.interview

import me.changyi.interview.model.LinkedList
import me.changyi.interview.model.ListNode
import me.changyi.interview.test.Test

// 面试题18（一）：在O(1)时间删除链表结点
// 题目：给定单向链表的头指针和一个结点指针，定义一个函数在O(1)时间删除该
// 结点。
// 仅针对单向链表

class Q18_01_DeleteNodeInList : Test() {

    private fun deleteNode(list: LinkedList<Int>?, node: ListNode<Int>?) {
        if (list?.head == null || node == null) {
            return
        }
        var next: ListNode<Int>?
        if (node.next != null) {
            // 删除的不是最后一个
            next = node.next!!
            // Copy the next node.
            node.value = next.value
            node.next = next.next
            // Then remove the next node.
            next.next = null
        } else if (list.head == node) {
            // 如果是第一个
            list.head = node.next
            node.next = null
        } else {
            // 删除的是最后一个，需要从头开始遍历
            next = list.head
            while (next != null) {
                if (next.next == node) {
                    next.next = null
                    break
                }
                next = next.next
            }
            node.next = null
        }
    }

    private fun test(list: LinkedList<Int>?, delete: ListNode<Int>?) {
        println("************")
        println("${list.toString()}, delete=${delete?.value}")
        deleteNode(list, delete)
        println(list.toString())
        println("************")
    }

    private fun test1() {
        val list = LinkedList(1, 2, 3, 4, 5)
        test(list, list.nodeAtIndex(2))
    }

    private fun test2() {
        val list = LinkedList(1, 2, 3, 4, 5)
        test(list, list.nodeAtIndex(list.size - 1))
    }

    private fun test3() {
        val list = LinkedList(1, 2, 3, 4, 5)
        test(list, list.nodeAtIndex(0))
    }

    private fun test4() {
        val list = LinkedList(1)
        test(list, list.nodeAtIndex(0))
    }

    override fun runTest() {
        test1()
        test2()
        test3()
        test4()
        test(null, null)
    }
}