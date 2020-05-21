package utils

import model.News
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

var count = 0;

object FileUtils {
    fun writeLog(new: News) = try {
        count += 1;
        val tmpDir = File("loging.txt")

        val exists: Boolean = tmpDir.exists()

        val logText = "$count \t : ${new.author}: \t ${new.title} : ${new.url} \t ${new.createdAt} \n"
        if (exists) {
            println("is file existing")
            Files.write(Paths.get("loging.txt"), logText.toByteArray(), StandardOpenOption.APPEND);

        } else {
            val fileWriter = FileWriter("loging.txt")
            File("loging.txt");
            Files.write(Paths.get("loging.txt"), logText.toByteArray(), StandardOpenOption.APPEND);
        }
    } catch (ex: IOException) {
        System.out.println("Error writing to file ${ex.toString()}")
    }
}