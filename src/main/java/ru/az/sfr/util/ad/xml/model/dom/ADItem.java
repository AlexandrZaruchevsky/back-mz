package ru.az.sfr.util.ad.xml.model.dom;

import java.util.List;

import lombok.Data;

@Data
public class ADItem {
	
	private String refId;
	private TN tn;
	private List<ADUser> adUsers;
	private ADUser adUser;
	private TNRef tnRef;
}
