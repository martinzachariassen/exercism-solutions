/**
 * An object containing utility methods related to counting eggs.
 *
 * This object provides methods to perform calculations or operations related to "eggs",
 * particularly involving binary representations of integers.
 */
object EliudsEggs {
    fun eggCount(number: Int): Int {
        var currentNumber = number
        var eggCount = 0

        while (currentNumber > 0) {
            if (currentNumber % 2 == 1) eggCount++
            currentNumber /= 2
        }

        return eggCount
    }
}
