package ru.raiffeisen.pfrxml.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import ru.raiffeisen.pfrxml.HasId;

import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@Table(name = "files")
public class File extends AbstractBaseEntity implements HasId {

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
    @Size(min = 12, max = 12)
    private String regNumToPfr;

    @Column(name = "district_code", nullable = false)
    @NotBlank
    @Size(min = 3, max = 3)
    private String districtCode;

    @Column(name = "package_number", nullable = false)
    @NotBlank
    @Size(min = 8, max = 8)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "package_id", nullable = false)
    @JsonBackReference
    private Pack pack;

    public File(Integer id) {
        super(id);
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

}
