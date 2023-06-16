package com.solvd.socialnetworkdao.dao;

import com.solvd.socialnetworkdao.Group;

public interface IGroupDAO extends IDAO<Group> {
    void insert(Group group);
    void update(Group group);
    Group getByGroupMembershipId(long id);
}
