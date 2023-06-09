package TestBHDStar.service.Impl;

import TestBHDStar.DTO.Page.UserPageDTO;
import TestBHDStar.DTO.UserDTO;
import TestBHDStar.exception.DuplicatedEmailException;
import TestBHDStar.exception.UserNotFoundException;
import TestBHDStar.Repository.UserRepository;
import TestBHDStar.service.UserService;
import TestBHDStar.entity.UserEntity;
import TestBHDStar.mapper.mapperImpl.UserMapper;
import org.hibernate.search.engine.search.query.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Value("${USER_PAGE_SIZE}")
    private int USER_PAGE_SIZE;
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDTO createUser(UserEntity userEntity) {
        try {
            return UserMapper.getInstance().toDTO(userRepository.save(userEntity));
        }
        catch (Exception e ) {
            throw new DuplicatedEmailException("Email của bạn đã được sử dung!");
        }

    }

    @Override
    public List<UserDTO> findAll() {
        return UserMapper.getInstance().toDTOList(userRepository.findAll());
    }

    @Override
    public UserDTO findUserById(int id) {
        Optional<UserEntity> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            UserDTO userDTO = UserMapper.getInstance().toDTO(userOptional.get());
            return userDTO;
        } else {
            throw new UserNotFoundException("Người dùng không tồn tại! Bạn vui lòng thử lại!");
        }
    }

    @Override
    public UserPageDTO findUserByPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, USER_PAGE_SIZE);
        Page<UserEntity> userEntityPage = userRepository.findAll(pageable);
        UserPageDTO userPageDTO = new UserPageDTO();
        userPageDTO.setUserDTOList(UserMapper.getInstance().toDTOList(userEntityPage.getContent()));
        userPageDTO.setTotalItemPage(userEntityPage.getTotalElements());
        userPageDTO.setTotalPage(userEntityPage.getTotalPages());
        return userPageDTO;
    }

    @Override
    public UserPageDTO searchUserByFullNameOrEmail(String fullNameOrEmail, int page) {
        SearchResult<UserEntity> results = userRepository.searchByFullNameOrEmail(fullNameOrEmail, page);
        long totalHits = results.total().hitCount();
        List<UserEntity> userEntityList = results.hits();
        UserPageDTO userPageDTO = new UserPageDTO();
        userPageDTO.setUserDTOList(UserMapper.getInstance().toDTOList(userEntityList));
        userPageDTO.setTotalItemPage(totalHits);
        userPageDTO.setTotalPage((long) Math.ceil((double) totalHits / USER_PAGE_SIZE));
        return userPageDTO;
    }

    @Override
    public void deleteUser(int id) {

        try{
            userRepository.deleteById(id);
        }
        catch (Exception e) {
            throw  new UserNotFoundException("Người dùng bạn muốn xóa không tồn tại");
        }

    }
}
