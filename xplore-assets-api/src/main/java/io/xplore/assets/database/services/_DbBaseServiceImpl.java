/*
 * Copyright (c) 2016. ArtRunners Ltd.
 *
 */

package io.xplore.assets.database.services;


import io.xplore.assets.model.QueryFilter;
import io.xplore.assets.model.QuerySort;
import io.xplore.assets.util.StringUtils;

import javax.persistence.criteria.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Base DB service
 */
public class _DbBaseServiceImpl<E>  {

    /**
     * Create dynamic query with filter and sort
     * @param clazz
     * @param cb
     * @param filter
     * @param sorting
     * @return
     */
    protected CriteriaQuery buildCriteriaQuery(Class<E> clazz, CriteriaBuilder cb, QueryFilter filter, QuerySort sorting) {

        CriteriaQuery cq = cb.createQuery();
        Root<E> from = cq.from(clazz);
        cq.select(from);

        List<Predicate> predicates = new ArrayList<Predicate>();

        // Add query filters
        if (filter != null) {
            for (String field : filter.getFilters().keySet()) {
                String filterField = this.validateField(clazz, field);
                if (StringUtils.isNotEmpty(filterField)) {

                    List<String> values = filter.getFilters().get(field);
                    List<Predicate> valuePredicates = new ArrayList<Predicate>();

                    // define predicate
                    for (String v : values) {
                        // set partial search
                        String search = String.format("%%%s%%", v);
                        valuePredicates.add(cb.like(from.get(field), search));

//                        v.replace("*", "%");
//                        if (v.contains("%")) {
//                            valuePredicates.add(cb.equal(from.get(field), v));
//                        } else {
//                            valuePredicates.add(cb.like(from.get(field), v));
//                        }
                    }
                    // values.forEach(v -> { valuePredicates.add(cb.equal(from.get(field), v)); });

                    if (valuePredicates.size() > 0) {
                        Predicate[] valuePredicateArray = new Predicate[valuePredicates.size()];
                        valuePredicates.toArray(valuePredicateArray);
                        cq.where(cb.and(valuePredicateArray));

                        Predicate filterPredicate = cb.or(valuePredicateArray);
                        predicates.add(filterPredicate);
                    }

                    // Predicate filterPredicate = cb.equal(from.get(field), filter.getFilters().get(field));
                    //predicates.add(filterPredicate);
                }
            }
        }

        if (predicates.size() > 0) {
            Predicate[] predArray = new Predicate[predicates.size()];
            predicates.toArray(predArray);
            cq.where(cb.and(predArray));
        }

        // Sorting
        if (sorting != null) {
            String sortField = this.validateField(clazz, sorting.getField());
            if (StringUtils.isNotEmpty(sortField)) {
                if (sorting.getDirection() == QuerySort.Direction.asc) {
                    cq.orderBy(cb.asc(from.get(sortField)));
                } else {
                    cq.orderBy(cb.desc(from.get(sortField)));
                }
            }
        }

        return cq;
    }

    /**
     * Validate field exists
     * @param fieldName Field name
     * @return
     */
    private String validateField(Class<E> clazz, String fieldName) {

        Field[] allFields = clazz.getDeclaredFields();
        for (Field field : allFields) {
            if (field.getName().equalsIgnoreCase(fieldName)) {
                return field.getName();
            }
        }
        return "";
    }
}
