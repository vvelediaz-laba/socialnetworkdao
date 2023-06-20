package com.solvd.socialnetworkdao.mapper;

import com.solvd.socialnetworkdao.Group;

import java.util.List;

public interface GroupMapper {

    List<Group> getAllGroups();

    Group getGroupById(long id);

    void insertGroup(Group group);

    void updateGroup(Group group);

    void deleteGroup(long id);

}
