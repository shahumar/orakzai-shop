package org.orakzai.lab.shop.web.admin.dto.reference;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Weight implements Serializable {
	private static final long serialVersionUID = -1006772612089740285L;
	private String code;
	private String name;
	

}
