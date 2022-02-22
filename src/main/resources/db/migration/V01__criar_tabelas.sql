
create table certificado (
       id  bigserial not null,
        ativo boolean not null,
        emitido varchar(80) not null,
        ipo varchar(2) not null,
        pin int4 not null,
        serial varchar(280) not null,
        validade_fim date,
        validade_ini date,
        empresa_id int8,
        primary key (id)
    )
; 
    
    create table cidade (
       id  bigserial not null,
        ativo boolean not null,
        cep int4 not null,
        nome varchar(80) not null,
        estado_id int8,
        primary key (id)
    )
; 
    
    create table contador (
       id  bigserial not null,
        ativo boolean not null,
        cpf varchar(14) not null,
        crc varchar(15) not null,
        nome varchar(200) not null,
        rg int4 not null,
        escritorio_id int8,
        primary key (id)
    )
; 
    
    create table empresa (
       id  bigserial not null,
        ativo boolean not null,
        pessoa_juridica_id int8,
        primary key (id)
    )
; 
    
    create table escritorio (
       id  bigserial not null,
        ativo boolean not null,
        pessoa_juridica_id int8,
        primary key (id)
    )
; 
    
    create table estado (
       id  bigserial not null,
        ativo boolean not null,
        nome varchar(80) not null,
        uf varchar(2) not null,
        primary key (id)
    )
; 
    
    create table fornecedor (
       id  bigserial not null,
        ativo boolean not null,
        empresa_id int8,
        pessoa_juridica_id int8,
        primary key (id)
    )
; 
    
    create table notas (
       id  bigserial not null,
        ativo boolean not null,
        cnpj_emp int4 not null,
        cnpj_forn int4 not null,
        dt_emissao date,
        dt_saida date,
        finalidade_operacao int4 not null,
        numero int4 not null,
        serie int4 not null,
        tipo int4 not null,
        tipo_operacao int4 not null,
        valor_total float4 not null,
        xml varchar(280) not null,
        empresa_id int8,
        fornecedor_id int8,
        primary key (id)
    )
; 
    
    create table permissao (
       id  bigserial not null,
        ativo boolean not null,
        tipo_permissao varchar(180) not null,
        primary key (id)
    )
; 
    
    create table permissao_usuario (
       permissao_id int8 not null,
        usuario_id int8 not null
    )
; 
    
    create table pessoa_juridica (
       id  bigserial not null,
        ativo boolean not null,
        bairro varchar(100) not null,
        cnpj int4 not null,
        complemento varchar(100) not null,
        logradouro varchar(200) not null,
        nome_fan varchar(180) not null,
        numero int4 not null,
        razao_soc varchar(180) not null,
        cidade_id int8,
        primary key (id)
    )
; 
    
    create table socios (
       id  bigserial not null,
        ativo boolean not null,
        cpf int4 not null,
        dt_emissao date,
        nome varchar(180) not null,
        org_esp varchar(80) not null,
        rg int4 not null,
        empresa_id int8 not null,
        primary key (id)
    )
; 
    
    create table usuario (
       id  bigserial not null,
        dt_bloqueio date,
        ativo boolean not null,
        email varchar(180) not null,
        login varchar(180) not null,
        senha varchar(255) not null,
        pessoa_juridica_id int8,
        primary key (id)
    )
; 
    
    alter table usuario 
       drop constraint if exists UK_te8bt20syqgnhaes5vrmv0d31;
    
    alter table usuario 
       add constraint UK_te8bt20syqgnhaes5vrmv0d31 unique (email)
; 
    
    alter table certificado 
       add constraint FKj5be8a02rylpdqmy4rqn2d38m 
       foreign key (empresa_id) 
       references empresa
; 
    
    alter table cidade 
       add constraint FKkworrwk40xj58kevvh3evi500 
       foreign key (estado_id) 
       references estado
; 
    
    alter table contador 
       add constraint FKhpmkmb71emltsv3hpp48xfig 
       foreign key (escritorio_id) 
       references escritorio
; 
    
    alter table empresa 
       add constraint FK7g618gjd3q6xnyoamh9x7vohx 
       foreign key (pessoa_juridica_id) 
       references pessoa_juridica
; 
    
    alter table escritorio 
       add constraint FK6b6787n48qxo304k79wx65dp9 
       foreign key (pessoa_juridica_id) 
       references pessoa_juridica
; 
    
    alter table fornecedor 
       add constraint FK6meujwuxsioxaiv65lpplf3it 
       foreign key (empresa_id) 
       references empresa
; 
    
    alter table fornecedor 
       add constraint FKc5gv3pi36l38vqamugxwggh7t 
       foreign key (pessoa_juridica_id) 
       references pessoa_juridica
; 
    
    alter table notas 
       add constraint FKk802gs4kctot94a3k73bo217n 
       foreign key (empresa_id) 
       references empresa
; 
    
    alter table notas 
       add constraint FK68t8ew4tbeoupkmhim5vq7xxw 
       foreign key (fornecedor_id) 
       references fornecedor
; 
    
    alter table permissao_usuario 
       add constraint FK3b2s9hbeysqtbkwrwbus7cuku 
       foreign key (usuario_id) 
       references usuario
; 
    
    alter table permissao_usuario 
       add constraint FKggkpksqth0nps65cnfjk6vhok 
       foreign key (permissao_id) 
       references permissao
; 
    
    alter table pessoa_juridica 
       add constraint FKrtvlcogijwrifw573wun01296 
       foreign key (cidade_id) 
       references cidade
; 
    
    alter table socios 
       add constraint FK6e7hr21sl015m9vq2ki7o4ebe 
       foreign key (empresa_id) 
       references empresa
; 
    
    alter table usuario 
       add constraint FKfda09rqim73ssb8upg0h8nm92 
       foreign key (pessoa_juridica_id) 
       references pessoa_juridica;