package com.upt.cbse.container;

import com.upt.cbse.container.exceptions.NoSuchBeanException;
import com.upt.cbse.container.model.Bean;
import com.upt.cbse.container.model.Property;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanFactory {
    private List<Bean> beans;
    private Map<Bean, Object> beanToObjectMap = new HashMap<>();

    public Map<Bean, Object> createBeans(List<Bean> allBeans) throws ClassNotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        beans = allBeans;
        for(Bean bean: beans)
        {
            if(beanToObjectMap.get(bean) == null)
            {
                Object o = createBean(bean);
                beanToObjectMap.put(bean, o);
            }
        }

        return beanToObjectMap;
    }

    private Object createBean(Bean bean) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Class _class = Class.forName(bean.get_class());
        Property[] properties = bean.getProperty();
        Field[] fields = _class.getDeclaredFields();
        Method[] methods = _class.getDeclaredMethods();

        Object object = _class.newInstance();

        for(Property property : properties){
            Class propertyType = getPropertyType(property, fields);
            if (propertyType == null){
                throw new NoSuchFieldException(property.getName() + " is not defined in type " + _class.getCanonicalName());
            }

            String setterName = property.getName();
            if (hasSetter(methods, createSetterName(setterName))){
                try {
                    Method setter = _class.getMethod(createSetterName(setterName), getPropertyType(property, fields));
                    setter.invoke(object, getPropertyValue(property, propertyType));
                } catch (NoSuchMethodException | NoSuchBeanException | InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
            else throw new NoSuchFieldException(property.getName() + " of type " + _class.getCanonicalName() + " has no conventional setter method");
        }

        return object;
    }

    private Class getPropertyType(Property property, Field[] allFields) {
        for(Field field: allFields)
        {
            if(field.getName().equals(property.getName()))
                return field.getType();
        }
        return null;
    }

    private Object getPropertyValue(Property property, Class type) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchBeanException, NoSuchFieldException, ClassNotFoundException {
        if(property.getRef() == null)
        {
            Method valueOf;
            if(type.equals(String.class))
                valueOf = type.getDeclaredMethod("valueOf", Object.class);
            else
                valueOf  =type.getDeclaredMethod("valueOf", String.class);
            return valueOf.invoke(null, property.getValue());
        }
        else
        {
            Bean beanDef = getBeanByName(property.getRef());
            if(beanDef == null )
                throw new NoSuchBeanException(property.getRef());

            Object object = beanToObjectMap.get(beanDef);
            if(object == null)
            {
                object = createBean(beanDef);
                beanToObjectMap.put(beanDef, object);
            }
            return object;
        }
    }

    private Bean getBeanByName(String ref) {
        for(Bean bean: beans)
        {
            if(bean.get_name().equals(ref))
                return bean;
        }

        return null;
    }

    private boolean hasSetter(Method[] methods, String setterName) {
        for(Method method: methods)
            if(method.getName().equals(setterName))
                return true;

        return false;
    }

    private String createSetterName(String name) {
        char[] characters = name.toCharArray();
        if(Character.isAlphabetic(characters[0]))
            characters[0] = Character.toUpperCase(characters[0]);

        return "set" + new String(characters);
    }
}
