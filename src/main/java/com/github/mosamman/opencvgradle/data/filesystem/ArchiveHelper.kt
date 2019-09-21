package com.github.mosamman.opencvgradle.data.filesystem

import org.apache.commons.compress.archivers.ArchiveEntry
import org.apache.commons.compress.archivers.ArchiveInputStream
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream
import org.apache.commons.compress.utils.IOUtils

import java.io.*
import java.nio.file.Files
import java.io.BufferedInputStream


object ArchiveHelper {

    @Throws(IOException::class)
    fun decompress(input: String, output: File) {


        try {

            Files.newInputStream(File(input).toPath()).use { fi ->
                BufferedInputStream(fi).use { bi ->
                    GzipCompressorInputStream(bi).use { gzi ->
                        TarArchiveInputStream(gzi).use { i ->
                            read(i)
                        }
                    }
                }
            }


        } catch (exc: Exception) {

        }

    }

    @Throws(IOException::class)
    private fun read(stream: ArchiveInputStream) {
        var entry: ArchiveEntry? = stream.nextEntry
        while (entry != null) {
            if (!stream.canReadEntryData(entry)) {
                // log something?
                continue
            }
            val name = ""//fileName(targetDir, entry);
            val f = File(name)
            if (entry.isDirectory) {
                if (!f.isDirectory && !f.mkdirs()) {
                    throw IOException("failed to create directory $f")
                }
            } else {
                val parent = f.parentFile
                if (!parent.isDirectory && !parent.mkdirs()) {
                    throw IOException("failed to create directory $parent")
                }
                Files.newOutputStream(f.toPath()).use { o -> IOUtils.copy(stream, o) }
            }
            entry = stream.nextEntry
        }
    }

}