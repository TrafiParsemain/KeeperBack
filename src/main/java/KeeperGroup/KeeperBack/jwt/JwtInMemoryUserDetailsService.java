package KeeperGroup.KeeperBack.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

  static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();

  static {
    inMemoryUserList.add(new JwtUserDetails(1L, "me",
        "$2a$10$siPUVAFawFBbYQD2i43S6.V83wudYbTiBvik/VFj1EsVseLYiNn/S", "ROLE_USER_2"));
    inMemoryUserList.add(new JwtUserDetails(1L, "Trafi",
            "$2a$10$WXzMHU0Tl4No/zEIzYBmv.2dQtZm3SP/G9zUIob8VBwvmN.wBREKa", "ROLE_USER_3"));
  }

  //BCryptPasswordEncoder
  //Bcrypt hash

  //"$2a$10$siPUVAFawFBbYQD2i43S6.V83wudYbTiBvik/VFj1EsVseLYiNn/S" mine
//"$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e"
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
        .filter(user -> user.getUsername().equals(username)).findFirst();

    if (!findFirst.isPresent()) {
      throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
    }

    return findFirst.get();
  }

}


