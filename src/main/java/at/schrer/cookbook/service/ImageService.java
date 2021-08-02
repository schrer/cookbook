package at.schrer.cookbook.service;

import at.schrer.cookbook.data.entity.ImageEntity;
import at.schrer.cookbook.data.entity.RecipeEntity;
import at.schrer.cookbook.data.model.ImageModel;
import at.schrer.cookbook.repository.FileRepository;
import at.schrer.cookbook.repository.ImageRepository;
import at.schrer.cookbook.repository.RecipeRepository;
import at.schrer.cookbook.repository.exceptions.UnsupportedFileTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Optional;
import java.util.UUID;

import static at.schrer.cookbook.CookbookConfig.COOOKBOOK_CONVERTER_BEAN_NAME;

@Service
public class ImageService {

    private final FileRepository fileRepository;
    private final ImageRepository imageRepository;
    private final RecipeRepository recipeRepository;
    private final ConversionService converter;

    @Autowired
    public ImageService(FileRepository fileRepository, ImageRepository imageRepository,
                        RecipeRepository recipeRepository, @Qualifier(COOOKBOOK_CONVERTER_BEAN_NAME) ConversionService converter) {
        this.fileRepository = fileRepository;
        this.imageRepository = imageRepository;
        this.recipeRepository = recipeRepository;
        this.converter = converter;
    }

    /**
     * Saves the image to the database. First the image will be put into the database, then the file will be saved using the generated ID.
     * Afterwards the path of the newly created image will be updated in the database.
     * @param multipartImage the image as MultipartFile
     * @return the ImageModel of the newly saved image.
     * @throws IOException if an error happens during while saving.
     */
    public ImageModel saveImage(MultipartFile multipartImage, long recipeId) throws IOException {
        try {
            RecipeEntity recipe = recipeRepository.findById(recipeId).orElse(null);
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setRecipe(recipe);
            ImageEntity savedImage = imageRepository.save(imageEntity);
            String filePath = fileRepository.saveImage(multipartImage, savedImage.getId());
            savedImage.setPath(filePath);
            return converter.convert(imageRepository.save(savedImage), ImageModel.class);

        } catch (UnsupportedFileTypeException e) {
            throw new IOException(e);
        }
    }

    /**
     * Returns the image as InputStream or null if no image can be found under this ID.
     * @param imageId the UUID of the image
     * @return the image file as InputStream
     * @throws FileNotFoundException if the file specified by the image cannot be found.
     */
    public InputStream getImageAsInputStream(UUID imageId) throws FileNotFoundException {
        Optional<ImageEntity> imageEntityOpt = imageRepository.findById(imageId);
        if (imageEntityOpt.isEmpty()){
            return null;
        }

        ImageEntity imageEntity = imageEntityOpt.get();
        String path = imageEntity.getPath();
        File file = new File(path);
        return new FileInputStream(file);
    }
}
