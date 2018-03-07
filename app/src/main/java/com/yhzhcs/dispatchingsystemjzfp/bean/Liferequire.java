package com.yhzhcs.dispatchingsystemjzfp.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Auto-generated: 2018-02-07 16:44:8
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class Liferequire implements Parcelable {

    private String cardNumber;
    private String createdBy;
    private Createddate createdDate;
    private String dangerouLevel;
    private int enabledFlag;
    private String fuelType;
    private String housingArea;
    private String id;
    private String isBusinessRegister;//风尚注册
    private String isCar;//是否有车
    private String isCivilServant;//是否是公务员
    private String isCommodityHouse;//是否有商品房
    private String isCooperative;//是否加入合作社
    private String isDilapidatedHouse;//是否危房
    private String isDrinkingSafe;//是否饮水安全
    private String isDrinkingWater;//饮水是否困难
    private String isElectricity;//是否通生活用电
    private String isHe;//是否参加合作医疗
    private String isRelocatedHouse;//是否搬迁户
    private String isToilet;
    private String lastUpdatedBy;
    private Lastupdateddate lastUpdatedDate;
    private String poorHouseId;
    private String roadType;
    private String trunkDistance;
    private String year;

    protected Liferequire(Parcel in) {
        cardNumber = in.readString();
        createdBy = in.readString();
        dangerouLevel = in.readString();
        enabledFlag = in.readInt();
        fuelType = in.readString();
        housingArea = in.readString();
        id = in.readString();
        isBusinessRegister = in.readString();
        isCar = in.readString();
        isCivilServant = in.readString();
        isCommodityHouse = in.readString();
        isCooperative = in.readString();
        isDilapidatedHouse = in.readString();
        isDrinkingSafe = in.readString();
        isDrinkingWater = in.readString();
        isElectricity = in.readString();
        isHe = in.readString();
        isRelocatedHouse = in.readString();
        isToilet = in.readString();
        lastUpdatedBy = in.readString();
        poorHouseId = in.readString();
        roadType = in.readString();
        trunkDistance = in.readString();
        year = in.readString();
    }

    public static final Creator<Liferequire> CREATOR = new Creator<Liferequire>() {
        @Override
        public Liferequire createFromParcel(Parcel in) {
            return new Liferequire(in);
        }

        @Override
        public Liferequire[] newArray(int size) {
            return new Liferequire[size];
        }
    };

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Createddate getCreatedDate() {
        return createdDate;
    }

    public String getDangerouLevel() {
        return dangerouLevel;
    }

    public int getEnabledFlag() {
        return enabledFlag;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getHousingArea() {
        return housingArea;
    }

    public String getId() {
        return id;
    }

    public String getIsBusinessRegister() {
        return isBusinessRegister;
    }

    public String getIsCar() {
        return isCar;
    }

    public String getIsCivilServant() {
        return isCivilServant;
    }

    public String getIsCommodityHouse() {
        return isCommodityHouse;
    }

    public String getIsCooperative() {
        return isCooperative;
    }

    public String getIsDilapidatedHouse() {
        return isDilapidatedHouse;
    }

    public String getIsDrinkingSafe() {
        return isDrinkingSafe;
    }

    public String getIsDrinkingWater() {
        return isDrinkingWater;
    }

    public String getIsElectricity() {
        return isElectricity;
    }

    public String getIsHe() {
        return isHe;
    }

    public String getIsRelocatedHouse() {
        return isRelocatedHouse;
    }

    public String getIsToilet() {
        return isToilet;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public Lastupdateddate getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public String getPoorHouseId() {
        return poorHouseId;
    }

    public String getRoadType() {
        return roadType;
    }

    public String getTrunkDistance() {
        return trunkDistance;
    }

    public String getYear() {
        return year;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedDate(Createddate createdDate) {
        this.createdDate = createdDate;
    }

    public void setDangerouLevel(String dangerouLevel) {
        this.dangerouLevel = dangerouLevel;
    }

    public void setEnabledFlag(int enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public void setHousingArea(String housingArea) {
        this.housingArea = housingArea;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIsBusinessRegister(String isBusinessRegister) {
        this.isBusinessRegister = isBusinessRegister;
    }

    public void setIsCar(String isCar) {
        this.isCar = isCar;
    }

    public void setIsCivilServant(String isCivilServant) {
        this.isCivilServant = isCivilServant;
    }

    public void setIsCommodityHouse(String isCommodityHouse) {
        this.isCommodityHouse = isCommodityHouse;
    }

    public void setIsCooperative(String isCooperative) {
        this.isCooperative = isCooperative;
    }

    public void setIsDilapidatedHouse(String isDilapidatedHouse) {
        this.isDilapidatedHouse = isDilapidatedHouse;
    }

    public void setIsDrinkingSafe(String isDrinkingSafe) {
        this.isDrinkingSafe = isDrinkingSafe;
    }

    public void setIsDrinkingWater(String isDrinkingWater) {
        this.isDrinkingWater = isDrinkingWater;
    }

    public void setIsElectricity(String isElectricity) {
        this.isElectricity = isElectricity;
    }

    public void setIsHe(String isHe) {
        this.isHe = isHe;
    }

    public void setIsRelocatedHouse(String isRelocatedHouse) {
        this.isRelocatedHouse = isRelocatedHouse;
    }

    public void setIsToilet(String isToilet) {
        this.isToilet = isToilet;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public void setLastUpdatedDate(Lastupdateddate lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public void setPoorHouseId(String poorHouseId) {
        this.poorHouseId = poorHouseId;
    }

    public void setRoadType(String roadType) {
        this.roadType = roadType;
    }

    public void setTrunkDistance(String trunkDistance) {
        this.trunkDistance = trunkDistance;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Liferequire{" +
                "cardNumber='" + cardNumber + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate=" + createdDate +
                ", dangerouLevel='" + dangerouLevel + '\'' +
                ", enabledFlag=" + enabledFlag +
                ", fuelType='" + fuelType + '\'' +
                ", housingArea='" + housingArea + '\'' +
                ", id='" + id + '\'' +
                ", isBusinessRegister='" + isBusinessRegister + '\'' +
                ", isCar='" + isCar + '\'' +
                ", isCivilServant='" + isCivilServant + '\'' +
                ", isCommodityHouse='" + isCommodityHouse + '\'' +
                ", isCooperative='" + isCooperative + '\'' +
                ", isDilapidatedHouse='" + isDilapidatedHouse + '\'' +
                ", isDrinkingSafe='" + isDrinkingSafe + '\'' +
                ", isDrinkingWater='" + isDrinkingWater + '\'' +
                ", isElectricity='" + isElectricity + '\'' +
                ", isHe='" + isHe + '\'' +
                ", isRelocatedHouse='" + isRelocatedHouse + '\'' +
                ", isToilet='" + isToilet + '\'' +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                ", lastUpdatedDate=" + lastUpdatedDate +
                ", poorHouseId='" + poorHouseId + '\'' +
                ", roadType='" + roadType + '\'' +
                ", trunkDistance='" + trunkDistance + '\'' +
                ", year='" + year + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(cardNumber);
        parcel.writeString(createdBy);
        parcel.writeString(dangerouLevel);
        parcel.writeInt(enabledFlag);
        parcel.writeString(fuelType);
        parcel.writeString(housingArea);
        parcel.writeString(id);
        parcel.writeString(isBusinessRegister);
        parcel.writeString(isCar);
        parcel.writeString(isCivilServant);
        parcel.writeString(isCommodityHouse);
        parcel.writeString(isCooperative);
        parcel.writeString(isDilapidatedHouse);
        parcel.writeString(isDrinkingSafe);
        parcel.writeString(isDrinkingWater);
        parcel.writeString(isElectricity);
        parcel.writeString(isHe);
        parcel.writeString(isRelocatedHouse);
        parcel.writeString(isToilet);
        parcel.writeString(lastUpdatedBy);
        parcel.writeString(poorHouseId);
        parcel.writeString(roadType);
        parcel.writeString(trunkDistance);
        parcel.writeString(year);
    }
}