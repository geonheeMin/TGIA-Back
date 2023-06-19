package capstone.market.bugigram;

import capstone.market.profile_dto.PostDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserJpaRepository userJpaRepository;




    // 회원가입
    public UserDTO createUser(UserDTO userDTO){
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setNickname(userDTO.getNickname());
        user.setPassword(userDTO.getPassword());
        user.setImageFilename("jj11.png"); // 디폴트 값으로 선언
        userJpaRepository.save(user);

        return new UserDTO(user);
    }

    //로그인
    public UserDTO loginUser(String email, String password) {
        User user = userJpaRepository.findByEmailAndPassword(email, password);
        if (user != null) {
            // 일치하는 유저 정보를 UserDTO로 매핑하여 반환
            UserDTO userDTO = new UserDTO();
            userDTO.setEmail(user.getEmail());
            userDTO.setName(user.getName());
            userDTO.setNickname(user.getNickname());
            userDTO.setPassword(user.getPassword());
            userDTO.setUserId(user.getUserId());
            userDTO.setImageFilename(user.getImageFilename());
            return userDTO;
        } else {
            return null;
        }
    }

    // 모든 유저 리스트 조회하기
    public List<UserDTO> getAllUsers() {
        List<User> all = userJpaRepository.findAll();

        List<UserDTO> SearchUsers = all.stream()
                .map(u -> new UserDTO(u))
                .collect(Collectors.toList());

        return SearchUsers;


    }






}
