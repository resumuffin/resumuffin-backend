package resu.muffin.users_and_comments.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import resu.muffin.users_and_comments.users.db.RoleRepository;
import resu.muffin.users_and_comments.users.db.UserRepository;
import resu.muffin.users_and_comments.users.entities.Role;
import resu.muffin.users_and_comments.users.entities.User;

@Component
public class DBConfig implements ApplicationListener<ContextRefreshedEvent>{

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        roleRepo.save(new Role(("Admin"), true, true, true, true, true));
        roleRepo.save(new Role(("User"), true, false, true, true, true));
        //Short shortz = 0;
       // userRepo.save(new User("test", "test", "test", roleRepo.findById(shortz).get()));
    }

}