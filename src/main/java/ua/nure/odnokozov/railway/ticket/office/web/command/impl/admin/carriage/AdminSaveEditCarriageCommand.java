package ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.carriage;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.constant.ApplicationConstants;
import ua.nure.odnokozov.railway.ticket.office.constant.PagesConstants;
import ua.nure.odnokozov.railway.ticket.office.domain.Carriage;
import ua.nure.odnokozov.railway.ticket.office.service.CarriageService;
import ua.nure.odnokozov.railway.ticket.office.web.command.Command;

public class AdminSaveEditCarriageCommand implements Command {

    private static final Logger LOG = Logger.getLogger(AdminSaveEditCarriageCommand.class);

    private static final String REQUEST_MODEL_IMAGE = "modelImageURL";
    private static final String REQUEST_ERROR_ADD_CARRIAGE = "errorEditCarriage";

    private static final String REQUEST_CARRIAGE_ID = "carriageId";

    private CarriageService carriageService = new CarriageService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Start AdminAddCarriageCommand ");
        if (request.getParameter(REQUEST_CARRIAGE_ID) != null) {
            long carriageId = Long.valueOf(request.getParameter(REQUEST_CARRIAGE_ID));
            Optional<Carriage> carriage = carriageService.getCarriage(carriageId);
            if (carriage.isPresent()) {
                try {
                    uploadModelImage(request, carriage.get().getImage());
                    LOG.debug("Updating image was success");
                } catch (IOException | ServletException e) {
                    LOG.error("Exception while addCarriage()!" + e);
                }
            } else {
                setRequestErrorMessage(request, "Carriage is invalid");
            }
            return PagesConstants.REDIRECT_ADMIN_VIEW_ALL_CARRIAGES;
        }
        LOG.debug("Invalid request parameter, carriageId == null !");
        return PagesConstants.REDIRECT_ADMIN_VIEW_ALL_CARRIAGES;
    }

    private void setRequestErrorMessage(HttpServletRequest request, String message) {
        LOG.trace(message);
        request.setAttribute(REQUEST_ERROR_ADD_CARRIAGE, message);
    }

    private void uploadModelImage(HttpServletRequest request, String fileName) throws IOException, ServletException {
        LOG.debug("Start uploadModelImage()");
        String uploadPath = request.getServletContext().getRealPath("") + ApplicationConstants.IMAGES_DIR;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        Part filePart = request.getPart(REQUEST_MODEL_IMAGE);
        filePart.write(uploadPath + File.separator + fileName);
    }
}