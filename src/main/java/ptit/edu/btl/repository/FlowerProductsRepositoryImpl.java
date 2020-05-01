package ptit.edu.btl.repository;

import ptit.edu.btl.entity.FilterForm;
import ptit.edu.btl.entity.FilterResult;
import ptit.edu.btl.entity.FlowerProducts;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FlowerProductsRepositoryImpl implements FlowerProductsRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public FilterResult findByName(FilterForm filter) {
        //RESETTING PAGE TO ZERO
        filter.setPage(filter.getPage() == 0 ? 0 : filter.getPage() - 1);

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<FlowerProducts> cq = cb.createQuery(FlowerProducts.class);
        Root<FlowerProducts> root = cq.from(FlowerProducts.class);
        List<Predicate> listPredicate = new ArrayList<>();
        // điều kiện cần
        listPredicate.add(cb.equal(root.get("fiActive"), filter.isFiActive()));
        listPredicate.add(cb.like(root.get("name"), filter.getKey()));
//        if (filter.isValidForLicenseQuery()) {
//            if (!filter.getKey().isEmpty()) {
//                listPredicate.add(root.get("name").in(filter.getKey()));
//            } else {
//                listPredicate.add(cb.disjunction());
//            }
//        }

        Path<Object> sortBy = root.get(filter.getSortBy());
        Order order = (filter.getOrder().equals("asc")) ? cb.asc(sortBy) : cb.desc(sortBy);
        Predicate[] finalPredicate = new Predicate[listPredicate.size()];
        listPredicate.toArray(finalPredicate);

        //Initialize Pagination
        TypedQuery<FlowerProducts> query = em.createQuery(cq.select(root).where(cb.and(finalPredicate)).orderBy(order));
        query.setMaxResults(filter.getSize());
        query.setFirstResult(filter.getPage() * filter.getSize());

        //Get all count
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<FlowerProducts> countRoot = countQuery.from(FlowerProducts.class);
        Long count = em.createQuery(countQuery.select(cb.count(countRoot)).where(cb.and(finalPredicate))).getSingleResult();

        FilterResult filterResult = new FilterResult();
        filterResult.setTotal(count);
        filterResult.setSize(filter.getSize());
        filterResult.setPage(filter.getPage());
        List<FlowerProducts> rawList = query.getResultList();
        filterResult.setData(Collections.singletonList(rawList));
        return filterResult;
    }
}