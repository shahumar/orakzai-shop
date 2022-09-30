package org.orakzai.lab.shop.web.dto.catalog.manufacturer;

import java.io.Serializable;

import lombok.Data;

@Data
public class ReadableManufacturer extends ManufacturerEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private ManufacturerDescription description;
}
