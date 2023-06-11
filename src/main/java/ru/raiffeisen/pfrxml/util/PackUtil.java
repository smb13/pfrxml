package ru.raiffeisen.pfrxml.util;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.arj.ArjArchiveEntry;
import org.apache.commons.compress.archivers.arj.ArjArchiveInputStream;
import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.raiffeisen.pfrxml.model.DataFile;
import ru.raiffeisen.pfrxml.to.DataFileTo;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class PackUtil {
    private static final Logger log = LoggerFactory.getLogger(PackUtil.class);
//    private static final int BUFFER = 1024;

//    add logs, make return optional
    public static List<DataFileTo> decompressPack(File packFile) throws IOException {
        List<DataFileTo> dataFileTos = new ArrayList<>();
        try (ArjArchiveInputStream aais = new ArjArchiveInputStream(
                new CompressorStreamFactory().createCompressorInputStream(
                        new BufferedInputStream(Files.newInputStream(packFile.toPath()))))) {
            ArjArchiveEntry entry;
            while ((entry = aais.getNextEntry()) != null) {
                if (entry.isDirectory()) {
                    log.warn("dir:{}", entry.getName());
                } else {
                    int size = (int) entry.getSize();
                    byte[] content = new byte[size];
                    int readCount = aais.read(content, 0, size);
                    log.info("fileName:{}", entry.getName());
                    ByteArrayInputStream bais = new ByteArrayInputStream(content, 0, readCount);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    IOUtils.copy(bais, baos);
                    bais.close();
                    baos.flush();
                    baos.close();
                    String body = baos.toString("utf-8");
                    String fileName = entry.getName();
                    dataFileTos.add(new DataFileTo(fileName, body));
                }
            }
        } catch (CompressorException e) {
            log.debug(e.toString());
            throw new IOException(e);
        } catch (ArchiveException e) {
            log.debug(e.toString());
            throw new IOException(e);
        }
        return dataFileTos;
    }



}
