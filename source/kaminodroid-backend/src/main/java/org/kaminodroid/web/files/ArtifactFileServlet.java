package org.kaminodroid.web.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.wicket.util.file.FileCleaner;
import org.apache.wicket.util.upload.DiskFileItemFactory;
import org.apache.wicket.util.upload.FileItem;
import org.apache.wicket.util.upload.FileItemFactory;
import org.apache.wicket.util.upload.FileUploadException;
import org.apache.wicket.util.upload.ServletFileUpload;

@WebServlet(urlPatterns = { "/artifacts" })
public class ArtifactFileServlet extends HttpServlet {
    private static final String ARTIFACTS_FOLDER = "artifacts";
    static final int BUFFER_SIZE = 16384;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        File file = getFileToDownload(request);
        if (file != null) {
            prepareResponseFor(response, file);
            streamFileTo(response, file);
        }
        else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
    IOException {
        response.setContentType("text/html");

        new File(ARTIFACTS_FOLDER).mkdir();

        boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
        if (!isMultipartContent) {
            return;
        }

        FileItemFactory factory = new DiskFileItemFactory(new FileCleaner());
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> fields = upload.parseRequest(request);
            Iterator<FileItem> it = fields.iterator();

            while (it.hasNext()) {
                FileItem fileItem = it.next();
                String uuid = UUID.randomUUID().toString();
                String fileName = getFileName(fileItem, uuid);
                fileItem.write(new File(fileName));

                String artifactUrl = request.getRequestURL().toString() + "?uuid=" + uuid;
                response.getOutputStream().write(artifactUrl.getBytes());
            }
        }
        catch (FileUploadException e) {
            e.printStackTrace();
        }
    }

    private String getFileExtension(String fileName) {
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            return fileName.substring(i + 1);
        }
        return ".unkown";
    }

    private String getFileName(FileItem fileItem, String uuid) {
        return ARTIFACTS_FOLDER + "/" + uuid + "." + getFileExtension(fileItem.getName());
    }

    private File getFileToDownload(HttpServletRequest request) {

        final String uuid = request.getParameter("uuid");

        File artifactsFolder = new File(ARTIFACTS_FOLDER);
        if (artifactsFolder.exists()) {
            File[] files = artifactsFolder.listFiles(new FilenameFilter() {

                public boolean accept(File dir, String name) {
                    return name.contains(uuid);
                }
            });

            if (files != null && files.length == 1) {
                return files[0];
            }

        }
        return null;
    }

    private void prepareResponseFor(HttpServletResponse response, File file) {
        StringBuilder type = new StringBuilder("attachment; filename=");
        type.append(file.getName());
        response.setContentLength((int) file.length());
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", type.toString());
    }

    private void streamFileTo(HttpServletResponse response, File file) throws IOException, FileNotFoundException {
        OutputStream os = response.getOutputStream();
        FileInputStream fis = new FileInputStream(file);
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = 0;
        while ((bytesRead = fis.read(buffer)) > 0) {
            os.write(buffer, 0, bytesRead);
        }
        os.flush();
        fis.close();
    }
}
