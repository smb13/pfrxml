package ru.raiffeisen.pfrxml.repository.jpa;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.raiffeisen.pfrxml.model.Pack;


public interface PackRepository extends JpaRepository<Pack, Integer> {

}