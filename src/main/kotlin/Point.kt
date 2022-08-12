import kotlin.math.pow
import kotlin.math.sqrt

data class Point(var x: Float, var y: Float) {
    operator fun minus(other: Point): Point {
        return Point(x - other.x, y - other.y)
    }

    operator fun plusAssign(deltaMove: Point) {
        x += deltaMove.x
        y += deltaMove.y
    }

    fun lengthToOther(other: Point): Float{
        return sqrt((x - other.x).pow(2) + (y - other.y).pow(2))
    }
}