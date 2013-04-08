package org.kaminodroid.web.qr;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import org.kaminodroid.client.KaminoDroidClient;

@WebServlet(urlPatterns = { "/qrcode" })
public class QrCodeServlet extends HttpServlet {

    private KaminoDroidClient restClient;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        // restClient = new KaminoDroidClient("http://localhost:8080/rest/");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        // restClient.getApplication("");
        response.setContentType("image/png");
        ServletOutputStream outputStream = response.getOutputStream();
        ByteArrayOutputStream qrCodeStream = QRCode.from("http://google.de").to(ImageType.PNG).withSize(250, 250)
                .stream();
        byte[] qrCodeBytes = qrCodeStream.toByteArray();
        response.setContentLength(qrCodeBytes.length);
        outputStream.write(qrCodeBytes);
    }
}
