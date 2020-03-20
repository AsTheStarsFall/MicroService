package com.tianhy.springwebmvc.converter;
import com.tianhy.springwebmvc.model.Person;
import org.springframework.http.*;
import org.springframework.http.converter.*;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * @Desc: 自描述消息类型处理
 * @Author: thy
 * @CreateTime: 2019/4/9
 **/
public class PersonHttpConverter extends AbstractHttpMessageConverter<Person> {

    public PersonHttpConverter() {
        super(MediaType.valueOf("application/properties-person"));
        setDefaultCharset(Charset.forName("UTF-8"));
    }

    /**
     * 是否支持当前POJO
     * @param clazz
     * @return
     */
    @Override
    protected boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(Person.class);
    }

    /**
     * @Description: Properties2Json
     * @Param: [clazz, inputMessage]
     * @return: com.tianhy.springboot.httpmessageconvert.model.Person
     * @Author: thy
     * @Date: 2019/4/10
     */
    @Override
    protected Person readInternal(Class<? extends Person> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        //请求内容
        InputStream body = inputMessage.getBody();
        Properties properties = new Properties();
        //请求内容放入properties
        properties.load(new InputStreamReader(body, getDefaultCharset()));
        //转为对象
        Person person = new Person();
        person.setId(Long.valueOf(properties.getProperty("person.id")));
        person.setName(properties.getProperty("person.name"));
        return person;
    }

    /**
     * @Description: Json2Properties
     * @Param: [person, outputMessage]
     * @return: void
     * @Author: thy
     * @Date: 2019/4/10
     */
    @Override
    protected void writeInternal(Person person, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        OutputStream body = outputMessage.getBody();
        Properties properties = new Properties();
        properties.setProperty("person.id", String.valueOf(person.getId()));
        properties.setProperty("person.name", person.getName());
        OutputStreamWriter osw = new OutputStreamWriter(body, getDefaultCharset());
        properties.store(osw, "Written by server");
    }
}
