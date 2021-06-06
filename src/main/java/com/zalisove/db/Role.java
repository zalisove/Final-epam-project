package com.zalisove.db;

import com.zalisove.db.entity.User;

/**
 * Role entity
 * @author O.S.Kyrychenko
 */
public enum Role {
    ADMIN, CLIENT;

    public static Role getRole(User user){
        int roleId = user.getRoleId();
        return Role.values()[roleId-1];
    }
    public static Role getRole(int id){
        return Role.values()[(id - 1)];
    }
    public static int getRoleId(Role role){
        for (int i = 0; i < Role.values().length; i++) {
            if (Role.values()[i].equals(role)){
                return i+1;
            }
        }
        return -1;
    }


}
