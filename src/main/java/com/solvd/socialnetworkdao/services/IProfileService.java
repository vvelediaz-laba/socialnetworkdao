package com.solvd.socialnetworkdao.services;

import com.solvd.socialnetworkdao.Profile;

public interface IProfileService extends IService<Profile>{
    void insert(Profile profile);
    void update(Profile profile);
}
