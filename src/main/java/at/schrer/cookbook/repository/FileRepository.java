package at.schrer.cookbook.repository;

import at.schrer.cookbook.repository.exceptions.UnsupportedFileTypeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

@Repository
public class FileRepository {

    @Value("${application.image-dir}")
    private String imageDirPath;

    private static final String MIME_IMAGE_JPG = "image/jpeg";
    private static final String MIME_IMAGE_PNG = "image/png";

    private static final String[] supportedImageTypes = {MIME_IMAGE_JPG,MIME_IMAGE_PNG};


    public String saveImage(MultipartFile image, UUID name) throws UnsupportedFileTypeException, IOException {
        if ( !Arrays.asList(supportedImageTypes).contains(image.getContentType()) ) {
            throw new UnsupportedFileTypeException("Unsupported file type for image. MIME type: " + image.getContentType());
        }

        File imageFile = new File(buildImagePath(name.toString(), image.getContentType()));
        image.transferTo(imageFile);
        return imageFile.getAbsolutePath();
    }

    private String buildImagePath (String name, String mimeType){
        String fileTypeSuffix = "";
        if (MIME_IMAGE_PNG.equals(mimeType)){
            fileTypeSuffix = ".png";
        }
        else if (MIME_IMAGE_JPG.equals(mimeType)){
            fileTypeSuffix = ".jpg";
        }

        createDirIfNotPresent(imageDirPath);

        String folder = imageDirPath.endsWith("/") ? imageDirPath : imageDirPath + "/";
        return folder + name + fileTypeSuffix;
    }

    private void createDirIfNotPresent(final String path){
        final File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }
}
