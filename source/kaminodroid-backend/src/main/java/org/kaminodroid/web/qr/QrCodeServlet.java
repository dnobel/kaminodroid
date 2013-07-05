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

@WebServlet(urlPatterns = { "/qrcode" })
public class QrCodeServlet extends HttpServlet {

	private static final long serialVersionUID = 3034545696981966841L;
	private static final String PARAMETER_URL = "url";
	private static final String IMAGE_PNG = "image/png";

    @Override

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType(IMAGE_PNG);

        ServletOutputStream outputStream = response.getOutputStream();
		String url = request.getParameter(PARAMETER_URL);
		ByteArrayOutputStream qrCodeStream = QRCode.from(url).to(ImageType.PNG).withSize(250, 250)
                .stream();
        byte[] qrCodeBytes = qrCodeStream.toByteArray();

        response.setContentLength(qrCodeBytes.length);
        outputStream.write(qrCodeBytes);
    }
}
