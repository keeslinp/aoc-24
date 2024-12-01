import kotlin.math.absoluteValue

fun main() {
    fun parseLists(input: List<String>): Pair<List<Int>, List<Int>> {
        val (first, second) = input.fold(
            Pair(ArrayList<Int>(input.size), ArrayList<Int>(input.size))
        ) { result, item ->
            val splits = item.split("   ");
            result.first.addLast(splits.firstOrNull()?.toIntOrNull() ?: 0);
            result.second.addLast(splits.lastOrNull()?.toIntOrNull() ?: 0);

            result
        };

        first.sort()
        second.sort()

        return Pair(first, second)
    }

    fun part1(input: List<String>): Int {
        val (first, second) = parseLists(input)
        return first.zip(second).map { (first, second) ->
            (first - second).absoluteValue
        }.reduce(Int::plus)
    }

    fun part2(input: List<String>): Int {
        val (first, second) = parseLists(input)
        val counts = second.groupingBy { it }.eachCount()
        return first.map { it * counts.getOrDefault(it, 0) }.reduce(Int::plus)
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()

    check(part2(testInput) == 31)
    part2(input).println()
}
