package TestBHDStar.Repository.Impl;

import TestBHDStar.entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class UserRepositoryImpl implements TestBHDStar.Repository.CustomizedRepository.UserSearchRepository {
    @PersistenceContext
    EntityManager entityManager;
    @Value("${USER_PAGE_SIZE}")
    private int USER_PAGE_SIZE;

    @Transactional
    public SearchResult<UserEntity> searchByFullNameOrEmail(String fullNameOrName,int page) {
        SearchSession searchSession = Search.session(entityManager);
        String queryString = fullNameOrName;


        SearchResult<UserEntity> results = searchSession.search(UserEntity.class)
                .where(f -> f.bool().should(
                                f.match().field("firstName")
                                        .matching(queryString).fuzzy(1))
                        .should(
                                f.match().field("lastName")
                                        .matching(queryString).fuzzy(1))
                        .should(f.nested().objectField("account")
                                .nest(f.match().field("account.email").matching(queryString).fuzzy(2)))

                ).fetch(page*USER_PAGE_SIZE,USER_PAGE_SIZE);


        return results;
    }
}
