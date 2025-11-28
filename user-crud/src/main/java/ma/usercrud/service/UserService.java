package ma.usercrud.service;

import jakarta.transaction.Transactional;
import ma.usercrud.model.User;
import org.springframework.stereotype.Service;
import ma.usercrud.repository.UserRepository;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public List<User> findAll() { return repo.findAll(); }

    public User findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public User create(User u) {
        // you may want to check unique email
        return repo.save(u);
    }

    public User update(Long id, User u) {
        User existing = repo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        existing.setNom(u.getNom());
        existing.setEmail(u.getEmail());
        existing.setAge(u.getAge());
        return repo.save(existing);
    }

    public void delete(Long id) { repo.deleteById(id); }
}

