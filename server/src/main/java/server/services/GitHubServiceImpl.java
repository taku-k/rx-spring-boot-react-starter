package server.services;

import rx.Observable;
import server.domain.Commit;
import server.domain.CommittedFile;
import server.domain.Repository;
import server.gateways.GitHubGateway;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class GitHubServiceImpl implements GitHubService {
    private GitHubGateway gitHubGateway;

    public GitHubServiceImpl(GitHubGateway gitHubGateway) {
        this.gitHubGateway = gitHubGateway;
    }

    @Override
    public Observable<String> getRepos(String user) {
        return Observable.create(s -> {
           gitHubGateway.getRepos(user).stream()
                   .map(Repository::getName)
                   .forEach(s::onNext);
           s.onCompleted();
        });
    }

    @Override
    public Observable<String> getReposInWeek(String user) {
        return Observable.create(s -> {
            gitHubGateway.getRepos(user).stream()
                    .filter(repo -> repo.getUpdate().isAfter(LocalDateTime.now().minusWeeks(1)))
                    .map(Repository::getName)
                    .forEach(s::onNext);
            s.onCompleted();
        });
    }

    @Override
    public Observable<Commit> getCommitsInWeek(String user, String repo) {
        return Observable.create(s -> {
            gitHubGateway.getCommitsInWeek(user, repo).stream()
                    .filter(commit -> commit.getCommitter().getLogin().equals(user))
                    .forEach(s::onNext);
            s.onCompleted();
        });
    }

    @Override
    public Observable<CommittedFile> getCommittedFiles(String user, String repo, String sha) {
        return Observable.create(s -> {
           gitHubGateway.getSingleCommit(user, repo, sha).getFiles()
                   .forEach(s::onNext);
           s.onCompleted();
        });
    }

    @Override
    public Observable<CommittedFile> getCommittedFilesByUser(String user) {
        return null;
    }
}
