package me.changyi.interview

import me.changyi.interview.algorithm.mergeSort
import me.changyi.interview.model.LinkedList
import me.changyi.interview.model.Node
import me.changyi.interview.test.Test

// 面试题18（二）：删除链表中重复的结点
// 题目：在一个排序的链表中，如何删除重复的结点？例如，在图3.4（a）中重复
// 结点被删除之后，链表如图3.4（b）所示。

class Q18_02_DeleteDuplicatedNode : Test() {

    private fun deleteDuplicatesInList(list: LinkedList<Int>) {
        list.head = list.mergeSort().head
        // Size > 2
        if (list.head?.next != null) {
            var current = list.head
            // 赋值第一个值，作为 -1 节点
            var prev = Node(current!!.value).apply {
                next = current
            }.also {
                list.head = it
            }
            var next = current
            while (next != null) {
                if (current!!.value != next?.next?.value) {
                    if (current != next) {
                        // 有重复的
                        current = next?.next
                        prev.next = current
                        next?.next = null
                        next = current
                    } else {
                        prev = current
                        current = current.next
                        next = next!!.next
                    }
                } else {
                    next = next!!.next
                }
            }
            prev = list.head!!
            list.head = list.head?.next
            prev.next = null
        }
    }

    private fun test(list: LinkedList<Int>) {
        println("****************")
        println("list=$list")
        deleteDuplicatesInList(list)
        println("duplicatesRemoved$list")
    }

    override fun runTest() {
        test(LinkedList(1, 2, 3, 3, 4, 4, 5))
        test(LinkedList(1, 2, 3, 4, 5, 6, 7))
        test(LinkedList(2, 1, 1, 1, 1, 1, 1))
        test(LinkedList(1, 1, 1, 1, 1, 1, 1))
        test(LinkedList(1, 2))
        test(LinkedList(1))
    }
}