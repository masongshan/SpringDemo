package com.masongshan.springdemo.spring;

import java.util.List;

public class BeanDefinition {
    private String id;
    private String clazz;
    List<PropertyDefinition> propertyDefinitions;

    public BeanDefinition() {
    }

    public BeanDefinition(String id, String clazz, List<PropertyDefinition> propertyDefinitions) {
        this.id = id;
        this.clazz = clazz;
        this.propertyDefinitions = propertyDefinitions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public List<PropertyDefinition> getPropertyDefinitions() {
        return propertyDefinitions;
    }

    public void setPropertyDefinitions(List<PropertyDefinition> propertyDefinitions) {
        this.propertyDefinitions = propertyDefinitions;
    }
}
