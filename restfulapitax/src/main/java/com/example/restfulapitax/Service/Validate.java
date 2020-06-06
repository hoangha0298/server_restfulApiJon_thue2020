package com.example.restfulapitax.Service;

import org.springframework.stereotype.Service;

@Service
public class Validate {

    // passwork chỉ chứa khoảng cách , kí tự 1->9, a->z, A->Z
    public static boolean validatePasswork (String passwork) {
        char[] passworks = passwork.toCharArray();
        if (passworks.length < 8 || passworks.length > 30) return false;
        for (char c : passworks)
        {
            if (c < 32) return false; // khoang cach = 32
            if (c > 32 && c < 48) return false; // kí tự 1->9 [48, 57]
            if (c > 57 && c < 65) return false; // a->z [65, 90]
            if (c > 90 && c < 97) return false; // A->Z [97 ,122]
            if (c > 122) return false;
        }
        return true;
    }




}
