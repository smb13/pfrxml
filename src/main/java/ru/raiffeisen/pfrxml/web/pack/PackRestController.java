package ru.raiffeisen.pfrxml.web.pack;

        import ru.raiffeisen.pfrxml.model.Pack;
        import ru.raiffeisen.pfrxml.service.PackService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.MediaType;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;
        import java.util.Optional;

@RestController
@RequestMapping(value = "/mycrud", produces = MediaType.APPLICATION_JSON_VALUE)
public class PackRestController {

    private final PackService packService;

    @Autowired
    public PackRestController(PackService packService) {
        this.packService = packService;
    }

    @GetMapping(value = "/packs")
    public ResponseEntity<List<Pack>> getAll() {
        final List<Pack> packs = packService.readAll();
        return packs != null &&  !packs.isEmpty()
                ? new ResponseEntity<>(packs, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/packs/{id}")
    public ResponseEntity<Pack> read(@PathVariable(name = "id") int id) {
        final Optional<Pack> pack = packService.read(id);
        return pack.isPresent()
                ? new ResponseEntity<>(pack.get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/packs", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody Pack pack) {
        packService.save(pack);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/packs/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Pack pack) {
        final boolean updated = packService.update(pack, id);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/packs/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = packService.delete(id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}

