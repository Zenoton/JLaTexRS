package com.zenoton.jlatexrs.directoryBrowser;

public enum FileType {

    FOLDER("text/html",false),
    RAW_FILE("application/octet-stream",true),
    PDF("application/pdf",false),
    LATEX("application/octet-stream",true),
    HTML("text/html",false);

    public String MIME_TYPE;

    public boolean DOWNLOAD;

    FileType(String MIMEType, boolean DOWNLOAD){
        this.MIME_TYPE = MIMEType;
        this.DOWNLOAD = DOWNLOAD;
    }
}
