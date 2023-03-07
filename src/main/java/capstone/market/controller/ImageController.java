package capstone.market.controller;

import capstone.market.domain.Image;
import capstone.market.filedata.UploadFile;
import capstone.market.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ImageController {
    private final FileService fileService;

    @GetMapping("/get_images_filename")
    public List<String> sendSavedImageList() {
        ArrayList<String> nameList = new ArrayList<>();
        List<Image> allImagesName = fileService.getAllImagesName();
        for (Image image : allImagesName) {
            nameList.add(image.getImageFilename());
        }
        return nameList;
    }

    @PostMapping("/send_image_native")
    public void sendImageFromNative(MultipartFile file) throws IOException {//        UploadFile attachFile = fileStore.storeFile(form.getAttachFile());
        UploadFile attachFile = fileService.storeFile(file);
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileService.getFullPath(filename));
    }
}
