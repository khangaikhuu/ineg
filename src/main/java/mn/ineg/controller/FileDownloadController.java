/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.ineg.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author developer
 */
@Controller
@RequestMapping("/download")
public class FileDownloadController {
    private static final String ROOT_PATH = "/Users/developer/uploadingdir";
    
    @RequestMapping(value = "/{folder}/{filename}" , method = RequestMethod.GET)
    public void downloadFile(
            @PathVariable("folder") String folderName,
            @PathVariable("filename") String fileName,
            HttpServletResponse response) throws IOException {
        File file = null;
        System.out.println("Root Path : " + ROOT_PATH);
        System.out.println("foldername : " + folderName);
        System.out.println("filename : " + fileName);
        System.out.println("FULL PATH : " + ROOT_PATH + "/"+ folderName + "/" + fileName + ".pdf");
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        file = new File(ROOT_PATH + "/"+ folderName + "/" + fileName + ".pdf");
        if (!file.exists()) {
            String errorMessage = "Таны файл байхгүй байна.";
            System.out.println(errorMessage);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            return;
        }

        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        if (mimeType == null) {
            System.out.println("mimetype is not detectable, will take default");
            mimeType = "application/octet-stream";
        }

        System.out.println("mimetype : " + mimeType);

        response.setContentType(mimeType);

        /* "Content-Disposition : inline" will show viewable types [like images/text/pdf/anything viewable by browser] right on browser 
            while others(zip e.g) will be directly downloaded [may provide save as popup, based on your browser setting.]*/
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));

        /* "Content-Disposition : attachment" will be directly download, may provide save as popup, based on your browser setting*/
        //response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
        response.setContentLength((int) file.length());

        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

        //Copy bytes from source to destination(outputstream in this example), closes both streams.
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }

}
