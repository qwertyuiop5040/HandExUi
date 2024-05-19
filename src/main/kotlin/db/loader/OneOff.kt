package db.loader

import java.io.File
import java.io.InputStream

fun main() {
    val classloader = Thread.currentThread().contextClassLoader
    val stream: InputStream = classloader.getResourceAsStream("probabilities-online.txt")
    ProbConverter.serialize(ProbConverter.convertFromOnline(stream), File("/home/henry/Downloads/probabilities.txt"))
}