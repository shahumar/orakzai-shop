package org.orakzai.lab.shop.web.admin.dto.reference;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Size implements Serializable {
	private static final long serialVersionUID = -4737272153685816396L;
	private String code;
	private String name;

}
