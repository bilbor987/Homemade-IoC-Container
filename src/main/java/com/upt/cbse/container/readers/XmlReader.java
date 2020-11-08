package com.upt.cbse.container.readers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.upt.cbse.container.model.Beans;

import java.io.File;
import java.io.IOException;

public class XmlReader {

    public Beans readXmlFile(String fileName) throws IOException {

        File file = new File(fileName);
        ObjectMapper objectMapper = new XmlMapper();
        Beans beans;

        beans = objectMapper.readValue(file, Beans.class);
        System.out.println(beans);

        return beans;
    }
}
