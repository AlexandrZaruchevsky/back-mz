package ru.az.mz.inventory.xmlmodel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CONTROLLERS")
public class Controller {

    private String caption;

    @XmlElement(name = "CAPTION")
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @Override
    public String toString() {
        return "Controller{" +
                "caption='" + caption + '\'' +
                '}';
    }
}
