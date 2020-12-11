import kotlin.math.sin
import kotlin.math.cos

class VectorFieldProvider {

    // returns force vector, only 2D for now but might as well keep 3d vectors standard
    fun someField(x: Double, y: Double) : Vector3d
    {
        return Vector3d(-y, x, 0.0)
    }
}