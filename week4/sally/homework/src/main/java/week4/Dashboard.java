package week4;

import org.kohsuke.github.*;

import java.io.IOException;
import java.util.*;

public class Dashboard {

    public GitHub connect(String token) throws IOException {
        GitHub gitHub = new GitHubBuilder().withOAuthToken(token).build();
        gitHub.checkApiUrlValidity();

        return gitHub;
    }

    public void getParticipationRate(String token, String repositoryName) throws IOException {
        GitHub gitHub = connect(token);

        GHRepository repository = gitHub.getRepository(repositoryName);
        List<GHIssue> issues = repository.getIssues(GHIssueState.ALL);
        Map<String, Integer> participants = new HashMap<>();

        for (GHIssue issue : issues) {
            // get comment from issue
            List<GHIssueComment> comments = issue.getComments();

            // to remove duplicated users
            Set<String> list = new HashSet<>();
            for (GHIssueComment comment : comments) {
                list.add(comment.getUser().getName());
            }

            // log how many times a certain user participated
            for (String name : list) {
                if (participants.containsKey(name)) {
                    participants.replace(name, participants.get(name) + 1);
                } else {
                    participants.put(name, 1);
                }
            }
        }

        participants.forEach((key, value) -> {
            double percent = (value * 100) / 18;
            if (percent > 50) {
                System.out.println(key + "'s participation rate is " + percent + "%");
            }
        });
    }
}
