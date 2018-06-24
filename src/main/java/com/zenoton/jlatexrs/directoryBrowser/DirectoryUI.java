package com.zenoton.jlatexrs.directoryBrowser;

import org.apache.catalina.servlets.DefaultServlet;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(value="/rs")
public class DirectoryUI implements ApplicationContextAware {

    ApplicationContext context;

    @Autowired
    private ServletContext servletContext;

    @RequestMapping(path = {"/**","/"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> getFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String restOfTheUrl = ((String) request.getAttribute(
                HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE)).substring(4);

        FileHandler handler = new FileHandler(restOfTheUrl, context);

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.parseMediaType(handler.getFileType().MIME_TYPE));

        headers.add("content-disposition",
                (handler.getFileType().DOWNLOAD?"attachment":"inline")
                +"filename=" + handler.getFileName());

        return new ResponseEntity<> (IOUtils.toByteArray(handler.getFile(request,response,servletContext)),headers, handler.Error404() ? HttpStatus.NOT_FOUND:HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public void redirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(request.getRequestURI()+"/");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
