import kotlin.math.ceil
import kotlin.math.sqrt

/**
 * Provides functionality to encrypt a plaintext string using the Crypto Square method.
 * The process involves normalizing the input, arranging it into a rectangular grid,
 * and then reading the grid column by column to generate the ciphertext.
 */
object CryptoSquare {
    fun ciphertext(plaintext: String): String {
        if (plaintext.isBlank()) return ""

        val normalizedText = plaintext.filter { it.isLetterOrDigit() }.lowercase()
        if (normalizedText.isEmpty()) return ""

        // Calculate the number of columns for the grid
        // The number of columns is determined by taking the square root of the length of the normalized text
        // `ceil` ensures we round up to the nearest integer, making the grid wide enough to fit all characters
        val columns = ceil(sqrt(normalizedText.length.toDouble())).toInt()

        // Calculate the number of rows for the grid
        // The number of rows is computed to ensure all characters fit into the grid.
        // The formula `(length + columns - 1) / columns` is a compact way of dividing `length` by `columns`
        // and rounding up to the nearest integer (essentially calculating the ceiling of `length / columns`)
        val rows = (normalizedText.length + columns - 1) / columns

        // Create the grid as a list of rows, where each row contains a chunk of characters from the normalized text.
        // Each row will have up to `columns` characters
        val grid =
            List(rows) { rowIndex ->
                normalizedText
                    .drop(rowIndex * columns) // Skip the characters that belong to all previous rows
                    .take(columns) // Extract up to `columns` characters for the current row
            }

        // Loop through each column index, from 0 to the number of columns (exclusive)
        return (0 until columns)
            // Combine the result of processing each column into a single string, with columns separated by spaces
            .joinToString(" ") { columnIndex ->
                // For the current column index, gather characters from each row at that column
                grid
                    .map { row ->
                        // Safely get the character at the current column index in the row
                        // If the column index is out of bounds for this row, use a space (" ")
                        row.getOrNull(columnIndex) ?: PADDING_CHAR
                    }
                    // Combine all characters from this column into a single string
                    .joinToString("")
            }
    }

    private const val PADDING_CHAR = " "
}
