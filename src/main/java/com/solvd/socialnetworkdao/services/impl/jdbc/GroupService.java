package com.solvd.socialnetworkdao.services.impl.jdbc;

import com.solvd.socialnetworkdao.Group;
import com.solvd.socialnetworkdao.GroupMembership;
import com.solvd.socialnetworkdao.dao.IGroupDAO;
import com.solvd.socialnetworkdao.dao.IGroupMembershipDAO;
import com.solvd.socialnetworkdao.dao.impl.jdbc.GroupDAO;
import com.solvd.socialnetworkdao.dao.impl.jdbc.GroupMembershipDAO;
import com.solvd.socialnetworkdao.services.IService;

import java.util.List;

public class GroupService implements IService<Group> {
    IGroupDAO groupDAO = new GroupDAO();
    IGroupMembershipDAO groupMembershipDAO = new GroupMembershipDAO();

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

    public void update(Group group) {
        groupDAO.update(group);
    }

    @Override
    public void delete(long id) {
        groupDAO.delete(id);
    }

    private void setValues(Group group) {
        List<GroupMembership> memberships = groupMembershipDAO.getMembershipsByGroupId(group.getId());
        group.setGroupMembers(memberships);
    }
}
