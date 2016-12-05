package server.gateways;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;
import server.domain.Commit;
import server.domain.Repository;

import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;

@Slf4j
public class GitHubGateway {
    private static Logger logger = Logger.getLogger(GitHubGateway.class);

    private RestTemplate restTemplate;

    private static final String BASE = "https://api.github.com";
    public static final String REPOS = BASE + "/users/%s/repos";
    public static final String COMMITS = BASE + "/repos/%s/%s/commits";

    public GitHubGateway(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Repository> getRepos(String user) {
        logger.debug(format("Get repos by user({})", user));
        Repository[] repos = restTemplate.getForObject(format(REPOS, user), Repository[].class);
        return Arrays.asList(repos);
    }

    public List<Commit> getCommits(String user, String repo) {
        logger.debug(format("Get commits by repo({})", repo));
        Commit[] commits = restTemplate.getForObject(format(COMMITS, user, repo), Commit[].class);
        return Arrays.asList(commits);
    }
}
