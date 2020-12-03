import java.io.File

object Utils

fun input(path: String) =
    Utils::class.java.classLoader.getResource(path)?.file?.let {
        File(it).readLines()
    } ?: error("$path does not exist")
