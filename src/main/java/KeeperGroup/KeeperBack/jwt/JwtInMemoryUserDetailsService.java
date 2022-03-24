package KeeperGroup.KeeperBack.jwt;

import KeeperGroup.KeeperBack.KeeperBackApplication;
import KeeperGroup.KeeperBack.Users.User;
import KeeperGroup.KeeperBack.Users.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

  private static final Logger LOGGER = LoggerFactory.getLogger(KeeperBackApplication.class);


  static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();

  @Autowired
  private UsersRepository usersRepository;


  void  init(){
    LOGGER.info("Init inmemory users");
    List<User> bdUsers = usersRepository.findAll();
    LOGGER.info("Boucle sur les users");
    bdUsers.forEach(u ->
            inMemoryUserList.add(new JwtUserDetails(1L, u.getUsername(),
                    u.getPassword(), "ROLE_USER_2"))
            );
    LOGGER.info("nb users : " + bdUsers.size());
  }

  /*static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();

  static {
    inMemoryUserList.add(new JwtUserDetails(1L, "me",
        "$2a$10$siPUVAFawFBbYQD2i43S6.V83wudYbTiBvik/VFj1EsVseLYiNn/S", "ROLE_USER_2"));
    inMemoryUserList.add(new JwtUserDetails(1L, "Trafi",
            "$2a$10$WXzMHU0Tl4No/zEIzYBmv.2dQtZm3SP/G9zUIob8VBwvmN.wBREKa", "ROLE_USER_3"));
  }*/

  //BCryptPasswordEncoder
  //Bcrypt hash

  //"$2a$10$siPUVAFawFBbYQD2i43S6.V83wudYbTiBvik/VFj1EsVseLYiNn/S" mine
//"$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e"
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    this.init();
    Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
        .filter(user -> user.getUsername().equals(username)).findFirst();

    if (!findFirst.isPresent()) {
      throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
    }

    return findFirst.get();
  }

}


