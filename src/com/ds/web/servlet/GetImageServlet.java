package com.ds.web.servlet;

import com.ds.domain.core.FileAttachment;
import com.ds.impl.service.ServiceLocatorFactory;
import com.ds.pact.service.core.FileAttachmentService;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author adlakha.vaibhav
 */
public class GetImageServlet extends HttpServlet {

    private FileAttachmentService fileAttachmentService;

    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String imageId = request.getParameter("imageId");
        if (StringUtils.isBlank(imageId)) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        FileAttachment fileAttachment = getFileAttachmentService().loadFileAttachment(Long.parseLong(imageId));
        if (fileAttachment == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        IOUtils.copy(new ByteArrayInputStream(fileAttachment.getFileContent()), resp.getOutputStream());
        resp.setContentType(fileAttachment.getMimeType());
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