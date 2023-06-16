package com.solvd.socialnetworkdao.dao;

import com.solvd.socialnetworkdao.ProfileTag;

import java.util.List;

public interface IProfileTagDAO extends IDAO<ProfileTag>{
    void insert(ProfileTag profileTag, long taggedPost, long taggedProfileId);
    void update(ProfileTag profileTag, long taggedPost, long taggedProfileId);
    List<ProfileTag> getProfileTagsByPostId(long id);
    List<ProfileTag> getProfileTagsByProfileId(long id);
}
