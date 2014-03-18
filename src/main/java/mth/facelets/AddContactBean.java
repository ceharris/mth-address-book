package mth.facelets;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import mth.Contact;
import mth.service.AddContactException;
import mth.service.AddContactService;

@Named
public class AddContactBean {

  static final String SUCCESS_OUTCOME_ID = "success";
  
  @Inject
  protected AddContactService addContactService;
  
  private Contact contact;

  @PostConstruct
  public void init() {
    contact = addContactService.newContact();
  }
  
  public Contact getContact() {
    return contact;
  }

  void setContact(Contact contact) {
    this.contact = contact;
  }
  
  public String save() {
    try {
      addContactService.addContact(contact);
      return SUCCESS_OUTCOME_ID;
    }
    catch (AddContactException ex) {
      return null;
    }
  }

}
