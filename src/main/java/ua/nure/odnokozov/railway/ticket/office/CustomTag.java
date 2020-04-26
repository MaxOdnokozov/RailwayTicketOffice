package ua.nure.odnokozov.railway.ticket.office;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import ua.nure.odnokozov.railway.ticket.office.domain.Language;
import ua.nure.odnokozov.railway.ticket.office.domain.Station;

public class CustomTag extends SimpleTagSupport {

    private Station station;
    private String locale;
    private List<Language> contextLanguages;

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public List<Language> getContextLanguages() {
        return contextLanguages;
    }

    public void setContextLanguages(List<Language> contextLanguages) {
        this.contextLanguages = contextLanguages;
    }

    @Override
    public void doTag() throws JspException, IOException {

        Optional<Language> language = contextLanguages.stream().filter(lang -> lang.getLabel().equals(locale))
                .findFirst();
        if(language.isPresent()) {
            String stationName = station.getNames().get(language.get().getId());        
        JspWriter out = getJspContext().getOut();
        out.print(stationName);
        }
    }
}
