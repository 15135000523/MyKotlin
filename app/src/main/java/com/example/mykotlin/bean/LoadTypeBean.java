package com.example.mykotlin.bean;

public class LoadTypeBean {
    private String key;
    private String valueHint;
    private String valueRules;
    private String inputType;


    public LoadTypeBean(String key, String valueHint, String valueRules, String inputType) {
        this.key = key;
        this.valueHint = valueHint;
        this.valueRules = valueRules;
        this.inputType = inputType;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValueHint() {
        return valueHint;
    }

    public void setValueHint(String valueHint) {
        this.valueHint = valueHint;
    }

    public String getValueRules() {
        return valueRules;
    }

    public void setValueRules(String valueRules) {
        this.valueRules = valueRules;
    }

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }
}
