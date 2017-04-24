package server.resources;

import org.springframework.web.bind.annotation.*;
import server.domain.GitHubStats;
import server.domain.Language;
import server.services.GitHubService;
import server.services.LanguageStatisticsService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/github-stats")
public class GitHubStatsResource {
    private LanguageStatisticsService langStats;

    private GitHubService gitHubService;

    public GitHubStatsResource(LanguageStatisticsService langStats, GitHubService gitHubService) {
        this.langStats = langStats;
        this.gitHubService = gitHubService;
    }

    @RequestMapping(value = "/{userName}", method = RequestMethod.GET)
    public List<GitHubStats> get(@PathVariable("userName") String userName) {
        return langStats.calcLangStats(gitHubService.getCommittedFilesByUser(userName));
    }
}
