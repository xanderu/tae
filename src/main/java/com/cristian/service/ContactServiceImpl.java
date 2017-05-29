package com.cristian.service;

import java.util.List;

import com.cristian.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristian.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {
	
	@Autowired
    private ContactRepository contactRepository;

    @Override
    public Iterable<Contact> findAll() {
        return contactRepository.findAll();
    }

    @Override
    public List<Contact> search(String q) {
        return contactRepository.findByNameContaining(q);
    }

    @Override
    public Contact findOne(int id) {
        return contactRepository.findOne(id);
    }

    @Override
    public void save(Contact contact) {
        contactRepository.save(contact);
    }

    @Override
    public void delete(int id) {
        contactRepository.delete(id);
    }

}