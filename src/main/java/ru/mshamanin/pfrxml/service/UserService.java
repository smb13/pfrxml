package ru.mshamanin.pfrxml.service;

import org.springframework.stereotype.Service;
import ru.mshamanin.pfrxml.model.User;
import ru.mshamanin.pfrxml.repository.UserRepository;

import java.util.List;

import static ru.mshamanin.pfrxml.util.ValidationUtil.checkNotFound;
import static ru.mshamanin.pfrxml.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserService {

   private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User create(User user) {
        return repository.save(user);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public User get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public User getByLogin(String login) {
        return checkNotFound(repository.getByLogin(login), "login=" + login);
    }

    public List<User> getAll() {
        return repository.getAll();
    }

    public void update(User user) {
        checkNotFoundWithId(repository.save(user), user.getId());
    }
}