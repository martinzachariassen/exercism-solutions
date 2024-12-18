

/**
 * A class that provides calculations related to the sum of squares,
 * the square of the sum, and their difference for a given range of numbers from 1 to the specified number.
 *
 * @property number The upper limit of the range of numbers to perform calculations on.
 */
class Squares(
    private val number: Int,
) {
    // Computes the sum of squares from 1 to 'number'
    fun sumOfSquares(): Int = (1..number).sumOf { it * it }

    // Computes the square of the sum from 1 to 'number'
    fun squareOfSum(): Int = (1..number).sum().let { it * it }

    // Computes the difference between the square of the sum and the sum of squares
    fun difference(): Int = squareOfSum() - sumOfSquares()
}
