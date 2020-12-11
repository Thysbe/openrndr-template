import kotlin.math.pow
import kotlin.math.sqrt

class GravityCalc {
    val gravitationalConst = 0.000000005

    fun calcAcceleration(movingDot: Dot, stationaryDot: Dot) :Vector3d
    {
        var radius = 0.1 * sqrt(
            (movingDot.position.x - stationaryDot.position.x).pow(2) +
                    (movingDot.position.y - stationaryDot.position.y).pow(2) +
                    (movingDot.position.z - stationaryDot.position.z).pow(2)
        )

        var F = gravitationalConst * movingDot.mass * stationaryDot.mass / radius.pow(2)

        var A = F / movingDot.mass

        var unitVector = calcUnitVector(stationaryDot, movingDot)

        return Vector3d(unitVector.x * A, unitVector.y * A, unitVector.z * A)

    }

    // Creates a unit vector pointing from the from dot to the to dot
    fun calcUnitVector(toDot: Dot, fromDot: Dot) : Vector3d
    {
        var direction = Vector3d(toDot.position.x - fromDot.position.x, toDot.position.y - fromDot.position.y, toDot.position.z - fromDot.position.z)
        var magnitude = direction.magnitude()

        return Vector3d(direction.x / magnitude, direction.y / magnitude, direction.z / magnitude)


    }


}