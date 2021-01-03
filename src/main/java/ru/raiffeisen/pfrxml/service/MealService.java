package ru.raiffeisen.pfrxml.service;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ru.raiffeisen.pfrxml.model.Meal;
import ru.raiffeisen.pfrxml.repository.MealRepository;

import java.time.LocalDate;
import java.util.List;

import static ru.raiffeisen.pfrxml.util.DateTimeUtil.atStartOfDayOrMin;
import static ru.raiffeisen.pfrxml.util.DateTimeUtil.atStartOfNextDayOrMax;
import static ru.raiffeisen.pfrxml.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    public List<Meal> getBetweenInclusive(@Nullable LocalDate startDate, @Nullable LocalDate endDate, int userId) {
        return repository.getBetweenHalfOpen(atStartOfDayOrMin(startDate), atStartOfNextDayOrMax(endDate), userId);
    }

    public List<Meal> getAll(int userId) {
        return repository.getAll(userId);
    }

    public void update(Meal meal, int userId) {
        checkNotFoundWithId(repository.save(meal, userId), meal.getId());
    }

    public Meal create(Meal meal, int userId) {
        return repository.save(meal, userId);
    }
}