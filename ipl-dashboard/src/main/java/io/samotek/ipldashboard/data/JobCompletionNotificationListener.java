package io.samotek.ipldashboard.data;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.samotek.ipldashboard.model.Team;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JobCompletionNotificationListener implements JobExecutionListener {

    private final EntityManager entityManager;

    @Autowired
    public JobCompletionNotificationListener(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

            Map<String, Team> teamData = new HashMap<>();
            entityManager
                    .createQuery("SELECT m.team1, count(m) FROM Match m group by m.team1", Object[].class)
                    .getResultList()
                    .stream()
                    .map(e -> new Team((String) e[0], (Long) e[1]))
                    .forEach(team -> teamData.put(team.getTeamName(), team));

            entityManager
                    .createQuery("SELECT m.team2, count(m) FROM Match m group by m.team2", Object[].class)
                    .getResultList()
                    .forEach(e -> {
                        Team team = teamData.get((String) e[0]);
                        team.setTotalMatchesPlayed(team.getTotalMatchesPlayed() + (Long) e[1]);
                    });

            entityManager.createQuery(
                    "SELECT m.matchWinner, count(m) FROM Match m GROUP BY m.matchWinner", Object[].class)
                    .getResultList().forEach(e -> {
                        Team team = teamData.get((String) e[0]);
                        if (team != null) {
                            team.setTotalWins((Long) e[1]);
                        }
                    });

            // Finally, persisting the data filtered / cleaned above.
            teamData.values().forEach(entityManager::persist);
            teamData.values().forEach(team -> log.info(String.valueOf(team)));
        }
    }
}
