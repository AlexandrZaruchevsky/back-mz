package ru.az.sfr.util.ad.xml.model.dom;

import lombok.Data;

@Data
public class ADUser {
	
	private String name;
	private String displayName;
	private String cN;
	private String surname;
	private String sn;
	private String givenName;
	private String middleName;
	private String otherName;
	private String samAccountName;
	private String userPrincipalName;
	private String emailAddress;
	private String mail;
	private String country;
	private String city;
	private String streetAddress;
	private String company;
	private String department;
	private String title;
	private String description;
	private String officePhone;
	private String telephoneNumber;
	private String mobile;
	private String mobilePhone;
	private String info;
	private boolean enabled;
	
}
