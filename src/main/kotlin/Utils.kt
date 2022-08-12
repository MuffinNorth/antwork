import processing.core.PApplet
import kotlin.random.Random
import kotlin.random.nextInt

const val DRAW_DELTA = 3f

fun PApplet.stroke(r: Int, g: Int, b: Int){
    stroke(r.toFloat(), g.toFloat(), b.toFloat())
}

fun PApplet.fill(r: Int, g: Int, b: Int){
    fill(r.toFloat(), g.toFloat(), b.toFloat())
}

fun PApplet.stroke(color: Color){
    stroke(color.r.toFloat(), color.g.toFloat(), color.b.toFloat())
}

fun PApplet.fill(color: Color){
    fill(color.r.toFloat(), color.g.toFloat(), color.b.toFloat())
}

fun Random.vector(): Point {
    return Point(Random.nextFloat() - 0.5f, Random.nextFloat() - 0.5f)
}

fun Random.point(xMin: Float, xMax: Float, yMin: Float, yMax: Float): Point {
    val x = xMax - (xMax - xMin) * Random.nextFloat()
    val y = yMax - (yMax - yMin) * Random.nextFloat()
    return Point(x, y)
}

fun interactObject(pObject_one: Placeable, pObject_two: Placeable): Boolean{
    val r1 = pObject_one.size
    val r2 = pObject_two.size
    val d = pObject_one.coordinate.lengthToOther(pObject_two.coordinate)
    return d <= r1 + r2
}

fun interactObject(pObject_one: Placeable, pObject_two: Placeable, size_up: Float): Boolean{
    val r1 = pObject_one.size + size_up
    val r2 = pObject_two.size
    val d = pObject_one.coordinate.lengthToOther(pObject_two.coordinate)
    if(pObject_one == pObject_two) return false
    return d <= r1 + r2
}