package ru.mshamanin.pfrxml.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mshamanin.pfrxml.to.DataFileTo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class PackUtil {
    private static final Logger log = LoggerFactory.getLogger(PackUtil.class);

    //TODO  add more logs, make return DataFileTo
    public static List<DataFileTo> unzipPack(File packFile) throws IOException {
        List<DataFileTo> dataFileTos = new ArrayList<>();
        try (ZipFile file = new ZipFile(packFile.getCanonicalPath())) {
            var entries = file.entries();
            String unzipPath = new File(file.getName()).getParent() + File.separator + packFile.getName().split("\\.")[0] + File.separator;
            File unzipFolder = new File(unzipPath);
            if (!unzipFolder.exists()) {
                unzipFolder.mkdirs();
            }
            while (entries.hasMoreElements()) {
                var entry = entries.nextElement();
                if (entry.isDirectory()) {
                    processDirectory(unzipPath, entry);
                } else {
                    processFile(file, unzipPath, entry);
                }
            }
        }
        return dataFileTos;
    }

    private static void processDirectory(String unzipPath, ZipEntry entry) {
        var newDirectory = unzipPath + entry.getName();
        log.info("Creating Directory: {}", newDirectory);
        var directory = new File(newDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    private static void processFile(ZipFile file, String unzipPath, ZipEntry entry) throws IOException {
        try (
                InputStream is = file.getInputStream(entry);
                BufferedInputStream bis = new BufferedInputStream(is)
        ) {
            var uncompressedFileName = unzipPath + entry.getName();
            try (
                    FileOutputStream os = new FileOutputStream(uncompressedFileName);
                    BufferedOutputStream bos = new BufferedOutputStream(os)
            ) {
                while (bis.available() > 0) {
                    bos.write(bis.read());
                }
            }
        }
        log.info("Written: {}", entry.getName());
    }
}
