package ousmane.alhassane.web;

import ousmane.alhassane.dao.AppUserRepository;
import ousmane.alhassane.entities.AppUser;
import ousmane.alhassane.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AppUserRepository appUserRepository;
    @PostMapping("/register")
    public AppUser register(@RequestBody UserForm userForm){
        return accountService.saveUser(userForm.getUsername(),userForm.getPassword(),userForm.getConfirmedPassword());
    }
    @GetMapping("/appUsers")
    public List<AppUser> listUsers(){
        return this.appUserRepository.findAll();
    }
}
class UserForm{
    private String username;
    private String password;
    private String confirmedPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }
}
