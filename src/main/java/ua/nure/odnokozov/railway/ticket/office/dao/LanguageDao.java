package ua.nure.odnokozov.railway.ticket.office.dao;

import java.util.List;

import ua.nure.odnokozov.railway.ticket.office.domain.Language;

public interface LanguageDao {

    Language getById(long id);

    boolean create(Language language);

    boolean update(Language Language);

    boolean delete(long id);

    Language getByLabel(String label);

    List<Language> getAll();
}
