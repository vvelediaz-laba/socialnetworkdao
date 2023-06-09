package com.solvd.socialnetworkdao.dao;

import com.solvd.socialnetworkdao.GroupMembership;

import java.util.List;

public interface IGroupMembershipDAO extends IDAO<GroupMembership>{
    List<GroupMembership> getGroupMembershipsByProfileId(long id);
    List<GroupMembership> getMembershipsByGroupId(long id);
}
