package com.solvd.socialnetworkdao.interfaces;

import com.solvd.socialnetworkdao.GroupMembership;

import java.util.List;

public interface IGroupMembershipDAO extends IDAO<GroupMembership>{
    List<GroupMembership> getGroupMembershipsByProfileId(long id);
}
