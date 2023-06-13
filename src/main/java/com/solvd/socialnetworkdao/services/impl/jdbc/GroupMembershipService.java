package com.solvd.socialnetworkdao.services.impl.jdbc;

import com.solvd.socialnetworkdao.Friendship;
import com.solvd.socialnetworkdao.Group;
import com.solvd.socialnetworkdao.GroupMembership;
import com.solvd.socialnetworkdao.Profile;
import com.solvd.socialnetworkdao.dao.IGroupDAO;
import com.solvd.socialnetworkdao.dao.IGroupMembershipDAO;
import com.solvd.socialnetworkdao.dao.IProfileDAO;
import com.solvd.socialnetworkdao.dao.impl.jdbc.GroupDAO;
import com.solvd.socialnetworkdao.dao.impl.jdbc.GroupMembershipDAO;
import com.solvd.socialnetworkdao.dao.impl.jdbc.ProfileDAO;
import com.solvd.socialnetworkdao.services.IGroupMembershipService;
import com.solvd.socialnetworkdao.services.IGroupService;
import com.solvd.socialnetworkdao.services.IProfileService;

import java.util.List;

public class GroupMembershipService implements IGroupMembershipService {
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
        List<GroupMembership> memberships = groupMembershipDAO.getAll();
        for(GroupMembership membership : memberships){
            setValues(membership);
        }
        return memberships;
    }

    @Override
    public void update(GroupMembership membership) {
        groupMembershipDAO.update(membership);
    }

    @Override
    public void delete(long id) {
        groupMembershipDAO.delete(id);
    }

    private void setValues(GroupMembership groupMembership) {
        Profile profile = profileDAO.getByGroupMembershipId(groupMembership.getId());
        profile = profileService.getById(profile.getId());

        Group group = groupDAO.getByGroupMembershipId(groupMembership.getId());

        groupMembership.setMemberProfile(profile);
        groupMembership.setGroup(group);
    }
}
