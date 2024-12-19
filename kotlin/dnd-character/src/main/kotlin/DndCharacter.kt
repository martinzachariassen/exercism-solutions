import kotlin.math.floor
import kotlin.random.Random

/**
 * Represents a Dungeons and Dragons (D&D) character with abilities and hitpoints derived from standard rules.
 * Each character has six ability scores and calculated hitpoints.
 *
 * @property strength The strength ability score, determined by rolling four six-sided dice.
 * @property dexterity The dexterity ability score, determined by rolling four six-sided dice.
 * @property constitution The constitution ability score, determined by rolling four six-sided dice.
 * @property intelligence The intelligence ability score, determined by rolling four six-sided dice.
 * @property wisdom The wisdom ability score, determined by rolling four six-sided dice.
 * @property charisma The charisma ability score, determined by rolling four six-sided dice.
 * @property hitpoints The character's hitpoints, calculated as 10 plus the modifier of the constitution ability score.
 */
class DndCharacter {
    val strength: Int = ability()
    val dexterity: Int = ability()
    val constitution: Int = ability()
    val intelligence: Int = ability()
    val wisdom: Int = ability()
    val charisma: Int = ability()
    val hitpoints: Int = 10 + modifier(constitution)

    /**
     * Companion object for the DndCharacter class, providing utility methods for character creation and mechanics.
     */
    companion object {
        /**
         * Generates a random ability score for a Dungeons and Dragons (D&D) character.
         * The score is based on the sum of the highest three rolls out of four six-sided dice rolls.
         *
         * @return An integer representing the generated ability score, ranging between 3 and 18.
         */
        fun ability(): Int = rollDice()

        /**
         * Calculates the ability modifier based on the given score according to Dungeons and Dragons (D&D) rules.
         * The modifier is determined by subtracting 10 from the score, dividing by 2, and rounding down to the nearest integer.
         *
         * @param score The ability score for which the modifier is calculated.
         * @return An integer representing the calculated ability modifier.
         */
        fun modifier(score: Int): Int = floor((score - CONSTITUTION_MODIFIER) / CONSTITUTION_ROUND_DOWN).toInt()

        /**
         * Rolls four six-sided dice, drops the lowest roll, and returns the sum of the highest three rolls.
         *
         * @return The sum of the three highest dice rolls, representing a random ability score between 3 and 18.
         */
        private fun rollDice(): Int {
            val rolls = List(4) { Random.nextInt(1, 7) }
            return rolls.sortedDescending().take(3).sum()
        }

        private const val CONSTITUTION_MODIFIER = 10.0
        private const val CONSTITUTION_ROUND_DOWN = 2
    }
}
