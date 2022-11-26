package par

import base.QuickSort
import base.swap
import java.util.concurrent.RecursiveAction

class QuickSortParFJP : QuickSort {
    override val algoName: String
        get() = "Quick sort Par ForkJoinPool"

    override fun sort(array: IntArray) {
        QuickSortParFJPImpl(array).sort()
    }
}

private class QuickSortParFJPImpl(
    private val array: IntArray,
    private val start: Int,
    private val end: Int
) : RecursiveAction() {

    constructor(array: IntArray) : this(array, 0, array.size)

    private fun sort(array: IntArray, start: Int, end: Int): Int {
        val pivot = end - 1
        val pivotValue = array[pivot]

        array.swap(pivot, end - 1)
        var pointer = start

        for (i in start until end) {
            if (array[i] < pivotValue) {
                array.swap(i, pointer)
                pointer++
            }
        }
        array.swap(pointer, end - 1)
        return pointer
    }


    private fun sortDirect(array: IntArray, start: Int, end: Int) {
        if (end - start <= 1) {
            return
        }

        val pointer = sort(array, start, end)

        sortDirect(array, start, pointer)
        sortDirect(array, pointer + 1, end)
    }


    private val threshold = 1_000
    override fun compute() {
        if (end - start <= threshold) {
            sortDirect(array, start, end)
            return
        }

        val pointer = sort(array, start, end)

        invokeAll(
            QuickSortParFJPImpl(array, start, pointer),
            QuickSortParFJPImpl(array, pointer + 1, end)
        )
    }

    fun sort() {
        this.compute()
    }
}