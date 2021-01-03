package ru.raiffeisen.pfrxml.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.raiffeisen.pfrxml.UserTestData;
import ru.raiffeisen.pfrxml.model.User;
import ru.raiffeisen.pfrxml.repository.UserRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static ru.raiffeisen.pfrxml.UserTestData.admin;
import static ru.raiffeisen.pfrxml.UserTestData.user;


@Repository
public class InMemoryUserRepository extends InMemoryBaseRepository<User> implements UserRepository {

    public void init() {
        map.clear();
        map.put(UserTestData.USER_ID, user);
        map.put(UserTestData.ADMIN_ID, admin);
    }

    @Override
    public List<User> getAll() {
        return getCollection().stream()
                .sorted(Comparator.comparing(User::getName).thenComparing(User::getEmail))
                .collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        return getCollection().stream()
                .filter(u -> email.equals(u.getEmail()))
                .findFirst()
                .orElse(null);
    }
}