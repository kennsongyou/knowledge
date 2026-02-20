package ai.neuron.copilot.knowledge.foundation.blob.qcloud.cos;

import ai.neuron.copilot.knowledge.common.io.FileUploadDTO;
import ai.neuron.copilot.knowledge.foundation.blob.*;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.model.COSObject;
import com.qcloud.cos.model.COSObjectInputStream;
import com.qcloud.cos.model.GeneratePresignedUrlRequest;
import com.qcloud.cos.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;

import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;

@RequiredArgsConstructor
public class QcloudCOSClient implements ObjectStorageClient {

    private final COSClient cosClient;

    private final QcloudCosProperties qcloudCosProperties;

    @Override
    public void save(InputStream inputStream, FileUploadDTO fileUploadDTO, BlobObject blobObject) {
        TimeZone timeZone = LocaleContextHolder.getTimeZone();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(fileUploadDTO.getSize());
        metadata.setContentType(fileUploadDTO.getContentType());
        Optional.ofNullable(blobObject.expiration())
                .map(e -> e.atZone(timeZone.toZoneId()))
                .map(e -> Date.from(e.toInstant())).ifPresent(metadata::setExpirationTime);
        cosClient.putObject(qcloudCosProperties.getBucket(), blobObject.blobObjectKey().value(), inputStream, metadata);
    }

    @Override
    public BlobInputStreamDTO fetch(BlobObjectKey blobObjectKey) {
        COSObject object = cosClient.getObject(qcloudCosProperties.getBucket(), blobObjectKey.value());
        COSObjectInputStream objectContent = object.getObjectContent();
        ObjectMetadata objectMetadata = object.getObjectMetadata();
        return BlobInputStreamDTO.builder()
                .objectKey(blobObjectKey.value())
                .blobProvider(blobProvider())
                .inputStream(objectContent)
                .size(objectMetadata.getContentLength())
                .contentType(objectMetadata.getContentType())
                .build();
    }

    @Override
    public URL url(BlobObjectKey blobObjectKey, Duration timeout) {
        GeneratePresignedUrlRequest request =
                new GeneratePresignedUrlRequest(qcloudCosProperties.getBucket(), blobObjectKey.value());
        request.setMethod(HttpMethodName.GET);
        Date expiration = new Date(System.currentTimeMillis() + timeout.toMillis());
        request.setExpiration(expiration);
        return cosClient.generatePresignedUrl(request);
    }

    @Override
    public String keyPrefix() {
        return qcloudCosProperties.getKeyPrefix();
    }

    @Override
    public BlobProvider blobProvider() {
        return BlobProvider.QCLOUD;
    }

}
