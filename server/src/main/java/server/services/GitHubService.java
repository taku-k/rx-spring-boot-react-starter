package server.services;

import rx.Observable;

import java.util.List;

public interface GitHubService {
    Observable<List<String>> getRepos(String user);

    Observable<List<String>> getReposInWeek(String user);
}
