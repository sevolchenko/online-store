package ru.vsu.cs.volchenko.site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.volchenko.site.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
}

