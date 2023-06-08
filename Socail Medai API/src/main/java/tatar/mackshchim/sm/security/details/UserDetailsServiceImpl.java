package tatar.mackshchim.sm.security.details;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import tatar.mackshchim.sm.models.User;
import tatar.mackshchim.sm.repositories.UsersRepository;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = usersRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User with email <" + email + "> not found"));
        return new UserDetailsImpl(user);
    }
}
