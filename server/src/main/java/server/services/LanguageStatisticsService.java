package server.services;

import io.reactivex.Flowable;
import server.domain.CommittedFile;
import server.domain.Language;

import java.util.Map;

public interface LanguageStatisticsService {
    Language getLanguageByName(String name);

    Map<Language, Double> calcLangStats(Flowable<CommittedFile> files);
}
