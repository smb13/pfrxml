package ru.mshamanin.pfrxml.repository;

import ru.mshamanin.pfrxml.model.Pack;

import java.time.LocalDateTime;
import java.util.List;

public interface PackRepository {
        Pack save(Pack pack);

        // null if updated pack do not belong to userId
        Pack save(Pack pack, int userId);

        // false if pack do not belong to userId
        boolean delete(int id, int userId);

        // null if pack do not belong to userId
        Pack get(int id);

        Pack get(int id, int userId);

        // ORDERED dateTime desc
        List<Pack> getAll();

        // ORDERED dateTime desc
        List<Pack> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
