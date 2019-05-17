package com.syleiman.gingermoney

import com.syleiman.gingermoney.core.global_entities.collections.DoubleRectArray
import org.junit.Assert.assertEquals
import org.junit.Test

class DoubleRectArrayTest {
    /**
     *
     */
    @Test(expected = IllegalArgumentException::class)
    fun createInvalidRows() {
        // Arrange
        DoubleRectArray(0, 5)
    }

    /**
     *
     */
    @Test(expected = IllegalArgumentException::class)
    fun createInvalidCols() {
        // Arrange
        DoubleRectArray(5, 0)
    }

    /**
     *
     */
    @Test(expected = ArrayIndexOutOfBoundsException::class)
    fun getInvalidRowBottomBoundary() {
        // Arrange
        val array = DoubleRectArray(5, 5)

        // Act
        array.get(-1, 2)
    }

    /**
     *
     */
    @Test(expected = ArrayIndexOutOfBoundsException::class)
    fun getInvalidRowTopBoundary() {
        // Arrange
        val array = DoubleRectArray(5, 5)

        // Act
        array.get(5, 2)
    }

    /**
     *
     */
    @Test(expected = ArrayIndexOutOfBoundsException::class)
    fun getInvalidColBottomBoundary() {
        // Arrange
        val array = DoubleRectArray(5, 5)

        // Act
        array.get(2, -1)
    }

    /**
     *
     */
    @Test(expected = ArrayIndexOutOfBoundsException::class)
    fun getInvalidColTopBoundary() {
        // Arrange
        val array = DoubleRectArray(5, 5)

        // Act
        array.get(2, 5)
    }

    /**
     *
     */
    @Test(expected = ArrayIndexOutOfBoundsException::class)
    fun setInvalidRowBottomBoundary() {
        // Arrange
        val array = DoubleRectArray(5, 5)

        // Act
        array.set(-1, 2, 42.0)
    }

    /**
     *
     */
    @Test(expected = ArrayIndexOutOfBoundsException::class)
    fun setInvalidRowTopBoundary() {
        // Arrange
        val array = DoubleRectArray(5, 5)

        // Act
        array.set(5, 2, 42.0)
    }

    /**
     *
     */
    @Test(expected = ArrayIndexOutOfBoundsException::class)
    fun setInvalidColBottomBoundary() {
        // Arrange
        val array = DoubleRectArray(5, 5)

        // Act
        array.set(2, -1, 42.0)
    }

    /**
     *
     */
    @Test(expected = ArrayIndexOutOfBoundsException::class)
    fun setInvalidColTopBoundary() {
        // Arrange
        val array = DoubleRectArray(5, 5)

        // Act
        array.set(2, 5, 42.0)
    }

    /**
     *
     */
    @Test
    fun getSetFirstItem() {
        // Arrange
        val array = DoubleRectArray(5, 5)

        // Act
        val valueSet = 42.0
        array.set(0, 0, valueSet)
        val valueGet = array.get(0, 0)

        // Assert
        assertEquals(valueSet, valueGet, 0.001)
    }

    /**
     *
     */
    @Test
    fun getSetLastItem() {
        // Arrange
        val array = DoubleRectArray(5, 5)

        // Act
        val valueSet = 42.0
        array.set(4, 4, valueSet)
        val valueGet = array.get(4, 4)

        // Assert
        assertEquals(valueSet, valueGet, 0.001)
    }
}