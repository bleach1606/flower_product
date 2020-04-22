package ptit.edu.btl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ptit.edu.btl.entity.Flower;
import ptit.edu.btl.repository.FlowerRepository;

@RestController
public class FlowerController {
    @Autowired
    FlowerRepository flowerRepository;

    @PostMapping("/create")
    public ResponseEntity<Flower> createFlower(@RequestBody Flower flower){
        return ResponseEntity.ok(flowerRepository.save(flower));
    }

    @PostMapping("/find-by-name")
    public ResponseEntity<Flower> createFlower(@RequestParam String flowerName){
        return ResponseEntity.ok(flowerRepository.findByName(flowerName).orElse(new Flower()));
    }

}
