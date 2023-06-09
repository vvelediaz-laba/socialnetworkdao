package com.solvd.socialnetworkdao.dao;

import com.solvd.socialnetworkdao.Group;

public interface IGroupDAO extends IDAO<Group> {
    Group getByGroupMembershipId(long id);
}
