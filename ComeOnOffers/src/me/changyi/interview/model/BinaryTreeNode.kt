package me.changyi.interview.model

import java.lang.StringBuilder


class BinaryTreeNode(val value: Int) {

    var leftTreeNode: BinaryTreeNode? = null
    var rightTreeNode: BinaryTreeNode? = null

    val depth: Int
        get() {
            val leftDepth = leftTreeNode?.depth ?: 0
            val rightDepth = rightTreeNode?.depth ?: 0
            return 1 + if (leftDepth > rightDepth) {
                leftDepth
            } else {
                rightDepth
            }
        }

    /**
     * Print binaryTree in preOrder.
     */
    override fun toString(): String {
        return StringBuilder().apply {
            append("{$value")
            if (leftTreeNode != null)
                append(", ${leftTreeNode.toString()}")
            if (rightTreeNode != null)
                append(", ${rightTreeNode.toString()}")
            append("}")
        }.toString()
    }
}