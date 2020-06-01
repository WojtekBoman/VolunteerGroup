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
class StatsControllerTests {

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
	void testGetAktywnosc() throws Exception {

		//pobranie aktywnosci dla 10 najlepszych wolontariuszy
		mockMvc.perform(MockMvcRequestBuilders.get("/api/statystyki/aktywnosc/10").header("Authorization",jwt)).andExpect(status().isOk());

		//pobranie aktywnosci dla 0 najlepszych wolontariuszy
		mockMvc.perform(MockMvcRequestBuilders.get("/api/statystyki/aktywnosc/0").header("Authorization",jwt)).andExpect(status().isNoContent());


	}

	@Test
	void testGetMiesiaceDlaZbiorek() throws Exception {

		//pobranie liczby zbiorek wg miesięcy dla roku 2020
		mockMvc.perform(MockMvcRequestBuilders.get("/api/statystyki/zbiorki/2020").header("Authorization",jwt)).andExpect(status().isOk());

		//pobranie liczby zbiorek wg miesięcy dal roku 2019
		mockMvc.perform(MockMvcRequestBuilders.get("/api/statystyki/zbiorki/2019").header("Authorization",jwt)).andExpect(status().isOk());

	}

	@Test
	void testGetMiesiaceDlaWydarzen() throws Exception {

		//pobranie liczby wydarzen wg miesięcy dla roku 2020
		mockMvc.perform(MockMvcRequestBuilders.get("/api/statystyki/wydarzenia/2020").header("Authorization",jwt)).andExpect(status().isOk());

		//pobranie liczby wydarzen wg miesięcy dla roku 2019
		mockMvc.perform(MockMvcRequestBuilders.get("/api/statystyki/wydarzenia/2019").header("Authorization",jwt)).andExpect(status().isOk());

	}

}
