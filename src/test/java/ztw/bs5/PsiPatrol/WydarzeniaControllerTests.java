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
class WydarzeniaControllerTests {

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
				new UsernamePasswordAuthenticationToken("pracownik1@wp.pl", "pracownik1"));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		jwt = jwtUtils.generateJwtToken(authentication);
	}


	@Test
	void testGetFilteredWydarzenia() throws Exception {

		//pobranie wszystkich wydarzen
		mockMvc.perform(MockMvcRequestBuilders.get("/api/wydarzenia/filtered").header("Authorization",jwt)).andExpect(status().isOk());

		//pobranie przefiltrowanych wydarzeń
		mockMvc.perform(MockMvcRequestBuilders.get("/api/wydarzenia/filtered?name=&place=&category=Inne&onlyAvailable=&endDate=&beginDate=&size=2&page=0")
				.header("Authorization",jwt)).andExpect(status().isOk());

		//pobranie przefiltrowanych wydarzeń
		mockMvc.perform(MockMvcRequestBuilders.get("/api/wydarzenia/filtered?name=&place=Anim&category=Sprzatanie&onlyAvailable=&endDate=&beginDate=&size=2&page=0")
				.header("Authorization",jwt)).andExpect(status().isOk());


	}




}
