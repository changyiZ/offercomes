package me.changyi.interview

import me.changyi.interview.model.BinaryTreeNode
import me.changyi.interview.test.Test

// 面试题55（一）：二叉树的深度
// 题目：输入一棵二叉树的根结点，求该树的深度。从根结点到叶结点依次经过的
// 结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。

class Q55_01_TreeDepth : Test() {

    private fun testTree(tree: BinaryTreeNode?, expectedHeight: Int) {
        val height = tree?.height ?: 0
        val depth = tree?.depth ?: 0
        val passed = if (expectedHeight == height && expectedHeight == depth) {
            "Passed"
        } else {
            "Failed"
        }
        println("${tree.toString()}, height=$expectedHeight, $passed")
    }

    //            1
    //         /      \
    //        2        3
    //       /\         \
    //      4  5         6
    //        /
    //       7
    private fun test1() {
        val node1 = BinaryTreeNode(1)
        val node2 = BinaryTreeNode(2)
        val node3 = BinaryTreeNode(3)
        val node4 = BinaryTreeNode(4)
        val node5 = BinaryTreeNode(5)
        val node6 = BinaryTreeNode(6)
        val node7 = BinaryTreeNode(7)
        node1.connectNodes(node2, node3)
        node2.connectNodes(node4, node5)
        node3.connectNodes(null, node6)
        node5.connectNodes(node7, null)
        testTree(node1, 4)
    }

    //               1
    //              /
    //             2
    //            /
    //           3
    //          /
    //         4
    //        /
    //       5
    private fun test2() {
        val node1 = BinaryTreeNode(1)
        val node2 = BinaryTreeNode(2)
        val node3 = BinaryTreeNode(3)
        val node4 = BinaryTreeNode(4)
        val node5 = BinaryTreeNode(5)
        node1.connectNodes(node2, null)
        node2.connectNodes(node3, null)
        node3.connectNodes(node4, null)
        node4.connectNodes(node5, null)
        testTree(node1, 5)
    }

    // 1
    //  \
    //   2
    //    \
    //     3
    //      \
    //       4
    //        \
    //         5
    private fun test3() {
        val node1 = BinaryTreeNode(1)
        val node2 = BinaryTreeNode(2)
        val node3 = BinaryTreeNode(3)
        val node4 = BinaryTreeNode(4)
        val node5 = BinaryTreeNode(5)
        node1.connectNodes(null, node2)
        node2.connectNodes(null, node3)
        node3.connectNodes(null, node4)
        node4.connectNodes(null, node5)
        testTree(node1, 5)
    }

    private fun test4() {
        testTree(BinaryTreeNode(1), 1)
    }

    override fun runTest() {
        test1()
        test2()
        test3()
        test4()
        testTree(null, 0)
    }
}