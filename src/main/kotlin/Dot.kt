class Dot(position: Vector3d, velocity: Vector3d, mass: Double ) {

    val maxVelocity = 0.01
    val velocityCompressionThreshold = 0.005
    val velocityCompressionStep = 0.0005

    var position = position
    var velocity = velocity
    var mass = mass

    fun stepPosition() {
        position = Vector3d(position.x + velocity.x, position.y + velocity.y, position.z + velocity.z)
        if(position.x > 1)
        {
            velocity.x *= -1
        }
        if(position.x < 0)
        {
            velocity.x *= -1
        }
        if(position.y > 1)
        {
            velocity.y *= -1
        }
        if(position.y < 0)
        {
            velocity.y *= -1
        }
        if(position.z > 1)
        {
            velocity.z *= -1
        }
        if(position.z < 0)
        {
            velocity.z *= -1
        }
    }

    fun updateVelocity(acceleration: Vector3d)
    {
        velocity = Vector3d(velocity.x + acceleration.x, velocity.y + acceleration.y, velocity.z + acceleration.z)

        if(velocity.magnitude() > maxVelocity)
        {
            velocity = velocity.unit().mult(maxVelocity)
        }


    }

    fun renderRadius() : Double {
        return mass * position.z + 3
    }
}