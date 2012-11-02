package com.ds.impl.core;

import com.ds.domain.core.FileAttachment;
import com.ds.pact.dao.BaseDao;
import com.ds.pact.service.core.FileAttachmentService;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author adlakha.vaibhav
 */
@Service
public class FileAttachmentServiceImpl implements FileAttachmentService {


  @Autowired
  private BaseDao baseDAO;

  public FileAttachment uploadFile(String fileName, String contentType, long fileSize, InputStream inputStream) throws IOException {
    FileAttachment fileAttachment = new FileAttachment();
    fileAttachment.setFileName(fileName);
    fileAttachment.setMimeType(contentType);
    fileAttachment.setFileSize(fileSize);
    fileAttachment.setFileContent(IOUtils.toByteArray(inputStream));

    if (contentType.startsWith("image")) {
      BufferedImage image = ImageIO.read(new ByteArrayInputStream(fileAttachment.getFileContent()));
      fileAttachment.setWidth(image.getWidth());
      fileAttachment.setHeight(image.getHeight());
    }

    getBaseDAO().save(fileAttachment);
    return fileAttachment;
  }

  public void deleteFile(Long fileAttachmentId) {
    FileAttachment fileAttachment = loadFileAttachment(fileAttachmentId);
    getBaseDAO().delete(fileAttachment);
  }

  public BaseDao getBaseDAO() {
    return baseDAO;
  }

  public void setBaseDAO(BaseDao baseDAO) {
    this.baseDAO = baseDAO;
  }

  @Override
  public FileAttachment loadFileAttachment(Long fileAttachmentId) {
    return getBaseDAO().get(FileAttachment.class, fileAttachmentId);
  }

}
