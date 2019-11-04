package me.changyi.interview

import me.changyi.interview.model.BinaryTreeNode
import me.changyi.interview.test.Test

// 面试题7：重建二叉树
// 题目：输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输
// 入的前序遍历和中序遍历的结果中都不含重复的数字。例如输入前序遍历序列{1,
// 2, 4, 7, 3, 5, 6, 8}和中序遍历序列{4, 7, 2, 1, 5, 3, 8, 6}，则重建出
// 图2.6所示的二叉树并输出它的头结点。

class Q07_ConstructBinaryTree : Test {

    private fun construct(preOrder: IntArray?, inOder: IntArray?): BinaryTreeNode? {
        if (preOrder == null || inOder == null || preOrder.size != inOder.size || preOrder.isEmpty()) {
            return null
        }

        return try {
            doConstruct(preOrder, 0, preOrder.size - 1, inOder, 0, inOder.size - 1)
        } catch (exception: Exception) {
            null
        }
    }

    private fun doConstruct(
        preOrder: IntArray, preStart: Int, preEnd: Int,
        inOder: IntArray, inStart: Int, inEnd: Int
    ): BinaryTreeNode? {
        if (preEnd >= preStart) {
            val rootValue = preOrder[preStart]
            val root = BinaryTreeNode(rootValue)
            var index = inStart
            var leftSize = 0
            while (index <= inEnd) {
                if (inOder[index++] == rootValue) {
                    break
                }
                leftSize++
            }
            require(leftSize < inEnd - inStart + 1)
            if (leftSize > 0) {
                root.leftTreeNode = doConstruct(
                    preOrder, preStart + 1, preStart + leftSize,
                    inOder, inStart, inStart + leftSize - 1
                )
            }
            if (preEnd - preStart - leftSize > 0) {
                root.rightTreeNode = doConstruct(
                    preOrder, preStart + leftSize + 1, preEnd,
                    inOder, inStart + leftSize + 1, inEnd
                )
            }
            return root
        }
        return null
    }

    private fun test(preOrder: IntArray?, inOder: IntArray?) {
        println(construct(preOrder, inOder))
    }

    override fun runTest() {
        test(intArrayOf(1, 2, 4, 7, 3, 5, 6, 8), intArrayOf(4, 7, 2, 1, 5, 3, 8, 6))
        test(intArrayOf(1, 2, 3, 4, 5), intArrayOf(5, 4, 3, 2, 1))
        test(intArrayOf(1, 2, 3, 4, 5), intArrayOf(1, 2, 3, 4, 5))
        test(intArrayOf(1), intArrayOf(1))
        // bad case
        test(intArrayOf(1, 2, 4, 5, 3, 6, 7), intArrayOf(4, 2, 8, 1, 6, 3, 7))
        test(null, null)
    }
}