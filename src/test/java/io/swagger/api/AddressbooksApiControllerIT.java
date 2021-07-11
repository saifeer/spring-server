package io.swagger.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import io.swagger.Swagger2SpringBoot;
import io.swagger.model.AddressBook;
import io.swagger.model.Entry;
import io.swagger.model.PhoneNumber;
import io.swagger.model.PhoneNumber.TypeEnum;
import io.swagger.service.AddressBookService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Swagger2SpringBoot.class,
webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AddressbooksApiControllerIT {
	
	@Autowired
	private AddressBookService addressBookService;
	private AddressBook addressBook;
	
	private String addrBookEndpoint = "http://localhost:8080/addressbooks";
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	
	private void setup() {
		AddressBook addrBook = new AddressBook();
		addrBook.id(0);
		addrBook.name("Basic");
		addrBook.addEntriesItem(createEntry());
		addressBook = addressBookService.createAddressBook(addrBook);
	}
	
	@Test
	public void testCreateAddressBook() {
		AddressBook addrBook = new AddressBook();
		addrBook.id(0);
		addrBook.name("Test");
		HttpEntity<AddressBook> body = new HttpEntity<>(addrBook);

        ResponseEntity<AddressBook> response = restTemplate.exchange(
        		addrBookEndpoint,
                HttpMethod.POST, body, AddressBook.class);
        assertTrue(response.getStatusCode().value() == 200);
        assertTrue(response.getBody().getId()>0);
        assertTrue(response.getBody().equals(addrBook));
	}
	
	@Test
	public void testCreateEntry() {
		setup();
		Entry entry = createEntry();
		HttpEntity<Entry> body = new HttpEntity<>(entry);

        ResponseEntity<Entry> response = restTemplate.exchange(
        		createAddrBookUrl() + "/entries",
                HttpMethod.POST, body, Entry.class);
        assertTrue(response.getStatusCode().value() == 200);
        assertTrue(response.getBody().getId()>0);
        assertTrue(response.getBody().equals(entry));
	}
	
	@Test
	public void testGetAddressBook() {
		setup();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");
		headers.add("Content-type", "application/json");
		ResponseEntity<AddressBook> response = restTemplate.exchange(
				createAddrBookUrl(),
                HttpMethod.GET,new HttpEntity<>(headers), AddressBook.class);
        assertEquals(response.getStatusCode().value(), 200);
	}
	
	@Test
	public void testGetEntries() {
		setup();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");
		headers.add("Content-type", "application/json");
		ResponseEntity<Entry[]> response = restTemplate.exchange(
				createAddrBookUrl()+"/entries",
                HttpMethod.GET, new HttpEntity<>(headers), Entry[].class);
        assertEquals(response.getStatusCode().value(), 200);
	}
	
	@Test
	public void testGetEntry() {
		setup();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");
		headers.add("Content-type", "application/json");
		ResponseEntity<Entry> response = restTemplate.exchange(
				createAddrBookUrl()+"/entries/"+addressBook.getEntries().get(0).getId(),
                HttpMethod.GET, new HttpEntity<>(headers), Entry.class);
        assertEquals(response.getStatusCode().value(), 200);
	}
	
	@Test
	public void testGetUniqueEntries() {
		setup();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");
		headers.add("Content-type", "application/json");
		ResponseEntity<Entry[]> response = restTemplate.exchange(
				"http://localhost:8080/entries/",
                HttpMethod.GET, new HttpEntity<>(headers), Entry[].class);
        assertEquals(response.getStatusCode().value(), 200);
	}
	
	@Test(expected = NotFoundException.class)
	public void testDeleteEntry() throws NotFoundException {
		setup();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");
		headers.add("Content-type", "application/json");
		Entry entry = addressBook.getEntries().get(0);
		ResponseEntity<Void> response = restTemplate.exchange(
				createAddrBookUrl()+"/entries/"+addressBook.getEntries().get(0).getId(),
                HttpMethod.DELETE, new HttpEntity<>(headers), Void.class);

        assertEquals(response.getStatusCode().value(), 204);

        addressBookService.getEntry(addressBook.getId(), entry.getId());
	}
	
	@Test(expected = NotFoundException.class)
	public void testDeleteAddressBook() throws NotFoundException {
		setup();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");
		headers.add("Content-type", "application/json");
		ResponseEntity<Void> response = restTemplate.exchange(
				createAddrBookUrl(),
                HttpMethod.DELETE, new HttpEntity<>(headers), Void.class);

        assertEquals(response.getStatusCode().value(), 204);

        addressBookService.getAddressBook(addressBook.getId());
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
	
	private String createAddrBookUrl() {
		return addrBookEndpoint + "/" + addressBook.getId();
	}
	
//	@Test
//    public void validateThatImplementationMatchesDocumentationSpecification(){
//        String designFirstSwagger = AddressbooksApiControllerIT.class.getResource("/openapi-spec.yaml").getPath();
//        SwaggerAssertions.assertThat("http://localhost:8080/v2/api-docs")
//            .isEqualTo(designFirstSwagger);
//    }
}