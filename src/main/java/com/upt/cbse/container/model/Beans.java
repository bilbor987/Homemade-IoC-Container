package com.upt.cbse.container.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.Arrays;

@JacksonXmlRootElement(localName = "beans")
public class Beans {

    @JacksonXmlElementWrapper(localName = "bean", useWrapping = false)
    private Bean[] bean;

    public Beans() {
    }

    public Bean[] getBean() {
        return bean;
    }

    public void setBean(Bean[] bean) {
        this.bean = bean;
    }

    @Override
    public String toString() {
        return "Beans{" +
                "bean=" + Arrays.toString(bean) +
                '}';
    }
}
