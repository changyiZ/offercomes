package me.changyi.interview

import me.changyi.interview.model.BinaryTreeNode
import me.changyi.interview.test.Test

// 面试题8：二叉树的下一个结点
// 题目：给定一棵二叉树和其中的一个结点，如何找出中序遍历顺序的下一个结点？
// 树中的结点除了有两个分别指向左右子结点的指针以外，还有一个指向父结点的指针。

class Q08_NextNodeInBinaryTrees : Test() {

    private fun getNextNodeInOder(node: BinaryTreeNode?) : BinaryTreeNode? {
        var next = node?.rightTreeNode
        if (next != null) {
            while (next?.leftTreeNode != null) {
                next = next.leftTreeNode
            }
        } else if (node?.parentTreeNode != null) {
            var child = node
            next = child.parentTreeNode
            while (next != null && next.leftTreeNode != child) {
                child = next
                next = next.parentTreeNode
            }
        }
        return next
    }

    private fun test(node: BinaryTreeNode?, next: BinaryTreeNode?) {
        val nextNode = getNextNodeInOder(node)
        val passed = if (nextNode == next && nextNode?.value == next?.value) {
            "Passed"
        } else {
            "Failed"
        }
        print(
            "tree=${node?.treeRoot.toString()}\n" +
                "node=${node?.value}, next=${nextNode?.value}\n" +
                "\t-->$passed\n"
        )
    }

    //            8
    //        6      10
    //       5 7    9  11
    private fun test1() {
        val node8 = BinaryTreeNode(8)
        val node6 = BinaryTreeNode(6)
        val node10 = BinaryTreeNode(10)
        val node5 = BinaryTreeNode(5)
        val node7 = BinaryTreeNode(7)
        val node9 = BinaryTreeNode(9)
        val node11 = BinaryTreeNode(11)
        node8.connectNodes(node6, node10)
        node6.connectNodes(node5, node7)
        node10.connectNodes(node9, node11)
        test(node8, node9)
        test(node6, node7)
        test(node10, node11)
        test(node5, node6)
        test(node7, node8)
        test(node9, node10)
        test(node11, null)
    }

    //            5
    //          4
    //        3
    //      2
    private fun test2() {
        val node5 = BinaryTreeNode(5)
        val node4 = BinaryTreeNode(4)
        val node3 = BinaryTreeNode(3)
        val node2 = BinaryTreeNode(2)
        node5.connectNodes(node4, null)
        node4.connectNodes(node3, null)
        node3.connectNodes(node2, null)
        test(node5, null)
        test(node4, node5)
        test(node3, node4)
        test(node2, node3)
    }

    //        2
    //         3
    //          4
    //           5
    private fun test3() {
        val node5 = BinaryTreeNode(5)
        val node4 = BinaryTreeNode(4)
        val node3 = BinaryTreeNode(3)
        val node2 = BinaryTreeNode(2)
        node2.connectNodes(null, node3)
        node3.connectNodes(null, node4)
        node4.connectNodes(null, node5)
        test(node5, null)
        test(node4, node5)
        test(node3, node4)
        test(node2, node3)
    }

    override fun runTest() {
        test1()
        test2()
        test3()
        test(BinaryTreeNode(5), null)
    }
}