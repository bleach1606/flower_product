package ptit.edu.btl.service;

import org.springframework.stereotype.Service;
import ptit.edu.btl.entity.Category;
import ptit.edu.btl.exception.BTLException;
import ptit.edu.btl.repository.CategoryRepository;
import ptit.edu.btl.repository.FlowerProductsRepository;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final FlowerProductsRepository flowerProductsRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, FlowerProductsRepository flowerProductsRepository) {
        this.categoryRepository = categoryRepository;
        this.flowerProductsRepository = flowerProductsRepository;
    }

    @Override
    public Category create(Category entity) throws BTLException {
        entity.setFiActive(true);
        return categoryRepository.save(entity);
    }

    @Override
    public Category findById(int id) throws BTLException {
        Category category = categoryRepository.findByIdAndAndFiActive(id, true);
        category.setFlowerProductsList(flowerProductsRepository.findByCategoryIdOrderByName(id));
        return category;
    }

    @Override
    public Category update(Category entity) throws BTLException {
        return categoryRepository.save(entity);
    }

    @Override
    public List<Category> findALl() throws BTLException {
        return categoryRepository.findByFiActiveOrderByName(true);
    }

    @Override
    public void delete(int id) throws BTLException {
        categoryRepository.deleteById(id);
    }
}
