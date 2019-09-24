package com.zk.sfservice.utils;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.util.StringUtils;

public class ConvertToJavaBean {
	
	private static final Logger logger = LoggerFactory.getLogger(ConvertToJavaBean.class);

	/**
     * xml转换成JavaBean
     * @param xml
     * @param c
     * @return
     */
    @SuppressWarnings("unchecked")
	public static <T> T convertToJavaBean(String xml, Class<T> c) {

        if (StringUtils.isEmpty(xml)){
            return null;
        }
        T t = null;
        try {
            JAXBContext context = JAXBContext.newInstance(c);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            t = (T) unmarshaller.unmarshal(new StringReader(xml));
        } catch (Exception e) {
        	logger.error("xml to JavaBean ex.", e);
        }

        return t;
    }
    
}
