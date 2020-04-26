package ua.nure.odnokozov.railway.ticket.office.service;

import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.dao.LanguageDao;
import ua.nure.odnokozov.railway.ticket.office.dao.impl.LanguageDaoImpl;
import ua.nure.odnokozov.railway.ticket.office.domain.Language;

public class LanguageService {

    private static final Logger LOG = Logger.getLogger(LanguageService.class);

    private LanguageDao languageDao = new LanguageDaoImpl();

    public List<Language> getAll() {
        LOG.trace("Getting all languages");
        return languageDao.getAll();
    }
}
