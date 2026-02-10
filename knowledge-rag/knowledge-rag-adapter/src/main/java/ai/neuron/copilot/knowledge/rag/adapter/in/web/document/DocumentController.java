package ai.neuron.copilot.knowledge.rag.adapter.in.web.document;

import ai.neuron.copilot.knowledge.common.io.FileUploadDTO;
import ai.neuron.copilot.knowledge.foundation.blob.ObjectStorageClient;
import ai.neuron.copilot.knowledge.foundation.core.context.ContextHolder;
import ai.neuron.copilot.knowledge.foundation.core.context.domain.model.TenantId;
import ai.neuron.copilot.knowledge.foundation.data.page.PageQuery;
import ai.neuron.copilot.knowledge.foundation.data.page.PageResult;
import ai.neuron.copilot.knowledge.foundation.web.file.FileUploadAdapter;
import ai.neuron.copilot.knowledge.rag.adapter.in.web.document.dto.request.PageDocumentRequest;
import ai.neuron.copilot.knowledge.rag.adapter.in.web.document.dto.response.CreateDocumentResponse;
import ai.neuron.copilot.knowledge.rag.adapter.in.web.document.dto.response.GetDocumentUrlResponse;
import ai.neuron.copilot.knowledge.rag.adapter.in.web.document.dto.shared.DocumentDTO;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.CreateDocumentUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.DeleteDocumentUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.GetDocumentUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.PageDocumentUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.command.CreateDocumentByFileCommand;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.command.CreateDocumentByTextCommand;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.command.DeleteDocumentCommand;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.query.GetDocumentQuery;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.query.GetDocumentUrlQuery;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.query.PageDocumentQuery;
import ai.neuron.copilot.knowledge.rag.domain.document.model.Document;
import ai.neuron.copilot.knowledge.rag.domain.document.model.DocumentId;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.SystemException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.util.XMLHelper;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStrings;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/documents")
public class DocumentController {

    private final CreateDocumentUseCase createDocumentUseCase;

    private final GetDocumentUseCase getDocumentUseCase;

    private final PageDocumentUseCase pageDocumentUseCase;

    private final DeleteDocumentUseCase deleteDocumentUseCase;

    private final ObjectStorageClient objectStorageClient;

    private final ObjectMapper objectMapper;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CreateDocumentResponse createByFile(@RequestPart("file") MultipartFile file,
                                               @RequestPart(value = "payload", required = false) String payload)
            throws IOException {
        FileUploadDTO fileUploadDTO = FileUploadAdapter.from(file);
        try (InputStream inputStream = file.getInputStream()) {
            CreateDocumentByFileCommand command = new CreateDocumentByFileCommand(
                    inputStream,
                    fileUploadDTO,
                    payload,
                    TenantId.reconstitute(ContextHolder.tenant().id())
            );
            DocumentId documentId = createDocumentUseCase.byFile(command);
            return new CreateDocumentResponse(documentId.value());
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CreateDocumentResponse createByText(@RequestBody CreateDocumentByTextCommand command) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/{document_id}")
    @ResponseStatus(HttpStatus.OK)
    public DocumentDTO get(@PathVariable("document_id") String documentId) {
        GetDocumentQuery query = new GetDocumentQuery(
                DocumentId.reconstitute(documentId),
                TenantId.reconstitute(ContextHolder.tenant().id())
        );
        Document document = getDocumentUseCase.execute(query);
        return new DocumentDTO(
                document.getId().value(),
                document.getDisplayName(),
                document.getExtension()
        );
    }

    @GetMapping("/{document_id}/access-url")
    @ResponseStatus(HttpStatus.OK)
    public GetDocumentUrlResponse getUrl(@PathVariable("document_id") String documentId) {
        GetDocumentUrlQuery query = new GetDocumentUrlQuery(
                DocumentId.reconstitute(documentId),
                TenantId.reconstitute(ContextHolder.tenant().id())
        );
        return new GetDocumentUrlResponse(getDocumentUseCase.accessUrl(query));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PageResult<DocumentDTO> page(@ModelAttribute PageDocumentRequest request) {
        PageDocumentQuery query = new PageDocumentQuery(request.getKeyword(),
                new PageQuery(request.getPageNo(), request.getPageSize()),
                TenantId.reconstitute(ContextHolder.tenant().id())
        );
        PageResult<Document> pageResult = pageDocumentUseCase.execute(query);
        List<DocumentDTO> records = pageResult.records().stream().map(document -> new DocumentDTO(
                document.getId().value(),
                document.getDisplayName(),
                document.getExtension()
        )).toList();
        return new PageResult<>(records, pageResult.total(), pageResult.pageNo(), pageResult.pageSize());
    }

    @DeleteMapping("/{document_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("document_id") String documentId) {
        deleteDocumentUseCase.execute(new DeleteDocumentCommand(
                DocumentId.reconstitute(documentId),
                TenantId.reconstitute(ContextHolder.tenant().id()))
        );
    }

    /**
     * ignore
     */
    @Deprecated
    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadDocument(@RequestPart("file") MultipartFile file) throws IOException {
        FileUploadDTO fileUploadDTO = FileUploadAdapter.from(file);
        String extension = fileUploadDTO.getExtension();

        // 检查文件类型
        if (!"jsonl".equals(extension) && !"xlsx".equals(extension)) {
            throw new IllegalArgumentException("Unsupported file type. Only jsonl and xlsx are allowed.");
        }

        // 处理 xlsx 文件的流式校验
        if ("xlsx".equals(extension)) {
            try (InputStream inputStream = file.getInputStream()) {
                // 使用 XSSFReader 进行流式读取，只读取第一行数据
                try (OPCPackage pkg = OPCPackage.open(inputStream)) {
                    XSSFReader reader = new XSSFReader(pkg);
                    // 获取第一个工作表的 XML 流
                    XSSFReader.SheetIterator sheets = (XSSFReader.SheetIterator) reader.getSheetsData();
                    SharedStrings sharedStrings = reader.getSharedStringsTable();
                    if (sheets.hasNext()) {
                        try (InputStream sheetStream = sheets.next()) {
                            // 解析第一行数据
                            List<String> firstRowCells = parseFirstRow(sheetStream, sharedStrings);
                            // 校验第一行数据
                            boolean isValid = validateFirstRow(firstRowCells);
                            if (!isValid) {
                                throw new RuntimeException("Invalid Excel file: first row validation failed");
                            }
                        }
                    }
                } catch (OpenXML4JException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        // 上传到 COS
//        try (InputStream inputStream = file.getInputStream()) {
//            BlobObjectKey blobObjectKey = BlobObjectKey.create(objectStorageClient.keyPrefix(), extension);
//            BlobObject blobObject = BlobObject.create(blobObjectKey);
//            objectStorageClient.save(inputStream, fileUploadDTO, blobObject);
//        }
    }

    private List<String> parseFirstRow(InputStream sheetStream,
                                       SharedStrings sharedStrings) throws IOException {
        ExcelSheetHandler handler = new ExcelSheetHandler(sharedStrings);;
        try {
            XMLReader parser = XMLHelper.newXMLReader();
            parser.setContentHandler(handler);
            parser.parse(new InputSource(sheetStream));
            throw new FileNotFoundException();
        } catch (StopParseException e) {
            return handler.getFirstRowCells();
        } catch (Exception e) {
            throw new IOException("Failed to parse Excel file", e);
        }
    }


    /**
     * Excel 工作表 SAX 事件处理器
     */
    private static class ExcelSheetHandler extends DefaultHandler {

        @Getter
        private final List<String> firstRowCells = new ArrayList<>();

        private final SharedStrings sharedStrings;

        private boolean isFirstRow = false;
        private boolean isCellValue = false;

        private String cellType; // s / inlineStr / null
        private final StringBuilder value = new StringBuilder();

        private int currentRow = -1;

        public ExcelSheetHandler(SharedStrings sharedStrings) {
            this.sharedStrings = sharedStrings;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            if ("row".equals(qName)) {
                currentRow = Integer.parseInt(attributes.getValue("r")) - 1;
                isFirstRow = currentRow == 0;
            }

            if (isFirstRow && "c".equals(qName)) {
                cellType = attributes.getValue("t"); // s / inlineStr / null
                value.setLength(0);
            }

            if (isFirstRow && ("v".equals(qName) || "t".equals(qName))) {
                isCellValue = true;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            if (isFirstRow && isCellValue) {
                value.append(ch, start, length);
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {

            if (isFirstRow && ("v".equals(qName) || "t".equals(qName))) {
                isCellValue = false;
            }

            if (isFirstRow && "c".equals(qName)) {
                String cellValue;

                if ("s".equals(cellType)) {
                    // shared string：value 是索引
                    int idx = Integer.parseInt(value.toString());
                    cellValue = sharedStrings.getItemAt(idx).getString();
                } else {
                    // number / inlineStr / 公式字符串等
                    cellValue = value.toString();
                }

                firstRowCells.add(cellValue.trim());
            }

            // 第一行读完就停
            if ("row".equals(qName) && currentRow == 0) {
                throw new StopParseException();
            }
        }
    }


    public static class StopParseException extends SAXException
    {
        /**
         * Constructor StopParseException.
         */
        StopParseException()
        {
            super("Stylesheet PIs found, stop the parse");
        }
    }

    private boolean validateFirstRow(List<String> firstRowCells) throws IOException {
        objectMapper.writeValue(System.out, firstRowCells);
        return true;
    }

}
