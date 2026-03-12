package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class ShipmentApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShipmentApplication.class, args);
    }

    @Entity
    public static class Shipment {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long shipmentId;
        private String description;

        // constructors
        public Shipment() {}
        public Shipment(String description) {
            this.description = description;
        }

        // getters and setters
        public Long getShipmentId() { return shipmentId; }
        public void setShipmentId(Long shipmentId) { this.shipmentId = shipmentId; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }

    public interface ShipmentRepository extends JpaRepository<Shipment, Long> {}

    @RestController
    @RequestMapping("/shipments")
    public static class ShipmentController {
        private final ShipmentRepository repo;
        public ShipmentController(ShipmentRepository repo) { this.repo = repo; }

        @GetMapping
        public List<Shipment> all() { return repo.findAll(); }

        @GetMapping("/{id}")
        public Shipment one(@PathVariable Long id) {
            return repo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        }

        @PostMapping
        public Shipment create(@RequestBody Shipment shipment) {
            return repo.save(shipment);
        }

        @PutMapping("/{id}")
        public Shipment update(@PathVariable Long id, @RequestBody Shipment s) {
            return repo.findById(id).map(existing -> {
                existing.setDescription(s.getDescription());
                return repo.save(existing);
            }).orElseThrow(() -> new RuntimeException("Not found"));
        }

        @DeleteMapping("/{id}")
        public void delete(@PathVariable Long id) {
            repo.deleteById(id);
        }
    }
}
