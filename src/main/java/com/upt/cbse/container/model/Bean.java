package com.upt.cbse.container.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.Arrays;
import java.util.Objects;

public class Bean {

    @JacksonXmlProperty(localName = "name")
    private String _name;

    @JacksonXmlProperty(localName = "class")
    private String _class;

    @JacksonXmlElementWrapper(localName = "property", useWrapping = false)
    private Property[] property;

    public Bean() {
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_class() {
        return _class;
    }

    public void set_class(String _class) {
        this._class = _class;
    }

    public Property[] getProperty() {
        return property;
    }

    public void setProperty(Property[] property) {
        this.property = property;
    }

    @Override
    public String toString() {
        return "\n\tBean{" +
                "name='" + _name + '\'' +
                ", class='" + _class + '\'' +
                ", property=" + Arrays.toString(property) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bean bean = (Bean) o;
        return Objects.equals(_name, bean._name) &&
                Objects.equals(_class, bean._class);
    }

    @Override
    public int hashCode() { return Objects.hash(_name, _class); }
}