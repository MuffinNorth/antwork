import processing.core.PApplet
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

class Anthill(x: Float, y: Float, applet: PApplet): PObject(applet), Placeable {
    var Score = 0
    val speed = 0.1f
    var vectorMove = !Vector(Random.vector())
    override val coordinate = Point(x, y)
    override var size: Float = 15f
    val color = Color(Random.nextInt(255),Random.nextInt(255),Random.nextInt(255))

    private val childList = CopyOnWriteArrayList<Ant>()
    var maxCountChild = 1

    constructor(applet: PApplet) : this(0f,0f,applet)

    override fun draw() = applet.run {
        noStroke()
        fill(color)
        ellipse(coordinate.x, coordinate.y, size*2, size*2)
        text(Score.toString(), coordinate.x + size + 5, coordinate.y + 5)
    }


    override fun logic() {
        if (childList.size < maxCountChild){
            val ant = Ant(coordinate.x, coordinate.y, this, applet)
            childList.add(ant)
            App.registerObject(ant)
        }
        move(vectorMove, speed)
    }
    private fun move(vector: Vector, speed: Float) {
        val deltaMove = vector * speed
        coordinate.x += deltaMove.x
        coordinate.y += deltaMove.y
        boundsCheck(coordinate.x, coordinate.y)
    }

    private fun boundsCheck(x: Float, y: Float) {
        if (x <= 0 + size || x + size >= WIDTH) {
            changeMoveVector(Vector(-vectorMove.x, vectorMove.y))
        }
        if (y <= 0 + size || y + size >= HEIGHT) {
            changeMoveVector(Vector(vectorMove.x, -vectorMove.y))
        }
    }

    private fun changeMoveVector(vector: Vector) {
        vectorMove = vector
    }
}
