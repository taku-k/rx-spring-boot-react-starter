package server.services;

import rx.Observable;
import server.domain.CommittedFile;
import server.domain.Language;

import java.util.Map;

public interface LanguageStatisticsService {
    Language getLanguageByName(String name);

    Map<Language, Double> calcLangStats(Observable<CommittedFile> files);
}
