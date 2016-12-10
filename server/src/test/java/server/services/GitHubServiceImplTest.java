package server.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rx.observers.TestSubscriber;
import server.domain.Commit;
import server.domain.Committer;
import server.domain.Repository;
import server.gateways.GitHubGateway;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringJUnit4ClassRunner.class)
public class GitHubServiceImplTest {
    @Mock
    private GitHubGateway gitHubGateway;

    private GitHubService gitHubService;

    @Before
    public void setup() {
        initMocks(this);
        gitHubService = new GitHubServiceImpl(gitHubGateway);

        when(gitHubGateway.getRepos(anyString())).thenAnswer(m -> getReposForTest());
        when(gitHubGateway.getCommitsInWeek(anyString(), anyString())).thenAnswer(m -> getCommitsInWeekForTest());
    }

    @Test
    public void getReposInWeek() throws Exception {
        // Setup
        TestSubscriber<String> testSubscriber = new TestSubscriber<>();
        gitHubService.getReposInWeek("test-user")
                .subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        assertThat(testSubscriber.getOnNextEvents())
                .as("getReposInWeek returns updated repos within one week")
                .containsExactly("repo2", "repo3");
    }

    @Test
    public void getCommitsInWeek() throws Exception {
        // Setup
        TestSubscriber<Commit> testSubscriber = new TestSubscriber<>();
        gitHubService.getCommitsInWeek("test-user", "test-repo")
                .subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        assertThat(testSubscriber.getOnNextEvents())
                .as("getCommitsInWeek returns commits updated by `user` within one week")
                .extracting(Commit::getSha)
                .containsExactly("sha1");
    }

    private List<Repository> getReposForTest() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException ignore) {
        }
        return Arrays.asList(
                new Repository("repo1", LocalDateTime.now().minusMonths(1)),
                new Repository("repo2", LocalDateTime.now()),
                new Repository("repo3", LocalDateTime.now().minusDays(3))
        );
    }

    private List<Commit> getCommitsInWeekForTest() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException ignore) {
        }
        return Arrays.asList(
                new Commit("sha1", new Committer("test-user")),
                new Commit("sha2", new Committer("no-test-user"))
        );
    }
}