package ru.mshamanin.pfrxml;

import ru.mshamanin.pfrxml.model.AbstractBaseEntity;
import ru.mshamanin.pfrxml.model.DataFile;

import java.util.List;

public class DataFileTestData {
    public static final TestMatcher<DataFile> DATA_FILE_MATCHER = TestMatcher.usingIgnoringFieldsComparator(DataFile.class, "body", "pack");

    public static final int DATA_FILE_NOT_FOUND = AbstractBaseEntity.START_SEQ + 12;
    public static final int DATA_FILE1_ID = AbstractBaseEntity.START_SEQ + 7;
    public static final int PACK2_ID = AbstractBaseEntity.START_SEQ + 4;

    public static final DataFile dataFile1 = new DataFile(DATA_FILE1_ID, "PFR", "700", "2020", "070-000-121212", "000", "00010-000", "OPPF", "6789", null, "BODY", PackTestData.pack1);
    public static final DataFile dataFile2 = new DataFile(DATA_FILE1_ID + 1, "PFR", "700", "2020", "070-000-121212", "000", "00010-001", "SPIS", "6789", null, "BODY", PackTestData.pack1);
    public static final DataFile dataFile3 = new DataFile(DATA_FILE1_ID + 2, "PFR", "700", "2020", "070-000-121212", "000", "00123-000", "OPPF", "6789", null, "BODY", PackTestData.pack2);
    public static final DataFile dataFile4 = new DataFile(DATA_FILE1_ID + 3, "PFR", "700", "2020", "070-000-121212", "000", "00123-001", "SPIS", "6789", null, "BODY", PackTestData.pack2);
    public static final List<DataFile> pack1DataFiles = List.of(dataFile1, dataFile2);
    public static final List<DataFile> userDataFiles = List.of(dataFile1, dataFile2, dataFile3, dataFile4);

    static {

    }

    public static DataFile getNewDataFile() {
        return new DataFile("PFR", "700", "2020", "070-000-121212", "000", "00123-002", "SPIS", "6789", null, "BODY");
    }

    public static DataFile getUpdated() {
        return new DataFile(DATA_FILE1_ID, "PFR", "700", "2020", "070-000-121212", "000", "00010-000", "OPPF", "6789", null, "UPDATED BODY", PackTestData.pack1);
    }
}


