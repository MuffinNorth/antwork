import processing.core.PApplet
import kotlin.random.Random

class Food(override val coordinate: Point, override var size: Float, applet:PApplet): PObject(applet), Placeable {
    val BASE_SPEED = 0.5f + Random.nextFloat()
    var speed = BASE_SPEED
    val lookRadius = 40f
    val lifetime = 255f
    var currentTime = 0f
    val lifeStep = Random.nextFloat()
    override fun draw() = applet.run {
        fill(0f, 255f, 0f, lifetime - currentTime)
        noStroke()
        ellipse(coordinate.x, coordinate.y, size * 2, size * 2)
        if(DEBUG_MODE){
            stroke(0f, 255f, 0f, 50f*(1f - currentTime / lifetime))
            noFill()
            ellipse(coordinate.x, coordinate.y, size * 2 + lookRadius * 2, size * 2 + lookRadius * 2)
        }
    }

    override fun logic() {
        val lookList = lookUp(lookRadius)
        if(lookList.isNotEmpty()){
            var xMid = 0f
            var yMid = 0f
            lookList.onEach {
                xMid += it.coordinate.x
                yMid += it.coordinate.y
            }
            val fleeVector = (!Vector(coordinate, Point(xMid, yMid))) * -1f
            move(fleeVector, speed)
        }
        currentTime += lifeStep
        speed = BASE_SPEED * (1f - currentTime / lifetime)
        if (currentTime >= lifetime){
            App.unregisterObject(this)
        }
    }

    private fun lookUp(radius: Float): List<Placeable> = App.objects.filterIsInstance<Placeable>()
        .filter { interactObject(this, it, radius) }

    private fun move(vector: Vector, speed: Float) {
        val deltaMove = vector * speed
        coordinate.x += deltaMove.x
        coordinate.y += deltaMove.y
        boundsCheck(coordinate.x, coordinate.y)
    }
    private fun boundsCheck(x: Float, y: Float) {
        if (x <= 0 || x >= WIDTH) {
            App.unregisterObject(this)
        }
        if (y <= 0 || y >= HEIGHT) {
            App.unregisterObject(this)
        }
    }

}