package seq

import base.QuickSort
import base.swap
import java.util.concurrent.ThreadLocalRandom

class QuickSortSeq : QuickSort{
    override val algoName: String
        get() = "Quick sort Seq"

    override fun sort(array: IntArray) {
        sort(array, 0, array.size)
    }

    private fun sort(array: IntArray, start: Int, end: Int) {
        if (end - start <= 1) {
            return
        }
        val pivot = ThreadLocalRandom.current().nextInt(start, end)
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
        sort(array, start, pointer)
        sort(array, pointer + 1, end)
    }
}