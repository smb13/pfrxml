package ru.mshamanin.pfrxml.util;

import ru.mshamanin.pfrxml.model.DataFile;
import ru.mshamanin.pfrxml.model.Pack;
import ru.mshamanin.pfrxml.to.DataFileTo;

public class DataFileUtil {
    private static final String REGEX_PFR = "\\^PFR-\\d{3}-Y-\\d{4}-ORG-\\d{3}-\\d{3}-\\d{6}-DIS-\\d{3}-DCK-\\d{5}-\\d{3}-DOC-[a-zA-Z]{4}-FSB-\\d{4}.XML$";
    private static final String REGEX_OUT = "\\^OUT-\\d{3}-Y-\\d{4}-ORG-\\d{3}-\\d{3}-\\d{6}-DIS-\\d{3}-DCK-\\d{5}-\\d{3}-DOC-[a-zA-Z]{4}-FSB-\\d{4}-OUTNMB-\\d{10}.XML$";


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

}
