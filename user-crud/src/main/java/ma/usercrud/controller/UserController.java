package ma.usercrud.controller;

import jakarta.validation.Valid;
import ma.usercrud.model.User;
import ma.usercrud.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000") // autorise React en dev
public class UserController {

    private final UserService service;
    public UserController(UserService service) { this.service = service; }

    @GetMapping
    public List<User> all() { return service.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Long id) {
        User u = service.findById(id);
        return u != null ? ResponseEntity.ok(u) : ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        User created = service.create(user);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @Valid @RequestBody User user) {
        try {
            User updated = service.update(id, user);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
