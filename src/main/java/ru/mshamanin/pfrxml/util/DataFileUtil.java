package ru.mshamanin.pfrxml.util;

import lombok.extern.slf4j.Slf4j;
import ru.mshamanin.pfrxml.model.DataFile;
import ru.mshamanin.pfrxml.model.Pack;
import ru.mshamanin.pfrxml.to.DataFileTo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class DataFileUtil {
    public static final Charset fileEncoding = Charset.forName("Windows-1251");
    public static final Charset storageEncoding = StandardCharsets.UTF_8;
    private static final String REGEX_PFR = "\\^PFR-\\d{3}-Y-\\d{4}-ORG-\\d{3}-\\d{3}-\\d{6}-DIS-\\d{3}-DCK-\\d{5}-\\d{3}-DOC-[a-zA-Z]{4}-FSB-\\d{4}.XML$";
    private static final String REGEX_OUT = "\\^OUT-\\d{3}-Y-\\d{4}-ORG-\\d{3}-\\d{3}-\\d{6}-DIS-\\d{3}-DCK-\\d{5}-\\d{3}-DOC-[a-zA-Z]{4}-FSB-\\d{4}-OUTNMB-\\d{10}.XML$";
    private static final String NAME_PARTS_SPLITTER = "-";

    public static DataFileTo createTo(DataFile dateFile) {
        String fileName = dateFile.getType() + "-" + dateFile.getFormatVersion()
                + "Y-" + dateFile.getYear()
                + "ORG-" + dateFile.getRegNumToPfr()
                + "DIS-" + dateFile.getDistrictCode()
                + "DCK-" + dateFile.getPackageNumber()
                + "DOC-" + dateFile.getDocumentCode()
                + "FSB-" + dateFile.getBranchNumber();
        fileName = (dateFile.getOutNumb() == null) ? (fileName + ".XML") : ("-OUTNMB-" + dateFile.getOutNumb() + ".XML");
        return new DataFileTo(dateFile.getId(), fileName, dateFile.getBody());
    }

    public static DataFile createFromToPfr(DataFileTo dateFileTo, Pack pack) {
        String fileName;


        if (dateFileTo.getFileName().toUpperCase().matches(REGEX_PFR)) {


        } else if (dateFileTo.getFileName().toUpperCase().matches(REGEX_OUT)) {

        } else {
            return null;
        }
        ;
        return null;
    }

    static DataFile createDataFileFromFile(@NotEmpty String filePath) throws IOException {
        //TODO add datafile name validation
        String body = "";
        File file = new File(filePath);
        try (
                ByteArrayOutputStream byteArrayOutStr = new ByteArrayOutputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), fileEncoding));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(byteArrayOutStr, storageEncoding)); ) {
            char[] buffer = new char[16384];
            int read;
            while ((read = br.read(buffer)) != -1) {
                bw.write(buffer, 0, read);
            }
            bw.flush();
            body = byteArrayOutStr.toString();
        } catch (Exception e) {
            log.warn(Arrays.toString(e.getStackTrace()));
            return null;
        }
        String [] parts = file.getName().split(NAME_PARTS_SPLITTER);
        return new DataFile(
                parts[0],
                parts[1],
                parts[3],
                String.join(NAME_PARTS_SPLITTER, parts[5], parts[6], parts[7]),
                parts[9],
                String.join(NAME_PARTS_SPLITTER, parts[11], parts[12]),
                parts[14],
                parts[16].split("\\.")[0],
                null,
                body);
    }
}
