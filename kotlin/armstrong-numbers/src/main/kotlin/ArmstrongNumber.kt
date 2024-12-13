import kotlin.math.pow

object ArmstrongNumber {
    fun check(input: Int): Boolean {
        // Convert the number to a string to extract its digits
        val digits = input.toString().map { it.toString().toInt() }

        val power = digits.size

        // Calculate the sum of each digit raised to the power
        val sum =
            digits.sumOf {
                it.toDouble().pow(power).toInt()
            }

        return sum == input
    }
}
