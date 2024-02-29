package kz.test.demo.service;

import kz.test.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public boolean existById(String id) {
        return userRepository.existsById(id);
    }
}
