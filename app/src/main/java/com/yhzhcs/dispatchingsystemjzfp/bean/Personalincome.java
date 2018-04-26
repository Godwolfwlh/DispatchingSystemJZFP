package com.yhzhcs.dispatchingsystemjzfp.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Auto-generated: 2018-02-08 11:47:22
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class Personalincome implements Parcelable {

    private int agMachineryMoney;
    private int agricultureMoney;
    private int animalMoney;
    private int architectureMoney;
    private String averageIncome;
    private String cardNumber;
    private int comprehensiveMoney;
    private String ecological;
    private int educationMoney;
    private int fisheriesMoney;
    private int forestryMoney;
    private int fpjThdMoney;
    private String id;
    private int interestDeposit;
    private int jsjJsbzMoney;
    private int landExpropMoney;
    private int landRentMoney;
    private String lowGold;
    private int mzjCdbMoney;
    private int mzjFlybtMoney;
    private int mzjGlbtMoney;
    private int mzjNdbMoney;
    private int mzjSjbtMoney;
    private int mzjWbMoney;
    private String netIncome;
    private String otherTransfer;
    private String poorHouseId;
    private int povertyCondolence;
    private int povertyEducation;
    private int povertyMoney;
    private int povertyOther;
    private String productbility;
    private String production;
    private int productionOther;
    private int productionThreeMoney;
    private String property;
    private int propertyOther;
    private String salary;
    private int socialAssMoney;
    private String transfer;
    private int wfgzMoney;
    private String year;
    private String yearIncome;
    private int ylbxMoney;

    protected Personalincome(Parcel in) {
        agMachineryMoney = in.readInt();
        agricultureMoney = in.readInt();
        animalMoney = in.readInt();
        architectureMoney = in.readInt();
        averageIncome = in.readString();
        cardNumber = in.readString();
        comprehensiveMoney = in.readInt();
        ecological = in.readString();
        educationMoney = in.readInt();
        fisheriesMoney = in.readInt();
        forestryMoney = in.readInt();
        fpjThdMoney = in.readInt();
        id = in.readString();
        interestDeposit = in.readInt();
        jsjJsbzMoney = in.readInt();
        landExpropMoney = in.readInt();
        landRentMoney = in.readInt();
        lowGold = in.readString();
        mzjCdbMoney = in.readInt();
        mzjFlybtMoney = in.readInt();
        mzjGlbtMoney = in.readInt();
        mzjNdbMoney = in.readInt();
        mzjSjbtMoney = in.readInt();
        mzjWbMoney = in.readInt();
        netIncome = in.readString();
        otherTransfer = in.readString();
        poorHouseId = in.readString();
        povertyCondolence = in.readInt();
        povertyEducation = in.readInt();
        povertyMoney = in.readInt();
        povertyOther = in.readInt();
        productbility = in.readString();
        production = in.readString();
        productionOther = in.readInt();
        productionThreeMoney = in.readInt();
        property = in.readString();
        propertyOther = in.readInt();
        salary = in.readString();
        socialAssMoney = in.readInt();
        transfer = in.readString();
        wfgzMoney = in.readInt();
        year = in.readString();
        yearIncome = in.readString();
        ylbxMoney = in.readInt();
    }

    public static final Creator<Personalincome> CREATOR = new Creator<Personalincome>() {
        @Override
        public Personalincome createFromParcel(Parcel in) {
            return new Personalincome(in);
        }

        @Override
        public Personalincome[] newArray(int size) {
            return new Personalincome[size];
        }
    };

    public int getAgMachineryMoney() {
        return agMachineryMoney;
    }

    public int getAgricultureMoney() {
        return agricultureMoney;
    }

    public int getAnimalMoney() {
        return animalMoney;
    }

    public int getArchitectureMoney() {
        return architectureMoney;
    }

    public String getAverageIncome() {
        return averageIncome;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public int getComprehensiveMoney() {
        return comprehensiveMoney;
    }

    public String getEcological() {
        return ecological;
    }

    public int getEducationMoney() {
        return educationMoney;
    }

    public int getFisheriesMoney() {
        return fisheriesMoney;
    }

    public int getForestryMoney() {
        return forestryMoney;
    }

    public int getFpjThdMoney() {
        return fpjThdMoney;
    }

    public String getId() {
        return id;
    }

    public int getInterestDeposit() {
        return interestDeposit;
    }

    public int getJsjJsbzMoney() {
        return jsjJsbzMoney;
    }

    public int getLandExpropMoney() {
        return landExpropMoney;
    }

    public int getLandRentMoney() {
        return landRentMoney;
    }

    public String getLowGold() {
        return lowGold;
    }

    public int getMzjCdbMoney() {
        return mzjCdbMoney;
    }

    public int getMzjFlybtMoney() {
        return mzjFlybtMoney;
    }

    public int getMzjGlbtMoney() {
        return mzjGlbtMoney;
    }

    public int getMzjNdbMoney() {
        return mzjNdbMoney;
    }

    public int getMzjSjbtMoney() {
        return mzjSjbtMoney;
    }

    public int getMzjWbMoney() {
        return mzjWbMoney;
    }

    public String getNetIncome() {
        return netIncome;
    }

    public String getOtherTransfer() {
        return otherTransfer;
    }

    public String getPoorHouseId() {
        return poorHouseId;
    }

    public int getPovertyCondolence() {
        return povertyCondolence;
    }

    public int getPovertyEducation() {
        return povertyEducation;
    }

    public int getPovertyMoney() {
        return povertyMoney;
    }

    public int getPovertyOther() {
        return povertyOther;
    }

    public String getProductbility() {
        return productbility;
    }

    public String getProduction() {
        return production;
    }

    public int getProductionOther() {
        return productionOther;
    }

    public int getProductionThreeMoney() {
        return productionThreeMoney;
    }

    public String getProperty() {
        return property;
    }

    public int getPropertyOther() {
        return propertyOther;
    }

    public String getSalary() {
        return salary;
    }

    public int getSocialAssMoney() {
        return socialAssMoney;
    }

    public String getTransfer() {
        return transfer;
    }

    public int getWfgzMoney() {
        return wfgzMoney;
    }

    public String getYear() {
        return year;
    }

    public String getYearIncome() {
        return yearIncome;
    }

    public int getYlbxMoney() {
        return ylbxMoney;
    }

    public void setAgMachineryMoney(int agMachineryMoney) {
        this.agMachineryMoney = agMachineryMoney;
    }

    public void setAgricultureMoney(int agricultureMoney) {
        this.agricultureMoney = agricultureMoney;
    }

    public void setAnimalMoney(int animalMoney) {
        this.animalMoney = animalMoney;
    }

    public void setArchitectureMoney(int architectureMoney) {
        this.architectureMoney = architectureMoney;
    }

    public void setAverageIncome(String averageIncome) {
        this.averageIncome = averageIncome;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setComprehensiveMoney(int comprehensiveMoney) {
        this.comprehensiveMoney = comprehensiveMoney;
    }

    public void setEcological(String ecological) {
        this.ecological = ecological;
    }

    public void setEducationMoney(int educationMoney) {
        this.educationMoney = educationMoney;
    }

    public void setFisheriesMoney(int fisheriesMoney) {
        this.fisheriesMoney = fisheriesMoney;
    }

    public void setForestryMoney(int forestryMoney) {
        this.forestryMoney = forestryMoney;
    }

    public void setFpjThdMoney(int fpjThdMoney) {
        this.fpjThdMoney = fpjThdMoney;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setInterestDeposit(int interestDeposit) {
        this.interestDeposit = interestDeposit;
    }

    public void setJsjJsbzMoney(int jsjJsbzMoney) {
        this.jsjJsbzMoney = jsjJsbzMoney;
    }

    public void setLandExpropMoney(int landExpropMoney) {
        this.landExpropMoney = landExpropMoney;
    }

    public void setLandRentMoney(int landRentMoney) {
        this.landRentMoney = landRentMoney;
    }

    public void setLowGold(String lowGold) {
        this.lowGold = lowGold;
    }

    public void setMzjCdbMoney(int mzjCdbMoney) {
        this.mzjCdbMoney = mzjCdbMoney;
    }

    public void setMzjFlybtMoney(int mzjFlybtMoney) {
        this.mzjFlybtMoney = mzjFlybtMoney;
    }

    public void setMzjGlbtMoney(int mzjGlbtMoney) {
        this.mzjGlbtMoney = mzjGlbtMoney;
    }

    public void setMzjNdbMoney(int mzjNdbMoney) {
        this.mzjNdbMoney = mzjNdbMoney;
    }

    public void setMzjSjbtMoney(int mzjSjbtMoney) {
        this.mzjSjbtMoney = mzjSjbtMoney;
    }

    public void setMzjWbMoney(int mzjWbMoney) {
        this.mzjWbMoney = mzjWbMoney;
    }

    public void setNetIncome(String netIncome) {
        this.netIncome = netIncome;
    }

    public void setOtherTransfer(String otherTransfer) {
        this.otherTransfer = otherTransfer;
    }

    public void setPoorHouseId(String poorHouseId) {
        this.poorHouseId = poorHouseId;
    }

    public void setPovertyCondolence(int povertyCondolence) {
        this.povertyCondolence = povertyCondolence;
    }

    public void setPovertyEducation(int povertyEducation) {
        this.povertyEducation = povertyEducation;
    }

    public void setPovertyMoney(int povertyMoney) {
        this.povertyMoney = povertyMoney;
    }

    public void setPovertyOther(int povertyOther) {
        this.povertyOther = povertyOther;
    }

    public void setProductbility(String productbility) {
        this.productbility = productbility;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public void setProductionOther(int productionOther) {
        this.productionOther = productionOther;
    }

    public void setProductionThreeMoney(int productionThreeMoney) {
        this.productionThreeMoney = productionThreeMoney;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public void setPropertyOther(int propertyOther) {
        this.propertyOther = propertyOther;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setSocialAssMoney(int socialAssMoney) {
        this.socialAssMoney = socialAssMoney;
    }

    public void setTransfer(String transfer) {
        this.transfer = transfer;
    }

    public void setWfgzMoney(int wfgzMoney) {
        this.wfgzMoney = wfgzMoney;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setYearIncome(String yearIncome) {
        this.yearIncome = yearIncome;
    }

    public void setYlbxMoney(int ylbxMoney) {
        this.ylbxMoney = ylbxMoney;
    }

    @Override
    public String toString() {
        return "Personalincome{" +
                "agMachineryMoney=" + agMachineryMoney +
                ", agricultureMoney=" + agricultureMoney +
                ", animalMoney=" + animalMoney +
                ", architectureMoney=" + architectureMoney +
                ", averageIncome='" + averageIncome + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", comprehensiveMoney=" + comprehensiveMoney +
                ", ecological='" + ecological + '\'' +
                ", educationMoney=" + educationMoney +
                ", fisheriesMoney=" + fisheriesMoney +
                ", forestryMoney=" + forestryMoney +
                ", fpjThdMoney=" + fpjThdMoney +
                ", id='" + id + '\'' +
                ", interestDeposit=" + interestDeposit +
                ", jsjJsbzMoney=" + jsjJsbzMoney +
                ", landExpropMoney=" + landExpropMoney +
                ", landRentMoney=" + landRentMoney +
                ", lowGold='" + lowGold + '\'' +
                ", mzjCdbMoney=" + mzjCdbMoney +
                ", mzjFlybtMoney=" + mzjFlybtMoney +
                ", mzjGlbtMoney=" + mzjGlbtMoney +
                ", mzjNdbMoney=" + mzjNdbMoney +
                ", mzjSjbtMoney=" + mzjSjbtMoney +
                ", mzjWbMoney=" + mzjWbMoney +
                ", netIncome='" + netIncome + '\'' +
                ", otherTransfer='" + otherTransfer + '\'' +
                ", poorHouseId='" + poorHouseId + '\'' +
                ", povertyCondolence=" + povertyCondolence +
                ", povertyEducation=" + povertyEducation +
                ", povertyMoney=" + povertyMoney +
                ", povertyOther=" + povertyOther +
                ", productbility='" + productbility + '\'' +
                ", production='" + production + '\'' +
                ", productionOther=" + productionOther +
                ", productionThreeMoney=" + productionThreeMoney +
                ", property='" + property + '\'' +
                ", propertyOther=" + propertyOther +
                ", salary='" + salary + '\'' +
                ", socialAssMoney=" + socialAssMoney +
                ", transfer='" + transfer + '\'' +
                ", wfgzMoney=" + wfgzMoney +
                ", year='" + year + '\'' +
                ", yearIncome='" + yearIncome + '\'' +
                ", ylbxMoney=" + ylbxMoney +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(agMachineryMoney);
        parcel.writeInt(agricultureMoney);
        parcel.writeInt(animalMoney);
        parcel.writeInt(architectureMoney);
        parcel.writeString(averageIncome);
        parcel.writeString(cardNumber);
        parcel.writeInt(comprehensiveMoney);
        parcel.writeString(ecological);
        parcel.writeInt(educationMoney);
        parcel.writeInt(fisheriesMoney);
        parcel.writeInt(forestryMoney);
        parcel.writeInt(fpjThdMoney);
        parcel.writeString(id);
        parcel.writeInt(interestDeposit);
        parcel.writeInt(jsjJsbzMoney);
        parcel.writeInt(landExpropMoney);
        parcel.writeInt(landRentMoney);
        parcel.writeString(lowGold);
        parcel.writeInt(mzjCdbMoney);
        parcel.writeInt(mzjFlybtMoney);
        parcel.writeInt(mzjGlbtMoney);
        parcel.writeInt(mzjNdbMoney);
        parcel.writeInt(mzjSjbtMoney);
        parcel.writeInt(mzjWbMoney);
        parcel.writeString(netIncome);
        parcel.writeString(otherTransfer);
        parcel.writeString(poorHouseId);
        parcel.writeInt(povertyCondolence);
        parcel.writeInt(povertyEducation);
        parcel.writeInt(povertyMoney);
        parcel.writeInt(povertyOther);
        parcel.writeString(productbility);
        parcel.writeString(production);
        parcel.writeInt(productionOther);
        parcel.writeInt(productionThreeMoney);
        parcel.writeString(property);
        parcel.writeInt(propertyOther);
        parcel.writeString(salary);
        parcel.writeInt(socialAssMoney);
        parcel.writeString(transfer);
        parcel.writeInt(wfgzMoney);
        parcel.writeString(year);
        parcel.writeString(yearIncome);
        parcel.writeInt(ylbxMoney);
    }
}