package com.masongshan.springdemo.spring;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyClassPathXmlApplicationContext {
    private List<BeanDefinition> beanDefinitions = new ArrayList<>();
    private Map<String, Object> beanInstances = new HashMap<>();

    public MyClassPathXmlApplicationContext(String filename) {
        readXml(filename);
        instanceBeans();
    }

    /**
     * 获得实例对象
     *
     * @param name bean id
     * @return bean实例
     */
    public Object getInstance(String name) {
        return beanInstances.get(name);
    }

    /**
     * 读取配置文件
     *
     * @param filename 配置文件
     */
    private void readXml(String filename) {
        SAXReader saxReader = new SAXReader();
        Document document = null;
        try {
            URL xmlPath = this.getClass().getClassLoader().getResource(filename);
            document = saxReader.read(xmlPath);
            Map<String, String> nsMap = new HashMap<>();
            nsMap.put("ns", "http://www.springframework.org/schema/beans");
            XPath xsub = document.createXPath("//ns:beans/ns:bean");
            xsub.setNamespaceURIs(nsMap);
            List<Element> beans = xsub.selectNodes(document);
            for (Element element: beans) {
                String id = element.attributeValue("id");
                String clazz = element.attributeValue("class");
                // 读取属性
                XPath propertySub = element.createXPath("ns:property");
                propertySub.setNamespaceURIs(nsMap);
                List<Element> properties = propertySub.selectNodes(element);
                List<PropertyDefinition> propertyDefinitions = new ArrayList<>();
                for (Element property: properties) {
                    String propertyName = property.attributeValue("name");
                    String propertyRef = property.attributeValue("ref");
                    PropertyDefinition propertyDefinition = new PropertyDefinition(propertyName, propertyRef);
                    propertyDefinitions.add(propertyDefinition);
                }
                BeanDefinition beanDefinition = new BeanDefinition(id, clazz, propertyDefinitions);
                beanDefinitions.add(beanDefinition);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *  实例化Bean
     */
    private void instanceBeans() {
        for (BeanDefinition bean: beanDefinitions) {
            try {
                if (beanInstances.get(bean.getId()) == null) {
                    beanInstances.put(bean.getId(), instanceBean(bean));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 实例化bean
     *
     * @param bean BeanDefinition对象
     * @return bean实例
     * @throws Exception
     */
    private Object instanceBean(BeanDefinition bean) throws Exception {
        Class clazz = Class.forName(bean.getClazz());
        Object beanObject = clazz.newInstance();
        // 依赖注入
        for (PropertyDefinition property: bean.getPropertyDefinitions()) {
            String id = property.getRef();
            Object dependencyBean = getDependencyBean(id);
            Field field = clazz.getDeclaredField(property.getName());
            field.setAccessible(true);
            field.set(beanObject, dependencyBean);
        }
        return beanObject;
    }

    /**
     * 根据 id 获得依赖bean实例
     *
     * @param id bean id
     * @return bean 实例
     */
    private Object getDependencyBean(String id) throws Exception {
        Object dependencyBean = beanInstances.get(id);
        if (dependencyBean == null) {
            dependencyBean = instanceBean(getBeanDefinitionById(id));
        }
        return dependencyBean;
    }

    /**
     * 通过bean id 获得BeanDefinition对象
     *
     * @param id bean id
     * @return
     * @throws RuntimeException
     */
    BeanDefinition getBeanDefinitionById(String id) throws RuntimeException {
        if (id == null) {
            throw  new RuntimeException("bean id 不能为空");
        }
        for (int i = 0; i < beanDefinitions.size(); i++) {
            BeanDefinition beanDefinition = beanDefinitions.get(i);
            if (id.equals(beanDefinition.getId())) {
                return beanDefinition;
            }
        }
        throw new RuntimeException("指定id的bean不存在");
    }
}
