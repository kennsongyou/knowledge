//package ai.neuron.copilot.knowledge.foundation.blob.local;
//
//import ai.neuron.copilot.knowledge.common.io.FileUploadDTO;
//import ai.neuron.copilot.knowledge.foundation.blob.*;
//import lombok.RequiredArgsConstructor;
//
//import java.io.InputStream;
//import java.net.URL;
//import java.nio.file.attribute.FileTime;
//import java.time.Duration;
//
//import org.springframework.http.MediaType;
//import org.springframework.http.MediaTypeFactory;
//import org.springframework.util.FileCopyUtils;
//
//import java.io.*;
//import java.nio.file.*;
//import java.time.ZoneId;
//import java.util.Date;
//import java.util.Optional;
//
//@RequiredArgsConstructor
//public class BlobLocalClient implements ObjectStorageClient {
//
//    private final BlobLocalProperties blobLocalProperties;
//
//    @Override
//    public void save(InputStream inputStream,
//                     FileUploadDTO fileUploadDTO,
//                     BlobObject blobObject) {
//
//        try {
//            Path root = Paths.get(blobLocalProperties.getFolder());
//            Path target = root.resolve(blobObject.blobObjectKey().value());
//
//            // 创建父目录
//            Files.createDirectories(target.getParent());
//
//            // 写文件
//            try (OutputStream os = Files.newOutputStream(target, StandardOpenOption.CREATE,
//                    StandardOpenOption.TRUNCATE_EXISTING)) {
//                FileCopyUtils.copy(inputStream, os);
//            }
//
//            // 处理过期时间（可选）
//            Optional.ofNullable(blobObject.expiration())
//                    .map(e -> e.atZone(ZoneId.systemDefault()))
//                    .map(e -> Date.from(e.toInstant()))
//                    .ifPresent(expireDate -> {
//                        try {
//                            Files.setLastModifiedTime(target,
//                                    FileTime.fromMillis(expireDate.getTime()));
//                        } catch (IOException ignored) {
//                        }
//                    });
//
//        } catch (IOException e) {
//            throw new RuntimeException("Local file save failed", e);
//        }
//    }
//
//    @Override
//    public BlobInputStreamDTO fetch(BlobObjectKey blobObjectKey) {
//
//        try {
//            Path root = Paths.get(blobLocalProperties.getFolder());
//            Path target = root.resolve(blobObjectKey.value());
//
//            if (!Files.exists(target)) {
//                throw new FileNotFoundException("File not found: " + blobObjectKey.value());
//            }
//
//            InputStream inputStream = Files.newInputStream(target);
//
//            String contentType = MediaTypeFactory
//                    .getMediaType(blobObjectKey.fileName())
//                    .map(MediaType::toString)
//                    .orElse("application/octet-stream");
//
//            return BlobInputStreamDTO.builder()
//                    .objectKey(blobObjectKey.value())
//                    .blobProvider(blobProvider())
//                    .inputStream(inputStream)
//                    .size(Files.size(target))
//                    .contentType(contentType)
//                    .build();
//
//        } catch (IOException e) {
//            throw new RuntimeException("Local file fetch failed", e);
//        }
//    }
//
//    @Override
//    public URL url(BlobObjectKey blobObjectKey, Duration timeout) {
//
//        try {
//            Path root = Paths.get(blobLocalProperties.getFolder());
//            Path target = root.resolve(blobObjectKey.value());
//
//            if (!Files.exists(target)) {
//                throw new FileNotFoundException("File not found: " + blobObjectKey.value());
//            }
//
//            // 本地文件 URL（file://）
//            return target.toUri().toURL();
//
//        } catch (IOException e) {
//            throw new RuntimeException("Local file url generate failed", e);
//        }
//    }
//
//    @Override
//    public String keyPrefix() {
//        return blobLocalProperties.getFolder();
//    }
//
//    @Override
//    public BlobProvider blobProvider() {
//        return BlobProvider.LOCAL;
//    }
//
//}
