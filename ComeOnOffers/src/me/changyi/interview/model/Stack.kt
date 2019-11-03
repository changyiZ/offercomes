package me.changyi.interview.model

class Stack<T> {
    val elements: MutableList<T> = mutableListOf()

    val isEmpty: Boolean
        get() = elements.isEmpty()

    val size: Int
        get() = elements.size

    fun push(item: T) = elements.add(item)

    fun pop(): T? {
        val item = elements.lastOrNull()
        if (!isEmpty) {
            elements.removeAt(elements.size - 1)
        }
        return item
    }

    fun peek(): T? = elements.lastOrNull()

    override fun toString(): String = elements.toString()
}