package com.solvd.socialnetworkdao.mapper;

import com.solvd.socialnetworkdao.Profile;

import java.util.List;

public interface ProfileMapper {

    Profile getProfileById(long id);

    void insertProfile(Profile profile);

    List<Profile> getAllProfiles();

    void updateProfile(Profile profile);

    void deleteProfile(long id);

}

