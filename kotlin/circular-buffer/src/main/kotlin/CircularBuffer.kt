class EmptyBufferException : Exception("Buffer is empty")

class BufferFullException : Exception("Buffer is full")

/**
 * A generic circular buffer implementation for storing and managing elements with a fixed capacity.
 * This buffer operates in a FIFO (First In, First Out) manner.
 *
 * @param T The type of elements stored in the buffer.
 * @property size The fixed capacity of the buffer. Must be greater than zero.
 * @throws IllegalArgumentException if the buffer size is less than or equal to zero.
 */
class CircularBuffer<T>(
    private val size: Int,
) {
    init {
        require(size > 0) { "Buffer size must be greater than zero" }
    }

    private val buffer = arrayOfNulls<Any?>(size)
    private var head = 0 // Points to the front of the buffer (oldest element)
    private var tail = 0 // Points to the next write position
    private var isFull = false // Tracks if the buffer is full

    fun read(): T {
        if (isEmpty()) throw EmptyBufferException()

        @Suppress("UNCHECKED_CAST")
        val value = buffer[head] as T
        buffer[head] = null // Clear the slot after reading
        head = (head + 1) % size
        isFull = false // Reading always free up space in buffer

        return value
    }

    fun write(value: T) {
        if (isFull) throw BufferFullException()

        buffer[tail] = value
        tail = (tail + 1) % size

        if (tail == head) {
            isFull = true // Buffer is now full
        }
    }

    /**
     * Ensures the buffer can continue receiving new values even when full,
     * but at the cost of losing the oldest data.
     */
    fun overwrite(value: T) {
        if (isFull) {
            buffer[head] = value // Overwrite the oldest element in buffer
            head = (head + 1) % size
            tail = (tail + 1) % size
        } else {
            write(value)
        }
    }

    fun clear() {
        buffer.fill(null) // Clear all elements in the buffer
        head = 0
        tail = 0
        isFull = false
    }

    private fun isEmpty(): Boolean = !isFull && tail == head
}
