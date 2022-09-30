package org.orakzai.lab.shop.domain.search.documents;

import java.util.List;

import javax.persistence.Id;

import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(indexName = "shopme")
public class ProductSearch {

	@Id
	private String id;
	
	private String name;
	
	private Double price;
	
	private List<String> categories;
	
	private String manufacturer;
	
	private String language;
	
	private boolean available;
	
	private String descriptions;
	
	private List<String> tags;
	
	private String store;
	
	private String highlight;
	
	
}
