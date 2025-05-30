package com.cs2.veterinaria.app.adapters.petOwner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs2.veterinaria.app.adapters.persons.entity.PersonEntity;
import com.cs2.veterinaria.app.adapters.petOwner.entity.PetOwnerEntity;
import com.cs2.veterinaria.app.adapters.petOwner.repository.PetOwnerRepository;
import com.cs2.veterinaria.app.domains.model.Person;
import com.cs2.veterinaria.app.domains.model.PetOwner;
import com.cs2.veterinaria.app.ports.PetOwnerPort;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@Service
public class PetOwnerAdapter implements PetOwnerPort {
    @Autowired
    private PetOwnerRepository petOwnerRepository;

    @Override
    public boolean existPetOwner(long document) {
        return petOwnerRepository.existsByPersonDocument(document);
    }

    @Override
    public PetOwner findByDocument(long document) {
        PetOwnerEntity petOwnerEntity = petOwnerRepository.findByPersonDocument(document);
        return adapterPetOwner(petOwnerEntity);
    }

    @Override
    public void savePetOwner(Person person) {
        PetOwnerEntity petOwnerEntity = new PetOwnerEntity();
        petOwnerEntity.setPerson(adapterPerson(person));
        petOwnerRepository.save(petOwnerEntity);
        person.setDocument(petOwnerEntity.getPerson().getDocument());
    }

    @Override
    public void deletePetOwner(long document) {
        petOwnerRepository.deleteByPersonDocument(document);
    }

    @Override
    public boolean existPetOwnerById(long idOwner) {
        return petOwnerRepository.existsById(idOwner);
    }

    @Override
    public List<PetOwner> findAllPetOwners() {
        return petOwnerRepository.findAll().stream()
                .map(this::adapterPetOwner)
                .collect(Collectors.toList());
    }

    private PetOwner adapterPetOwner(PetOwnerEntity petOwnerEntity) {
        PetOwner petOwner = new PetOwner();
        petOwner.setIdOwner(petOwnerEntity.getIdOwner());
        petOwner.setDocument(petOwnerEntity.getPerson().getDocument());
        petOwner.setName(petOwnerEntity.getPerson().getName());
        petOwner.setAge(petOwnerEntity.getPerson().getAge());

        return petOwner;
    }

    

    private PersonEntity adapterPerson(Person person) {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setPersonId(person.getPersonId());
        personEntity.setDocument(person.getDocument());
        personEntity.setName(person.getName());
        personEntity.setAge(person.getAge());
        return personEntity;
    }
}