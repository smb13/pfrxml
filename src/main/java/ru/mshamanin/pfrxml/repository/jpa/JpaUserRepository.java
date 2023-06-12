package ru.mshamanin.pfrxml.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.mshamanin.pfrxml.model.User;
import ru.mshamanin.pfrxml.repository.UserRepository;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaUserRepository implements UserRepository {
    @Override
    @Transactional
    public User save(User user) {
        return null;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return false;
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public User getByLogin(String login) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
