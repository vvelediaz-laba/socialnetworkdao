package com.solvd.socialnetworkdao.dao;

import com.solvd.socialnetworkdao.GroupMembership;

import java.util.List;

public interface IGroupMembershipDAO extends IDAO<GroupMembership>{
    void insert(GroupMembership groupMembership, long groupMemberProfileId, long groupId);
    void update(GroupMembership groupMembership, long groupMemberProfileId, long groupId);
    List<GroupMembership> getGroupMembershipsByProfileId(long id);
    List<GroupMembership> getMembershipsByGroupId(long id);
}
