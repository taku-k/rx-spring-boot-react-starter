package server.services;

import rx.Observable;
import server.domain.Commit;

import java.util.List;

public interface GitHubService {
    Observable<List<String>> getRepos(String user);

    Observable<List<String>> getReposInWeek(String user);

    Observable<List<Commit>> getCommitsInWeek(String user, String repo);
}
