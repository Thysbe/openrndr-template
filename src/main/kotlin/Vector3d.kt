import kotlin.math.pow
import kotlin.math.sqrt

class Vector3d(initX: Double, initY: Double, initZ: Double) {
    var x = initX
    var y = initY
    var z = initZ

    fun magnitude() :Double
    {
        return sqrt(x.pow(2) + y.pow(2) + z.pow(2))
    }

    fun divide(div: Double): Vector3d {
        return Vector3d(x/div, y/div, z/div)
    }

    fun mult(div: Double): Vector3d {
        return Vector3d(x*div, y*div, z*div)
    }

    fun unit(): Vector3d {
        val magnitude = magnitude()

        return this.divide(magnitude)
    }
}