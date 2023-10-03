    package com.example.kd23backend.cinema.model;

    import com.example.kd23backend.address.model.Address;
    import com.example.kd23backend.employee.model.Employee;
    import com.example.kd23backend.program.model.Program;
    import com.example.kd23backend.theater.model.Theater;
    import com.fasterxml.jackson.annotation.JsonManagedReference;
    import jakarta.annotation.Nullable;
    import jakarta.persistence.*;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import org.springframework.beans.factory.annotation.Value;

    import java.util.Set;

    @Entity
    @Data
    @NoArgsConstructor
    public class Cinema {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;


        private String name;

        @OneToOne(cascade = CascadeType.MERGE)
        @JoinColumn(name = "address_id", referencedColumnName = "id")
        private Address address;

        @OneToMany(mappedBy = "cinema", cascade = CascadeType.ALL)
        @JsonManagedReference
        private Set<Employee> employees;

        @OneToMany(mappedBy = "cinema", cascade = CascadeType.ALL)
        @JsonManagedReference
        private Set<Theater> theaters;

        @OneToMany(mappedBy = "cinema")
        @JsonManagedReference
        private Set<Program> programs;
    }
