package me.changyi.interview.model

class Node<T>(var value: T) {
    var next: Node<T>? = null
    var previous: Node<T>? = null
}

class LinkedList<T>(vararg values: T) {

    var head: Node<T>? = null

    val isEmpty: Boolean
        get() = head == null

    val first: Node<T>?
        get() = head

    val last: Node<T>?
        get() {
            var node = head
            while (node?.next != null) {
                node = node.next
            }
            return node
        }

    val size: Int
        get() {
            head ?: return 0
            var node = head
            var count = 1
            while (node?.next != null) {
                count++
                node = node.next
            }
            return count
        }

    init {
        if (values.isNotEmpty()) {
            head= Node(values[0])
            if (values.size > 1) {
                var current = head!!
                for (index in 1 until values.size) {
                    current.next = Node(values[index])
                    current = current.next!!
                }
            }
        }
    }

    constructor(head: Node<T>) : this() {
        this.head = head
    }

    fun nodeAtIndex(index: Int): Node<T>? {
        if (index >= 0) {
            var node = head
            var current = 0
            while (node != null) {
                if (current++ == index) return node
                node = node.next
            }
        }
        return null
    }

    fun append(value: T) : LinkedList<T> {
        val newNode = Node(value)
        val lastNode = last
        if (lastNode != null) {
            newNode.previous = lastNode
            lastNode.next = newNode
        } else {
            head = newNode
        }
        return this
    }

    fun removeAll() {
        head = null
    }

    fun removeNode(node: Node<T>): T {
        val prev = node.previous
        val next = node.next
        if (prev != null) {
            prev.next = next
        } else {
            head = next
        }
        next?.previous = prev
        node.previous = null
        node.next = null
        return node.value
    }

    fun removeLast(): T? {
        val lastNode = last
        return if (lastNode != null) {
            removeNode(lastNode)
        } else {
            null
        }
    }

    fun removeAtIndex(index: Int): T? {
        val node = nodeAtIndex(index)
        return if (node != null) {
            removeNode(node)
        } else {
            null
        }
    }

    override fun toString(): String {
        var s = "["
        var node = head
        while (node != null) {
            s += "${node.value}"
            node = node.next
            if (node != null) {
                s += ", "
            }
        }
        return "$s]"
    }
}