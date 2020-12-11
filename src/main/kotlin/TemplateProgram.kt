import org.openrndr.Mouse
import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.*
import org.openrndr.panel.elements.round
import kotlin.random.Random

//IDEAS:
/*
    DOTS: Floating Dots
       Gravity (GRAV)
       Boid (BOID) - schooling sim
       Grid (GRID) - floating points, can apply vector fields to move points around maybe?

       Some sort of interpolation between modes?

    BIOS: Cellular Automata

    LINES:

 */


fun main() = application {
    configure {
        width = 1920
        height = 1080
    }

    program {
        val dotList = mutableListOf<Dot>()
        val rng = Random
        val width = 1920.0
        val height = 1080.0

        drawer.shadeStyle = shadeStyle {
            fragmentTransform = "x_fill.rgb = vec3(1.0, 0.0, 0.0);"
        }

        val center = Vector3d(0.5, 0.5, 0.5)
        val zeroVector = Vector3d(0.0, 0.0, 0.0)
        var bigBoi = Dot(center, zeroVector, 50.0)

        // initialize DOTS
        /* Rand Dots
        for(i in 1..256)
        {
            val somePos = Vector3d(rng.nextDouble(), rng.nextDouble() * 0.1 + 0.45, 0.5)
            val someVelocity = Vector3d((rng.nextDouble()-0.5) * 0.01, rng.nextDouble() * 0.0001, 0.0)
            val someMass = rng.nextDouble() * 10

            val spacedPos = Vector3d((i % 8)/32.0, (i % 8)/32.0, 0.5)


            dotList.add(Dot(spacedPos, zeroVector, 5.0))
        }*/

        /*spaced dots


         */
        for(i in 1..16)
        {
            for(j in 1..16)
            {
                var spacedPos = Vector3d(i/32.0 + .26, j/32.0 + .25, 0.5)
                dotList.add(Dot(spacedPos, zeroVector, 0.5))
            }
        }


        extend {
            //gravity(drawer, bigBoi, dotList, width, height, mouse)
            //entropy(drawer, dotList, width, height)
            //vector(drawer, dotList, width, height)

            if (seconds < 2)
            {
                vector(drawer, dotList, width, height)
            }
            else if (seconds < 4)
            {
                entropy(drawer, dotList, width, height)
            }
            else if (seconds < 6)
            {
                vector(drawer, dotList, width, height)
            }
            else if (seconds < 8)
            {
                entropy(drawer, dotList, width, height)
            }
            else if (seconds < 10)
            {
                vector(drawer, dotList, width, height)
            }
            else if (seconds < 12)
            {
                entropy(drawer, dotList, width, height)
            }
            else if (seconds < 14)
            {
                vector(drawer, dotList, width, height)
            }
            else if (seconds < 16)
            {
                entropy(drawer, dotList, width, height)
            }
            else if (seconds < 18)
            {
                vector(drawer, dotList, width, height)
            }
            else if (seconds < 20)
            {
                entropy(drawer, dotList, width, height)
            }



        }
    }
}

fun vector(drawer: Drawer, dotList: MutableList<Dot>, width: Double, height: Double) {
    drawer.fill = ColorRGBa.WHITE
    drawer.strokeWeight = 1.0
    val vectorProvider = VectorFieldProvider()
    for(dot in dotList)
    {
        drawer.circle(dot.position.x * width, dot.position.y * height, dot.renderRadius())
        var acceleration = vectorProvider.someField(dot.position.x - 0.5, dot.position.y - 0.5)
        dot.updateVelocity(acceleration.divide(500.0))
        dot.stepPosition()
    }
}

fun entropy(drawer: Drawer, dotList: MutableList<Dot>, width: Double, height: Double)
{

    drawer.fill = ColorRGBa.WHITE
    drawer.strokeWeight = 1.0

    for(dot in dotList)
    {
        drawer.circle(dot.position.x * width, dot.position.y * height, dot.renderRadius())

        dot.stepPosition()
    }

}

fun gravity(drawer: Drawer, bigBoi: Dot, dotList: MutableList<Dot>, width: Double, height: Double, mouse: Mouse) {
    var grav = GravityCalc()
    drawer.fill = ColorRGBa.WHITE
    drawer.strokeWeight = 1.0

    bigBoi.position.x = mouse.position.x / width
    bigBoi.position.y = mouse.position.y / height

    drawer.circle(bigBoi.position.x * width, bigBoi.position.y * height, bigBoi.renderRadius())

    for(dot in dotList)
    {
        if(dot.position.z < 0.5)
        {
            drawer.strokeWeight = 0.0
        }
        else
        {
            drawer.strokeWeight = 1.0
        }
        drawer.circle(dot.position.x * width, dot.position.y * height, dot.renderRadius())

        var acceleration = grav.calcAcceleration(dot, bigBoi)
        acceleration = Vector3d(acceleration.x, acceleration.y, acceleration.z)
        dot.updateVelocity(acceleration)
        dot.stepPosition()
    }
}


/* Some weird cylinder thing
            for(i in 1..500)
            {
                drawer.fill = (ColorRGBa(cos((seconds + i)/100), sin((seconds - i/2)/150), -cos((seconds * i)/130)))
                drawer.circle(
                    (cos(seconds * .69 + i) * (width-10) / .4 + (width-10) / 2.0 * 20)* .1 - 1000,
                    ((height - 10) / .4 + (height - 10) / 2.0 * 20) * .07 + i*1.8 - 900,
                    kotlin.math.abs(5*cos(seconds/3 + i) + 5)
                )
            }
*/

/* Floating Grid
            for(dot in dotList)
            {
                drawer.fill = ColorRGBa(dot.getXPos(width), dot.getYPos(height), (dot.getXPos(width) + dot.getYPos(height)) % 256 )
                drawer.circle(dot.getXPos(width) + dot.xJitter + 20*sin(seconds - dot.getYPos(height)), dot.getYPos(height)+dot.yJitter + 10*cos((seconds - dot.getXPos(width))*4), dot.radius)
                dot.addJitter()
            }
 */