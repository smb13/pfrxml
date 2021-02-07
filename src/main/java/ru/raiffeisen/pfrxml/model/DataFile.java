package ru.raiffeisen.pfrxml.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import ru.raiffeisen.pfrxml.HasId;

import javax.persistence.*;
import javax.validation.constraints.*;

@NamedQueries({
        @NamedQuery(name = DataFile.DELETE, query = "DELETE FROM DataFile d WHERE d.id=:id"),
        @NamedQuery(name = DataFile.DELETE_BY_PACK, query = "DELETE FROM DataFile d WHERE d.pack.id=:packId"),
        @NamedQuery(name = DataFile.ALL_BY_PACK, query = "SELECT d FROM DataFile d WHERE d.pack.id=:packId ORDER BY d.documentCode, d.packageNumber"),
        @NamedQuery(name = DataFile.GET_WITH_PACK, query = "SELECT d FROM DataFile d WHERE d.id=:id AND d.pack.id=:packId")
})

@Entity
@Table(name = "datafiles")
public class DataFile extends AbstractBaseEntity implements HasId {
    public static final String DELETE = "DataFile.delete";
    public static final String DELETE_BY_PACK = "DataFile.deleteByPack";
    public static final String ALL_BY_PACK = "DataFile.getAllByPack";
    public static final String GET_WITH_PACK = "DataFile.getWithPack";


    @Column(name = "type", nullable = false)
    @NotBlank
    @Size(min = 3, max = 3)
    private String type;

    @Column(name = "format_version", nullable = false)
    @NotBlank
    @Size(min = 3, max = 3)
    private String formatVersion;

    @Column(name = "year", nullable = false)
    @NotBlank
    @Size(min = 4, max = 4)
    private String year;

    @Column(name = "reg_num_to_pfr", nullable = false)
    @NotBlank
    @Size(min = 14, max = 14)
    private String regNumToPfr;

    @Column(name = "district_code", nullable = false)
    @NotBlank
    @Size(min = 3, max = 3)
    private String districtCode;

    @Column(name = "package_number", nullable = false)
    @NotBlank
    @Size(min = 9, max = 9)
    private String packageNumber;

    @Column(name = "document_code", nullable = false)
    @NotBlank
    @Size(min = 4, max = 4)
    private String documentCode;

    @Column(name = "branch_number", nullable = false)
    @NotBlank
    @Size(min = 4, max = 4)
    private String branchNumber;

    @Column(name = "out_numb", nullable = true)
    @Size(min = 10, max = 10)
    private String outNumb;

    @Column(name = "body", nullable = false)
    @NotBlank
    private String body;

//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pack_id", nullable = false)
    @JsonBackReference
    @NotNull
    private Pack pack;

    public DataFile() {
    }

    public DataFile(Integer id, @NotBlank @Size(min = 3, max = 3) String type, @NotBlank @Size(min = 3, max = 3) String formatVersion, @NotBlank @Size(min = 4, max = 4) String year, @NotBlank @Size(min = 12, max = 12) String regNumToPfr, @NotBlank @Size(min = 3, max = 3) String districtCode, @NotBlank @Size(min = 8, max = 8) String packageNumber, @NotBlank @Size(min = 4, max = 4) String documentCode, @NotBlank @Size(min = 4, max = 4) String branchNumber, @Size(min = 10, max = 10) String outNumb, @NotBlank String body, Pack pack) {
        super(id);
        this.type = type;
        this.formatVersion = formatVersion;
        this.year = year;
        this.regNumToPfr = regNumToPfr;
        this.districtCode = districtCode;
        this.packageNumber = packageNumber;
        this.documentCode = documentCode;
        this.branchNumber = branchNumber;
        this.outNumb = outNumb;
        this.body = body;
        this.pack = pack;
    }

    public DataFile(@NotBlank @Size(min = 3, max = 3) String type, @NotBlank @Size(min = 3, max = 3) String formatVersion, @NotBlank @Size(min = 4, max = 4) String year, @NotBlank @Size(min = 12, max = 12) String regNumToPfr, @NotBlank @Size(min = 3, max = 3) String districtCode, @NotBlank @Size(min = 8, max = 8) String packageNumber, @NotBlank @Size(min = 4, max = 4) String documentCode, @NotBlank @Size(min = 4, max = 4) String branchNumber, @Size(min = 10, max = 10) String outNumb, @NotBlank String body) {
        super();
        this.type = type;
        this.formatVersion = formatVersion;
        this.year = year;
        this.regNumToPfr = regNumToPfr;
        this.districtCode = districtCode;
        this.packageNumber = packageNumber;
        this.documentCode = documentCode;
        this.branchNumber = branchNumber;
        this.outNumb = outNumb;
        this.body = body;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFormatVersion() {
        return formatVersion;
    }

    public void setFormatVersion(String formatVersion) {
        this.formatVersion = formatVersion;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRegNumToPfr() {
        return regNumToPfr;
    }

    public void setRegNumToPfr(String regNumToPfr) {
        this.regNumToPfr = regNumToPfr;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getPackageNumber() {
        return packageNumber;
    }

    public void setPackageNumber(String packageNumber) {
        this.packageNumber = packageNumber;
    }

    public String getDocumentCode() {
        return documentCode;
    }

    public void setDocumentCode(String documentCode) {
        this.documentCode = documentCode;
    }

    public String getBranchNumber() {
        return branchNumber;
    }

    public void setBranchNumber(String branchNumber) {
        this.branchNumber = branchNumber;
    }

    public String getOutNumb() {
        return outNumb;
    }

    public void setOutNumb(String outNumb) {
        this.outNumb = outNumb;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Pack getPack() {
        return pack;
    }

    public void setPack(Pack pack) {
        this.pack = pack;
    }
}
