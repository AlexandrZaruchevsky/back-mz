package ru.az.mz.inventory.service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.InputStream;

public final class JAXBService<T> {

    public T getObjectFromFile(String filename, Class<? extends T> clazz) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        return (T) jaxbContext.createUnmarshaller().unmarshal(new File(filename));
    }

    public T getObjectFromFile(InputStream in, Class<? extends T> clazz) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        return (T) jaxbContext.createUnmarshaller().unmarshal(in);
    }

}
