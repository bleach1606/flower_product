package ptit.edu.btl.service;

import org.springframework.stereotype.Service;
import ptit.edu.btl.entity.Category;
import ptit.edu.btl.entity.FilterForm;
import ptit.edu.btl.entity.FilterResult;
import ptit.edu.btl.entity.FlowerProducts;
import ptit.edu.btl.exception.BTLException;
import ptit.edu.btl.repository.CategoryRepository;
import ptit.edu.btl.repository.FlowerProductsRepository;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FlowerProductsServiceImpl implements FlowerProductsService {

    private final FlowerProductsRepository flowerProductsRepository;
    private final CategoryRepository categoryRepository;

    public FlowerProductsServiceImpl(FlowerProductsRepository flowerProductsRepository, CategoryRepository categoryRepository) {
        this.flowerProductsRepository = flowerProductsRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public FlowerProducts create(FlowerProducts entity) throws BTLException {
        entity.setActive(true);
        if (Objects.nonNull(flowerProductsRepository.findByNameIsLike(entity.getName()))) {
            throw new BTLException("Tên đã tồn tại !!!");
        }
        FlowerProducts flowerProducts = flowerProductsRepository.save(entity);
//        Category category = categoryRepository.findByIdAndAndFiActive(entity.getCategoryId(), true);
        return flowerProducts;
    }

    //todo search by key
    @Override
    public List<FlowerProducts> findByName(String key) throws BTLException {
        return flowerProductsRepository.findByNameIsLikeOrderByName("%" + key + "%");
    }

    @Override
    public FlowerProducts update(FlowerProducts entity) throws BTLException {
        return flowerProductsRepository.save(entity);
    }

    @Override
    public void delete(int id) throws BTLException {
        flowerProductsRepository.deleteById(id);
    }

    @Override
    public FlowerProducts findByIdAndActive(int id, boolean bo) throws BTLException {
        return flowerProductsRepository.findByIdAndActive(id, bo).orElse(null);
    }
}
