package ru.az.sfr.util.ad.xml.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.az.sfr.util.ad.xml.model.dom.ADItem;
import ru.az.sfr.util.ad.xml.model.dom.ADUser;
import ru.az.sfr.util.ad.xml.model.dom.ADUserList;
import ru.az.sfr.util.ad.xml.model.dom.TN;
import ru.az.sfr.util.ad.xml.model.dom.TNRef;

public class ADXML {

	private ADXML() {
	}

	public static ADUserList parse(InputStream inputStream) throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = builder.parse(inputStream);

		Node root = document.getFirstChild();

		ADUserList adUserList = new ADUserList();
		adUserList.setItems(new ArrayList<>());

		NamedNodeMap attrs = root.getAttributes();

		for (int i = 0; i < attrs.getLength(); i++) {
			adUserList.getAttrs().put(attrs.item(i).getNodeName(), attrs.item(i).getNodeValue());
		}

		NodeList aDItemNodes = root.getChildNodes();

		for (int i = 0; i < aDItemNodes.getLength(); i++) {
			ADItem adItem = new ADItem();
			Node aDItemNode = aDItemNodes.item(i);
			if (aDItemNode.getNodeType() == Node.ELEMENT_NODE) {
				adItem.setRefId(aDItemNode.getAttributes().getNamedItem("RefId").getTextContent());
				NodeList aDItemChildNodes = aDItemNode.getChildNodes();
				for (int j = 0; j < aDItemChildNodes.getLength(); j++) {
					// Вложенный элементы для Obj
					Node item = aDItemChildNodes.item(j);
					if (item.getNodeType() == Node.ELEMENT_NODE) {
						switch (item.getNodeName()) {
						case "TN":
							adItem.setTn(getTN(item));
							break;
						case "TNRef":
							adItem.setTnRef(getTNRef(item));
							break;
						case "MS":
							adItem.setAdUser(getADUser(item));
							break;
						}
					}
				}
				adUserList.getItems().add(adItem);
			}
		}
		return adUserList;
	}

	public static ADUserList parse(String filename) throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = builder.parse(filename);

		Node root = document.getFirstChild();

		ADUserList adUserList = new ADUserList();
		adUserList.setItems(new ArrayList<>());

		NamedNodeMap attrs = root.getAttributes();

		for (int i = 0; i < attrs.getLength(); i++) {
			adUserList.getAttrs().put(attrs.item(i).getNodeName(), attrs.item(i).getNodeValue());
		}

		NodeList aDItemNodes = root.getChildNodes();

		for (int i = 0; i < aDItemNodes.getLength(); i++) {
			ADItem adItem = new ADItem();
			Node aDItemNode = aDItemNodes.item(i);
			if (aDItemNode.getNodeType() == Node.ELEMENT_NODE) {
				adItem.setRefId(aDItemNode.getAttributes().getNamedItem("RefId").getTextContent());
				NodeList aDItemChildNodes = aDItemNode.getChildNodes();
				for (int j = 0; j < aDItemChildNodes.getLength(); j++) {
					// Вложенный элементы для Obj
					Node item = aDItemChildNodes.item(j);
					if (item.getNodeType() == Node.ELEMENT_NODE) {
						switch (item.getNodeName()) {
						case "TN":
							adItem.setTn(getTN(item));
							break;
						case "TNRef":
							adItem.setTnRef(getTNRef(item));
							break;
						case "MS":
							adItem.setAdUser(getADUser(item));
							break;
						}
					}
				}
				adUserList.getItems().add(adItem);
			}
		}
		return adUserList;
	}

	private static TN getTN(Node item) {
		TN tn = new TN();
		tn.setRefId(item.getAttributes().getNamedItem("RefId").getNodeValue());
		tn.setTList(new ArrayList<>());
		NodeList childNodes = item.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node item2 = childNodes.item(i);
			if (item2.getNodeType() == Node.ELEMENT_NODE) {
				tn.getTList().add(item2.getTextContent());
			}
		}
		return tn;
	}

	private static TNRef getTNRef(Node item) {
		TNRef tnRef = new TNRef();
		tnRef.setRefId(item.getAttributes().getNamedItem("RefId").getNodeValue());
		return tnRef;
	}

	private static ADUser getADUser(Node item) {
		ADUser adUser = new ADUser();
		NodeList childNodes = item.getChildNodes();
		Map<String, Props> fields = new HashMap<>();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node item2 = childNodes.item(i);
			if (item2.getNodeType() == Node.ELEMENT_NODE) {
				fields.put(item2.getAttributes().getNamedItem("N").getNodeValue(),
						new Props(item2.getNodeName(), item2.getTextContent()));
			}
		}
		fields.keySet().forEach(nameFiels -> {
			boolean flag = !"Nil".equalsIgnoreCase(fields.get(nameFiels).getTypeField());
			String value = fields.get(nameFiels).getValue();
			switch (nameFiels) {
			case "Name":
				adUser.setName(flag ? value : null);
				break;
			case "DisplayName":
				adUser.setDisplayName(flag ? value : null);
				break;
			case "CN":
				adUser.setCN(flag ? value : null);
				break;
			case "Surname":
				adUser.setSurname(flag ? value : null);
				break;
			case "sn":
				adUser.setSn(flag ? value : null);
				break;
			case "GivenName":
				adUser.setGivenName(flag ? value : null);
				break;
			case "middleName":
				adUser.setMiddleName(flag ? value : null);
				break;
			case "OtherName":
				adUser.setOtherName(flag ? value : null);
				break;
			case "SamAccountName":
				adUser.setSamAccountName(flag ? value : null);
				break;
			case "UserPrincipalName":
				adUser.setUserPrincipalName(flag ? value : null);
				break;
			case "EmailAddress":
				adUser.setEmailAddress(flag ? value : null);
				break;
			case "mail":
				adUser.setMail(flag ? value : null);
				break;
			case "Country":
				adUser.setCountry(flag ? value : null);
				break;
			case "City":
				adUser.setCity(flag ? value : null);
				break;
			case "StreetAddress":
				adUser.setStreetAddress(flag ? value : null);
				break;
			case "Company":
				adUser.setCompany(flag ? value : null);
				break;
			case "Department":
				adUser.setDepartment(flag ? value : null);
				break;
			case "Title":
				adUser.setTitle(flag ? value : null);
				break;
			case "Description":
				adUser.setDescription(flag ? value : null);
				break;
			case "OfficePhone":
				adUser.setOfficePhone(flag ? value : null);
				break;
			case "telephoneNumber":
				adUser.setTelephoneNumber(flag ? value : null);
				break;
			case "mobile":
				adUser.setMobile(flag ? value : null);
				break;
			case "MobilePhone":
				adUser.setMobilePhone(flag ? value : null);
				break;
			case "info":
				adUser.setInfo(flag ? value : null);
				break;
			case "Enabled":
				adUser.setEnabled(flag && "B".equalsIgnoreCase(fields.get(nameFiels).getTypeField())? "true".equalsIgnoreCase(value.trim()) : false);
				break;
			}
		});
		return adUser;
	}

	@Data
	@AllArgsConstructor
	static class Props {
		private String typeField;
		private String value;
	}

}
