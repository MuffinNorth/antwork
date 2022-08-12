import processing.core.PApplet
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.random.Random


const val WIDTH = 1280
const val HEIGHT = 720
class App: PApplet() {

    companion object Registery{
        val objects = CopyOnWriteArrayList<PObject>()

        fun registerObject(entity: PObject){
            objects.add(entity)
        }

        fun unregisterObject(entity: PObject){
            objects.remove(entity)
        }
    }


    val array = Array(3){Anthill(Random.nextInt(WIDTH - 300).toFloat() + 150, Random.nextInt(HEIGHT - 300).toFloat() + 150, this)}
    val foodController = FoodController(Random.nextInt(50) + 50,this)
    override fun settings() {
        kotlin.io.println(foodController.maxCount)
        size(WIDTH, HEIGHT)
    }

    override fun setup() {
        ellipseMode(CENTER)
        array.forEach { registerObject(it) }
        registerObject(foodController)
    }

    override fun draw() {
        if(DEBUG_MODE){
            background(0)
        }else{
            noStroke()
            fill(0f,0f,0f,50f)
            rect(0f,0f,WIDTH.toFloat(),HEIGHT.toFloat())
        }
        objects.asSequence().onEach { it.update() }.filter { it.markerForDelete }.forEach { unregisterObject(it) }
    }

}
var DEBUG_MODE = false

fun main(args: Array<String>) {
    if (args.isNotEmpty() && args.first() == "-d"){
        DEBUG_MODE = args[1] == "true"
    }
    PApplet.main(App().javaClass)
}
