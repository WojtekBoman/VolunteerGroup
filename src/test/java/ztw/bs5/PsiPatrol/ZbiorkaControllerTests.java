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
class ZbiorkaControllerTests {

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
				new UsernamePasswordAuthenticationToken("prac@wp.pl", "prac123"));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		jwt = jwtUtils.generateJwtToken(authentication);
	}


	@Test
	void testCreateZbiorka() throws Exception {

		String jsonString = "{\"tytul\": \"Testowa zbiórka\",\"opis\":\"To jest opis testowej zbiórki\",\"dataRozpoczecia\":\"2020-05-26\",\"dataZakonczenia\":\"2020-08-03\",\"kwotaPotrzebna\":\"1101\"}";

		//pomyślne utworzenie oferty
		mockMvc.perform(MockMvcRequestBuilders.post("/api/zbiorki").header("Authorization",jwt).contentType(APPLICATION_JSON_UTF8).content(jsonString)).andExpect(status().isCreated());

	}

	@Test
	void testGetZbiorka() throws Exception {

		//pobranie wszystkich zbiórek
		mockMvc.perform(MockMvcRequestBuilders.get("/api/zbiorki").header("Authorization",jwt)).andExpect(status().isOk());

		//pobranie zbiórki wg id
		mockMvc.perform(MockMvcRequestBuilders.get("/api/zbiorki/1").header("Authorization",jwt)).andExpect(status().isOk());

		//próba pobrania zbiórki o niestniejącym id
		mockMvc.perform(MockMvcRequestBuilders.get("/api/zbiorki/16").header("Authorization",jwt)).andExpect(status().isNotFound());

		//pobranie przefiltrowanych zbiórek
		mockMvc.perform(MockMvcRequestBuilders.get("/api/zbiorki/filtered?title=ar").header("Authorization",jwt)).andExpect(status().isOk());

		//próba pobrania przefiltrowanych zbiórek (błędne wyszukiwanie)
		mockMvc.perform(MockMvcRequestBuilders.get("/api/zbiorki/filtered?title=qwerty").header("Authorization",jwt)).andExpect(status().isNoContent());

	}

}
