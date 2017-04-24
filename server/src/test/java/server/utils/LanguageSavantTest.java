package server.utils;

import org.junit.Before;
import org.junit.Test;
import server.domain.Language;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class LanguageSavantTest {
    private LanguageSavant savant;

    @Before
    public void setup() {
        savant = new LanguageSavant();
    }

    @Test
    public void languagesHasRubyLanguage() throws Exception {
        Optional<Language> ruby = savant.getLangByExtension(".rb");
        assertThat(ruby).isNotNull();
    }
}