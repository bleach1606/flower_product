package ptit.edu.btl.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import ptit.edu.btl.DTO.CategoryDTO;
import ptit.edu.btl.entity.Category;
import ptit.edu.btl.entity.FlowerProducts;
import ptit.edu.btl.exception.BTLException;
import ptit.edu.btl.repository.CategoryRepository;
import ptit.edu.btl.repository.FlowerProductsRepository;

import java.util.ArrayList;
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
        entity.setActive(true);
        return categoryRepository.save(entity);
    }

    @Override
    public CategoryDTO findById(int id) throws BTLException {
        Category category = categoryRepository.findByIdAndAndActive(id, true);
        List<FlowerProducts> flowerProductsList =
                flowerProductsRepository.findByCategoryIdAndActiveOrderByName(category.getId(), true);
        CategoryDTO dto = new CategoryDTO(category, flowerProductsList);
         return dto;
    }

    @Override
    public Category update(Category entity) throws BTLException {
        return categoryRepository.save(entity);
    }

    @Override
    public List<CategoryDTO> findALl() throws BTLException {
        List<Category> categoryList = categoryRepository.findByActiveOrderByName(true);
        List<CategoryDTO> listDTO = new ArrayList<>();
        for (Category x : categoryList) {
            listDTO.add(new CategoryDTO(x,
                    flowerProductsRepository.findByCategoryIdAndActiveOrderByName(x.getId(), true)));
        }
        return listDTO;
    }

    @Override
    public void delete(int id) throws BTLException {
        categoryRepository.deleteById(id);
    }
}
