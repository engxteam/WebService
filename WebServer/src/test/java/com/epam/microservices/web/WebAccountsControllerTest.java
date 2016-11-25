/**
 * 
 */
package com.epam.microservices.web;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.MockitoAnnotations;
import org.mockito.MockitoAnnotations.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.epam.microservices.config.AccountConfiguration;
import com.epam.microservices.service.WebAccountsService;
import com.epam.microservices.vo.Account;

/**
 * @author Dilip
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AccountConfiguration.class})
//@WebMvcTest(WebAccountsController.class)
public class WebAccountsControllerTest {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	WebApplicationContext  wac ;
	
	private MockMvc mockMvc ;
	@SuppressWarnings("deprecation")
	@Mock
	protected WebAccountsService accountsServiceMock;
	@Before
	public void setUp() {
		
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		MockitoAnnotations.initMocks(this);

	}
	
	

	/**
	 * Test method for {@link com.epam.microservices.web.WebAccountsController#goHome(org.springframework.ui.Model)}.
	 * @throws Exception 
	 */
	@Test
	public void testGoHome() throws Exception {
		
		mockMvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(view().name("accountSearch"))
        .andExpect(forwardedUrl("accountSearch"));

	}

	/**
	 * Test method for {@link com.epam.microservices.web.WebAccountsController#byNumber(org.springframework.ui.Model, java.lang.String)}.
	 * @throws Exception 
	 */
	@Ignore
	@Test
	
	public void testByNumber() throws Exception {
		Account dummyAccount = new Account();
		dummyAccount.setId(1L);
		dummyAccount.setNumber("123");
		dummyAccount.setOwner("abc");
		//String uri = "http://ACCOUNTS-SERVICE:2222/accounts/123";
		when(restTemplate.getForObject(Matchers.anyString(), Account.class, 
				Matchers.anyMapOf(String.class, String.class))).thenReturn(dummyAccount);
		
		mockMvc.perform(get("/accounts/{accountNumber}",123))
        .andExpect(status().isOk())
        .andExpect(view().name("accounts"))
        .andExpect(forwardedUrl("/accounts.html"))
        .andExpect(model().attribute("account", hasProperty("id", is(1L))))
        .andExpect(model().attribute("account", hasProperty("number", is("123"))))
        .andExpect(model().attribute("account", hasProperty("owner", is("abc"))))
        .andExpect(model().attribute("account", hasProperty("balance", is(0))));
		when(accountsServiceMock.findByNumber(anyString())).thenReturn(dummyAccount);
		
		verify(accountsServiceMock, times(1)).findByNumber(anyString());

	}

	/**
	 * Test method for {@link com.epam.microservices.web.WebAccountsController#ownerSearch(org.springframework.ui.Model, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testOwnerSearch() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.epam.microservices.web.WebAccountsController#searchForm(org.springframework.ui.Model)}.
	 */
	@Ignore
	@Test
	public void testSearchForm() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.epam.microservices.web.WebAccountsController#doSearch(org.springframework.ui.Model, com.epam.microservices.web.SearchCriteria, org.springframework.validation.BindingResult)}.
	 */
	@Ignore
	@Test
	public void testDoSearch() {
		fail("Not yet implemented");
	}

}
