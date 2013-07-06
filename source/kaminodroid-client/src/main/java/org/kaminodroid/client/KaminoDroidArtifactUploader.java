package org.kaminodroid.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;

public class KaminoDroidArtifactUploader {

    private final String fileUploadUrl;

    public KaminoDroidArtifactUploader(String fileUploadUrl) {
        this.fileUploadUrl = fileUploadUrl;
    }

    public String uploadFile(File fileToUpload) throws IOException {

        String boundary = Long.toHexString(System.currentTimeMillis());
        URLConnection connection = new URL(this.fileUploadUrl).openConnection();
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        PrintWriter writer = null;
        try {
            OutputStream outputStream = connection.getOutputStream();
            writer = new PrintWriter(new OutputStreamWriter(outputStream), true);

            writer.println("--" + boundary);
            writer.println("Content-Disposition: form-data; name=\"fileToUpload\"; filename=\""
                    + fileToUpload.getName() + "\"");
            writer.println("Content-Type: " + URLConnection.guessContentTypeFromName(fileToUpload.getName()));
            writer.println("Content-Transfer-Encoding: binary");
            writer.println();

            InputStream is = new FileInputStream(fileToUpload);
            try {
                byte[] buffer = new byte[4096];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }
                outputStream.flush();
            }
            finally {
                is.close();
            }
            writer.println();
            writer.println("--" + boundary + "--");
        }
        finally {
            if (writer != null) {
                writer.close();
            }
        }

        return IOUtils.toString(((HttpURLConnection) connection).getInputStream());
    }

}