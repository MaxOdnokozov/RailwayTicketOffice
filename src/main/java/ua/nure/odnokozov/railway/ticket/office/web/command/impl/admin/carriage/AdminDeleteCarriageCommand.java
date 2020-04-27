package ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.carriage;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.constant.ApplicationConstants;
import ua.nure.odnokozov.railway.ticket.office.constant.PagesConstants;
import ua.nure.odnokozov.railway.ticket.office.domain.Carriage;
import ua.nure.odnokozov.railway.ticket.office.service.CarriageService;
import ua.nure.odnokozov.railway.ticket.office.util.Validator;
import ua.nure.odnokozov.railway.ticket.office.web.command.Command;

public class AdminDeleteCarriageCommand implements Command {

    private static final Logger LOG = Logger.getLogger(AdminDeleteCarriageCommand.class);

    private static final String REQUEST_CARRIAGE_ID = "carriageId";
    private static final String REQUEST_MODEL_IMAGE_NAME = "modelImageName";
    private static final String ERROR_DELETE = "errorDelete";

    private CarriageService carriageService = new CarriageService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("Start AdminDeleteCarriageCommand ");
        
        if(request.getParameter(REQUEST_CARRIAGE_ID) == null) {
            LOG.warn("Request parameter is invalid");
            request.setAttribute(ERROR_DELETE, ERROR_DELETE);
            return PagesConstants.REDIRECT_ADMIN_VIEW_ALL_CARRIAGES;
        }
        
        long carriageId = Long.valueOf(request.getParameter(REQUEST_CARRIAGE_ID));
        if (!carriageService.deleteCarriage(carriageId)) {
            LOG.warn("Can't delete carriage");
            request.setAttribute(ERROR_DELETE, ERROR_DELETE);
            return PagesConstants.REDIRECT_ADMIN_VIEW_ALL_CARRIAGES;
        }
        LOG.debug("Carriage was deleted from database");
        deleteImageFileFromServer(request);
        List<Carriage> contextCarriages = removeCarriage(request, carriageId);
        request.getServletContext().setAttribute(ApplicationConstants.CONTEXT_CARRIAGES, contextCarriages);
        return PagesConstants.REDIRECT_ADMIN_VIEW_ALL_CARRIAGES;
    }

    @SuppressWarnings("unchecked")
    private List<Carriage> removeCarriage(HttpServletRequest request, long carriageId) {
        ServletContext servletContext = request.getServletContext();
        List<Carriage> contextCarriages = (List<Carriage>) servletContext
                .getAttribute(ApplicationConstants.CONTEXT_CARRIAGES);
        for (Carriage carriage : contextCarriages) {
            if (carriage.getId() == carriageId) {
                contextCarriages.remove(carriage);
                LOG.debug("Carriage was deleted from ServletContext");
            }
        }
        return contextCarriages;
    }

    private void deleteImageFileFromServer(HttpServletRequest request) {
        String removableFileName = request.getParameter(REQUEST_MODEL_IMAGE_NAME);
        ServletContext servletContext = request.getServletContext();
        String uploadImagePath = servletContext.getRealPath("") + ApplicationConstants.IMAGES_DIR;
        new File(uploadImagePath + File.separator + removableFileName).delete();
    }
}
