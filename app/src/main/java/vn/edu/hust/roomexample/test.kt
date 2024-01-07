package vn.edu.hust.roomexample

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random
import kotlin.time.measureTime

fun main() {
    runBlocking {
        val time = measureTime {
            val value1 = doWork(3000L)
            val value2 = doWork(2000L)
            println("Sum: ${value1 + value2}")
        }
        println("Time: ${time.inWholeMilliseconds}")
    }
}

suspend fun doWork(d: Long): Int {
    delay(d)
    return Random.nextInt(10, 100)
}