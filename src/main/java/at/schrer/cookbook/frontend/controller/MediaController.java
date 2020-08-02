package at.schrer.cookbook.frontend.controller;

import at.schrer.cookbook.service.ImageService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.io.InputStream;

import static at.schrer.cookbook.frontend.FrontendConstants.SEGMENT_IMAGES;
import static at.schrer.cookbook.frontend.FrontendConstants.SEGMENT_MEDIA;

@Controller
@RequestMapping("/" + SEGMENT_MEDIA)
public class MediaController {

    ImageService imageService;

    @Autowired
    public MediaController(ImageService imageService){
        this.imageService = imageService;
    }

    @GetMapping("/" + SEGMENT_IMAGES + "/{imageId}")
    public @ResponseBody byte[] serveImage(@PathVariable @NotBlank String imageId) throws IOException {
        InputStream imageStream = imageService.getImageAsInputStrem(imageId);
        return IOUtils.toByteArray(imageStream);
    }
}
