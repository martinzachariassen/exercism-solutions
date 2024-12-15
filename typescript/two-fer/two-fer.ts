/**
 * Generates a phrase based on the given name.
 *
 * @param name - The name to include in the phrase (default: "you").
 * @returns A string in the format: "One for [name], one for me."
 */
export function twoFer(name: string = "you"): string {
  return `One for ${name}, one for me.`;
}
