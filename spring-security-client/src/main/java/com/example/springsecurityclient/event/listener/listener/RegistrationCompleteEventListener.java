package com.example.springsecurityclient.event.listener.listener;

import com.example.springsecurityclient.entity.User;
import com.example.springsecurityclient.event.RegistrationCompleteEvent;
import com.example.springsecurityclient.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import java.util.UUID;

@Slf4j
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
   @Autowired
    private UserService userService;
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        //create the verification token for user

        User user = event.getUser();
        //save this verification token in db
        String token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(token,user);
        //send email to user
        String url =
                event.getApplicationUrl() + "verifyRegistration?token="+token;
        //implement sendVerificationEmail method here,
        //right now we are just mocking it
        log.info("Click the link to verify your account:{}",url);
    }
}
