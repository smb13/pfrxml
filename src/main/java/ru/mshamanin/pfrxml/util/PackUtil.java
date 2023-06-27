package ru.mshamanin.pfrxml.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.FileSystemUtils;
import ru.mshamanin.pfrxml.model.DataFile;
import ru.mshamanin.pfrxml.model.Pack;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@Slf4j
public class PackUtil {

    public static Pack unzipPack(File packFile) throws IOException {
        List<DataFile> dataFiles = new ArrayList<>();
        try (ZipFile file = new ZipFile(packFile.getCanonicalPath())) {
            var entries = file.entries();
            String unzipPath = new File(file.getName()).getParent() + File.separator + packFile.getName().split("\\.")[0] + File.separator;
            File unzipFolder = new File(unzipPath);
            if (unzipFolder.exists() && FileSystemUtils.deleteRecursively(unzipFolder)) {
                log.info("folder {} has been deleted", unzipFolder);
            }
            if (unzipFolder.mkdirs()) {
                log.info("folder {} has been created", unzipFolder);
            } else {
                log.warn("folder {} hasn't been created", unzipFolder);
            };

            while (entries.hasMoreElements()) {
                var entry = entries.nextElement();
                if (entry.isDirectory()) {
                    processDirectory(unzipPath, entry);
                } else {
                    processFile(file, unzipPath, entry);
                    dataFiles.add(DataFileUtil.createDataFileFromFile(unzipPath + entry.getName()));
                }
            }
        }
        return new Pack(packFile.getName(), dataFiles);
    }


    private static void processDirectory(String unzipPath, ZipEntry entry) {
        var newDirectory = unzipPath + entry.getName();
        log.info("creating directory: {}", newDirectory);
        var directory = new File(newDirectory);
        if (!directory.exists() && directory.mkdirs()) {
            log.info("folder {} has been created", directory);
        }
    }

    private static File processFile(ZipFile file, String unzipPath, ZipEntry entry) throws IOException {
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
        log.info("unzipped: {}", unzipPath + entry.getName());
        return new File(unzipPath + entry.getName());
    }
}
