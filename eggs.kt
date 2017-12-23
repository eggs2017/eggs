/**
 https://try.kotlinlang.org/
 */

val bestNextStep: (Int) -> Int = memorise { floorsLeft ->
  if (floorsLeft <= 2) 1
  else (1..floorsLeft)
        .toList()
        .minBy { maxThrows(floorsLeft, it) }!!
}

fun maxThrows(floorsLeft: Int, nextFloor: Int): Int =
  if (floorsLeft <= 2) floorsLeft
  else maxOf(nextFloor, bestMaxThrows(floorsLeft - nextFloor) + 1)


val bestMaxThrows: (Int) -> Int = memorise { floorsLeft ->
  maxThrows(floorsLeft, bestNextStep(floorsLeft))
}

fun <V, T> memorise(f: (V) -> T): (V) -> T {
    val map = mutableMapOf<V, T>()
    return { map.getOrPut(it) { f(it) } }
}
fun main(args: Array<String>) {
    println("Hello, world!")
    
    var floorMax = 100
    println("steps "+  bestMaxThrows(floorMax) ) // Prints: 14
    
    var floor = 0
    
    while (floor < floorMax) {
        val floorsLeft = floorMax - floor
        val nextStep = bestNextStep(floorsLeft)
        floor += nextStep
        print("$floor, ")
    }
}