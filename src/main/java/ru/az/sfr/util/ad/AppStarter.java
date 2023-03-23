package ru.az.sfr.util.ad;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import ru.az.sfr.util.ad.xml.model.ADXML;
import ru.az.sfr.util.ad.xml.model.dom.ADUserList;

public class AppStarter {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

		long start = System.currentTimeMillis();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		System.out.println("[" + formatter.format(new Date(start)) + "] ::: Start parse");
		ADUserList adUserList = ADXML.parse("/home/az/develop/tmp-files/ad-users.xml");
		long end = System.currentTimeMillis();
		System.out.println("[" + formatter.format(new Date(end)) + "] ::: Complete parse");
		// adUserList.getItems().forEach(item -> {
		// 	System.out.println(item.getAdUser().getName().replaceAll("\\s+", " ").trim());
		// });

	}

}
