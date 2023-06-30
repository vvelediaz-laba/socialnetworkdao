package com.solvd.socialnetworkdao.services.impl;

import com.solvd.socialnetworkdao.Group;
import com.solvd.socialnetworkdao.GroupMembership;
import com.solvd.socialnetworkdao.dao.IGroupDAO;
import com.solvd.socialnetworkdao.dao.IGroupMembershipDAO;
import com.solvd.socialnetworkdao.services.IService;

import java.util.List;

public class GroupService implements IService<Group> {
    private IGroupDAO groupDAO;
    private IGroupMembershipDAO groupMembershipDAO;

    public GroupService(IGroupDAO groupDAO, IGroupMembershipDAO groupMembershipDAO) {
        this.groupDAO = groupDAO;
        this.groupMembershipDAO = groupMembershipDAO;
    }

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

    public IGroupDAO getGroupDAO() {
        return groupDAO;
    }

    public void setGroupDAO(IGroupDAO groupDAO) {
        this.groupDAO = groupDAO;
    }

    public IGroupMembershipDAO getGroupMembershipDAO() {
        return groupMembershipDAO;
    }

    public void setGroupMembershipDAO(IGroupMembershipDAO groupMembershipDAO) {
        this.groupMembershipDAO = groupMembershipDAO;
    }

    private void setValues(Group group) {
        List<GroupMembership> memberships = groupMembershipDAO.getMembershipsByGroupId(group.getId());
        group.setGroupMembers(memberships);
    }
}
