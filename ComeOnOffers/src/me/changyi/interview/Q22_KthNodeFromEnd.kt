package me.changyi.interview

import me.changyi.interview.model.LinkedList
import me.changyi.interview.model.ListNode
import me.changyi.interview.test.Test

// 面试题22：链表中倒数第k个结点
// 题目：输入一个链表，输出该链表中倒数第k个结点。为了符合大多数人的习惯，
// 本题从1开始计数，即链表的尾结点是倒数第1个结点。例如一个链表有6个结点，
// 从头结点开始它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个结点是
// 值为4的结点。

class Q22_KthNodeFromEnd : Test() {

    private fun findKthToTail(linkedList: LinkedList<Int>?, k: Int): ListNode<Int>? {
        var tail = linkedList?.head
        var tailK: ListNode<Int>? = null
        if (k > 0) {
            var count = 0
            while (tail != null) {
                count++
                if (count - k == 0) {
                    tailK = linkedList?.head
                }
                tail = tail.next
                if (tail != null) {
                    tailK = tailK?.next
                }
            }
        }
        return tailK
    }

    private fun test(linkedList: LinkedList<Int>?, k: Int, expected: Int?) {
        println(if (findKthToTail(linkedList, k)?.value == expected) {
            "Passed"
        } else {
            "Failed"
        })
    }

    override fun runTest() {
        test(LinkedList(1, 2, 3, 4, 5), 1, 5)
        test(LinkedList(1, 2, 3, 4, 5), 2, 4)
        test(LinkedList(1, 2, 3, 4, 5), 5, 1)
        test(null, 100, null)
        test(LinkedList(1, 2, 3, 4, 5), 6, null)
        test(LinkedList(1, 2, 3, 4, 5), 0, null)
    }
}