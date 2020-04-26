package ua.nure.odnokozov.railway.ticket.office.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.dao.LanguageDao;
import ua.nure.odnokozov.railway.ticket.office.domain.Language;

public class LanguageDaoImpl extends AbstractDao<Language> implements LanguageDao {

    private static final Logger LOG = Logger.getLogger(LanguageDaoImpl.class);
    
    private static final String SQL_SELECT_LANGUAGE_BY_ID = "SELECT * FROM languages WHERE id=? LIMIT 1";
    private static final String SQL_SELECT_LANGUAGE_BY_LABEL = "SELECT * FROM languages WHERE label=? LIMIT 1";
    private static final String SQL_SELECT_ALL_LANGUAGES = "SELECT * FROM languages";
    private static final String SQL_INSERT_INTO_LANGUAGES = "INSERT INTO languages VALUES(default, ?)";
    private static final String SQL_UPDATE_LANGUAGE = "UPDATE languages Set label=? WHERE id=? LIMIT 1";
    private static final String SQL_DELETE_LANGUAGE_BY_ID = "DELETE FROM languages WHERE id=?";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_LABEL = "label";

    @Override
    public Language getById(long id) {
        LOG.debug("Getting language from the database by id :: id =" + id);
        return getByParameter(SQL_SELECT_LANGUAGE_BY_ID, ps -> ps.setLong(1, id), getMapper());
    }
    
    @Override
    public Language getByLabel(String label) {
        LOG.debug("Getting language from the database by label :: label =" + label);
        return getByParameter(SQL_SELECT_LANGUAGE_BY_LABEL, ps -> ps.setString(1, label), getMapper());
    }

    public EntityMapper<Language> getMapper() {
        LOG.debug("Mapping language from ResultSet");
        return resultSet -> new Language(resultSet.getLong(COLUMN_ID), resultSet.getString(COLUMN_LABEL));
    }

    @Override
    public boolean create(Language entity) {
        LOG.debug("Creating a new language in the databese :: " + entity);
        long generatedId = create(SQL_INSERT_INTO_LANGUAGES, ps -> ps.setString(1, entity.getLabel()));
        return generatedId > 0;
    }

    @Override
    public boolean update(Language entity) {
        LOG.debug("Updating language in the databese :: " + entity);
        int updatedRow = updateDelete(SQL_UPDATE_LANGUAGE, ps -> {
            int count = 1;
            ps.setString(count++, entity.getLabel());
            ps.setLong(count, entity.getId());
        });
        return updatedRow == 1;
    }

    @Override
    public boolean delete(long id) {
        LOG.debug("Deleting language from the database by id :: id = " + id);
        int updatedRow = updateDelete(SQL_DELETE_LANGUAGE_BY_ID, ps -> ps.setLong(1, id));
        return updatedRow == 1;
    }

    @Override
    public List<Language> getAll() {
        LOG.debug("Getting all languages from the database");
        return getAll(SQL_SELECT_ALL_LANGUAGES, getMapper());
    }
}
