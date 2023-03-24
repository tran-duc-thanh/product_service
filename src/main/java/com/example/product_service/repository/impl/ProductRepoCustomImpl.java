package com.example.product_service.repository.impl;

import com.example.product_service.dto.object.ProductDTO;
import com.example.product_service.dto.request.SearchProductRequestDTO;
import com.example.product_service.repository.ProductRepoCustom;
import com.example.product_service.utils.DataUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class ProductRepoCustomImpl implements ProductRepoCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Page<ProductDTO> search(SearchProductRequestDTO dataSearch, Pageable pageable) {
        Query queryCount = buildQuerySearch(dataSearch, null);
        long countResult = DataUtils.safeToLong(queryCount.getSingleResult());
        List results = new ArrayList<>();
        if (countResult > 0) {
            Query queryData = buildQuerySearch(dataSearch, pageable);
            results = queryData.getResultList();
        }
        return new PageImpl<>(results, pageable, countResult);
    }

    private Query buildQuerySearch (SearchProductRequestDTO dataSearch, Pageable pageable) {
        Map<String, Object> params = new HashMap<>();
        String sqlFinal;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT p.id id, p.title title, p.quantity quantity, p.price price, p.status status");
        sql.append(", p.created created, c.name categoryName, c.code categoryCode");
        sql.append(", (SELECT GROUP_CONCAT(img.url) FROM image img WHERE img.product_id = p.id) pathImages");
        sql.append(" FROM product p JOIN product_categories pc ON p.id = pc.product_id");
        sql.append(" JOIN category c ON c.id = pc.category_id");
        sql.append(" WHERE 1=1");

        if (!DataUtils.isNullOrEmpty(dataSearch.getKeySearch())) {
            sql.append(" AND p.title LIKE :key");
            params.put("key", DataUtils.makeLikeStr(dataSearch.getKeySearch()));
        }
        if (!DataUtils.isNullOrEmpty(dataSearch.getCategoryIds()) && dataSearch.getCategoryIds().size() > 0) {
            sql.append(" AND c.id IN (:categoryIds)");
            params.put("categoryIds", dataSearch.getCategoryIds());
        }
        if (!DataUtils.isNullOrEmpty(dataSearch.getPriceMin()) && !DataUtils.isNullOrEmpty(dataSearch.getPriceMax())) {
            sql.append(" AND p.price BETWEEN :minPrice AND :maxPrice");
            params.put("minPrice", dataSearch.getPriceMin());
            params.put("maxPrice", dataSearch.getPriceMax());
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
        Query query = !DataUtils.isNullOrEmpty(pageable) ? em.createNativeQuery(sqlFinal, "productMapping") : em.createNativeQuery(sqlFinal);
        if (!DataUtils.isNullOrEmpty(pageable)) {
            query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
            query.setMaxResults(pageable.getPageSize());
        }
        params.forEach(query::setParameter);
        return query;
    }
}
