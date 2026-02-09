package ai.neuron.copilot.knowledge.foundation.web.file;

import ai.neuron.copilot.knowledge.common.io.FileUploadDTO;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadAdapter {

    public static FileUploadDTO from(MultipartFile file) {
        FileUploadDTO fileUploadDTO = new FileUploadDTO();
        fileUploadDTO.setSize(file.getSize());
        fileUploadDTO.setContentType(file.getContentType());
        fileUploadDTO.setOriginalFileName(file.getOriginalFilename());
        fileUploadDTO.setBaseName(FilenameUtils.getBaseName(file.getOriginalFilename()));
        fileUploadDTO.setExtension(FilenameUtils.getExtension(file.getOriginalFilename()));
        return fileUploadDTO;
    }

}
