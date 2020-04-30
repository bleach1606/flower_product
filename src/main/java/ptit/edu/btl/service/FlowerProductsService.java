package ptit.edu.btl.service;

import ptit.edu.btl.entity.FilterForm;
import ptit.edu.btl.entity.FilterResult;
import ptit.edu.btl.entity.FlowerProducts;
import ptit.edu.btl.exception.BTLException;

public interface FlowerProductsService {

    FlowerProducts create(FlowerProducts entity) throws BTLException;

    FilterResult findByName(FilterForm filterForm) throws BTLException;

    FlowerProducts update(FlowerProducts entity) throws BTLException;

    void delete(int id) throws BTLException;
}
