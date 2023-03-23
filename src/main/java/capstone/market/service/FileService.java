package capstone.market.service;

import capstone.market.domain.Image;
import capstone.market.domain.Post;
import capstone.market.filedata.UploadFile;
import capstone.market.repository.ImageRepository;
import capstone.market.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    private final PostRepository postRepository;
    private final ImageRepository imageRepository;
    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    public List<Image> getAllImagesName() {
        return imageRepository.findAll();
    }

    public Image findImageFilename(String file_name) {
        return imageRepository.findByImageFilename(file_name);
    }

//    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
    public List<Image> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
//        List<UploadFile> storeFileResult = new ArrayList<>();
        List<Image> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                storeFileResult.add(storeFile(multipartFile));
            }
        }
        return storeFileResult;
    }

//    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
    public Image storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFilename);
        multipartFile.transferTo(new File(getFullPath(storeFileName)));

        // 이미지 파일명 저장
        Image image = new Image(storeFileName);
        imageRepository.save(image);

        return image;
//        return new UploadFile(originalFilename, storeFileName);
    }

    private String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }


}
