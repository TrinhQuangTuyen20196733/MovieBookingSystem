package TestBHDStar.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.massindexing.MassIndexer;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;
@Slf4j
@Configuration
public class HibernateSearchIndexBuild  implements ApplicationListener<ApplicationReadyEvent> {


   @PersistenceContext

    private EntityManager entityManager;

    @Override

    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {

        log.info("Started Initializing Indexes");

        SearchSession searchSession = Search.session(entityManager);

        MassIndexer indexer = searchSession.massIndexer()

                .threadsToLoadObjects(6);

        try {

            indexer.startAndWait();

        } catch (InterruptedException e) {

            log.warn("Failed to load data from database");

            Thread.currentThread().interrupt();

        }

        log.info("Completed Indexing");

    }
}
