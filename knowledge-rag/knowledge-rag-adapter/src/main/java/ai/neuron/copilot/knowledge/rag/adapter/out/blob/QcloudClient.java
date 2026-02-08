package ai.neuron.copilot.knowledge.rag.adapter.out.blob;

import ai.neuron.copilot.knowledge.common.io.InputStreamDTO;
import ai.neuron.copilot.knowledge.foundation.blob.qcloud.cos.QcloudCosProperties;
import ai.neuron.copilot.knowledge.rag.app.port.out.blob.ObjectStorageClient;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.BlobObject;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;

@RequiredArgsConstructor
@Component
public class QcloudClient implements ObjectStorageClient {

    private final COSClient cosClient;

    private final QcloudCosProperties qcloudCosProperties;

    @Override
    public void save(InputStreamDTO inputStreamDTO, BlobObject blobObject) {
        TimeZone timeZone = LocaleContextHolder.getTimeZone();
        ObjectMetadata metadata = new ObjectMetadata();
        inputStreamDTO.size().ifPresent(metadata::setContentLength);
        metadata.setContentType(inputStreamDTO.contentType());
        Optional.ofNullable(blobObject.expiration())
                .map(e -> e.atZone(timeZone.toZoneId()))
                .map(e -> Date.from(e.toInstant())).ifPresent(metadata::setExpirationTime);
        cosClient.putObject(qcloudCosProperties.getBucket(), blobObject.blobObjectKey().value(),
                inputStreamDTO.inputStream(), metadata);
    }

    @Override
    public URL url(BlobObject blobObject) {
        return cosClient.getObjectUrl(qcloudCosProperties.getBucket(), blobObject.blobObjectKey().value());
    }

}
