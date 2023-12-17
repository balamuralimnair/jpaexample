create table manifest.parenttable (
key1_parent varchar(20) not null,
key2_parent numeric,
key3_parent varchar(5),
parent_desc varchar(100),
purge_flag boolean,
created_tmstp timestamp default current_timestamp,
updated_tmstp timestamp default current_timestamp,

primary key (key1_parent,key2_parent,key3_parent)
);

create table manifest.childtable (
key1_child numeric,
key1_parent varchar(20) not null,
key2_parent numeric,
key3_parent varchar(5),
child_desc varchar(100),
created_tmstp timestamp default current_timestamp,
updated_tmstp timestamp default current_timestamp,

foreign key (key1_parent,key2_parent,key3_parent) references manifest.parenttable (key1_parent,key2_parent,key3_parent),
primary key (key1_child,key1_parent,key2_parent,key3_parent)
);

create unique index parenttable_pk on manifest.parenttable (key1_parent,key2_parent,key3_parent);
create unique index childtable_pk on manifest.childtable (key1_child,key1_parent,key2_parent,key3_parent);

create index purgeparent_index on manifest.parenttable(date(created_tmstp),purge_flag);

create index purgeparentdate_index on manifest.parenttable(date(created_tmstp));

create index purgechild_index on manifest.childtable(date(created_tmstp));