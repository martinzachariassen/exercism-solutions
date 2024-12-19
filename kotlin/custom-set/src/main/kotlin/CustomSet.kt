/**
 * Represents a custom implementation of a mathematical set that supports common set operations
 * such as union, intersection, difference, and subset checks.
 *
 * @constructor Initializes the set with a variable number of integer elements. Duplicate elements
 * provided at initialization are automatically removed.
 *
 * @param numbers The elements to initialize the set with.
 */
class CustomSet(
    vararg numbers: Int,
) {
    private val elements = numbers.distinct().toMutableList()

    /**
     * Checks if the set is empty.
     *
     * @return True if the set contains no elements, false otherwise.
     */
    fun isEmpty(): Boolean = elements.isEmpty()

    /**
     * Checks if the current set is a subset of another set. A set is a subset of another set
     * if all its elements are contained within the other set.
     *
     * @param other The set to compare against to determine whether the current set is a subset.
     * @return True if the current set is a subset of the provided set, false otherwise.
     */
    fun isSubset(other: CustomSet): Boolean {
        for (element in elements) {
            if (!other.contains(element)) return false
        }
        return true
    }

    /**
     * Determines if this set and another set are disjoint, meaning they share no common elements.
     *
     * @param other The set to check against this set for shared elements.
     * @return True if the two sets have no elements in common, false otherwise.
     */
    fun isDisjoint(other: CustomSet): Boolean {
        for (element in elements) {
            if (other.contains(element)) return false
        }
        return true
    }

    /**
     * Checks if the specified element is present in the set.
     *
     * @param other The element to check for in the set.
     * @return True if the element is present in the set, false otherwise.
     */
    fun contains(other: Int): Boolean = elements.contains(other)

    /**
     * Computes the intersection of this set with another set, resulting in a new set containing only the elements
     * that are present in both sets.
     *
     * @param other The set to intersect with this set.
     * @return A new CustomSet containing elements that are present in both this set and the other set.
     */
    fun intersection(other: CustomSet): CustomSet {
        val result = elements.filter { other.contains(it) }
        return CustomSet(*result.toIntArray())
    }

    /**
     * Adds a specified element to the set if it is not already present.
     *
     * @param other The element to be added to the set.
     */
    fun add(other: Int) {
        if (!elements.contains(other)) elements.add(other)
    }

    /**
     * Checks if this CustomSet is equal to another object. Two CustomSets are considered
     * equal if they have the same size and all elements in this set are also present in
     * the other set, i.e., this set is a subset of the other.
     *
     * @param other The object to be compared with this CustomSet.
     * @return True if the specified object is a CustomSet with the same elements as this set, false otherwise.
     */
    override fun equals(other: Any?): Boolean {
        if (other !is CustomSet) return false
        return elements.size == other.elements.size && isSubset(other)
    }

    /**
     * Computes the hash code for this CustomSet by generating a hash code
     * based on the sorted elements of the set.
     *
     * @return The hash code representing the contents of this set.
     */
    override fun hashCode(): Int = elements.sorted().hashCode()

    /**
     * Combines this set with another set to create a new set containing all unique elements from both sets.
     *
     * @param other The set to be combined with this set.
     * @return A new CustomSet containing all unique elements from this set and the other set.
     */
    operator fun plus(other: CustomSet): CustomSet = CustomSet(*(elements + other.elements).distinct().toIntArray())

    /**
     * Computes the difference between this set and another set, resulting in a new set containing elements
     * that belong to this set but not to the other set.
     *
     * @param other The set to be subtracted from this set.
     * @return A new CustomSet containing elements that are present in this set but not in the other set.
     */
    operator fun minus(other: CustomSet): CustomSet {
        val result = elements.filter { !other.contains(it) }
        return CustomSet(*result.toIntArray())
    }
}
