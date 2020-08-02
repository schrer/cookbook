package at.schrer.cookbook.service;

import at.schrer.cookbook.data.entity.ImageEntity;
import at.schrer.cookbook.data.model.ImageModel;
import at.schrer.cookbook.repository.FileRepository;
import at.schrer.cookbook.repository.ImageRepository;
import at.schrer.cookbook.repository.exceptions.UnsupportedFileTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

import static at.schrer.cookbook.CookbookConfig.COOOKBOOK_CONVERTER_BEAN_NAME;

@Service
public class ImageService {

    private final FileRepository fileRepository;
    private final ImageRepository imageRepository;
    private final ConversionService converter;

    @Autowired
    public ImageService(FileRepository fileRepository, ImageRepository imageRepository, @Qualifier(COOOKBOOK_CONVERTER_BEAN_NAME) ConversionService converter) {
        this.fileRepository = fileRepository;
        this.imageRepository = imageRepository;
        this.converter = converter;
    }

    public ImageModel saveImage(MultipartFile multipartImage) throws IOException {
        try {

            ImageEntity imageEntity = new ImageEntity();
            ImageEntity savedImage = imageRepository.save(imageEntity);
            String filePath = fileRepository.saveImage(multipartImage, savedImage.getId());
            savedImage.setPath(filePath);
            return converter.convert(imageRepository.save(savedImage), ImageModel.class);

        } catch (UnsupportedFileTypeException e) {
            throw new IOException(e);
        }
    }

    public InputStream getImageAsInputStrem(String imageId){
        //TODO implement
        return null;
    }
}
