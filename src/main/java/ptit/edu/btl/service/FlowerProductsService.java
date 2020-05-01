package ptit.edu.btl.service;

import ptit.edu.btl.entity.FilterForm;
import ptit.edu.btl.entity.FilterResult;
import ptit.edu.btl.entity.FlowerProducts;
import ptit.edu.btl.exception.BTLException;

import java.util.List;

public interface FlowerProductsService {

    FlowerProducts create(FlowerProducts entity) throws BTLException;

    List<FlowerProducts> findByName(FilterForm filterForm) throws BTLException;

    FlowerProducts update(FlowerProducts entity) throws BTLException;

    void delete(int id) throws BTLException;
}
