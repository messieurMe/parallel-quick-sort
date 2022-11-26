import base.QuickSort
import par.QuickSortParFJP
import seq.QuickSortSeq
import java.util.*

const val ARRAY_SIZE: Int = 100_000_000

@Suppress("UNUSED_PARAMETER")
fun main(args: Array<String>) {

    val algos: List<QuickSort> = listOf(
        QuickSortSeq(), QuickSortParFJP()
    )

    val arrayGeneratorType: List<Pair<() -> IntArray, String>> = listOf(
        ::generateIncrementalArray to "generated incremental",
        ::generateRandomRandomArray to "generated randomly" // comment this line to skip sorting random array
    )

    algos.forEach { sortir ->
        printlnTabbed(0, "==== " + sortir.algoName + " ====")

        arrayGeneratorType.forEach { (generator, arrayTitle) ->
            printlnTabbed(1, "On $arrayTitle:")

            val results = LinkedList<Long>()
            repeat(5) {
                val generatedArray = generator()
                val (_, time) = withStopWatch { sortir.sort(generatedArray) }
                validate(generatedArray)
                results.add(time)
            }
            printlnTabbed(2, "All results: ${results.toList()}")
            printlnTabbed(2, "Average: ${results.average()}")
        }
    }
}


fun printlnTabbed(tabs: Int, message: String) {
    println("\t".repeat(tabs) + message)
}

fun generateIncrementalArray(): IntArray = IntArray(ARRAY_SIZE) { it }.apply { shuffle() }

fun generateRandomRandomArray(): IntArray {
    return IntArray(ARRAY_SIZE) { Random().nextInt(0, ARRAY_SIZE) }.apply { shuffle() }
}

fun validate(array: IntArray) {
    for (i in 1 until array.size) {
        if (array[i - 1] <= array[i]) {
            continue
        }
        throw IllegalStateException("Not sorted")
    }
}

data class ResultWithTime<T>(val result: T, val spentTime: Long)

fun <T> withStopWatch(block: () -> T): ResultWithTime<T> {
    val start = System.currentTimeMillis()
    val result = block()
    val spentTime = System.currentTimeMillis() - start
    return ResultWithTime(result, spentTime)

}