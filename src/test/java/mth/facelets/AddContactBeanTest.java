package mth.facelets;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import mth.Contact;
import mth.service.AddContactException;
import mth.service.AddContactService;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class AddContactBeanTest {

  @Rule
  public final JUnitRuleMockery context = new JUnitRuleMockery();
  
  @Mock
  private AddContactService addContactService;
  
  @Mock
  private Contact contact;
  
  private AddContactBean addContactBean = new AddContactBean();
  
  @Before
  public void setUp() throws Exception {
    addContactBean.addContactService = addContactService;
  }
  
  @Test
  public void testInit() throws Exception {
    context.checking(new Expectations() { {
      oneOf(addContactService).newContact();
      returnValue(contact);
    } });
    
    addContactBean.init();
    assertThat(addContactBean.getContact(), is(not(nullValue())));
  }
  
  @Test
  public void testSaveSuccess() throws Exception {
    context.checking(new Expectations() { { 
      oneOf(addContactService).addContact(contact);
    } });

    addContactBean.setContact(contact);
    assertThat(addContactBean.save(), 
        is(equalTo(AddContactBean.SUCCESS_OUTCOME_ID)));
  }

  @Test
  public void testSaveFailure() throws Exception {
    context.checking(new Expectations() { { 
      oneOf(addContactService).addContact(contact);
      will(throwException(new AddContactException()));
    } });

    addContactBean.setContact(contact);
    assertThat(addContactBean.save(), is(nullValue()));
  }

}
