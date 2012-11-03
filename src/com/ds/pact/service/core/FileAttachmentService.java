package com.ds.pact.service.core;

import com.ds.domain.core.FileAttachment;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author adlakha.vaibhav
 */
public interface FileAttachmentService {

  /**
     * Uploads a File on Server
     *
     * @param fileName : Name of File
     * @param contentType : Content Type
     * @param fileSize : Size of File
     * @param inputStream : Stream to the file content
     * @return FileAttachment Dataobject representing file
     * @throws IOException
     */
    public FileAttachment uploadFile(String fileName, String contentType, long fileSize, InputStream inputStream) throws IOException;

    /**
     * Deletes file specified by fileAttachmentId is deleted from server
     *
     * @param fileAttachmentId
     */
    public void deleteFile(Long fileAttachmentId);

    /**
     * Loads a File Attachment specified by fileAttachmentId
     *
     * @param fileAttachmentId
     * @return {@link FileAttachment} loads FileAttachment
     */
    public FileAttachment loadFileAttachment(Long fileAttachmentId);
}
