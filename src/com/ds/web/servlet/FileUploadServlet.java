package com.ds.web.servlet;

import com.ds.domain.core.FileAttachment;
import com.ds.impl.service.ServiceLocatorFactory;
import com.ds.pact.service.core.FileAttachmentService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author adlakha.vaibhav
 */
public class FileUploadServlet extends HttpServlet {

  private FileAttachmentService fileAttachmentService;

    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        doPost(request, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        // Parse the request
        try {
            List<FileItem> items = upload.parseRequest(request);
            for (FileItem fileItem : items) {
                if (!fileItem.isFormField()) {
                    FileAttachment fileAttachment = getFileAttachmentService().uploadFile(fileItem.getName(), fileItem.getContentType(), fileItem.getSize(),
                            fileItem.getInputStream());
                    StringBuilder sb = new StringBuilder();
                    sb.append("{");
                    sb.append(" \"fileName\": ").append("\"").append(fileAttachment.getFileName()).append("\",");
                    sb.append(" \"mimeType\": ").append("\"").append(fileAttachment.getMimeType()).append("\",");
                    sb.append(" \"fileSize\": ").append("\"").append(fileAttachment.getFileSize()).append("\",");
                    sb.append(" \"key\": ").append("\"").append(fileAttachment.getId()).append("\",");
                    sb.append(" \"fileUrl\": ").append("\"/getImage?imageId=").append(fileAttachment.getId()).append("\",");
                    sb.append(" \"height\": ").append(fileAttachment.getHeight()).append(",");
                    sb.append(" \"width\": ").append(fileAttachment.getWidth());
                    sb.append("}");
                    // resp.setContentType("application/json");
                    resp.getWriter().write(sb.toString());
                }
            }

        } catch (Throwable e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "File Upload Failed");
        }
    }

    public FileAttachmentService getFileAttachmentService() {
        if (this.fileAttachmentService == null) {
            this.fileAttachmentService = ServiceLocatorFactory.getService(FileAttachmentService.class);
        }
        return fileAttachmentService;
    }

    public void setFileAttachmentService(FileAttachmentService fileAttachmentService) {
        this.fileAttachmentService = fileAttachmentService;
    }
}
