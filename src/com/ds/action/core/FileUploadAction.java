package com.ds.action.core;

import com.ds.domain.core.FileAttachment;
import com.ds.pact.service.core.FileAttachmentService;
import com.ds.pact.service.core.FileManageService;
import com.ds.web.action.BaseAction;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author adlakha.vaibhav
 */
@Component
public class FileUploadAction extends BaseAction {

  private Logger logger = LoggerFactory.getLogger(FileUploadAction.class);

  private int fileManageType;
  private String companyShortName;
  private String userId;
  

  @Autowired
  private FileAttachmentService fileAttachmentService;
  @Autowired
  private FileManageService fileManageService;

  @DefaultHandler
  public Resolution uploadFile() {
    HttpServletRequest request = getContext().getRequest();

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
          //handleFileUpload(fileAttachment);
        }
      }

    } catch (Throwable e) {
      logger.error("Error while  uploading file", e);
    }

    return null;
  }



  public FileAttachmentService getFileAttachmentService() {
    return fileAttachmentService;
  }

  public int getFileManageType() {
    return fileManageType;
  }

  public void setFileManageType(int fileManageType) {
    this.fileManageType = fileManageType;
  }

  public String getCompanyShortName() {
    return companyShortName;
  }

  public void setCompanyShortName(String companyShortName) {
    this.companyShortName = companyShortName;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
  

  public FileManageService getFileManageService() {
    return fileManageService;
  }
}
