package me.changyi.interview.algorithm

import me.changyi.interview.model.LinkedList
import me.changyi.interview.model.Node
import me.changyi.interview.test.Test

class Sorting : Test() {

    /**
     * 返回分区后， pivot 的 index
     */
    private fun partition(numbers: IntArray, start: Int, end: Int): Int? {
        if (start in 0 until end && end < numbers.size) {
            val pivot = numbers[end]
            var left = start
            var right = end - 1
            if (left < right)
                while (left < right) {
                    while (left < right && numbers[left] < pivot) {
                        left++
                    }
                    while (left < right && numbers[right] >= pivot) {
                        right--
                    }
                    if (left < right) {
                        swap(numbers, left, right)
                    }
                }
            if (pivot < numbers[right]) {
                swap(numbers, right, end)
            }
            return right
        }
        return null
    }

    private fun swap(numbers: IntArray, from: Int, to: Int) {
        numbers[to] += numbers[from]
        numbers[from] = numbers[to] - numbers[from]
        numbers[to] -= numbers[from]
    }

    private fun quickSort(numbers: IntArray, start: Int, end: Int) {
        val index = partition(numbers, start, end)
        if (index != null) {
            quickSort(numbers, start, index - 1)
            quickSort(numbers, index + 1, end)
        }
    }

    fun quickSort(numbers: IntArray?) {
        if (true == numbers?.isNotEmpty()) {
            quickSort(numbers, 0, numbers.size - 1)
        }
    }

    private fun testQuickSort(numbers: IntArray?) {
        quickSort(numbers)
        println("sort=${numbers?.contentToString()}")
    }

    override fun runTest() {
        testQuickSort(intArrayOf(1, 3, 1, 2, 3, 8, 8, 1, 8, 4, 6))
        testQuickSort(intArrayOf(1))
        testQuickSort(intArrayOf(1, 5, 9, 0, 1, 2, 6, 6, 6, 7, 5))
    }
}

private fun merge(list1: LinkedList<Int>, list2: LinkedList<Int>): LinkedList<Int> {
    var node1 = list1.head
    var node2 = list2.head
    // 设定一个临时的 -1 节点
    var node = Node(-1)
    val list = LinkedList(node)
    // Merging
    while (node1 != null && node2 != null) {
        if (node1.value < node2.value) {
            node.next = node1
            node1 = node1.next
        } else {
            node.next = node2
            node2 = node2.next
        }
        node = node.next!!
    }
    if (node1 != null) {
        node.next = node1
    } else if (node2 != null) {
        node.next = node2
    }
    // 移除临时的 -1 节点
    node = list.head!!
    list.head = list.head?.next
    node.next = null
    return list
}

fun LinkedList<Int>.mergeSort(): LinkedList<Int> {
    // Size > 1
    if (head?.next != null) {
        var slow = head!!
        var fast = slow
        // Find the middle one.
        while (fast.next?.next != null) {
            // Two steps one time
            fast = fast.next?.next!!
            // One step one time
            slow = slow.next!!
        }
        val middle = slow.next!!
        // Cut it two.
        slow.next = null
        // Merge
        return merge(LinkedList(head!!).mergeSort(), LinkedList(middle).mergeSort())
    }
    return this
}