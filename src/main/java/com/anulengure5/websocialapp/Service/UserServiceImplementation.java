package com.anulengure5.websocialapp.Service;

import com.anulengure5.websocialapp.Repository.UserRepository;
import com.anulengure5.websocialapp.config.JwtProvider;
import com.anulengure5.websocialapp.exceptions.UserExceptions;
import com.anulengure5.websocialapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation  implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User registerUser(User user) {

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(user.getPassword());
        newUser.setId(user.getId());

        User savedUser=userRepository.save(newUser);
        return savedUser;


    }

    @Override
    public User findUserById(Integer userId) throws UserExceptions {

        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            return user.get();
        }
        throw new UserExceptions("user not exist with userid " + userId);
    }

    @Override
    public User findUserByEmail(String email) {
      User user=userRepository.findByEmail(email);
      return user;

    }

    @Override
    public User followUser(Integer reqUserId, Integer userId2) throws UserExceptions{
//       reqUser follows User 2
       User user2=findUserById(userId2);
       User reqUser=findUserById(reqUserId);
   //check test modified
         if(reqUser.getFollowers().contains(user2)){
           reqUser.getFollowers().remove(user2);
           return userRepository.save(reqUser);
       }

       user2.getFollowers().add(reqUser.getId());
       reqUser.getFollowing().add(user2.getId());

       userRepository.save(user2);
       userRepository.save(reqUser);
    return reqUser;
    }

    @Override
    public User updateUser(User user,Integer userId) throws UserExceptions {

        Optional <User> user1=userRepository.findById(userId);

        if(user1.isEmpty()){
            throw new UserExceptions("user not exist with userid " + userId);
        }

        User oldUser=user1.get();


        if(user.getFirstName()!=null)
        {
            oldUser.setFirstName(user.getFirstName());
        }

        if(user.getLastName()!=null)
        {
            oldUser.setLastName(user.getLastName());
        }
        if(user.getEmail()!=null)
        {
            oldUser.setEmail(user.getEmail());
        }

        if(user.getGender()!=null)
        {
            oldUser.setGender(user.getGender());
        }

        User updatedUser=userRepository.save(oldUser);
//        oldUser has been updated with request body values

        return updatedUser;
    }

    @Override
    public List<User> searchUser(String query) {

        List<User> users=userRepository.searchUser(query);
        return users;
    }

    @Override
    public User findUserByJwt(String jwt) throws UserExceptions {

        String email= JwtProvider.getEmailFromJwtToken(jwt);
        User user=userRepository.findByEmail(email);
        return user;
    }
}
