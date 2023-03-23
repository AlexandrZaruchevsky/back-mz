package ru.az.sfr.util.ad.xml.model.dom;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class ADUserList {
	
	private Map<String, String> attrs = new HashMap<>();
	
	private List<ADItem> items;
	
}
