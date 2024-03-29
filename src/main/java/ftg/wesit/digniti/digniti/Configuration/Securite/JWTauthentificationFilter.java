package ftg.wesit.digniti.digniti.Configuration.Securite;


import com.fasterxml.jackson.databind.ObjectMapper;
import ftg.wesit.digniti.digniti.Configuration.GestionErreurs.ErrorMessages;
import ftg.wesit.digniti.digniti.GestionUtilisateur.Model.Utilisateur;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JWTauthentificationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JWTauthentificationFilter(AuthenticationManager authenticationManager) {
        super();
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //ObjectMapper permet de recuperer un    objet json et de le stoker (rendre) dans une class java
        try {
            Utilisateur utilisateur=new ObjectMapper().readValue(request.getInputStream(),Utilisateur.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken( utilisateur.getUsername(),utilisateur.getPassword()));
        } catch (IOException e) {
            throw new ErrorMessages("L'utilisateur n'a pas été identifié", HttpStatus.CONFLICT);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User springUser= (User) authResult.getPrincipal();//on connait le user(springuser) on va utiliser ses information pour generer le token

        List<String> roles=new ArrayList<>();
        authResult.getAuthorities().forEach(r->{
            roles.add(r.getAuthority());
        });
        String jwt= Jwts.builder()
                .setSubject(springUser.getUsername())
                .setExpiration(new Date(System.currentTimeMillis()+ SecurityConstant.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstant.SECRET)
                .claim("roles",springUser.getAuthorities())
                .compact();
        System.out.println("le json web token est : "+jwt);
        response.addHeader(SecurityConstant.HEADER_STRING, SecurityConstant.TOKEN_PREFIX+jwt);
        System.out.println("la reponse du json web token est : "+response.getHeaderNames()+"status est "+response.getStatus()+" et le type "+response.getContentType());
        //super.successfulAuthentication(request, response, chain, authResult);
    }
}
