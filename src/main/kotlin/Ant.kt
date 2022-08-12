import processing.core.PApplet
import kotlin.random.Random

class Ant(x: Float, y: Float, private val base: Anthill, applet: PApplet) : PObject(applet), Placeable {
    override val coordinate = Point(x, y)
    val BASE_SIZE = 2.5f
    override var size = BASE_SIZE
    val BASE_SPEED = 1.5f
    var speed = BASE_SPEED
    var vectorMove = !Vector(Random.vector())
    val lookRadius = size * 20f

    var invetory: Int = 0


    override fun draw() = applet.run {
        if (invetory > 0) {
            stroke(Color(255, 255, 255))
            fill(base.color)
        } else {
            noStroke()
            fill(base.color)
        }

        ellipse(coordinate.x, coordinate.y, size * 2, size * 2)
        if (DEBUG_MODE) {
            stroke(255f, 0f, 0f, 50f)
            noFill()
            ellipse(coordinate.x, coordinate.y, size * 2 + lookRadius * 2, size * 2 + lookRadius * 2)
            stroke(255, 255, 255)
            line(
                coordinate.x,
                coordinate.y,
                coordinate.x + vectorMove.x * speed * 10,
                coordinate.y + vectorMove.y * speed * 10
            )
        }
    }

    override fun logic() {
        val lookList = lookUp(lookRadius)
        val interactList = interactCheck()

        if (invetory > 0) {
            changeMoveVector(!Vector(coordinate, base.coordinate))
        }

        if (lookList.isNotEmpty()) {
            if (lookList.any { it is Food }) {
                val closes = lookList.filterIsInstance<Food>().minBy { coordinate.lengthToOther(it.coordinate) }
                val vector = !Vector(coordinate, closes.coordinate)
                changeMoveVector(vector)
            }
        }
        if (interactList.isNotEmpty()) {
            if (interactList.any { it is Food }) {
                val count = lookList.filterIsInstance<Food>().count()
                interactList.filterIsInstance<Food>().forEach {
                    it.markerForDelete = true
                }
                invetory += count
                size += count / 2
                speed *= 0.8f
            }
            if (invetory != 0) {
                if (interactList.contains(base)) {
                    base.Score += invetory
                    base.maxCountChild = base.Score / 10 + 1
                    invetory = 0
                    speed = BASE_SPEED
                    size = BASE_SIZE
                    changeMoveVector(!Vector(Random.vector()))
                }
            }
        }
        move(vectorMove, speed)
    }

    private fun lookUp(radius: Float): List<Placeable> = App.objects.filterIsInstance<Placeable>()
        .filter { interactObject(this, it, radius) }

    private fun interactCheck(): List<Placeable> = lookUp(0f)

    private fun move(vector: Vector, speed: Float) {
        val deltaMove = vector * speed
        coordinate.x += deltaMove.x
        coordinate.y += deltaMove.y
        boundsCheck(coordinate.x, coordinate.y)
    }

    private fun boundsCheck(x: Float, y: Float) {
        if (x <= 0 || x >= WIDTH) {
            changeMoveVector(Vector(-vectorMove.x, vectorMove.y))
        }
        if (y <= 0 || y >= HEIGHT) {
            changeMoveVector(Vector(vectorMove.x, -vectorMove.y))
        }
    }

    private fun changeMoveVector(vector: Vector) {
        vectorMove = vector
    }
}