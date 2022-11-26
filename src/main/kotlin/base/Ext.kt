package base

fun IntArray.swap(l: Int, r: Int) {
    val swap = this[l]
    this[l] = this[r]
    this[r] = swap
}