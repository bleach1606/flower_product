package ptit.edu.btl.service;

import ptit.edu.btl.entity.Category;
import ptit.edu.btl.exception.BTLException;

import java.util.List;

public interface CategoryService {
    Category create(Category entity) throws BTLException;

    Category findById(int id) throws BTLException;

    Category update(Category entity) throws BTLException;

    List<Category> findALl() throws BTLException;

    void delete(int id) throws BTLException;
}
