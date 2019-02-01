package com.syleiman.gingermoney.core.globalEntities.collections

/**
 * Rect array of Double values
 */
class DoubleRectArray(private val rows: Int, private val cols: Int) {
    private val innerArray: DoubleArray

    init {
        if(rows < 1) {
            throw IllegalArgumentException("Rows value is invalid. It must be greater than 0")
        }

        if(cols < 1) {
            throw IllegalArgumentException("Cols value is invalid. It must be greater than 0")
        }

        innerArray = DoubleArray(rows*cols)
    }

    /**
     *
     */
    fun get(row: Int, col: Int): Double {
        checkRowAndCol(row, col)
        return innerArray[row*cols + col]
    }

    /**
     *
     */
    fun set(row: Int, col: Int, value: Double) {
        checkRowAndCol(row, col)
        innerArray[row*cols + col] = value
    }

    /**
     *
     */
    private fun checkRowAndCol(row: Int, col: Int) {
        if(row !in 0 until rows) {
            throw ArrayIndexOutOfBoundsException("Row value is invalid. It must be in a 0..${rows-1} interval (inclusive)")
        }

        if(col !in 0 until cols) {
            throw ArrayIndexOutOfBoundsException("Col value is invalid. It must be in a 0..${cols-1} interval (inclusive)")
        }
    }
}