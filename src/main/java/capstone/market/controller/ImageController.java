package capstone.market.controller;

import capstone.market.domain.Image;
import capstone.market.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ImageController {
    private final FileService fileService;

    @GetMapping("/get_images_filname")
    public List<String> sendSavedImageList() {
        ArrayList<String> nameList = new ArrayList<>();
        List<Image> allImagesName = fileService.getAllImagesName();
        for (Image image : allImagesName) {
            nameList.add(image.getImageFilename());
        }
        return nameList;
    }
}
