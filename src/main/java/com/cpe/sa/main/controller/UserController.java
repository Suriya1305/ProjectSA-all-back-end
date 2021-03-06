package com.cpe.sa.main.controller;

import java.util.List;
import java.util.Map;

import com.cpe.sa.main.entity.User;
import com.cpe.sa.main.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/user")
public class UserController{
    @Autowired private UserRepository userRepository;

    @GetMapping()
    public List<User> users(){
        return userRepository.findAll();
    }

    @PostMapping()
    public Boolean findUserName(@RequestBody Map<String,String> body) throws NotFoundException {

        User user = userRepository.findByUserName(body.get("username"));
        

        if(user == null){
            //System.out.println("No User!");
            //return false;
            throw new NotFoundException();
        }
            
        System.out.println(user.getPassWord().compareTo(body.get("password")));
        if(user.getPassWord().compareTo(body.get("password")) != 0){
            //System.out.println("Not Password!");
            //return false;
            throw new NotFoundException();
        }
            
        
        return true;
    }
}