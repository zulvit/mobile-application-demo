package ru.zulvit.utils

import android.content.Context
import android.os.Environment
import android.widget.Toast
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun exportCharactersToFile(context: Context, characters: List<String>, fileName: String) {
    val directory = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)

    if (directory?.exists() != true) {
        directory?.mkdirs()
    }

    val file = File(directory, "$fileName.txt")
    file.writeText(characters.joinToString("\n"))

    Toast.makeText(context, "File exported: ${file.absolutePath}", Toast.LENGTH_SHORT).show()
}

fun checkIfFileExists(context: Context, fileName: String): Boolean {
    val directory = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
    val file = File(directory, "$fileName.txt")
    return file.exists()
}

fun deleteFile(context: Context, fileName: String): Boolean {
    val directory = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
    val file = File(directory, "$fileName.txt")
    return if (file.exists()) {
        file.delete()
    } else {
        false
    }
}

fun saveFileToInternalStorage(context: Context, fileName: String, content: String) {
    try {
        val file = File(context.filesDir, "$fileName.txt")
        val outputStream = FileOutputStream(file)
        val writer = OutputStreamWriter(outputStream)
        writer.write(content)
        writer.close()
    } catch (e: IOException) {
        Toast.makeText(context, "Error saving file to internal storage", Toast.LENGTH_SHORT).show()
    }
}

fun restoreFile(context: Context, fileName: String): Boolean {
    val internalFile = File(context.filesDir, "$fileName.txt")
    if (internalFile.exists()) {
        // Чтение содержимого из внутреннего хранилища
        val inputStream = FileInputStream(internalFile)
        val content = InputStreamReader(inputStream).readText()
        inputStream.close()

        // Сохраняем файл обратно в Documents
        val externalDirectory = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        val externalFile = File(externalDirectory, "$fileName.txt")
        val outputStream = FileOutputStream(externalFile)
        val writer = OutputStreamWriter(outputStream)
        writer.write(content)
        writer.close()
        return true
    } else {
        return false
    }
}
