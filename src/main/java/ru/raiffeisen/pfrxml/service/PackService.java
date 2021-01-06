package ru.raiffeisen.pfrxml.service;

import ru.raiffeisen.pfrxml.model.Pack;
import ru.raiffeisen.pfrxml.repository.jpa.PackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PackService  {

        private final PackRepository packRepository;

        @Autowired
        public PackService(PackRepository packRepository) {
            this.packRepository = packRepository;
        }

        public void save(Pack pack) {
            packRepository.save(pack);
        }


        public List<Pack> readAll() {
            return packRepository.findAll();
        }

        public boolean update(Pack pack, Integer id) {
            if (packRepository.existsById(id)) {
                pack.setId(id);
                packRepository.save(pack);
                return true;
            }
            return false;
        }

        public Optional<Pack> read(Integer id) {
            return packRepository.findById(id);
        }


        public boolean delete(Integer id) {
            packRepository.deleteById(id);
            return !packRepository.existsById(id);
        }
}
