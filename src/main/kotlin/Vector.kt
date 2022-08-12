import kotlin.math.sqrt

class Vector(vector: Point) {
    val x = vector.x
    val y = vector.y
    val length = sqrt(x*x + y*y)

    constructor(start: Point, end: Point): this(end - start)
    constructor(x: Float, y: Float): this(Point(x, y))

    operator fun not(): Vector{
        val vector = Vector(Point(x/length, y/length))
        return vector
    }

    operator fun plus(point: Point): Point{
        return Point(point.x + x, point.y + y)
    }

    operator fun plus(other: Vector): Vector{
        return Vector(x + other.x, y + other.y)
    }

    operator fun times(on: Float): Vector{
        return Vector(x * on, y * on)
    }

    override fun toString(): String {
        return "Vector($x $y)"
    }
}