package org.karti.service;

import org.karti.model.User;

import java.util.*;

public class UserService {

    private Map<Integer, User> users;

    public UserService(){
        users = new HashMap<>();
    }

    public User createUser(User user){
        users.put(user.getId(), user);
        return user;
    }

    public List<User> getAllUsers(){
        return new ArrayList<>(users.values());
    }

    public User getUserById(Integer id){
        return users.getOrDefault(id, null);
    }

    public User putUser(User user){
        users.remove(user.getId());
        users.put(user.getId(), user);
        return user;
    }

    public User deleteUserById(Integer id){
        return users.remove(id);
    }

    public String getJson(User user){
        return String.format("{\n\"id\":%d,\n\"name\":\"%s\",\n\"email\":\"%s\",\n\"mobile\":\"%s\"\n}", user.getId(), user.getName(), user.getEmail(), user.getMobile());
    }

    public String getListJson(List<User> list){
        Iterator<User> it = list.iterator();
        StringBuilder s = new StringBuilder("[");
        while(it.hasNext()){
            if(s.length()==1){
                s.append(getJson(it.next()));
                continue;
            }
            s.append(",\n"+getJson(it.next()));
        }
        s.append("]");
        return s.toString();
    }

}
