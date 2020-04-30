package ptit.edu.btl.repository;

import ptit.edu.btl.entity.FilterForm;
import ptit.edu.btl.entity.FilterResult;

public interface FlowerProductsRepositoryCustom {
    FilterResult findByName(FilterForm filterForm);

}
