package com.solvd.socialnetworkdao.services.impl;

import com.solvd.socialnetworkdao.Group;
import com.solvd.socialnetworkdao.GroupMembership;
import com.solvd.socialnetworkdao.Profile;
import com.solvd.socialnetworkdao.dao.IGroupDAO;
import com.solvd.socialnetworkdao.dao.IGroupMembershipDAO;
import com.solvd.socialnetworkdao.dao.IProfileDAO;
import com.solvd.socialnetworkdao.dao.impl.GroupDAO;
import com.solvd.socialnetworkdao.dao.impl.GroupMembershipDAO;
import com.solvd.socialnetworkdao.dao.impl.ProfileDAO;
import com.solvd.socialnetworkdao.services.IGroupMembershipService;
import com.solvd.socialnetworkdao.services.IGroupService;
import com.solvd.socialnetworkdao.services.IProfileService;

import java.util.List;

public class GroupMembershipServiceService implements IGroupMembershipService {
    private final IGroupMembershipDAO groupMembershipDAO = new GroupMembershipDAO();
    private final IProfileDAO profileDAO = new ProfileDAO();
    private final IProfileService profileService = new ProfileService();
    private final IGroupDAO groupDAO = new GroupDAO();
    private final IGroupService groupService = new GroupService();

    @Override
    public void insert(GroupMembership groupMembership) {
        groupMembershipDAO.insert(groupMembership);
    }

    @Override
    public GroupMembership getById(long id){
        GroupMembership groupMembership = new GroupMembershipDAO().getById(id);
        setValues(groupMembership);

        return groupMembership;
    }

    @Override
    public List<GroupMembership> getAll() {
        return null;
    }

    @Override
    public void update(GroupMembership object) {

    }

    @Override
    public void delete(long id) {

    }

    public void setValues(GroupMembership groupMembership) {
        Profile profile = profileDAO.getByGroupMembershipId(groupMembership.getId());
        profileService.setValues(profile);

        Group group = groupDAO.getByGroupMembershipId(groupMembership.getId());

        groupMembership.setMemberProfile(profile);
        groupMembership.setGroup(group);
    }
}
