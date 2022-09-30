package org.orakzai.lab.shop.domain.search.repository;

import org.orakzai.lab.shop.domain.search.documents.ProductSearch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductSearchRepository extends ElasticsearchRepository<ProductSearch, String> {

	
}
