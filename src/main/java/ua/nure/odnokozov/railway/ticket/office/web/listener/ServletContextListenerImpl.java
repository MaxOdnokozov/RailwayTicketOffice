package ua.nure.odnokozov.railway.ticket.office.web.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.constant.ApplicationConstants;
import ua.nure.odnokozov.railway.ticket.office.domain.Carriage;
import ua.nure.odnokozov.railway.ticket.office.domain.Language;
import ua.nure.odnokozov.railway.ticket.office.domain.Station;
import ua.nure.odnokozov.railway.ticket.office.enums.ComfortType;
import ua.nure.odnokozov.railway.ticket.office.service.CarriageService;
import ua.nure.odnokozov.railway.ticket.office.service.LanguageService;
import ua.nure.odnokozov.railway.ticket.office.service.StationService;

public class ServletContextListenerImpl implements ServletContextListener {

    private static final Logger LOG = Logger.getLogger(ServletContextListenerImpl.class);

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOG.trace("Destroy ServletContext");
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        LOG.debug("Initializing ServletContext");

        ServletContext servletContext = event.getServletContext();
        initContextStations(servletContext);
        initContextLanguages(servletContext);
        initContextComfortTypes(servletContext);
        initContextCarriages(servletContext);
        initImagesDir(servletContext);
    }

    private void initContextStations(ServletContext servletContext) {
        LOG.debug("Initialazing stations in ServletContext");
        List<Station> contextStations = new StationService().getAll();
        servletContext.setAttribute(ApplicationConstants.CONTEXT_STATIONS, contextStations);
    }

    private void initContextLanguages(ServletContext servletContext) {
        LOG.debug("Initialazing languages in ServletContext");
        List<Language> contextLanguages = new LanguageService().getAll();
        LOG.trace("Languages :: " + contextLanguages);
        List<String> locales = contextLanguages.stream().map(lang -> lang.getLabel()).collect(Collectors.toList());
        servletContext.setAttribute("locales", locales);
        servletContext.setAttribute(ApplicationConstants.CONTEXT_LANGUAGES, contextLanguages);
    }

    private void initContextComfortTypes(ServletContext servletContext) {
        LOG.debug("Initializing ComfortType in ServletContext");
        List<String> contextComfortTypes = new ArrayList<>();
        ComfortType[] types = ComfortType.values();
        for (ComfortType type : types) {
            contextComfortTypes.add(type.getName());
        }
        LOG.debug("ComfortTypes :: " + contextComfortTypes);
        servletContext.setAttribute(ApplicationConstants.CONTEXT_COMFORT_TYPES, contextComfortTypes);
    }

    private void initContextCarriages(ServletContext servletContext) {
        LOG.debug("Initialazing carriages in ServletContext");
        List<Carriage> contextCarriages = new CarriageService().getAll();
        LOG.debug("Carriages :: " + contextCarriages);
        servletContext.setAttribute(ApplicationConstants.CONTEXT_CARRIAGES, contextCarriages);
    }

    private void initImagesDir(ServletContext servletContext) {
        LOG.debug("Initialazing imagesDir in ServletContext");
        servletContext.setAttribute("contextImagesDir", ApplicationConstants.IMAGES_DIR);
        LOG.debug("ImagesDir :: " + ApplicationConstants.IMAGES_DIR);
    }
}
