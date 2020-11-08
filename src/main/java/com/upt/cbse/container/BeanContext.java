package com.upt.cbse.container;

import com.upt.cbse.container.exceptions.NoSuchBeanException;
import com.upt.cbse.container.model.Bean;
import com.upt.cbse.container.model.Beans;
import com.upt.cbse.container.readers.XmlReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BeanContext {

    private List<Bean> allBeans;
    private BeanFactory beanFactory;
    private XmlReader xmlReader;

    private boolean isAlreadyInitialized = false;

    private void initializeContext() {
        if (isAlreadyInitialized)
            return;

        isAlreadyInitialized = true;
        allBeans = new ArrayList<>();
        beanFactory = new BeanFactory();
        xmlReader = new XmlReader();
    }

    public void initializeContext(String... configFileNames) {
        initializeContext();

        for (String configFileName : configFileNames) {
            allBeans.addAll(getBeansFromConfigFile(configFileName));
        }
    }

    private List<Bean> getBeansFromConfigFile(String configFileName) {

        Beans beans;

        try {
            beans = xmlReader.readXmlFile(getClass().getClassLoader().getResource(configFileName).getFile());
        } catch (IOException e) {
            System.out.println("No such configuration file " + configFileName);
            return new ArrayList<>();
        }

        return Arrays.asList(beans.getBean());
    }

    public Map<Bean, Object> getBeanToObjectsMap() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, NoSuchBeanException {
        return beanFactory.createBeans(allBeans);
    }

}
