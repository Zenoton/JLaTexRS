package com.zenoton.jlatexrs.directoryBrowser;

import com.zenoton.jlatexrs.settings.SettingsHandler;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.Nullable;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * A class responsible for serving up any files in the /rs/ directory.
 * It's state is based on the file that is getting accessed.
 */

public class FileHandler {

    /**
     * The application context to render the views
     */
    ApplicationContext context;

    /**
     * The settings of the application
     */
    private SettingsHandler settingsHandler;

    /**
     * ThymeLeaf template engine
     */
    private SpringTemplateEngine templateEngine;

    /**
     * The path of the file that we are trying to access (relative)
     */
    private final String relativePath;

    /**
     * It the file is not found in the specified path
     */
    private final boolean FileNotFound;

    /**
     * If the file location is a directory
     */
    private final boolean Directory;

    /**
     * The file itself
     */
    @Nullable
    private final InputStream file;


    /**
     * Constructor for the class
     * @param relativePath the path to the file
     * @param context
     * @throws FileNotFoundException
     */
    public FileHandler(String relativePath, ApplicationContext context) throws FileNotFoundException {

        this.relativePath = relativePath;

        this.context = context;

        this.settingsHandler = context.getBean(SettingsHandler.class);

        this.templateEngine = context.getBean(SpringTemplateEngine.class);

        if(IsGenerated()){
            file = null;
            FileNotFound = false;
            Directory = false;
            return;
        }
        InputStream fileTemp;
        boolean FileNotFoundTemp;

        if(!new File(settingsHandler.getReportPath()+"/"+relativePath).exists()){
            file = null;
            this.FileNotFound = true;
            Directory = false;
            return;
        }

        Directory = new File(settingsHandler.getReportPath()+"/"+relativePath).isDirectory();

        if(!Directory){
            file = new FileInputStream(settingsHandler.getReportPath()+"/"+relativePath);
            this.FileNotFound = false;
            return;
        }

        file = null;
        FileNotFound = false;
        return;
    }

    public FileType getFileType(){
        if(FileNotFound) return FileType.HTML;
        String extension = FilenameUtils.getExtension(relativePath);
        switch (extension){
            case "":
                //We are dealing with a folder
                return FileType.FOLDER;
            case "pdf":
                return FileType.PDF;
            case "html":
                return FileType.HTML;
            case "rtex":
                return FileType.LATEX;
            default:
                return FileType.RAW_FILE;
        }
    }

    public boolean IsGenerated(){
        return (FilenameUtils.getExtension(relativePath).equals("pdf") || FilenameUtils.getExtension(relativePath).equals("html"))
                && new File(settingsHandler.getReportPath()+"/"+FilenameUtils.getPath(relativePath)+FilenameUtils.getBaseName(relativePath)+".rtex").exists();
    }

    public String getFileName(){
        return  FilenameUtils.getName(relativePath);
    }

    public boolean Error404(){
        return FileNotFound;
    }

    public InputStream getFile(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws IOException {
        if(FileNotFound){
            WebContext ThymeContext = new WebContext(request,response,context);
            String html = templateEngine.process("directoryBrowser/404_directory",ThymeContext);
            return IOUtils.toInputStream(html,"UTF-8");
        }

        if(getFileType() == FileType.FOLDER){
            WebContext ThymeContext = new WebContext(request,response,context);
            ThymeContext.setVariable("folder", FilenameUtils.getName(relativePath.equals("") ? "root" : relativePath));
            String html = templateEngine.process("directoryBrowser/directory",ThymeContext);
            return IOUtils.toInputStream(html,"UTF-8");
        }

        if(IsGenerated()){
            return IOUtils.toInputStream("NOT IMPLEMENTED","UTF-8");
        }
        return file;
    }


}
