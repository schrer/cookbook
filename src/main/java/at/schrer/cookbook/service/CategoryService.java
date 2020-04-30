package at.schrer.cookbook.service;

import at.schrer.cookbook.data.dto.CategoryModel;
import at.schrer.cookbook.data.entity.CategoryEntity;
import at.schrer.cookbook.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static at.schrer.cookbook.CookbookConfig.COOOKBOOK_CONVERTER_BEAN_NAME;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ConversionService converter;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, @Qualifier(COOOKBOOK_CONVERTER_BEAN_NAME) ConversionService converter) {
        this.categoryRepository = categoryRepository;
        this.converter = converter;
    }

    public CategoryModel saveCategory(CategoryModel category) {

        CategoryEntity entity = converter.convert(category, CategoryEntity.class);
        if (entity == null) {
            return null;
        }

        entity = categoryRepository.save(entity);
        return converter.convert(entity, CategoryModel.class);
    }

    public List<CategoryModel> getAllCategories() {

        List<CategoryModel> categories = new LinkedList<>();
        categoryRepository.findAll().forEach(category -> categories.add(converter.convert(category, CategoryModel.class)));
        return categories;

    }

    public Optional<CategoryModel> getCategoryByID(long id) {
        Optional<CategoryEntity> optionalEntity = categoryRepository.findById(id);
        return optionalEntity.map(categoryEntity -> converter.convert(categoryEntity, CategoryModel.class));
    }
}
