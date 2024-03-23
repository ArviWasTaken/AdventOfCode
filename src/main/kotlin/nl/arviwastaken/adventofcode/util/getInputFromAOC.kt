package nl.arviwastaken.adventofcode.util

import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils
import org.jsoup.Jsoup
import java.net.URI
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.*
import kotlin.system.exitProcess

const val puzzleInputFolder = "puzzleInputs"
const val sessionTokenFile = "sessionToken.txt"
const val exampleFileName = "example.txt"
const val inputFileName = "input.txt"
const val adventOfCodeUrl = "https://adventofcode.com"
var sessionToken = ""

fun retrieveInput(): MutableList<String> {
    val fullPackage = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).callerClass.name
    val (packageName, dayName) = getPackageAndName(fullPackage)
    val pathToInput = Path(puzzleInputFolder, packageName, dayName)
    val dayURI = URI.create("$adventOfCodeUrl/${packageName.getDigits()}/day/${dayName.getDigits()}")

    val file = Path(pathToInput.toString(), inputFileName)
    if (file.notExists())
        getInput(pathToInput, dayURI)

    return Files.readAllLines(file)
}

fun retrieveExample(): MutableList<String> {
    val fullPackage = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).callerClass.name
    val (packageName, dayName) = getPackageAndName(fullPackage)
    val pathToInput = Path(puzzleInputFolder, packageName, dayName)
    val dayURI = URI.create("$adventOfCodeUrl/${packageName.getDigits()}/day/${dayName.getDigits()}")

    val file = Path(pathToInput.toString(), exampleFileName)
    if (file.notExists())
        getExample(pathToInput, dayURI)

    return Files.readAllLines(file)
}

private fun getPackageAndName(fullPackage: String): Pair<String, String> {
    val split = fullPackage.split(".").toMutableList()
    val name = split.removeLast()!!.removeSuffix("Kt")
    val packageName = split.removeLast()!!
    return Pair(packageName, name)
}

private fun getInput(pathToInputFolder: Path, url: URI) {
    createFileIfDoesNotExist(pathToInputFolder, inputFileName)
    val response = getFromUrl(URI.create("$url/input"))

    Path(pathToInputFolder.toString(), inputFileName).writeText(response)
}

private fun getExample(pathToInputFolder: Path, url: URI) {
    createFileIfDoesNotExist(pathToInputFolder, exampleFileName)
    val response = getFromUrl(url)
    val body = Jsoup.parse(response).body()
    val example = body.select("pre").first()!!.child(0).text()

    Path(pathToInputFolder.toString(), exampleFileName).writeText(example)
}

private fun createFileIfDoesNotExist(pathToFile: Path, fileName: String) {
    val fullPath = pathToFile.resolve(fileName)
    if (Files.notExists(fullPath)) {
        pathToFile.createDirectories()
        fullPath.createFile()
    }
}

private fun getFromUrl(url: URI): String {
    val request = HttpGet("$url")
    if (sessionToken.isEmpty()) getSessionToken()
    request.addHeader("cookie", "session=$sessionToken")

    val entity = HttpClients.createDefault().execute(request).entity

    return EntityUtils.toString(entity)
}

private fun getSessionToken() {
    createFileIfDoesNotExist(Path(puzzleInputFolder), sessionTokenFile)
    val tmpSesToken = Path(puzzleInputFolder, sessionTokenFile).readLines()
    if (tmpSesToken.isEmpty()) printErrorAndExit("ERROR: ${Path(puzzleInputFolder, sessionTokenFile).toAbsolutePath()} is empty. Please put a valid session token")
    sessionToken = tmpSesToken.first()
}

fun printErrorAndExit(message: String) {
    printErrorLn(message)
    exitProcess(-1)
}
fun printErrorLn(message: String) = System.err.println(message)