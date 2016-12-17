package server.services;

import org.junit.Before;
import org.junit.Test;
import server.domain.Language;

import static org.assertj.core.api.Assertions.*;

public class LanguageStatisticsServiceImplTest {
    private LanguageStatisticsService languageStatisticsService;

    @Before
    public void setup() {
        languageStatisticsService = new LanguageStatisticsServiceImpl();
    }

    @Test
    public void languagesHasRubyLanguage() throws Exception {
        Language rubyLang = languageStatisticsService.getLanguageByName("Ruby");
        assertThat(rubyLang).isNotNull();
    }
}