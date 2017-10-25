package com.crow.domain;

/**
 * Created by CrowHawk on 17/10/11.
 */
public class User {

    private int id;
    private String name;
    private String gender;
    private String homeTeam;//用户主队
    private String address;//用户所在地
    private Integer views;//用户主页访问量

    private String provinceAddress;//省名

    private Integer peopleNum;//各省人数

    private Integer maleNum;//男性人数

    private Integer femaleNum;//女性人数

    private Integer unknownNum;//未填写性别的人数

    private Integer genderNum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getProvinceAddress() {
        return provinceAddress;
    }

    public void setProvinceAddress(String provinceAddress) {
        this.provinceAddress = provinceAddress;
    }

    public Integer getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Integer peopleNum) {
        this.peopleNum = peopleNum;
    }

    public Integer getGenderNum() {
        return genderNum;
    }

    public void setGenderNum(Integer genderNum) {
        this.genderNum = genderNum;
    }

    public Integer getMaleNum() {
        return maleNum;
    }

    public void setMaleNum(Integer maleNum) {
        this.maleNum = maleNum;
    }

    public Integer getFemaleNum() {
        return femaleNum;
    }

    public void setFemaleNum(Integer femaleNum) {
        this.femaleNum = femaleNum;
    }

    public Integer getUnknownNum() {
        return unknownNum;
    }

    public void setUnknownNum(Integer unknownNum) {
        this.unknownNum = unknownNum;
    }
}
