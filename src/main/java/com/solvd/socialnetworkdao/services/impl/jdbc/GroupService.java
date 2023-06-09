package com.solvd.socialnetworkdao.services.impl.jdbc;

import com.solvd.socialnetworkdao.Group;
import com.solvd.socialnetworkdao.GroupMembership;
import com.solvd.socialnetworkdao.dao.IGroupDAO;
import com.solvd.socialnetworkdao.dao.IGroupMembershipDAO;
import com.solvd.socialnetworkdao.dao.impl.jdbc.GroupDAO;
import com.solvd.socialnetworkdao.dao.impl.jdbc.GroupMembershipDAO;
import com.solvd.socialnetworkdao.services.IGroupService;

import java.util.List;

public class GroupService implements IGroupService {
    IGroupDAO groupDAO = new GroupDAO();
    IGroupMembershipDAO groupMembershipDAO = new GroupMembershipDAO();

    @Override
    public void insert(Group group) {
        groupDAO.insert(group);
    }

    @Override
    public Group getById(long id) {
        Group group = groupDAO.getById(id);
        setValues(group);
        return group;
    }

    @Override
    public List<Group> getAll() {
        List<Group> groups =  groupDAO.getAll();
        for(Group group : groups){
            setValues(group);
        }
        return groups;
    }

    @Override
    public void update(Group group) {
        groupDAO.update(group);
    }

    @Override
    public void delete(long id) {
        groupDAO.delete(id);
    }

    @Override
    public void setValues(Group group) {
        List<GroupMembership> memberships = groupMembershipDAO.getMembershipsByGroupId(group.getId());
        for(GroupMembership membership : memberships){
            new GroupMembershipServiceService().setValues(membership);
        }
        group.setGroupMembers(memberships);
    }
}
