package ru.az.sfr.util.glpi.configmachine.service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;

public final class JAXBService<T> {

    public T getObjectFromFile(String filename, Class<? extends T> clazz) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        try {
            return (T) jaxbContext.createUnmarshaller().unmarshal(new File(filename));
        } catch (JAXBException e) {
            throw new Exception("JAXBException");
        }
    }

    public T getObjectFromFile(InputStream in, Class<?> clazz) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        try {
            return (T) jaxbContext.createUnmarshaller().unmarshal(in);
        } catch (JAXBException e) {
            throw new Exception("JAXBException");
        }
    }

    public String createXmlFromInputStream(T t, Class<? extends T> clazz) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(t, stringWriter);
        return stringWriter.toString();
    }

    public T createFromXmlString(String xml, Class<? extends T> clazz) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        StringReader reader = new StringReader((xml));
        return (T) jaxbContext.createUnmarshaller().unmarshal(reader);
    }


}
