package org.kaminodroid.web.qr;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

@WebServlet(urlPatterns = { "/qrcode" })
public class QrCodeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("image/png");
        ServletOutputStream outputStream = response.getOutputStream();
        ByteArrayOutputStream qrCodeStream = QRCode.from("http://google.de").to(ImageType.PNG).withSize(250, 250)
                .stream();
        byte[] qrCodeBytes = qrCodeStream.toByteArray();
        response.setContentLength(qrCodeBytes.length);
        outputStream.write(qrCodeBytes);
    }

}
