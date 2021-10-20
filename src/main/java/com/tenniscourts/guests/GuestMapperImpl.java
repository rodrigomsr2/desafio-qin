package com.tenniscourts.guests;

import com.tenniscourts.guests.dto.CreateGuestDTO;
import com.tenniscourts.guests.dto.GuestOutputDTO;
import com.tenniscourts.guests.dto.UpdateGuestDTO;
import com.tenniscourts.usergroup.UserGroup;
import com.tenniscourts.usergroup.dto.CreateGuestUserGroupDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-30T04:19:10-0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 11.0.11 (Oracle Corporation)"
)
@Component
public class GuestMapperImpl implements GuestMapper {

    @Override
    public GuestOutputDTO map(Guest guest) {
        if ( guest == null ) {
            return null;
        }

        GuestOutputDTO guestOutputDTO = new GuestOutputDTO();

        guestOutputDTO.setId( guest.getId() );
        guestOutputDTO.setName( guest.getName() );

        return guestOutputDTO;
    }

    @Override
    public Guest map(CreateGuestDTO createGuestDTO) {
        if ( createGuestDTO == null ) {
            return null;
        }

        Guest guest = new Guest();

        guest.setName( createGuestDTO.getName() );
        guest.setUserGroup( createGuestUserGroupDTOToUserGroup( createGuestDTO.getUserGroup() ) );

        return guest;
    }

    @Override
    public Guest map(UpdateGuestDTO updateGuestDTO) {
        if ( updateGuestDTO == null ) {
            return null;
        }

        Guest guest = new Guest();

        guest.setName( updateGuestDTO.getName() );

        return guest;
    }

    protected UserGroup createGuestUserGroupDTOToUserGroup(CreateGuestUserGroupDTO createGuestUserGroupDTO) {
        if ( createGuestUserGroupDTO == null ) {
            return null;
        }

        UserGroup userGroup = new UserGroup();

        userGroup.setId( createGuestUserGroupDTO.getId() );

        return userGroup;
    }
}
