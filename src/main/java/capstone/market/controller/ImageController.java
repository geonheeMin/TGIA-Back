package capstone.market.controller;

import capstone.market.domain.Image;
import capstone.market.filedata.UploadFile;
import capstone.market.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
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

    @CrossOrigin("*")
    @PostMapping("/send_image_native")
    public void sendImageFromNative(@RequestBody MultipartFile image) throws IOException {

        System.out.println("file.getInputStream() = " + image.getInputStream());
//        System.out.println("post.getResource() = " + image.getResource());

//        UploadFile attachFile = fileService.storeFile(image);
        fileService.storeFile(image);
//        return attachFile.getStoreFileName();
    }

    @PostMapping("/image/send_images")
//    public List<String> uploadImages(List<MultipartFile> images) throws IOException {
    public List<String> uploadImages(List<MultipartFile> images) throws IOException {
        // 파일 처리 작업 수행
        List<String> filenames = new ArrayList<>();
//        List<UploadFile> uploadFiles = fileService.storeFiles(files);
        List<Image> uploadFiles = fileService.storeFiles(images);
        for (Image uploadFile : uploadFiles) {
            filenames.add(uploadFile.getImageFilename());
        }
        return filenames;
    }

    private String deepLearn;

    @PostMapping("/send-data")
    public String sendYolov5(@RequestBody String data) {
        System.out.println("deepLearn: " + data);
        deepLearn = data;
        return data;
    }

    @GetMapping("/send-data")
    public String getYolov5() {
        System.out.println("deepLearn: " + deepLearn);
        if (deepLearn == "" || deepLearn.isEmpty()) {
            return "cant";
        }
        return deepLearn;
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileService.getFullPath(filename));
    }
}
