package TestBHDStar.Repository.CustomizedRepository;

import TestBHDStar.entity.UserEntity;
import org.hibernate.search.engine.search.query.SearchResult;

public interface UserSearchRepository {
    SearchResult<UserEntity> searchByFullNameOrEmail(String fullNameOrName, int page);
}
