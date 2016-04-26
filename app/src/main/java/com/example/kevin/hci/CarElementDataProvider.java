package com.example.kevin.hci;

/**
 * Created by Kevin on 25/04/2016.
 */
public class CarElementDataProvider {
    private String element;
    private String value;

    public CarElementDataProvider(String element, String value){
        this.element = element;
        this.value = value;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
