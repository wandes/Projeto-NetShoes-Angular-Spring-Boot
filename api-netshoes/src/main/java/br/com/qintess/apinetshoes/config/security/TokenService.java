package br.com.qintess.apinetshoes.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import br.com.qintess.apinetshoes.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${api-netshoes.jwt.expiration}")
	private String expiration;

	@Value("${api-netshoes.jwt.secret}")
	private String secret;

	public String gerarToken(Authentication authentication) {
		try {

			Usuario logado = (Usuario) authentication.getPrincipal();

			Date hoje = new Date();

			Date dataEpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));

			return Jwts.builder().setIssuer("API da netshoes").setSubject(logado.getId().toString()).setIssuedAt(hoje)
					.setExpiration(dataEpiracao).signWith(SignatureAlgorithm.HS256, secret).compact();

		} catch (AuthenticationException e) {
			System.out.println("ERROR TOKEN " + e.getMessage());
		}
		return null;
	}

	public boolean isTokenValido(String token) {

		try {

			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;

		} catch (Exception e) {
			return false;
		}

	}

	public Long getIdUsuario(String token) {
		
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

}
