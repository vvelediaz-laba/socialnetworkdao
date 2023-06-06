package com.solvd.socialnetworkdao.interfaces;

import com.solvd.socialnetworkdao.ProfileTag;

import java.util.List;

public interface IProfileTagDAO extends IDAO<ProfileTag>{
    List<ProfileTag> getProfileTagsByProfileId(long id);
}
