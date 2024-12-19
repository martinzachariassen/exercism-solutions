import kotlin.math.pow
import kotlin.math.sqrt

/**
 * An object that represents a scoring system for a dartboard based on the coordinates of a dart throw.
 *
 * The dartboard is divided into three scoring zones:
 * - Inner circle (radius ≤ 1): 10 points
 * - Middle circle (1 < radius ≤ 5): 5 points
 * - Outer circle (5 < radius ≤ 10): 1 point
 * - Outside all circles (radius > 10): 0 points
 *
 * The score is calculated using the Euclidean distance from the origin (0, 0) to the point (x, y).
 */
object Darts {
    fun score(
        x: Number,
        y: Number,
    ): Int {
        val xValue = x.toDouble()
        val yValue = y.toDouble()

        // Calculate the straight-line distance from the origin (0,0) to the point (xValue, yValue).
        //
        // This is based on the Pythagorean theorem, where the distance is the hypotenuse
        // of a right triangle formed by the horizontal (x) and vertical (y) legs:
        //
        //    distance = sqrt(x^2 + y^2)
        //
        // Steps:
        // 1. Square the x and y values to ensure all distances are positive: x^2 and y^2.
        // 2. Add the squares to get the squared distance: x^2 + y^2.
        // 3. Take the square root of the sum to compute the actual distance.
        //
        // Visual Representation:
        //
        //     y-axis
        //       ↑
        //       |
        //    2  |
        //    1  |                  * <-- Point (0.8, -0.8)
        //       |                /
        //       |              /   ← Hypotenuse (distance)
        //       |            /
        // ------O-----------------------> x-axis
        //       | (0, 0)      (0.8 units right, 0.8 units down)
        //       |
        //      -1
        //       |
        //      -2
        //
        // Key:
        // O = Origin (0, 0)
        // * = Point (xValue = 0.8, yValue = -0.8)
        //
        // Example: If xValue = 0.8 and yValue = -0.8, the distance is:
        // sqrt(0.8^2 + (-0.8)^2) = sqrt(0.64 + 0.64) ≈ 1.13
        val distanceFromCenter = sqrt(xValue.pow(2) + yValue.pow(2))

        return when {
            distanceFromCenter <= 1 -> 10 // Inner circle
            distanceFromCenter <= 5 -> 5 // Middle circle
            distanceFromCenter <= 10 -> 1 // Outer circle
            else -> 0 // Outside all circles
        }
    }
}
