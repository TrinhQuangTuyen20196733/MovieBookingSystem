package TestBHDStar.utils;

import TestBHDStar.exception.FilmActionException;
import TestBHDStar.exception.MovieNotFoundException;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ThumbnailFileUtil {
    private static TestBHDStar.utils.ThumbnailFileUtil INSTANCE;

    public static TestBHDStar.utils.ThumbnailFileUtil getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TestBHDStar.utils.ThumbnailFileUtil();
        }
        return INSTANCE;
    }

    public byte[] getThumbnail(String pathThumbnail) {
        try {
            Path path = Paths.get(pathThumbnail);
            return Files.readAllBytes(path);
        } catch (Exception e) {
            throw new MovieNotFoundException("Không thể tải được ảnh của phim!");
        }

    }

    public String saveThumbnailToDisk(MultipartFile image) {
        try {
            //Loại bỏ các ký tự không hợp lệ trong file name
            String fileName = StringUtils.cleanPath(image.getOriginalFilename());
            //get() tạo một đường dẫn tuyệt đối và resolve method nối tên của fileName vào đường dẫn tuyệt đối này
            Path path = Paths.get("./uploads").resolve(fileName);
            //Chuyển dữ liệu thành luồng InputStream, sau đó lưu vào đường dẫn với option là ghi đè các file đã tồn tại
            Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            return path.toString();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new FilmActionException("Thêm phim không thành công! Bạn vui lòng tạo lại!");
        }

    }
    public  void deleteImageInDisk(String pathName) {
        try {
            Path path= Paths.get(pathName);
            Files.delete(path);
        }
        catch (Exception e) {
            throw  new FilmActionException("Xóa phim không thành công! Bạn vui lòng thử lại!");
        }


    }

}
