package ru.az.mz.inventory.xmlmodel;

import javax.xml.bind.annotation.XmlElement;

public class User {

    private String domain;
    private String login;

    @XmlElement(name = "DOMAIN")
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @XmlElement(name = "LOGIN")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "User{" +
                "domain='" + domain + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
