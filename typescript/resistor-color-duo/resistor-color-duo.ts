const Color = {
    black: 0,
    brown: 1,
    red: 2,
    orange: 3,
    yellow: 4,
    green: 5,
    blue: 6,
    violet: 7,
    grey: 8,
    white: 9
} as const;

export function decodedValue(colors: string[]): number {
    if (colors.length < 2) {
        throw new Error("At least two colors are required");
    }

    // Extract the first two color names and map them to their numeric values
    const firstTwoNumbers = colors.slice(0, 2).map(color => {
        const value = Color[color.toLowerCase() as keyof typeof Color];

        if (value === undefined) {
            throw new Error(`Invalid color: ${color}`);
        }

        return value;
    });

    return firstTwoNumbers[0] * 10 + firstTwoNumbers[1];
}



