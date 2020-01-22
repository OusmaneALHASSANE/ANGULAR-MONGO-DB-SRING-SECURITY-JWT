package ousmane.alhassane;

import ousmane.alhassane.entities.AppRole;
import ousmane.alhassane.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.stream.Stream;

@SpringBootApplication
public class SecServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecServiceApplication.class, args);
    }
  @Bean
    CommandLineRunner start(AccountService accountService){
       return args -> {
           accountService.save(new AppRole("USER"));
           accountService.save(new AppRole("ADMIN"));
           accountService.save(new AppRole("ANALYSE_MANAGER"));
           Stream.of("usr1","usr2","usr3","admin").forEach(u->{
           accountService.saveUser(u,"1234","1234");

                   }
           );
           accountService.addRoleToUser("admin","ADMIN");
           accountService.addRoleToUser("admin","ANALYSE_MANAGER");
           accountService.addRoleToUser("usr1","ANALYSE_MANAGER");

       };
  }
  @Bean
  BCryptPasswordEncoder getBCPE(){
        return new  BCryptPasswordEncoder();
  }
}
