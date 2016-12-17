package server.services;

import server.domain.Language;

public interface LanguageStatisticsService {
    Language getLanguageByName(String name);
}
