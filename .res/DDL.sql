-- ------------------------------------------------------------------------
-- SCHEMA 
-- ------------------------------------------------------------------------
create schema if not exists itall_dev;
use itall_dev;
create user if not exists 'admin'@'localhost' identified by 'admin';
grant all on itall_dev.* to admin@'localhost';


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
-- USUARIOS
-- ------------------------------------------------------------------------
call drop_index('usuarios','usuarios_nome_idx');
call drop_index('usuarios','usuarios_email_idx');
create table if not exists usuarios (
      id                    bigint unsigned primary key auto_increment
    , nome                  varchar(80)     not null
    , email                 varchar(255)    not null
    , senha                 varchar(32)     not null
    , data_criacao    		timestamp		not null
    , constraint usuarios_email_uk unique (email)
);
create index usuarios_nome_idx ON usuarios (nome);
create index usuarios_email_idx ON usuarios (email);

-- ------------------------------------------------------------------------
-- CLIENTES
-- ------------------------------------------------------------------------
call drop_index('clientes','clientes_nome_idx');
call drop_index('clientes','clientes_sobrenome_idx');
call drop_index('clientes','clientes_email_idx');
create table if not exists clientes (
      id                    bigint unsigned primary key auto_increment
    , nome                  varchar(80)     not null
    , sobrenome             varchar(80)     not null
    , sexo                  varchar(9)      not null
    , data_nascimento	    date	        not null
    , nacionalidade			varchar(20)   
    , email                 varchar(255)    
    , endereco				varchar(255)
    , cidade				varchar(100)    
    , estado				varchar(2)
	, telefone				varchar(13)
);
create index clientes_nome_idx ON clientes (nome);
create index clientes_sobrenome_idx ON clientes (sobrenome);
create index clientes_email_idx ON clientes (email);

call drop_index('produtos','produtos_nome_idx');
call drop_index('produtos','produtos_descricao_idx');
-- ------------------------------------------------------------------------
-- PRODUTOS
-- ------------------------------------------------------------------------
create table if not exists produtos (
      id                    bigint unsigned primary key auto_increment
    , nome                  varchar(80)     not null
    , descricao             varchar(80)     not null
    , valor_venda           decimal(12,2)   
    , quantidade			decimal(12,4)   
);
create index produtos_nome_idx ON produtos (nome);
create index produtos_descricao_idx ON produtos (descricao);

-- ------------------------------------------------------------------------
-- PEDIDOS
-- ------------------------------------------------------------------------
call drop_index('pedidos','pedidos_nome_idx');
call drop_index('pedidos','pedidos_descricao_idx');
create table if not exists pedidos (
      id                    bigint unsigned primary key auto_increment
    , nome                  varchar(80)     not null
    , descricao             varchar(80)     not null
    , valor_venda           decimal(12,2)   
);
create index pedidos_nome_idx ON pedidos (nome);
create index pedidos_descricao_idx ON pedidos (descricao);

commit;