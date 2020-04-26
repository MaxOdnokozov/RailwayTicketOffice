package ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.carriage;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.constant.ApplicationConstants;
import ua.nure.odnokozov.railway.ticket.office.constant.PagesConstants;
import ua.nure.odnokozov.railway.ticket.office.domain.Carriage;
import ua.nure.odnokozov.railway.ticket.office.domain.builder.CarriageBuilder;
import ua.nure.odnokozov.railway.ticket.office.enums.ComfortType;
import ua.nure.odnokozov.railway.ticket.office.service.CarriageService;
import ua.nure.odnokozov.railway.ticket.office.util.Validator;
import ua.nure.odnokozov.railway.ticket.office.web.command.Command;

public class AdminAddCarriageCommand implements Command {

    private static final Logger LOG = Logger.getLogger(AdminAddCarriageCommand.class);

    private static final String REQUEST_MODEL_NAME = "modelName";
    private static final String REQUEST_TOTAL_SEATS = "totalSeats";
    private static final String REQUEST_COMFORT_TYPE = "comfortType";
    private static final String REQUEST_PRICE_COEFFICIENT = "priceCoefficient";
    private static final String REQUEST_MODEL_IMAGE = "modelImageURL";
    private static final String REQUEST_ERROR_ADD_CARRIAGE = "errorAddCarriage";

    private static final int MAX_MODEL_NAME_LENGTH = 20;
    private static final int MIN_TOTAL_SEATS_VALUE = 0;
    private static final int MAX_TOTAL_SEATS_VALUE = 200;
    private static final double MIN_PRICE_COEFFICIENT_VALUE = 1.00;
    private static final double MAX_PRICE_COEFFICIENT_VALUE = 10.00;
    private static final int FILE_NAME_MAX_LENGHT = 30;
    private static final int FILE_NAME_NEW_LENGHT = 20;

    private CarriageService carriageService = new CarriageService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Start AdminAddCarriageCommand ");
        if (isValidRequestParamenters(request)) {
            try {
                Optional<Carriage> carriage = carriageService.addCarriage(getCarriage(request));
                if (carriage.isPresent()) {
                    uploadModelImage(request, carriage.get().getImage());
                    updateContextCarriages(request, carriage.get());
                    return PagesConstants.REDIRECT_ADMIN_VIEW_ALL_CARRIAGES;
                } else {
                    setRequestErrorMessage(request, "Carriage is invalid");
                }
            } catch (IOException | ServletException e) {
                setRequestErrorMessage(request, "modelImageURL is invalid");
                LOG.trace("Exception while addCarriage()!" + e);
            }
        }
        setRequestAttributes(request);
        return PagesConstants.ADMIN_ADD_CARRIAGE_PAGE;
    }

    private void setRequestAttributes(HttpServletRequest request) {
        LOG.debug("Rewrite attributes in request");
        request.setAttribute(REQUEST_MODEL_NAME, request.getParameter(REQUEST_MODEL_NAME));
        request.setAttribute(REQUEST_TOTAL_SEATS, request.getParameter(REQUEST_TOTAL_SEATS));
        request.setAttribute(REQUEST_PRICE_COEFFICIENT, request.getParameter(REQUEST_PRICE_COEFFICIENT));
    }

    private boolean isValidRequestParamenters(HttpServletRequest request) {
        if (!isValidModelName(request) || !isValidTotalSeats(request) || !isValidPriceCoefficient(request)
                || !isValidComfortType(request)) {
            LOG.debug("Request parameters are incorect");
            return false;
        }
        LOG.debug("Request parameters are corect");
        return true;
    }

    private boolean isValidModelName(HttpServletRequest request) {
        if (!Validator.isFilled(request.getParameter(REQUEST_MODEL_NAME), MAX_MODEL_NAME_LENGTH)) {
            setRequestErrorMessage(request, "modelName is incorect");
            return false;
        }
        return true;
    }

    private void setRequestErrorMessage(HttpServletRequest request, String message) {
        LOG.trace(message);
        request.setAttribute(REQUEST_ERROR_ADD_CARRIAGE, message);
    }

    private boolean isValidTotalSeats(HttpServletRequest request) {
        if (!Validator.isIntegerFromInterval(request.getParameter(REQUEST_TOTAL_SEATS), MIN_TOTAL_SEATS_VALUE,
                MAX_TOTAL_SEATS_VALUE)) {
            setRequestErrorMessage(request, "totalSeats is incorect");
            return false;
        }
        return true;
    }

    private boolean isValidPriceCoefficient(HttpServletRequest request) {
        if (!Validator.isDoubleFromInterval(request.getParameter(REQUEST_PRICE_COEFFICIENT),
                MIN_PRICE_COEFFICIENT_VALUE, MAX_PRICE_COEFFICIENT_VALUE)) {
            setRequestErrorMessage(request, "priceCoefficient is incorect");
            return false;
        }
        return true;
    }

    private boolean isValidComfortType(HttpServletRequest request) {
        String comfortType = request.getParameter(REQUEST_COMFORT_TYPE);
        LOG.trace("ComfortType :: " + comfortType);
        @SuppressWarnings("unchecked")
        List<String> comfortTypes = (List<String>) request.getServletContext()
                .getAttribute(ApplicationConstants.CONTEXT_COMFORT_TYPES);
        LOG.trace("comfortTypes :: " + comfortTypes);
        if (!Validator.isFilled(comfortType) || !comfortTypes.contains(comfortType)) {
            setRequestErrorMessage(request, "ComfortType is incorect");
            return false;
        }
        return true;
    }

    private Carriage getCarriage(HttpServletRequest request) throws IOException, ServletException {
        return CarriageBuilder.getInstance()
                .model(request.getParameter(REQUEST_MODEL_NAME))
                .totalSeats(Integer.valueOf(request.getParameter(REQUEST_TOTAL_SEATS)))
                .priceCoefficient(BigDecimal.valueOf(Double.valueOf(request.getParameter(REQUEST_PRICE_COEFFICIENT))))
                .comfortType(ComfortType.valueOf(request.getParameter(REQUEST_COMFORT_TYPE)))
                .image(getModelImageName(request))
                .build();
    }

    private String getModelImageName(HttpServletRequest request) throws IOException, ServletException {
        Part filePart = request.getPart(REQUEST_MODEL_IMAGE);
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        LOG.trace("fileName :: " + fileName);
        if (fileName.length() > FILE_NAME_MAX_LENGHT) {
            fileName = fileName.substring(fileName.length() - FILE_NAME_NEW_LENGHT);
        }
        String newFileName = System.nanoTime() + fileName;
        LOG.trace("newFileName :: + " + newFileName);
        return newFileName;
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

    private void updateContextCarriages(HttpServletRequest request, Carriage carriage) {
        ServletContext servletContext = request.getServletContext();
        @SuppressWarnings("unchecked")
        List<Carriage> contextCarriages = (List<Carriage>) servletContext
                .getAttribute(ApplicationConstants.CONTEXT_CARRIAGES);
        contextCarriages.add(carriage);
        servletContext.setAttribute(ApplicationConstants.CONTEXT_CARRIAGES, contextCarriages);      
    }
}