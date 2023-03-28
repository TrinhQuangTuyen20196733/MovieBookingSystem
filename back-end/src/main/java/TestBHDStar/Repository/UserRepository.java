package TestBHDStar.Repository;

import TestBHDStar.Repository.CustomizedRepository.UserSearchRepository;
import TestBHDStar.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Integer>, UserSearchRepository {

}
