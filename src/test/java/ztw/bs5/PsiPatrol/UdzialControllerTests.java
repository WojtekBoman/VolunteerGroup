package ztw.bs5.PsiPatrol;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;
import ztw.bs5.PsiPatrol.Security.Jwt.JwtUtils;
import ztw.bs5.PsiPatrol.Security.SecurityConfig;

import java.net.URI;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UdzialControllerTests {

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private MockMvc mockMvc;

	private String jwt;


	@BeforeEach
	void preparation(){
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken("wolon1@wp.pl", "wolon1"));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		jwt = jwtUtils.generateJwtToken(authentication);
	}


	@Test
	void testWydarzeniaAssignments() throws Exception {

		//pomyślne wzięcie udziału w wydarzeniu
		mockMvc.perform(MockMvcRequestBuilders.post("/api/udzial/wez/11").header("Authorization",jwt)).andExpect(status().isOk());

		//ponowna próba wzięcia udziału w tym samym wydarzeniu
		mockMvc.perform(MockMvcRequestBuilders.post("/api/udzial/wez/11").header("Authorization",jwt)).andExpect(status().isConflict());

		//próba wzięcia udziału w tym "pełnym" wydarzeniu
		mockMvc.perform(MockMvcRequestBuilders.post("/api/udzial/wez/1").header("Authorization",jwt)).andExpect(status().isConflict());

		//pomyślna rezygnacja z udziału w wydarzeniu
		mockMvc.perform(MockMvcRequestBuilders.post("/api/udzial/anuluj/11").header("Authorization",jwt)).andExpect(status().isOk());

		//próba rezygnacji z wydarzenia nie będąc do niego przypisanym
		mockMvc.perform(MockMvcRequestBuilders.post("/api/udzial/anuluj/11").header("Authorization",jwt)).andExpect(status().isConflict());
	}

	@Test
	void testGetWydarzeniaUzytkownika() throws Exception {

		//pobranie wydarzeń użytkownika
		mockMvc.perform(MockMvcRequestBuilders.get("/api/udzial/wydarzenia-uzytkownika").header("Authorization",jwt)).andExpect(status().isOk());

		//pobranie wydarzeń użytkownika
		mockMvc.perform(MockMvcRequestBuilders.get("/api/udzial/wydarzenia-uzytkownika/wolon2@wp.pl").header("Authorization",jwt)).andExpect(status().isOk());

		//pobranie wydarzeń użytkownika "bez wydarzeń"
		mockMvc.perform(MockMvcRequestBuilders.get("/api/udzial/wydarzenia-uzytkownika/wolon5@wp.pl").header("Authorization",jwt)).andExpect(status().isNoContent());

	}

	@Test
	void testGetUzytkownicyWydarzenia() throws Exception {

		//pobranie użytkowników wydarzenia
		mockMvc.perform(MockMvcRequestBuilders.get("/api/udzial/uzytkownicy-wydarzenia/1").header("Authorization",jwt)).andExpect(status().isOk());

		//próba pobrania użytkowników nieistniejącego wydarzenia
		mockMvc.perform(MockMvcRequestBuilders.get("/api/udzial/uzytkownicy-wydarzenia/99").header("Authorization",jwt)).andExpect(status().isNotFound());

		//próba pobrania użytkowników "pustego" wydarzenia
		mockMvc.perform(MockMvcRequestBuilders.get("/api/udzial/uzytkownicy-wydarzenia/31").header("Authorization",jwt)).andExpect(status().isNoContent());

	}


}
