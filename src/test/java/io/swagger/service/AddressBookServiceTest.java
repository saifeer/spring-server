package io.swagger.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import io.swagger.api.NotFoundException;
import io.swagger.model.AddressBook;
import io.swagger.model.Entry;
import io.swagger.model.PhoneNumber;
import io.swagger.model.PhoneNumber.TypeEnum;

public class AddressBookServiceTest {
	
	private AddressBookService addressBookService;
	
	@Before
    public void init() {
        addressBookService = new AddressBookService();
    }
	
	private Entry createEntry() {
		Entry entry = new Entry();
		entry.id(0);
		entry.name("John Doe");
		PhoneNumber pn = new PhoneNumber();
		pn.type(TypeEnum.HOME);
		pn.value("+61-42773883");
		entry.addNumbersItem(pn);
		return entry;
	}
	
	private AddressBook createAddressBook() {
		AddressBook addrBook = new AddressBook();
		addrBook.id(0);
		addrBook.name("Basic");
		addrBook.addEntriesItem(createEntry());
		return addrBook;
	}
	
	@Test
    public void testCreateAddressBook() {
		AddressBook addrBook = addressBookService.createAddressBook(createAddressBook());
        Assert.assertTrue(addrBook.equals(createAddressBook()));
    }
	
	@Test
    public void testCreateEntry() throws NotFoundException {
		AddressBook addrBook = addressBookService.createAddressBook(createAddressBook());
		Entry entry = addressBookService.createEntry(addrBook.getId(),createEntry());
        Assert.assertTrue(entry.equals(createEntry()));
    }
	
	@Test(expected = NotFoundException.class)
    public void testCreateEntryThrowsExceptionWhenAddrBookNotExist() throws NotFoundException {
		addressBookService.createEntry(-1,createEntry());
    }
	
	@Test(expected = NotFoundException.class)
    public void testDeleteEntry() throws NotFoundException {
		AddressBook addrBook = addressBookService.createAddressBook(createAddressBook());
		Entry entry = addressBookService.createEntry(addrBook.getId(),createEntry());
		addressBookService.deleteEntry(addrBook.getId(), entry.getId());
		addressBookService.getEntry(addrBook.getId(), entry.getId());
    }
	
	@Test(expected = NotFoundException.class)
    public void testDeleteEntryThrowsExceptionWhenEntryNotExists() throws NotFoundException {
		AddressBook addrBook = addressBookService.createAddressBook(createAddressBook());
		addressBookService.deleteEntry(addrBook.getId(), -1);
    }
	
	@Test(expected = NotFoundException.class)
    public void testDeleteAddressBook() throws NotFoundException {
		AddressBook addrBook = addressBookService.createAddressBook(createAddressBook());
		addressBookService.deleteAddressBook(addrBook.getId());
		addressBookService.getAddressBook(addrBook.getId());
    }
	
	@Test(expected = NotFoundException.class)
    public void testDeleteAddressBookThrowsExceptionWhenNotExists() throws NotFoundException {
		addressBookService.deleteAddressBook(-1);
    }
	
	
	
	public void testGetUniqueEntries() throws NotFoundException {
		AddressBook addrBook = addressBookService.createAddressBook(createAddressBook());
		Entry entry = addressBookService.createEntry(addrBook.getId(),createEntry());
		AddressBook addrBook2 = addressBookService.createAddressBook(createAddressBook());
		addressBookService.createEntry(addrBook2.getId(),entry);
		addressBookService.createEntry(addrBook2.getId(),entry);
		List<Entry> uqEntries = addressBookService.getUniqueEntries();
		Assert.assertTrue(uqEntries.size()==1);
	}

}
