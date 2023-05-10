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
        System.out.println("filenames = " + images.stream().count());
        List<Image> uploadFiles = fileService.storeFiles(images);
        for (Image uploadFile : uploadFiles) {
            filenames.add(uploadFile.getImageFilename());
        }
        return filenames;
    }

    @PostMapping("/image/send_image")
//    public List<String> uploadImages(List<MultipartFile> images) throws IOException {
    public String uploadImage(MultipartFile image) throws IOException {
        // 파일 처리 작업 수행
//        List<String> filenames = new ArrayList<>();
//        List<UploadFile> uploadFiles = fileService.storeFiles(files);
        Image imageFile = fileService.storeFile(image);
        return imageFile.getImageFilename();
//        List<Image> uploadFiles = fileService.storeFiles(image);
//        for (Image uploadFile : uploadFiles) {
//            filenames.add(uploadFile.getImageFilename());
//        }
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

        String objectName = deepLearn.substring(1, deepLearn.length() - 1);
        if (objectName == null) {
            return "생활가전";
        }
        System.out.println("deepLearn: " + objectName);
        if (objectName.equals("laptop")) {
            return "전자기기";
        } else if (objectName.equals("bicycle")) {
            return "생활가전";
        } else if (objectName.equals("clock")) {
            return "의류";
        } else if (objectName.equals("0")) {
            return "도서";
        } else {
            return "뷰티미용";
        }
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileService.getFullPath(filename));
    }
}
