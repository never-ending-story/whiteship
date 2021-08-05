package week4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

class DashboardTest {

    Dashboard dashboard;

    @BeforeEach
    void setUp() {
        dashboard = new Dashboard();
    }

    @Test
    void connect() {
        String token = System.getProperty("token");
        try {
            dashboard.connect(token);
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    void idk() throws IOException {
        String token = System.getProperty("token");
        String repository = "whiteship/live-study";
        dashboard.getParticipationRate(token, repository);
    }
}
