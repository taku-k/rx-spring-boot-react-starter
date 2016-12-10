package server.services;

import rx.Observable;
import server.domain.Commit;
import server.domain.CommittedFile;

import java.util.List;

public interface GitHubService {
    Observable<String> getRepos(String user);

    Observable<String> getReposInWeek(String user);

    Observable<Commit> getCommitsInWeek(String user, String repo);

    Observable<CommittedFile> getCommittedFiles(String user, String repo, String sha);

    Observable<CommittedFile> getCommittedFilesByUser(String user);
}
