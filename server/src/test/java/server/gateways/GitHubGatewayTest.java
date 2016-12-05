package server.gateways;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import server.domain.Commit;
import server.domain.Repository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GitHubGatewayTest {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private GitHubGateway gitHubGateway;

    @Test
    public void getRepos() {
        MockRestServiceServer mockServer = MockRestServiceServer.bindTo(restTemplate).build();
        mockServer.expect(requestTo(String.format(GitHubGateway.REPOS, "taku-k")))
            .andRespond(withSuccess(
                            "[{\"name\":\"repo1\",\"updated_at\":\"2016-11-28T14:12:46Z\"}]",
                            MediaType.APPLICATION_JSON)
            );

        List<Repository> repos = gitHubGateway.getRepos("taku-k");
        assertThat(repos.size()).isEqualTo(1);
        assertThat(repos.get(0).getUpdate().getHour()).isEqualTo(14);
        mockServer.verify();
    }

    @Test
    public void getCommits() {
        MockRestServiceServer mockServer = MockRestServiceServer.bindTo(restTemplate).build();
        mockServer.expect(requestTo(String.format(GitHubGateway.COMMITS, "taku-k", "rx-spring-boot-react-starter")))
                .andRespond(withSuccess(
                        "[{" +
                                    "\"sha\":\"6dcb09b5b57875f334f61aebed695e2e4193db5e\"," +
                                    "\"url\":\"https://example.com\"," +
                                    "\"committer\":{" +
                                        "\"login\":\"taku-k\"" +
                                    "}" +
                                "}]",
                        MediaType.APPLICATION_JSON));
        List<Commit> commits = gitHubGateway.getCommits("taku-k", "rx-spring-boot-react-starter");
        assertThat(commits.size()).isEqualTo(1);
        assertThat(commits.get(0).getCommitter().getLogin()).isEqualTo("taku-k");
        mockServer.verify();
    }
}