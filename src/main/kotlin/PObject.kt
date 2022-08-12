import processing.core.PApplet

abstract class PObject(protected val applet: PApplet) {
    var markerForDelete = false
    protected abstract fun draw()
    protected abstract fun logic()

    fun update(){
        logic()
        draw()
    }
}