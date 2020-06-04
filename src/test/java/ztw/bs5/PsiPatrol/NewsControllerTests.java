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
class NewsControllerTests {

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private MockMvc mockMvc;

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

	private String jwt;



	@Test
	void testCreateNews() throws Exception {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken("przewo@wp.pl", "przewo"));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		jwt = jwtUtils.generateJwtToken(authentication);

		String jsonString = "{\"naglowek\": \"Test trestowy news\",\"tresc\":\"To jest tres testowego newsa\"}";

		//pomyślne utworzenie oferty
		mockMvc.perform(MockMvcRequestBuilders.post("/api/newsy").header("Authorization",jwt).contentType(APPLICATION_JSON_UTF8).content(jsonString)).andExpect(status().isCreated());

	}

	@Test
	void testGetOferty() throws Exception {

		//pobranie wszystkich newsów
		mockMvc.perform(MockMvcRequestBuilders.get("/api/newsy")).andExpect(status().isOk());

		//pobranie newsa wg id
		mockMvc.perform(MockMvcRequestBuilders.get("/api/newsy/1")).andExpect(status().isOk());

		//próba pobrania newsa o niestniejącym id
		mockMvc.perform(MockMvcRequestBuilders.get("/api/newsy/16")).andExpect(status().isNotFound());

	}





}
