package mth.service;

import mth.Contact;

public interface AddContactService {

  Contact newContact();
  
  void addContact(Contact contact) throws AddContactException;
  
}
