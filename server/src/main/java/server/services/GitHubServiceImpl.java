package server.services;

import rx.Observable;
import server.domain.Repository;
import server.gateways.GitHubGateway;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GitHubServiceImpl implements GitHubService {
    private GitHubGateway gitHubGateway;

    public GitHubServiceImpl(GitHubGateway gitHubGateway) {
        this.gitHubGateway = gitHubGateway;
    }

    @Override
    public Observable<List<String>> getRepos(String user) {
        return Observable.just(gitHubGateway.getRepos(user)
                .stream()
                .map(Repository::getName)
                .collect(Collectors.toList()));
    }

    @Override
    public Observable<List<String>> getReposInWeek(String user) {
        return Observable.just(gitHubGateway.getRepos(user)
                .stream()
                .filter(repo -> repo.getUpdate().isAfter(LocalDateTime.now().minusWeeks(1)))
                .map(Repository::getName)
                .collect(Collectors.toList()));
    }
}
