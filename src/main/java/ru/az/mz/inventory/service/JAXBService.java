package ru.az.mz.inventory.service;

import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;

public final class JAXBService<T> {

    public T getObjectFromFile(String filename, Class<? extends T> clazz) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        return (T) jaxbContext.createUnmarshaller().unmarshal(new File(filename));
    }

    public T getObjectFromFile(InputStream in, Class<? extends T> clazz) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        return (T) jaxbContext.createUnmarshaller().unmarshal(in);
    }

    public String createXmlFromInputStream(T t, Class<? extends T> clazz) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(t, stringWriter);
        return stringWriter.toString();
    }

}
