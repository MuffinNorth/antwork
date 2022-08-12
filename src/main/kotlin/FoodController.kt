import processing.core.PApplet
import kotlin.random.Random

class FoodController(val maxCount: Int, applet: PApplet): PObject(applet) {

    override fun draw() {}
    override fun logic() {
        if(App.objects.filterIsInstance<Food>().count() <= maxCount) {
            App.registerObject(
                Food(Random.point(0f, WIDTH.toFloat(), 0f, HEIGHT.toFloat()), 2f, applet)
            )
        }
    }

}