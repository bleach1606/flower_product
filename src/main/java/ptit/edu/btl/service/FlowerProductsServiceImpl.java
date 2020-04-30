package ptit.edu.btl.service;

import org.springframework.stereotype.Service;
import ptit.edu.btl.entity.FilterForm;
import ptit.edu.btl.entity.FilterResult;
import ptit.edu.btl.entity.FlowerProducts;
import ptit.edu.btl.exception.BTLException;
import ptit.edu.btl.repository.FlowerProductsRepository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class FlowerProductsServiceImpl implements FlowerProductsService {

    private final FlowerProductsRepository flowerProductsRepository;

    public FlowerProductsServiceImpl(FlowerProductsRepository flowerProductsRepository) {
        this.flowerProductsRepository = flowerProductsRepository;
    }

    @Override
    public FlowerProducts create(FlowerProducts entity) throws BTLException {
        if (Objects.nonNull(flowerProductsRepository.findByNameIsLike(entity.getName()))) {
            throw new BTLException("Tên đã tồn tại !!!");
        }
        return flowerProductsRepository.save(entity);
    }

    //todo search by key
    @Override
    public FilterResult findByName(FilterForm filterForm) throws BTLException {
        return flowerProductsRepository.findByName(filterForm);
    }

    @Override
    public FlowerProducts update(FlowerProducts entity) throws BTLException {
        return flowerProductsRepository.save(entity);
    }

    @Override
    public void delete(int id) throws BTLException {
        flowerProductsRepository.deleteById(id);
    }
}
