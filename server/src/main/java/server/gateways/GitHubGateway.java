package server.gateways;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;
import server.domain.Commit;
import server.domain.Repository;
import server.domain.SingleCommit;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;

@Slf4j
public class GitHubGateway {
    private static Logger logger = Logger.getLogger(GitHubGateway.class);

    private RestTemplate restTemplate;

    private static final String BASE = "https://api.github.com";
    public static final String REPOS = BASE + "/users/%s/repos";
    public static final String COMMITS = BASE + "/repos/%s/%s/commits?since=%s";
    public static final String SINGLE_COMMIT = BASE + "/repos/%s/%s/commits/%s";

    public GitHubGateway(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Repository> getRepos(String user) {
        logger.debug(format("Get repos by user(%s)", user));
        Repository[] repos = restTemplate.getForObject(format(REPOS, user), Repository[].class);
        return Arrays.asList(repos);
    }

    public List<Commit> getCommitsInWeek(String user, String repo) {
        logger.debug(format("Get commits by repo(%s)", repo));
        String aWeekAgo = ZonedDateTime.now(ZoneOffset.UTC).minusWeeks(1).minusDays(1)
                .format(DateTimeFormatter.ofPattern("yyyy-mm-dd'T'00:00:00'Z'"));
        Commit[] commits = restTemplate.getForObject(format(COMMITS, user, repo, aWeekAgo), Commit[].class);
        return Arrays.asList(commits);
    }

    public SingleCommit getSingleCommit(String user, String repo, String sha) {
        logger.debug(format("Get a single commit by sha(%s)", sha));
        return restTemplate.getForObject(format(SINGLE_COMMIT, user, repo, sha), SingleCommit.class);
    }
}
