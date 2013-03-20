
    create table k_event_property (
        EVENT_ID number(19,0) not null,
        PROPERTY_ID number(19,0) not null
    )

    create table k_pet_property (
        EVENT_ID number(19,0) not null,
        PROPERTY_ID number(19,0) not null
    )

    create table k_user_event (
        USER_ID number(19,0) not null,
        EVENT_ID number(19,0) not null
    )

    create table k_user_friend (
        USER_ID number(19,0) not null,
        FRIEND_ID number(19,0) not null
    )

    create table k_wife_pet (
        WIFE_ID number(19,0) not null,
        PET_ID number(19,0) not null
    )

    create table m_audit (
        id number(19,0) not null,
        content varchar2(255 char),
        primary key (id)
    )

    create table m_event (
        id number(19,0) not null,
        name varchar2(255 char),
        primary key (id)
    )

    create table m_friend (
        id number(19,0) not null,
        name varchar2(255 char),
        UserCard_id number(19,0),
        primary key (id)
    )

    create table m_man (
        id number(19,0) not null,
        CREATEDDATE date,
        name varchar2(255 char),
        UserCard_id number(19,0),
        Wife_id number(19,0),
        primary key (id)
    )

    create table m_pet (
        id number(19,0) not null,
        name varchar2(255 char),
        primary key (id)
    )

    create table m_property (
        id number(19,0) not null,
        key varchar2(255 char),
        value varchar2(255 char),
        primary key (id)
    )

    create table m_userCard (
        id number(19,0) not null,
        cardNumber varchar2(255 char),
        primary key (id)
    )

    create table m_wife (
        id number(19,0) not null,
        name varchar2(255 char),
        UserCard_id number(19,0),
        primary key (id)
    )

    alter table k_event_property 
        add constraint UK2C1F142E50EB47C5 unique (PROPERTY_ID)

    alter table k_event_property 
        add constraint FK2C1F142EFE4B93FD 
        foreign key (PROPERTY_ID) 
        references m_property

    alter table k_event_property 
        add constraint FK2C1F142E59C617F7 
        foreign key (EVENT_ID) 
        references m_event

    alter table k_pet_property 
        add constraint UK81BFE04950EB47C5 unique (PROPERTY_ID)

    alter table k_pet_property 
        add constraint FK81BFE049FE4B93FD 
        foreign key (PROPERTY_ID) 
        references m_property

    alter table k_pet_property 
        add constraint FK81BFE0499BA51BC 
        foreign key (EVENT_ID) 
        references m_pet

    alter table k_user_event 
        add constraint UKEAACF17A1093C0E0 unique (EVENT_ID)

    alter table k_user_event 
        add constraint FKEAACF17A59C617F7 
        foreign key (EVENT_ID) 
        references m_event

    alter table k_user_event 
        add constraint FKEAACF17AF0617326 
        foreign key (USER_ID) 
        references m_man

    alter table k_user_friend 
        add constraint UK6C6F67BEBA8EFA5C unique (FRIEND_ID)

    alter table k_user_friend 
        add constraint FK6C6F67BE9925B01D 
        foreign key (FRIEND_ID) 
        references m_friend

    alter table k_user_friend 
        add constraint FK6C6F67BEF0617326 
        foreign key (USER_ID) 
        references m_man

    alter table k_wife_pet 
        add constraint UKCFFC1845C4E4383B unique (PET_ID)

    alter table k_wife_pet 
        add constraint FKCFFC1845BE0AC917 
        foreign key (PET_ID) 
        references m_pet

    alter table k_wife_pet 
        add constraint FKCFFC18457BADE3FD 
        foreign key (WIFE_ID) 
        references m_wife

    alter table m_friend 
        add constraint FK90D622B08476125D 
        foreign key (UserCard_id) 
        references m_userCard

    alter table m_man 
        add constraint FK62CD6888476125D 
        foreign key (UserCard_id) 
        references m_userCard

    alter table m_man 
        add constraint FK62CD6887BADE3FD 
        foreign key (Wife_id) 
        references m_wife

    alter table m_wife 
        add constraint FKBF72A3A38476125D 
        foreign key (UserCard_id) 
        references m_userCard

    create sequence hibernate_sequence
