package ru.az.mz.inventory.xmlmodel;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class VersionProvider {

    private List<String> comments;
    private String name;
    private String perl_exe;
    private String perl_version;
    private String program;
    private String version;

    @XmlElement(name = "COMMENTS")
    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    @XmlElement(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "PERL_EXE")
    public String getPerl_exe() {
        return perl_exe;
    }

    public void setPerl_exe(String perl_exe) {
        this.perl_exe = perl_exe;
    }

    @XmlElement(name = "PERL_VERSION")
    public String getPerl_version() {
        return perl_version;
    }

    public void setPerl_version(String perl_version) {
        this.perl_version = perl_version;
    }

    @XmlElement(name = "PROGRAM")
    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    @XmlElement(name = "VERSION")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "VersionProvider{" +
                "comments=" + comments +
                ", name='" + name + '\'' +
                ", perl_exe='" + perl_exe + '\'' +
                ", perl_version='" + perl_version + '\'' +
                ", program='" + program + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
