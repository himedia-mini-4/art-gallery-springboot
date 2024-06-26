package com.team4.artgallery.service.helper;

import jakarta.servlet.ServletContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;

/**
 * MultipartFile 객체를 다루는 서비스
 */
@Service
@RequiredArgsConstructor
final public class MultipartFileService {

    private final ServletContext context;

    /**
     * 요청으로 받은 파일을 저장하고 저장된 파일 이름을 반환합니다.
     */
    public FileNamePair saveFile(MultipartFile file, String uploadDirName) {
        // 파일 이름이 비어있으면 중단
        String filename = file.getOriginalFilename();
        if (filename == null || filename.isEmpty()) {
            System.out.println("No file selected");
            return null;
        }

        // 디렉토리 생성에 실패하면 중단
        File uploadDir = new File(context.getRealPath(uploadDirName));
        if (!uploadDir.exists() && !uploadDir.mkdir()) {
            System.out.println("Failed to create directory: " + uploadDir);
            return null;
        }

        // 파일 이름에 타임스탬프를 붙여서 저장 파일 이름을 생성
        long timestamp = Calendar.getInstance().getTimeInMillis();
        String saveFilename = filename.replaceFirst("\\.", timestamp + ".");

        try {
            file.transferTo(new File(uploadDir, saveFilename));
        } catch (IllegalStateException e) {
            System.out.println("IllegalStateException: " + Arrays.toString(e.getStackTrace()));
            return null;
        } catch (IOException e) {
            System.out.println("IOException: " + Arrays.toString(e.getStackTrace()));
            return null;
        }

        return new FileNamePair(filename, saveFilename);
    }

    public record FileNamePair(String fileName, String saveFileName) {
    }
}
