package ztw.bs5.PsiPatrol;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ztw.bs5.PsiPatrol.Security.Jwt.JwtUtils;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@EnableWebMvc
class WiadomoscControllerTests {

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private MockMvc mockMvc;

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

	private String jwt;


	@BeforeEach
	void preparation(){
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken("wolon1@wp.pl", "wolon1"));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		jwt = jwtUtils.generateJwtToken(authentication);
	}


	@Test
	void testWyslijWiadomosc() throws Exception {

		String jsonString = "{\"temat\": \"Jutro akcja\",\"tresc\":\"Pamietasz o akcji?\"}";
		String correctMail = "wolon5@wp.pl" ;
		String incorrectMail = "nieistniejacy@mail.com";


		//wysłanie wiadomosci
		mockMvc.perform(MockMvcRequestBuilders.post("/api/wiadomosci/wyslij/wolon5@wp.pl").header("Authorization",jwt).contentType(APPLICATION_JSON_UTF8).content(jsonString)).andExpect(status().isOk());

		//próba wysłania wiadomosci na nieistniejący adres
		mockMvc.perform(MockMvcRequestBuilders.post("/api/wiadomosci/wyslij/"+incorrectMail).header("Authorization",jwt).contentType(APPLICATION_JSON_UTF8).content(jsonString)).andExpect(status().isBadRequest());

	}

	@Test
	void testGetWiadomosci() throws Exception {

		//pobranie wszystkich wiadomosci
		mockMvc.perform(MockMvcRequestBuilders.get("/api/wiadomosci").header("Authorization",jwt)).andExpect(status().isOk());

		//pobranie wiadomosci wg id
		mockMvc.perform(MockMvcRequestBuilders.get("/api/wiadomosci/1").header("Authorization",jwt)).andExpect(status().isOk());

		//próba pobrania wiadomosci o niestniejącym id
		mockMvc.perform(MockMvcRequestBuilders.get("/api/wiadomosci/16").header("Authorization",jwt)).andExpect(status().isNotFound());

		//pobranie wiadomosci wysłanych przez zalogowanego
		mockMvc.perform(MockMvcRequestBuilders.get("/api/wiadomosci/wyslane").header("Authorization",jwt)).andExpect(status().isOk());

		//pobranie wiadomosci, odebranych przez zalogowanego
		mockMvc.perform(MockMvcRequestBuilders.get("/api/wiadomosci/odebrane").header("Authorization",jwt)).andExpect(status().isOk());



	}





}
