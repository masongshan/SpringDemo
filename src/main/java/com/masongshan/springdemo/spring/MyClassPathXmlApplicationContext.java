package com.masongshan.springdemo.spring;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyClassPathXmlApplicationContext {
    private List<BeanDefinition> beanDefinitions = new ArrayList<BeanDefinition>();

    public MyClassPathXmlApplicationContext(String filename) {
        readXml(filename);
    }

    public BeanDefinition getInstance() {
        return null;
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
            Map<String, String> nsMap = new HashMap<String, String>();
            nsMap.put("ns", "http://www.springframework.org/schema/beans");
            XPath xsub = document.createXPath("//ns:beans/ns:bean");
            xsub.setNamespaceURIs(nsMap);
            List<Element> beans = xsub.selectNodes(document);
            for (Element element: beans) {
                String id = element.attributeValue("id");
                String clazz = element.attributeValue("class");
                BeanDefinition beanDefinition = new BeanDefinition(id, clazz);
                beanDefinitions.add(beanDefinition);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * TODO 实例化Bean
     */
    private void instanceBean() {

    }
}
