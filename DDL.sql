-- ------------------------------------------------------------------------
-- SCHEMA 
-- ------------------------------------------------------------------------
create schema if not exists itall_dev;
use itall_dev;
create user if not exists 'admin'@'localhost' identified by 'admin';
grant select, insert, delete, update on itall_dev.* to admin@'localhost';

-- ------------------------------------------------------------------------
-- PROCEDURE drop_index
-- ------------------------------------------------------------------------
delimiter //
drop procedure if exists drop_index //
create procedure drop_index(
	in tab VARCHAR(255),
    in idx VARCHAR(255))
begin
    declare tot int;
    declare qry varchar(1000);

    SET tot = ( select count(*) from INFORMATION_SCHEMA.STATISTICS A
                where A.index_name = idx
                  and A.table_name = tab
                  and A.table_schema = 'itall_dev'
    );

	if tot > 0 then
		set @qry = concat('drop index ', idx, ' ON ', tab);
        prepare comando FROM @qry;
        execute comando;
        deallocate prepare comando;
    end if;
end //
delimiter ;

-- ------------------------------------------------------------------------
-- USUARIO
-- ------------------------------------------------------------------------
call drop_index('usuario','usuario_nome_idx');
call drop_index('usuario','usuario_email_idx');
create table if not exists usuario (
      id                    bigint unsigned primary key auto_increment
    , nome                  varchar(80)     not null
    , email                 varchar(255)    not null
    , senha                 varchar(80)     not null
    , data_criacao    		datetime
    , constraint usuario_email_uk unique (email)
);

create index usuario_nome_idx ON usuario (nome);
create index usuario_email_idx ON usuario (email);

select * from usuario;
-- drop table usuario;
