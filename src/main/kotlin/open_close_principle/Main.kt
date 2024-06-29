package open_close_principle

import kotlin.math.sqrt

fun main() {

}

/** SHAPES EXAMPLE */

/** Correct implementation
 *
 *  As we can see below, new shapes like Square can be added without modifying the
 *  existing codebase. At the same time, the existing Rectangle class does not need
 *  to be modified to accommodate the addition of Square or any other shapes that
 *  might inherit from it.
 */

interface Shape {
    fun calculateArea(): Float
    fun calculatePerimeter(): Float
}

open class Rectangle(
    private val length: Float,
    private val width: Float
) : Shape {

    override fun calculateArea() =
        length * width

    override fun calculatePerimeter() =
        length * 2 + width * 2
}

class Square(
    private val side: Float
) : Rectangle(side, side) {

    fun calculateDiagonal() =
        (side * sqrt(2.0)).toFloat()
}

/** Incorrect implementation
 *
 *  The ShapeIncorrect interface forces every shape to implement square specific
 *  logic. This will force us to either handle the case in every non-square-like
 *  object, or modify the interface, which violates the discussed principle.
 */

interface ShapeIncorrect {
    fun calculateArea(): Float
    fun calculatePerimeter(): Float
    fun calculateSquareDiagonal(): Float
}

open class RectangleIncorrect(
    private val length: Float,
    private val width: Float
) : ShapeIncorrect {
    override fun calculateArea(): Float = length * width
    override fun calculatePerimeter(): Float = length * 2 + width * 2

    override fun calculateSquareDiagonal(): Float {
        if (length != width) {
            throw IllegalArgumentException("Not a square!")
        }
        return (length * sqrt(2.0)).toFloat()
    }
}

class SquareIncorrect(
    private val side: Float
) : RectangleIncorrect(side, side) {
    override fun calculateSquareDiagonal() =
        (side * sqrt(2.0)).toFloat()
}

class Circle(
    private val radius: Float
) : ShapeIncorrect {
    override fun calculateArea(): Float = Math.PI.toFloat() * radius * radius
    override fun calculatePerimeter(): Float = 2 * Math.PI.toFloat() * radius

    override fun calculateSquareDiagonal(): Float {
        throw IllegalArgumentException("A circle does not have a diagonal like a square.")
    }
}