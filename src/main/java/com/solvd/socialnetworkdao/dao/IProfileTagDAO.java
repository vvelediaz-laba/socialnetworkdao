package com.solvd.socialnetworkdao.dao;

import com.solvd.socialnetworkdao.ProfileTag;

import java.util.List;

public interface IProfileTagDAO extends IDAO<ProfileTag>{
    List<ProfileTag> getProfileTagsByTaggedProfileId(long id);
    List<ProfileTag> getProfileTagsByProfileId(long id);
}
