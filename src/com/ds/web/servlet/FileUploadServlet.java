package com.ds.web.servlet;

import com.ds.action.company.CompanyAction;
import com.ds.action.marketing.MarketingMaterialAction;
import com.ds.constants.FileManageType;
import com.ds.domain.core.FileAttachment;
import com.ds.impl.service.ServiceLocatorFactory;
import com.ds.pact.service.core.FileAttachmentService;
import com.ds.pact.service.core.FileManageService;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.util.ssl.SslUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * @author adlakha.vaibhav
 */
public class FileUploadServlet extends HttpServlet {
  private Logger logger = LoggerFactory.getLogger(FileUploadServlet.class);


  private FileAttachmentService fileAttachmentService;
  private FileManageService fileManageService;


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

      ServletMultiPartRequestUtil multiPartRequestUtil = new ServletMultiPartRequestUtil(request);
      List<FileItem> items = multiPartRequestUtil.getFileItems();
      String identifier = multiPartRequestUtil.getFormFieldValue("identifier");
      String fileManageTypeStr = multiPartRequestUtil.getFormFieldValue("fileManageType");
      int fileManageType = Integer.parseInt(fileManageTypeStr);
      for (FileItem fileItem : items) {
        if (!fileItem.isFormField()) {
          FileAttachment fileAttachment = getFileAttachmentService().uploadFile(fileItem.getName(), fileItem.getContentType(), fileItem.getSize(),
              fileItem.getInputStream());
          handleFileUpload(fileManageType, identifier, fileAttachment);
        }
      }
      handleFileUploadSuccess(request, resp, fileManageType, identifier);
    } catch (Throwable e) {
      logger.error("Error while  uploading file", e);
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "File Upload Failed");
    }

  }

  private void handleFileUploadSuccess(HttpServletRequest request, HttpServletResponse response, int fileManageType, String identifier) {
    try {
      RedirectResolution redirectResolution ;
      switch (fileManageType) {
        case FileManageType.COMPANY_LOGO:

          redirectResolution = new RedirectResolution(CompanyAction.class).addParameter("companyShortName", identifier);
          response.sendRedirect(getUrlFromResolution(request,response, redirectResolution));
          //request.getRequestDispatcher(getUrlFromResolution(request,response, redirectResolution)).forward(request, response);
          //return;
          break;
        case FileManageType.MARKETING_MATERIAL:
          redirectResolution = new RedirectResolution(MarketingMaterialAction.class).addParameter("marketingMaterialId", Long.parseLong(identifier));
          response.sendRedirect(getUrlFromResolution(request,response, redirectResolution));
          break;
          
      }
    } catch (Throwable e) {
      logger.error("Error while  uploading file", e);

    }
  }

  private String getUrlFromResolution(HttpServletRequest request, HttpServletResponse response, RedirectResolution redirectResolution) {
        String url = redirectResolution.getUrl(Locale.getDefault());

        String contextPath = request.getContextPath();
        return SslUtil.encodeUrlFullForced(request, response, url, contextPath);
    }


  private void handleFileUpload(int fileManageType, String identifier, FileAttachment fileAttachment) {

    switch (fileManageType) {

      case FileManageType.COMPANY_LOGO:
        getFileManageService().associateCompanyLogo(identifier, fileAttachment.getId());
        break;
      case FileManageType.MARKETING_MATERIAL:
        getFileManageService().associateMaketingMaterialImage(Long.parseLong(identifier), fileAttachment.getId());
        break;

    }
  }

  public FileAttachmentService getFileAttachmentService() {
    if (this.fileAttachmentService == null) {
      this.fileAttachmentService = ServiceLocatorFactory.getService(FileAttachmentService.class);
    }
    return fileAttachmentService;
  }

  public FileManageService getFileManageService() {
    if (this.fileManageService == null) {
      this.fileManageService = ServiceLocatorFactory.getService(FileManageService.class);
    }
    return fileManageService;

  }

  public void setFileAttachmentService(FileAttachmentService fileAttachmentService) {
    this.fileAttachmentService = fileAttachmentService;
  }
}
