package server.services;

import io.reactivex.Flowable;
import server.domain.CommittedFile;
import server.domain.GitHubStats;

import java.util.List;

public interface LanguageStatisticsService {
    List<GitHubStats> calcLangStats(Flowable<CommittedFile> files);
}
