package me.changyi.interview.model

import java.lang.StringBuilder


class BinaryTreeNode(val value: Int) {

    var parentTreeNode: BinaryTreeNode? = null
    var leftTreeNode: BinaryTreeNode? = null
    var rightTreeNode: BinaryTreeNode? = null

    val treeRoot: BinaryTreeNode
        get() {
            var root = this
            while (root.parentTreeNode != null) {
                root = root.parentTreeNode!!
            }
            return root
        }

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

    fun connectNodes(left: BinaryTreeNode?, right: BinaryTreeNode?) {
        leftTreeNode = left?.apply {
            parentTreeNode = this@BinaryTreeNode
        }
        rightTreeNode = right?.apply {
            parentTreeNode = this@BinaryTreeNode
        }
    }

    /**
     * Print binaryTree in preOrder.
     */
    override fun toString(): String {
        return StringBuilder().apply {
            append("{$value")
            if (leftTreeNode != null)
                append(", left=${leftTreeNode.toString()}")
            if (rightTreeNode != null)
                append(", right=${rightTreeNode.toString()}")
            append("}")
        }.toString()
    }
}