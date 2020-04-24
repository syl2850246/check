package com.sypm.check.bean;

import java.io.Serializable;

public class Teacher implements Serializable {

    private String DEPARTMENTS;
    private String TESTED_NAME;
    private String TEST_NAME;
    private Integer QUESTION_ID;
    private Integer OPTION_ID1;
    private Integer OPTION_ID2;
    private Integer OPTION_ID3;
    private Integer OPTION_ID4;
    private Integer OPTION_ID5;
    private Integer SCORE;

    public String getDEPARTMENTS() {
        return DEPARTMENTS;
    }

    public void setDEPARTMENTS(String DEPARTMENTS) {
        this.DEPARTMENTS = DEPARTMENTS;
    }

    public String getTESTED_NAME() {
        return TESTED_NAME;
    }

    public void setTESTED_NAME(String TESTED_NAME) {
        this.TESTED_NAME = TESTED_NAME;
    }

    public String getTEST_NAME() {
        return TEST_NAME;
    }

    public void setTEST_NAME(String TEST_NAME) {
        this.TEST_NAME = TEST_NAME;
    }

    public Integer getQUESTION_ID() {
        return QUESTION_ID;
    }

    public void setQUESTION_ID(Integer QUESTION_ID) {
        this.QUESTION_ID = QUESTION_ID;
    }

    public Integer getOPTION_ID1() {
        return OPTION_ID1;
    }

    public void setOPTION_ID1(Integer OPTION_ID1) {
        this.OPTION_ID1 = OPTION_ID1;
    }

    public Integer getOPTION_ID2() {
        return OPTION_ID2;
    }

    public void setOPTION_ID2(Integer OPTION_ID2) {
        this.OPTION_ID2 = OPTION_ID2;
    }

    public Integer getOPTION_ID3() {
        return OPTION_ID3;
    }

    public void setOPTION_ID3(Integer OPTION_ID3) {
        this.OPTION_ID3 = OPTION_ID3;
    }

    public Integer getOPTION_ID4() {
        return OPTION_ID4;
    }

    public void setOPTION_ID4(Integer OPTION_ID4) {
        this.OPTION_ID4 = OPTION_ID4;
    }

    public Integer getOPTION_ID5() {
        return OPTION_ID5;
    }

    public void setOPTION_ID5(Integer OPTION_ID5) {
        this.OPTION_ID5 = OPTION_ID5;
    }

    public Integer getSCORE() {
        return SCORE;
    }

    public void setSCORE(Integer SCORE) {
        this.SCORE = SCORE;
    }

    public Teacher(String DEPARTMENTS, String TESTED_NAME, String TEST_NAME, Integer QUESTION_ID, Integer OPTION_ID1, Integer OPTION_ID2, Integer OPTION_ID3, Integer OPTION_ID4, Integer OPTION_ID5, Integer SCORE) {
        this.DEPARTMENTS = DEPARTMENTS;
        this.TESTED_NAME = TESTED_NAME;
        this.TEST_NAME = TEST_NAME;
        this.QUESTION_ID = QUESTION_ID;
        this.OPTION_ID1 = OPTION_ID1;
        this.OPTION_ID2 = OPTION_ID2;
        this.OPTION_ID3 = OPTION_ID3;
        this.OPTION_ID4 = OPTION_ID4;
        this.OPTION_ID5 = OPTION_ID5;
        this.SCORE = SCORE;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "DEPARTMENTS='" + DEPARTMENTS + '\'' +
                ", TESTED_NAME='" + TESTED_NAME + '\'' +
                ", TEST_NAME='" + TEST_NAME + '\'' +
                ", QUESTION_ID=" + QUESTION_ID +
                ", OPTION_ID1=" + OPTION_ID1 +
                ", OPTION_ID2=" + OPTION_ID2 +
                ", OPTION_ID3=" + OPTION_ID3 +
                ", OPTION_ID4=" + OPTION_ID4 +
                ", OPTION_ID5=" + OPTION_ID5 +
                ", SCORE=" + SCORE +
                '}';
    }
}
