object Acronym {
    fun generate(phrase: String): String {
        // Split the given phrase into words using spaces and hyphens as delimiters.
        // The regex "[\\s\\-]" matches any whitespace character (`\\s`) or a hyphen (`-`).
        val words = phrase.split(Regex("[\\s\\-]"))

        return words
            .filter { it.isNotBlank() }
            .mapNotNull { word ->
                word.firstOrNull { it.isLetterOrDigit() }
            }.joinToString("") {
                it.uppercase()
            }
    }
}
