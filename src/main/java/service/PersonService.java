package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.DataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class PersonService {
    private static final Logger log = LoggerFactory.getLogger(PersonService.class);
    private final DataRepository repository;

    public PersonService(DataRepository repository) {
        this.repository = repository;
    }

    public void addPerson(Person person) {
        log.debug("Ajout d'une personne : {}", person.getFirstName());
        repository.getPersons().add(person);
    }

    public boolean updatePerson(Person updatedPerson) {
        List<Person> persons = repository.getPersons();
        for (int i = 0; i < persons.size(); i++) {
            Person p = persons.get(i);
            if (p.getFirstName().equals(updatedPerson.getFirstName()) &&
                    p.getLastName().equals(updatedPerson.getLastName())) {
                persons.set(i, updatedPerson);
                log.debug("Personne mise à jour : {}", updatedPerson.getFirstName());
                return true;
            }
        }
        log.debug("Personne non trouvée pour mise à jour : {} {}", updatedPerson.getFirstName(), updatedPerson.getLastName());
        return false;
    }

    public boolean deletePerson(String firstName, String lastName) {
        Iterator<Person> iterator = repository.getPersons().iterator();
        while (iterator.hasNext()) {
            Person p = iterator.next();
            if (p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)) {
                iterator.remove();
                log.debug("Personne supprimée : {} {}", firstName, lastName);
                return true;
            }
        }
        log.debug("Personne non trouvée pour suppression : {} {}", firstName, lastName);
        return false;
    }
}
