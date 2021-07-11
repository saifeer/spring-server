package io.swagger.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import io.swagger.api.ApiResponseMessage;
import io.swagger.api.NotFoundException;
import io.swagger.model.AddressBook;
import io.swagger.model.Entry;

@Service
public class AddressBookService {
	private Map<Integer, AddressBook> addressBooks = new HashMap<>();
	private Integer addressBookIDs = 0;
	private Integer entryIDs = 0;
	
	public AddressBook createAddressBook(AddressBook addressBook) {
		addressBook.setId(++addressBookIDs);
		for (Iterator iterator = addressBook.getEntries().iterator(); iterator.hasNext();) {
			Entry entry = (Entry) iterator.next();
			entry.setId(++entryIDs);
		}
		addressBooks.put(addressBookIDs, addressBook);
		return addressBook;
	}
	
	public Entry createEntry(Integer addressbookId, Entry entry) throws NotFoundException {
		entry.setId(++entryIDs);
		if (addressBooks.containsKey(addressbookId)) {
			addressBooks.get(addressbookId).addEntriesItem(entry);
			return entry;
		}
		throw new NotFoundException(ApiResponseMessage.ERROR, "Address book with that id not found.");
	}
	
	public void deleteAddressBook(Integer addressBookId) throws NotFoundException {
		if (addressBooks.containsKey(addressBookId)) {
			addressBooks.remove(addressBookId);
			return;
		}
		throw new NotFoundException(ApiResponseMessage.ERROR, "Address book with that id not found.");
	}
	
	public void deleteEntry(Integer addressBookId, Integer entryId) throws NotFoundException {
		if (addressBooks.containsKey(addressBookId)) {
			AddressBook addressBook = addressBooks.get(addressBookId);
			for (Iterator<Entry> iterator = addressBook.getEntries().iterator(); iterator.hasNext();) {
				Entry entry = (Entry) iterator.next();
				if (entry.getId()==entryId) {
					iterator.remove();
					return;
				}
			}
			throw new NotFoundException(ApiResponseMessage.ERROR, "Entry with that id not found.");
		}
		throw new NotFoundException(ApiResponseMessage.ERROR, "Address book with that id not found.");
	}
	
	public AddressBook getAddressBook(Integer addressBookId) throws NotFoundException {
		if (addressBooks.containsKey(addressBookId)) {
			return addressBooks.get(addressBookId);
		}
		throw new NotFoundException(ApiResponseMessage.ERROR, "Address book with that id not found.");
	}
	
	public List<Entry> getEntries(Integer addressBookId) throws NotFoundException {
		if (addressBooks.containsKey(addressBookId)) {
			return addressBooks.get(addressBookId).getEntries();
		}
		throw new NotFoundException(ApiResponseMessage.ERROR, "Address book with that id not found.");
	}
	
	public Entry getEntry(Integer addressBookId, Integer entryId) throws NotFoundException {
		if (addressBooks.containsKey(addressBookId)) {
			AddressBook addressBook = addressBooks.get(addressBookId);
			for (Iterator<Entry> iterator = addressBook.getEntries().iterator(); iterator.hasNext();) {
				Entry entry = (Entry) iterator.next();
				if (entry.getId()==entryId) {
					return entry;
				}
			}
			throw new NotFoundException(ApiResponseMessage.ERROR, "Entry with that id not found.");
		}
		throw new NotFoundException(ApiResponseMessage.ERROR, "Address book with that id not found.");
	}
	
	public List<AddressBook> getAddressBooks() {
		return new ArrayList<AddressBook>(addressBooks.values());
	}
	
	public List<Entry> getUniqueEntries() {
		Set<Entry> entries = new HashSet<Entry>();
		for (Iterator<AddressBook> iterator = addressBooks.values().iterator(); iterator.hasNext();) {
			AddressBook addrBook = (AddressBook) iterator.next();
			for (Iterator<Entry> iterator2 = addrBook.getEntries().iterator(); iterator2.hasNext();) {
				Entry entry = (Entry) iterator2.next();
			}
		}
		return new ArrayList<Entry>(entries);
	}

}
