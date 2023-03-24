package com.example.product_service.repository.impl;

import com.example.product_service.dto.object.ProductDTO;
import com.example.product_service.dto.request.SearchProductRequestDTO;
import com.example.product_service.repository.ProductRepoCustom;
import com.example.product_service.utils.DataUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepoCustomImpl implements ProductRepoCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<ProductDTO> search(SearchProductRequestDTO dataSearch, Pageable pageable) {
        return null;
    }

    private Query buildQuerySearch (SearchProductRequestDTO dataSearch, Pageable pageable) {
        Map<String, Object> params = new HashMap<>();
        String sqlFinal;
        StringBuilder sql = new StringBuilder("");
        sql.append("SELECT p.id id, p.title title, p.quantity quantity, p.price price, p.status status");
        sql.append(", p.created created, c.name, c.code");
        sql.append(" FROM product p JOIN product_categories pc ON p.id = pc.product_id");
        sql.append(" JOIN category c ON c.id = pc.category_id");
        sql.append(" WHERE 1=1");

        if (!DataUtils.isNullOrEmpty(dataSearch.getKeySearch())) {
            sql.append(" AND p.title LIKE :key");
        }

        if (DataUtils.isNullOrEmpty(pageable)) {
            sqlFinal = "SELECT COUNT(1) FROM (" + sql + ") p";
        } else {
            sql.append(" ORDER BY ");
            Sort sort = pageable.getSort();
            sort.forEach(val -> {
                sql.append(val.getProperty()).append(" ").append(val.getDirection().name()).append(",");
            });
            sql.deleteCharAt(sql.length() - 1);
            sqlFinal = sql.toString();
        }
        Query query = DataUtils.isNullOrEmpty(pageable) ? em.createNativeQuery(sqlFinal, "") : em.createNativeQuery(sqlFinal);
        if (DataUtils.isNullOrEmpty(pageable)) {
            query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
            query.setMaxResults(pageable.getPageSize());
        }
        params.forEach(query::setParameter);
        return query;
    }
}
