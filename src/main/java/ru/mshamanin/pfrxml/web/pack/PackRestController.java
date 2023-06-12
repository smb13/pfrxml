package ru.mshamanin.pfrxml.web.pack;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.mshamanin.pfrxml.model.Pack;
import ru.mshamanin.pfrxml.View;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

//@RestController
//@RequestMapping(value = PackRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class PackRestController extends AbstractPackController {
        static final String REST_URL = "/rest/profile/packs";

        @Override
        @GetMapping("/{id}")
        public Pack get(@PathVariable int id) {
                return super.get(id);
        }

        @Override
        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void delete(@PathVariable int id) {
                super.delete(id);
        }

        @Override
        @GetMapping
        public List<Pack> getAll() {
                return super.getAll();
        }

        @Override
        @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void update(@Validated(View.Web.class) @RequestBody Pack pack, @PathVariable int id) {
                super.update(pack, id);
        }

        @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Pack> createWithLocation(@Validated(View.Web.class) @RequestBody Pack pack) {
                Pack created = super.create(pack);

                URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path(REST_URL + "/{id}")
                        .buildAndExpand(created.getId()).toUri();

                return ResponseEntity.created(uriOfNewResource).body(created);
        }

        @Override
        @GetMapping("/filter")
        public List<Pack> getBetween(
                @RequestParam @Nullable LocalDate startDate,
                @RequestParam @Nullable LocalDate endDate) {
                return super.getBetween(startDate, endDate);
        }
}
