package com.upt.cbse.container;

import com.upt.cbse.container.exceptions.NoSuchBeanException;
import com.upt.cbse.container.model.Bean;

import java.util.HashMap;
import java.util.Map;

public class IocContainer {

    private BeanContext beanContext;

    private Map<String, Object> namesToBeanMap;

    public IocContainer()
    {
        this.beanContext = new BeanContext();
        namesToBeanMap = new HashMap<>();
    }

    public void initializeAndBuild(String ... files) throws ClassNotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException, NoSuchBeanException {
        beanContext.initializeContext(files);

        namesToBeanMap = convertMap(beanContext.getBeanToObjectsMap());
    }

    private Map<String,Object> convertMap(Map<Bean, Object> beanToObjectsMap) {
        Map<String, Object> map = new HashMap<>();

        for(Bean bean: beanToObjectsMap.keySet())
        {
            map.put(bean.get_name(), beanToObjectsMap.get(bean));
        }

        return map;
    }

    public Object getBean(String name)
    {
        return namesToBeanMap.get(name);
    }
}
