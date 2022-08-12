import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class VectorTest {

    @Test
    fun `get simple vector length`() {
        val vector = Vector(0f, 10f)
        assertEquals(10f, vector.length)
    }

    @Test
    fun `get vector length with float`() {
        val vector = Vector(12.5f, 3.3f)
        assertEquals(12.9282f, vector.length, 0.001f)
    }

    @Test
    fun `get vector length with minus coordinate`() {
        val vector = Vector(-12.3f, 25f)
        assertEquals(27.86198f, vector.length, 0.001f)
    }

    @Test
    fun `simple normalize vector`() {
        val normVector = !Vector(5f, 0f)
        assertArrayEquals(arrayOf(1f,0f), arrayOf(normVector.x, normVector.y))
    }

    @Test
    fun `negative normalize vector`() {
        val normVector = !Vector(0f, -5f)
        assertArrayEquals(arrayOf(0f,-1f), arrayOf(normVector.x, normVector.y))
    }

    @Test
    fun `normalize vector with to components`() {
        val normVector = !Vector(12f, -5f)
        assertEquals(0.923f, normVector.x, 0.01f)
        assertEquals(-0.384f, normVector.y, 0.01f)
    }

    @Test
    fun `create from two point`(){
        val pStart = Point(0f,0f)
        val pEnd = Point(12f,-10f)
        val vector = Vector(pStart, pEnd)
        assertEquals(vector.x, 12f)
        assertEquals(vector.y, -10f)
    }
    @Test
    fun `multiply vector on 2`(){
        val vector = Vector(0.5f, 0.5f)
        val newVector = vector * 3.3f
        assertEquals(1.65f, newVector.x)
        assertEquals(1.65f, newVector.y)
    }

}